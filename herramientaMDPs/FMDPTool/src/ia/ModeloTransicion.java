package ia;

import java.util.Arrays;
import utileria.*;
import java.util.Vector;

/**
 * <p>Title: Clase Modelo de Transicion</p>
 * <p>Description: Aqui hay metodos para obtener una matriz de transicion</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Tec de Monterrey</p>
 * @author Alberto Reyes B.
 * @version 1.0
 */

public class ModeloTransicion {

  public Vector distribuciones;
  public RBD rbd;


  // constructor del modelo de transicion
  public ModeloTransicion(RBD rbd,
                          Vector distribuciones) {

    this.rbd=rbd;
    this.distribuciones = distribuciones;
  }


  public static double[][] generaMatrizTransAccionNula(int noEstados){
   double[][] tabla=new double[noEstados][noEstados];

   for(int i=0; i<noEstados; i++)
     for(int j=0; j<noEstados; j++)
     if(i==j) tabla[i][j]=1.0;

     return tabla;
  }


  // devuelve indice del estado en el vector de distibuciones
  public int getStateIndex(int[] estado){

  DistProb dp;
  int dist=-1;
  for(int i=0; i<distribuciones.size(); i++){
    dp=(DistProb) distribuciones.elementAt(i);
    if (Listas.esIgual(estado, dp.estado)){
      dist = i;
      break;
    }
  }
  return dist;
  }

  double getProbability(String[] estado_i, String[] estado_j){

    int[] edoI=new int[estado_i.length];
    int[] edoJ=new int[estado_j.length];

    for(int i=0; i<rbd.valoresEvidencia.length;i++){
      edoI[i] = Listas.indice(rbd.valoresEvidencia[i], estado_i[i]);
     // System.out.println("var evidenc no. "+i+"buscando "+estado_i[i]+" en");
     // Matrices.imprimeLista(valoresEvidencia[i]);

    }

    //  Listas.imprimeLista(edoI);

    for(int i=0; i<rbd.valoresInteres.length;i++){
      edoJ[i] = Listas.indice(rbd.valoresInteres[i], estado_j[i]);
    }

    //  Listas.imprimeLista(edoJ);

    return getProbability(edoI, edoJ);
  }

  // obtiene probabilidad del estado j dado el estado i formato enteros
  double getProbability(int[] estado_i, int[] estado_j){
    double producto=1;

    // busca indice del estado i
    int i=getStateIndex(estado_i);
    double[][] probs=((DistProb)distribuciones.elementAt(i)).probs;

    // long estado j debe ser mismo no de filas de probs

    if(((DistProb)distribuciones.elementAt(i)).estadoValido())

    for(int j=0; j<probs.length; j++){
      producto = producto * probs[j][estado_j[j]];
      //System.out.println(probs[j][estado_j[j]]);
    }

    else producto=0; // caso de un estado no valido

    return producto;
  }

  int[] indicesVarsEntrada;
  int[][] caractEntrada;
  int[][] estados;
  int[][] caract;
  int[] indicesCaract;
  double[][] matriz;

  public ModeloTransicion(int[] indicesVarsEntrada,
                          int[] indicesVarsSalida,
                          int[][] tablaGralEstados,
                          int[][] tablaEstadosT0,
                          int[][] tablaEstadosT1,
                          double[][] ModeloRBD) {

  this.indicesVarsEntrada=indicesVarsEntrada;
  caractEntrada=tablaEstadosT0;
  estados=tablaGralEstados;
  caract=tablaEstadosT1;
  indicesCaract=indicesVarsSalida;
  matriz=ModeloRBD;

  }
/*
  public double[] tranxMatrix(int[] estado){

  int x=indiceCaractEntrada(estado, indicesVarsEntrada, caractEntrada);
  return generaModeloTranx(estados,caract, indicesCaract, matriz[x]);
  }

  private double[] generaModeloTranx(int[][] estados,
                                    int[][] caract,
                                    int[] indicesCaract,
                                    double[] probCaract){

    double[] probsTot=new double[estados.length];
    int i;
    for(int s=0; s<estados.length; s++){
      i=indiceCaractEntrada(estados[s], indicesCaract,caract);
      if(i>=0) probsTot[s]=probCaract[i];
    }
    return probsTot;
  }

  private int indiceCaractEntrada(int[] estado, int[] indicesVarsEntrada,
                              int[][] caractEntrada){
    int[] key=Utileria.dameElementos(estado, indicesVarsEntrada);
    return Utileria.indiceSublista(caractEntrada,key);
  }
*/

  public double[] tranxMatrix(int[] estado){

  int x=indiceCaract(estado,indicesVarsEntrada,caractEntrada);
  return generaModeloTranx(matriz[x]);
  }

  private double[] generaModeloTranx(double[] probCaract){

    double[] probsTot=new double[estados.length];
    int i;
    for(int s=0; s<estados.length; s++){
      i=indiceCaract(estados[s],indicesCaract,caract);
      if(i>=0) probsTot[s]=probCaract[i];
    }
    return probsTot;
  }

  private int indiceCaract(int[] estado,int[] indicesVars,int[][] caracter){
    int[] key=Listas.dameElementos(estado, indicesVars);
    return Matrices.indiceSublista(caracter,key);
  }


/*
 public double[] generaMatrizTransAccionNula(int estado){
  double[] tabla=new double[estados.length];

  for(int i=0; i<estados.length; i++)
  //  for(int j=0; j<estados.length; j++)
    if(i==estado) tabla[i]=1.0;

    return tabla;
 }

 public double[][] generaMatrizTransAccionNula(){
  double[][] tabla=new double[estados.length][estados.length];

  for(int i=0; i<estados.length; i++)
    for(int j=0; j<estados.length; j++)
    if(i==j) tabla[i][j]=1.0;

    return tabla;
 }

  public double[] generaFuncionRecompensa(//int[][] estados,
                                          int[][] caract,
                                          int[] indicesCaract,
                                          int[] edosOptimos){
    double[] r=new double[estados.length];
    int i;
    for(int s=0; s<estados.length; s++){
      i=indiceCaract(estados[s], indicesCaract,caract);
      if(i>=0&&Arrays.binarySearch(edosOptimos,i)>=0) r[s]=1.0;
      else r[s]=-0.04;
    }
    return  r;
  }
*/
}