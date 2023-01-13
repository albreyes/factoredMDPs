package ia;


import java.io.IOException;
import elvira.parser.ParseException;
import java.util.Vector;
import utileria.Matrices;
import utileria.FileOutput01 ;
import aprendizaje.*;
import utileria.Listas;
import utileria.FileOutput;

public class RBD {


// lista de atributos de clase
  public PropagaRB red;
  public String[] varsT;
  public String[][] valoresEvidencia;
  public String[] varsTmas1;
  public String[][] valoresInteres;
  public int[] valoresT;
  public int[][] estadosEv;
  public int[] valoresTmas1;
  public int[][] estadosInt;


public RBD(String netFilename) throws  ParseException,IOException{
  red=new PropagaRB(netFilename);
  // obtiene variables en t
  varsT=Listas.stringVectorToStringArray(dameNodosTiempoT());
  // obtiene tabla de valores en tiempo t
  valoresEvidencia=new String[varsT.length][];

  for(int i=0; i<varsT.length; i++)
    valoresEvidencia[i]=red.dameEstados(varsT[i]);

  //  System.out.println("vars tiempo t ");
  //Listas.imprimeLista(varsT);
  // obtiene variables en t+1
  varsTmas1=Listas.stringVectorToStringArray(dameNodosTiempoTmas1());

  valoresInteres=new String[varsTmas1.length][];

  for(int i=0; i<varsTmas1.length; i++)
    valoresInteres[i]=red.dameEstados(varsTmas1[i]);

  //  System.out.println("vars tiempo t+1 ");
  //Listas.imprimeLista(varsTmas1);
  // determina los n estados de evidencia
  valoresT=new int[varsT.length];

  for(int i=0; i<varsT.length;i++)
    valoresT[i]=red.getStatesNum(varsT[i]);

 // estadosEv=Estados.generaEstados(valoresT);

  // determina los m estados de interes
  valoresTmas1=new int[varsTmas1.length];

  for(int i=0; i<varsTmas1.length;i++)
    valoresTmas1[i]=red.getStatesNum(varsTmas1[i]);

//  estadosInt=Estados.generaEstados(valoresTmas1);
 // generaEstadosEvidencia();
 // generaEstadosInteres();
}

public void generaEstadosEvidencia(){

  estadosEv=Estados.generaEstados(valoresT);
}

public void generaEstadosInteres(){
  estadosInt=Estados.generaEstados(valoresTmas1);
}

// devuelve un vector con los nodos: tiempo t: t=0, tiempo t+1: t=1
public String[] dameNodos(int t) {
  Vector nodo = red.dameNombreDeNodos();
  String var, sub;
  Vector v = new Vector();
  int min, max;
  for (int i = 0; i < nodo.size(); i++) {

    var = nodo.elementAt(i).toString();

    max = var.length();
    min = var.length() - 5;

    if (t == 0) {

      if (min > 0) {
        sub = var.substring(min, max);

        if (!sub.equals("prime"))
          v.addElement(var);
      }
      else
        v.addElement(var);
    }
    else if (t == 1) {

      if (min > 0) {
        sub = var.substring(min, max);

        if (sub.equals("prime"))
          v.addElement(var);
      }
    }
  }
  return Listas.stringVectorToStringArray(v);
}


// devuelve un vector con los nodos en el tiempo t que tengan padres o hijos
public Vector dameNodosTiempoT() {
  Vector nodo = red.dameNombreDeNodos();
  String var, sub;
  Vector v = new Vector();
  int min, max;
  for (int i = 0; i < nodo.size(); i++) {

    var = nodo.elementAt(i).toString();
    int n=red.damePadres(var).length+red.dameHijos(var).length;
    // verifica que tenga padres o hijos
    if(n>0){


    max = var.length();
    min = var.length() - 5;

    if (min > 0) {
      sub = var.substring(min, max);

      if (!sub.equals("prime"))
        v.addElement(var);
    } else v.addElement(var);

    }

  }
  return v;
}

// devuelve un vector con los nodos en el tiempo t+1 que tengan padres o hijos
public Vector dameNodosTiempoTmas1() {
  Vector nodo = red.dameNombreDeNodos();
  String var, sub;
  Vector v = new Vector();
  int min, max;
  for (int i = 0; i < nodo.size(); i++) {
    var = nodo.elementAt(i).toString();

    int n=red.damePadres(var).length+red.dameHijos(var).length;

 //   System.out.println("la var "+var+" tiene "+red.damePadres(var).length+" padres");
    // verifica que tenga padres o hijos

    if(n>0){

    max = var.length();
    min = var.length() - 5;

    if (min > 0) {
      sub = var.substring(min, max);

      if (sub.equals("prime"))
        v.addElement(var);
    }}
  }
  return v;
}


// funcion que compila una red bayesiana
// recibe el archivo donde leer la red y devuelve un vector de distribuciones por estado
// el indice en el vector es el numero de estado.

public Vector compilaRedBayesiana() throws  ParseException,IOException{

  Vector v=new Vector();

  double[][] distProb;

  for(int se=0;se<estadosEv.length;se++) {

     // propaga evidencia
    distProb= red.propagaMultiple(varsTmas1,varsT,estadosEv[se]);
    v.addElement(new DistProb(estadosEv[se],distProb));

  }

  // devuelve distribucion de probs
  return v;
}


public int estadosEvidencia[][];
public int estadosInteres[][];
private PropagaRB[] redes;
private String[] varsInteres;
private int[] noVals;
private int[][] indicesVarsEvidencia;


// la RBD se construye con las variables de evidencia, los archivos de elvira,
// los indices de cada variable de evidencia, los estados de evidencia(t=0)
// y los estados de interes(t=1)

  public RBD(String[] vi, int[][] e0, int[][] e1, String[] files, int[][] ive, int[] noValxVarSalida)
  throws ParseException, IOException{

    redes=new PropagaRB[files.length];
    for(int i=0; i<files.length; i++)
      redes[i]=new PropagaRB(files[i]);
      estadosEvidencia=e0;
      estadosInteres=e1;
      varsInteres=vi;
      indicesVarsEvidencia=ive;
      noVals=noValxVarSalida;
  }


  public static String[] aprendeRBD(Vector v, String path, int SNumMaxParents) {

    String[] archivo=new String[v.size()];
    int[] noCasos=new int[v.size()];

    // v contiene datos para cada accion

    for(int i=0; i<v.size();i++){
      String[][] ejemplosAumentados = (String[][]) v.elementAt(i);

  //    System.out.println("formato de atributos en viejo metodo");
  //  Matrices.imprimeTabla(Matrices.conjunto(ejemplosAumentados));

      BaseNodos bn = new BaseNodos("base_nodos", ejemplosAumentados,
                                   Matrices.conjunto(ejemplosAumentados));
      FileOutput01 fo = new FileOutput01(path + "/a" + i + "Elv.dbc");

      fo.write(bn.getDatosCompletos());
      fo.close();
      archivo[i] = path + "/a" + i + "Elv.dbc";
      noCasos[i] = ejemplosAumentados.length - 1;
    }

   String[] fileOutput=new String[v.size()];

   for(int i=0; i<v.size();i++){
   String ArchivoELV=path+"/a"+i+"Elv.elv";
   fileOutput[i]=ArchivoELV;
   int SNumCasos=noCasos[i];
     System.out.println(SNumCasos);

   AprendeELV aprende=new AprendeELV();
     aprende.generaRB(archivo[i],
                      "" + SNumMaxParents,
                      ArchivoELV,
                     "" + SNumCasos);
   }
   return fileOutput;
  }

  // esta version permite aprender modelos ordenados de acuerdo a un archivo de
  // atributos. esto permite una red

  public static String[] aprendeRBD(Vector v, String path, String attributePath, int SNumMaxParents) {

    String[] archivo=new String[v.size()];
    int[] noCasos=new int[v.size()];


    Variable[] var= Variable.getVars(attributePath);
    var=Variable.getVarsRBD(var);
    String[][] atbs=new String[var.length][];

    for(int i=0; i<var.length; i++){
      String[] aux={var[i].getName()};
      atbs[i]= Listas.concatena(aux,var[i].values);
    }


  // esto se debe hacer de otra forma
  //  String[][] atbs=tablas.fileToMatrix(attributePath,"\t:,");


 //   atbs=Matrices.eliminaFila(atbs,atbs.length-1);
    // v contiene datos para cada accion

//System.out.println("formato de atributos en nuevo metodo");
//    Matrices.imprimeTabla(atbs);

    for(int i=0; i<v.size();i++){
      String[][] ejemplosAumentados = (String[][]) v.elementAt(i);

      // aqui hay que contruir con attributePath
      BaseNodos bn = new BaseNodos("base_nodos", ejemplosAumentados,
                                   atbs);
      FileOutput01 fo = new FileOutput01(path + "/a" + i + "Elv.dbc");

      fo.write(bn.getDatosCompletos());
      fo.close();
      archivo[i] = path + "/a" + i + "Elv.dbc";
      noCasos[i] = ejemplosAumentados.length - 1;
    }

   String[] fileOutput=new String[v.size()];

   for(int i=0; i<v.size();i++){
   String ArchivoELV=path+"/a"+i+"Elv.elv";
   fileOutput[i]=ArchivoELV;
   int SNumCasos=noCasos[i];
     System.out.println(SNumCasos);

   AprendeELV aprende=new AprendeELV();
     aprende.generaRB(archivo[i],
                      "" + SNumMaxParents,
                      ArchivoELV,
                     "" + SNumCasos);
   }
   return fileOutput;
  }


// genera la matriz de transicion de un estado a todos los demas

  public double[] probTransEstadost1(double[][] probt1){
   double[] probs=new double[estadosInteres.length];
   int[] estado=new int[probt1.length];
   double prod;

   for(int s=0; s<estadosInteres.length; s++){
    estado=estadosInteres[s];
    prod=1.0;
    for(int var=0; var<probt1.length; var++)
    prod=prod*probt1[var][estado[var]];

    probs[s]= prod;
   // System.out.println(s+": "+probs[s]);
   }
    return probs;
 }


 // genera la lista de valores de evidencia de una red

 private  int[] combina(int[] vals, int[] indices){

  int[] resultado= new int[indices.length];
  for(int i=0; i<indices.length; i++){
    resultado[i]=vals[indices[i]];
    //System.out.println(resultado[i]);
    }
    return resultado;
 }

// este es el metodo estrella. devuelve la matriz de transicion de todos
// los estados de entrada a los estados de salida

 public double[][] matrizTransTotal() throws IOException{

   double[][] matrizTrans=new double[estadosEvidencia.length][estadosInteres.length];
   double[][] prob=new double[varsInteres.length][];

   for(int i=0; i<varsInteres.length; i++)
    prob[i]=new double[noVals[i]];


    for(int s=0;s<estadosEvidencia.length;s++){
      for(int v=0;v<varsInteres.length;v++)
        prob[v]=redes[v].propagaPorNombreVarDesc(varsInteres[v],
                                                  combina(estadosEvidencia[s],
                                                  indicesVarsEvidencia[v]));

        matrizTrans[s]=probTransEstadost1(prob);
    }
         return matrizTrans;
 }


 // imprime tablas de probabilidades condicionales en variables del tiempo t+1
 public  void cpts() {

     Vector nodo = dameNodosTiempoTmas1();
     String var, sub;

     int min, max;
     for (int i = 0; i < nodo.size(); i++) {

       var = nodo.elementAt(i).toString();

       String[] padres = red.damePadres(var);
       String[] padresInv = Listas.reverse(padres);

       if(padres.length!=0){

       System.out.println("nodo: " + var);
       System.out.print("valores: ");
       Listas.imprimeLista(ValorDiscreto.ordenaValores( red.dameEstados(var)));

       for (int x = 0; x < padres.length; x++) {

         System.out.print("padre: "+padresInv[x]);
         System.out.print(" valores: ");
         Listas.imprimeLista(ValorDiscreto.ordenaValores( red.dameEstados(padresInv[x])));
       }
       Matrices.imprimeTabla(red.dameProbCondicionalDeNodoOrdenada(nodo.
           elementAt(i).toString()));

     }}
 }


 // imprime en un archivo las tablas de probabilidades condicionales en
 // variables del tiempo t+1

 public void cpts(FileOutput fo) {

     Vector nodo = dameNodosTiempoTmas1();
     String var, sub;

     int min, max;
     for (int i = 0; i < nodo.size(); i++) {

       var = nodo.elementAt(i).toString();

       String[] padres = red.damePadres(var);
       String[] padresInv = Listas.reverse(padres);

       if(padres.length!=0){

       System.out.print("nodo: " + var);
       fo.write("nodo: " + var);

       System.out.print(" valores: ");
       fo.write(" valores: ");
       String[] valores=ValorDiscreto.ordenaValores( red.dameEstados(var));
       Listas.imprimeLista(valores);
       Listas.escribeLista(valores,fo);

       for (int x = 0; x < padres.length; x++) {

         System.out.print("padre: "+padresInv[x]);
         fo.write("padre: "+padresInv[x]);
         System.out.print(" valores: ");
         fo.write(" valores: ");
         String valoresP[]=ValorDiscreto.ordenaValores( red.dameEstados(padresInv[x]));
         Listas.imprimeLista(valoresP);
         Listas.escribeLista(valoresP,fo);
       }
    //   double[][] probs=red.dameProbCondicionalDeNodoOrdenada(nodo.
      //     elementAt(i).toString());
       // la pongo sin ordenar asumiendo que las CPTs estan ordenadas
      double[][] probs=red.dameProbCondicionalDeNodo(nodo.
           elementAt(i).toString());
       probs=Matrices.roundMatrix( probs, 3);
       Matrices.imprimeTabla(probs);
       Matrices.escribeTabla(probs,fo);

     }}
 }

 public static void main(String[] args) throws IOException, ParseException{

   RBD rbd=new RBD("C:/Documents and Settings/xperto/Escritorio/powerPlant/a4Elv.elv");
   Listas.imprimeLista ( Matrices.stringVectorToStringArray(rbd.dameNodosTiempoTmas1()));
   Listas.imprimeLista(rbd.red.damePadres("fms_prime"));

 }
}

