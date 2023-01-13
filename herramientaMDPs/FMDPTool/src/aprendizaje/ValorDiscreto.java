package aprendizaje;

import utileria.Listas;
import java.util.Arrays;
import java.util.ArrayList;
import utileria.tablas;
import utileria.Matrices;
import java.io.File;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class ValorDiscreto {
  String atributo;
  double max,min;
  int noPartes;


  public ValorDiscreto(){}

  public ValorDiscreto(String a, double min, double max, int partes) {
    atributo=a;
    this.max=max;
    this.min=min;
    noPartes=partes;
  }

  public ValorDiscreto(double min, double max, int partes) {
    atributo="";
    this.max=max;
    this.min=min;
    noPartes=partes;
  }

  public void setValoresDiscretos(double min, double max, int partes){
        atributo="";
        this.max=max;
        this.min=min;
        noPartes=partes;

  }

  // funcion de discretizacion en base a informacion de archivos
  // recibe archivos de ejemplos y atributos continuos, atributos
  // discretos y devuelve nombre archivo con ejemplos discretizados

  public static String discretAuto(String contExampleFile, String contAttribFile, String discAttribFile){
    String[][] tabla = tablas.fileToMatrix(
        contExampleFile,
        "\t");
    String[][] atbCont = tablas.fileToMatrix(
        contAttribFile,":,\t ");
    String[][] atb = tablas.fileToMatrix(
        discAttribFile,":,\t ");

        for(int i=0; i<atbCont.length-1; i++)
          if(atbCont[i][1].equals("real")&&!atb[i][1].equals("real")){
            String atName=atbCont[i][0];
            int limInf=Integer.parseInt(atbCont[i][2]);
            int limSup=Integer.parseInt(atbCont[i][3]);
            int numPartes=atb[i].length-1;
          tabla=ValorDiscreto.discretizVar(tabla,atName,
                                           limInf,
                                           limSup,
                                           atb[i].length-1);
        }

      File file=new File(contExampleFile);

      String path=file.getParent()+"/";

      String nombre=file.getName();
      nombre=nombre.substring(0,nombre.length()-4)+"_DIS.txt";
      String discExampleFile=path+nombre;

       tablas.matrixToFile(discExampleFile,tabla);
       return discExampleFile;
  }



  public static void instanciaTablas(String contAttribFile,
                                     String discAttribFile) {
    String[][] atbCont = tablas.fileToMatrix(
        contAttribFile, ":,\t ");
    String[][] atb = tablas.fileToMatrix(
        discAttribFile, ":,\t ");
  }

  public static String[] discretAutoOnLine(double[] estadoCont, String[][] atbCont, String[][] atb){

    String[] estadoDis=new String[estadoCont.length];

        for(int i=0; i<estadoDis.length; i++){
        //  if(atbCont[i][1].equals("real")){ // asumimos que todos son reales
         ValorDiscreto vd=new ValorDiscreto(Integer.parseInt(atbCont[i][2]),Integer.parseInt(atbCont[i][3]),atb[i].length-1);
          estadoDis[i]=vd.getValorDiscreto(estadoCont[i]); }
      //  }
       return estadoDis;
  }


  public static int[] discretAutoOnLineInt(double[] estadoCont,
                                              String[][] atbCont,
                                              String[][] atb) {

    ArrayList al=new ArrayList();


    for (int i = 0; i < estadoCont.length; i++) {
      if (!atb[i][1].equals("real")) { // asumimos que todos son reales
        ValorDiscreto vd = new ValorDiscreto(Integer.parseInt(atbCont[i][2]),
                                             Integer.parseInt(atbCont[i][3]),
                                             atb[i].length - 1);
       al.add(new Integer(vd.getValorDiscretoInt(estadoCont[i])));
      }
    }
      int[] estadoDis=Listas.toIntArray(al);
    return estadoDis;
  }

  public String getValorDiscreto(double valorContinuo){

    String s = "none";
    double dif = (max - min) / (double) noPartes;
    double val = min;
    for (int i = 0; i < noPartes; i++,val+=dif) {
      if (i < noPartes - 1) {
        if (valorContinuo >= val && valorContinuo <= val + dif)
          s = "s" + i;
      }
      else{
        if (valorContinuo >= val && valorContinuo <= max)
          s = "s" + i;
      }
    }
    return s;
  }
  // igual que el anterior pero regresa la primera S mayuscula
  public String getValorDiscreto1(double valorContinuo){

    String s = "none";
    if(valorContinuo<min) s="S0";
    else if(valorContinuo>max) s="S"+(noPartes-1);
    
    double dif = (max - min) / (double) noPartes;
    double val = min;
    for (int i = 0; i < noPartes; i++,val+=dif) {
      if (i < noPartes - 1) {
        if (valorContinuo >= val && valorContinuo <= val + dif)
          s = "S" + i;
      }
      else{
        if (valorContinuo >= val && valorContinuo <= max)
          s = "S" + i;
      }
    }
    return s;
  }

  // igual que el anterior pero no regresa la primera S mayuscula devuelve -1 si no existe
  public int getValorDiscretoInt(double valorContinuo){

    int s = -1;

 if(valorContinuo < min )
        valorContinuo = min;
    else if(valorContinuo > max)
        valorContinuo = max;

    double dif = (max - min) / (double) noPartes;
    double val = min;
    for (int i = 0; i < noPartes; i++,val+=dif) {
      if (i < noPartes - 1) {
        if (valorContinuo >= val && valorContinuo <= val + dif)
          s =  i;
      }
      else{
        if (valorContinuo >= val && valorContinuo <= max)
          s =  i;
      }
    }
    return s;
  }

  // ordena valores cualitativos (pos, neg, cero) o discretos (s0,s1,s2..) de atributos
  public static String[] ordenaValores(String[] valores){
    String[] ordenados=new String[valores.length];

    for(int i=0; i<valores.length;i++)
      ordenados[i]=valores[i];

    int[] aux=new int[valores.length];
    String[] formato1={"cero","pos","neg"};
    int format=0;

    if(Listas.miembro(formato1[0],valores)||
       Listas.miembro(formato1[1],valores)||
       Listas.miembro(formato1[2],valores))
      format=1;
    else if(valores[0].substring(0,1).equals("S"))
      format=2;

      // caso donde vengan cualitativos los valores
    if(format==1){
      // transforma a numeros
      for(int i=0; i<valores.length;i++){
        if(valores[i].equals("cero")) aux[i]=0;
          else if(valores[i].equals("pos")) aux[i]=1;
            else if(valores[i].equals("neg")) aux[i]=2;
      }
      Arrays.sort(aux);
      // transforma a datos
      for(int i=0; i<valores.length;i++){
        if(aux[i]==0) ordenados[i]="cero";
          else if(aux[i]==1) ordenados[i]="pos";
            else if(aux[i]==2) ordenados[i]="neg";
      }
    }

    else if(format==2){
      // transforma a numeros
      for(int i=0; i<valores.length;i++){
        aux[i]=Integer.parseInt(valores[i].substring(1));
      }
      Arrays.sort(aux);
      for(int i=0;i<valores.length;i++)
        ordenados[i]="S"+aux[i];
    }

      return ordenados;

  }

  public String[] getValoresDiscretos(){

    String a[]=new String[noPartes];
    for(int i=0; i<noPartes; i++)
      a[i]="s"+i;
    return a;
  }

  public static String[][] discretizVar(String[][] tabla, String var, int inf, int sup, int noPartes){
	  //System.out.println("var:"+var);
	  
	  int col=Listas.indice(tabla[0],var);
    
    return discretizaColumna(tabla, col, inf, sup, noPartes);
  }

  private static String[][] discretizaColumna(String[][] tabla, int col, int inf, int sup, int noPartes){
    ValorDiscreto vd=new ValorDiscreto("",inf,sup,noPartes);

    String[] datos=Matrices.dameColumna(tabla,col);
    tabla=Matrices.eliminaColumna(tabla,col);

    for(int i=1; i<datos.length; i++)
      datos[i]=vd.getValorDiscreto1(Double.parseDouble(datos[i]));

        return Matrices.insCol(tabla,datos,col);
  }

  private static String[][] cambioCualitativo(String[][] tabla, int col, int inf, int sup, double umbral){
    double delta=Math.abs(sup-inf)*umbral;

    String[] datos=Matrices.dameColumna(tabla,col);
    String[] datos2=new String[datos.length];
    tabla=Matrices.eliminaColumna(tabla,col);

    for(int i=2; i<datos.length; i++){
      double actual=Double.parseDouble(datos[i]);
      double anterior=Double.parseDouble(datos[i - 1]);
      if (actual >
          anterior + delta)
        datos2[i] = "pos";
      else if (actual <
               anterior - delta)
        datos2[i] = "neg";
      else
        datos2[i] = "cero";
    }

    datos2[0]=datos[0];
        datos2[1]="cero";

        return Matrices.insCol(tabla,datos2,col);
  }



  public String infoAtributos(){

    String s=atributo;
    for(int i=0;i<noPartes; i++)
      s=s+"\ts"+i;
    return s;
  }

  public static void main(String[] args){
/*
    //String[] lista={"neg", "pos", "cero"};
    String[] lista={"S2", "S1", "S0"};

    Listas.imprimeLista( ValorDiscreto.ordenaValores(lista));
*/

    String[][] tabla=tablas.fileToMatrix("../ejemplos/robotNoObst/randomWalk/verQD/datos.txt","\t");

  //  tabla=ValorDiscreto.discretizVar(tabla,"x",-1000,10000,10);
  //  tabla=ValorDiscreto.discretizVar(tabla,"y",-1000,10000,10);
    tabla=ValorDiscreto.discretizVar(tabla,"heading",0,360,5);
  //  tabla=ValorDiscreto.discretizVar(tabla,"obst",0,1,2);

    tablas.matrixToFile("../ejemplos/robotNoObst/randomWalk/verQD/ejemplosQD.txt",tabla);
/*
    ValorDiscreto angle=new ValorDiscreto(0,360,5);
    String[][] states=tablas.fileToMatrix("../ejemplos/crudos/testStates.txt","\t");
    Matrices.imprimeTabla(states);

    for(int i=0;i<states.length;i++)
     System.out.println( angle.getValorDiscretoInt(Double.parseDouble(states[i][2])));

     int[] l1={1,3,4,5,6};
     l1=Listas.inserta(4,l1);
     Listas.imprimeLista(l1);
*/
String[][] tablaN=tablas.fileToMatrix("C:/Documents and Settings/xperto/Escritorio/powerPlant.txt","\t");
 Matrices.imprimeTabla(tablaN);
 Matrices.imprimeTabla(tablaN=ValorDiscreto.cambioCualitativo(tablaN,5,0,1,0.0005));
 Matrices.imprimeTabla(tablaN=ValorDiscreto.cambioCualitativo(tablaN,6,0,1,0.0005));

 String datos3[]=new String[tablaN.length];
 datos3[0]="a";

 for(int i=1; i<tablaN.length; i++)
   if(tablaN[i][5].equals("cero")&& tablaN[i][6].equals("cero"))
     datos3[i]="0"; // sin cambios
     else if(tablaN[i][5].equals("pos")&& tablaN[i][6].equals("cero"))
       datos3[i]="1"; // abrir fwv
     else if(tablaN[i][5].equals("neg")&& tablaN[i][6].equals("cero"))
       datos3[i]="2"; // cerrar fwv
     else if(tablaN[i][5].equals("cero")&& tablaN[i][6].equals("pos"))
       datos3[i]="3"; // abrir msv
     else if(tablaN[i][5].equals("cero")&& tablaN[i][6].equals("neg"))
       datos3[i]="4"; // cerrar msv
     else if(tablaN[i][5].equals("pos")&& tablaN[i][6].equals("neg"))
       datos3[i]="5"; // abrir fwv y cerrar msv
     else if(tablaN[i][5].equals("neg")&& tablaN[i][6].equals("pos"))
       datos3[i]="6"; // cerrar fwv y abrir msv
     else if(tablaN[i][5].equals("neg")&& tablaN[i][6].equals("neg"))
       datos3[i]="7"; // cerrar fwv y cerrar msv
     else if(tablaN[i][5].equals("pos")&& tablaN[i][6].equals("pos"))
       datos3[i]="8"; // abrir fwv y abrir msv
       else datos3[i]="NA";

  tablaN=Matrices.insCol(tablaN,datos3,7);

  tablaN=ValorDiscreto.discretizVar(tablaN,"fms",39,55,6);
  tablaN=ValorDiscreto.discretizVar(tablaN,"ffw",39,48,2);
  tablaN=ValorDiscreto.discretizVar(tablaN,"d",0,1,2);
  tablaN=ValorDiscreto.discretizVar(tablaN,"pd",3100,5200,8);
  tablaN=ValorDiscreto.discretizVar(tablaN,"g",0,31800,2);

 tablas.matrixToFile("C:/Documents and Settings/xperto/Escritorio/powerPlantN2.txt",tablaN);

  }



}
