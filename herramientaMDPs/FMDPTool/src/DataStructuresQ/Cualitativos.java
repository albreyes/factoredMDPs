package DataStructuresQ;

import java.util.Arrays;
import java.util.Vector;
import java.awt.Dimension;
import robotica.Celda;
import robotica.Punto;
//import utileria.Matrices;
import ia.Variable;
import utileria.*;
import java.io.File;
import aprendizaje.miWeka;
import aprendizaje.Instancias;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Cualitativos {
  public Cualitativos() {
  }

static public double umbral(double[] lista, double porciento){
    double[] lista1=new double[lista.length];

  for(int i=0; i<lista1.length; i++)
    lista1[i]=lista[i];
  Arrays.sort(lista1);
  return (lista1[lista1.length-1]-lista1[0])*porciento/100;
  }

static public double[] umbrales(double[][] tabla, double porciento){

    double[] lista=new double[tabla[0].length];
    int i=0;
    for(int j=0; j<tabla[0].length; j++){
      lista[i]=umbral(Matrices.dameColumna(tabla,j),porciento);
      i++;
    }
    return lista;
  }

  public static String[][] matrizCualitativa(String matriz[][], int posRef ,double umbral){
    String resultado[][]=new String[matriz.length-1][matriz[0].length];
    resultado[0]=matriz[0];
    int count=1;
    for(int i=1;i<matriz.length;i++)
      if(i!=posRef){
      resultado[count]=listaCualitativa(matriz[i],matriz[posRef], umbral);
      count++;
    }
      return resultado;
  }


  public static String[][] matrizCualitativa(String matriz[][], int posRef,
                                           double[] umbral) {

  return matrizCualitativa(matriz, matriz[posRef],umbral);
}


  // modificnado esta funcion
  public static String[][] matrizCualitativa(String matriz[][], String[] vectorRef,
                                             double[] umbral) {
    String resultado[][] = new String[matriz.length][matriz[0].length];
    resultado[0] = matriz[0];
    //int count = 1;
    for (int i = 0; i < matriz.length; i++)
      //if (i != posRef) {
        resultado[i] = listaCualitativa(matriz[i], vectorRef, umbral);
        //count++;
     // }
    return resultado;
  }

  private static String[] listaCualitativa(String lista[],String ref[], double[] umbral){
    String resultado[]=new String[lista.length];

    for(int i=0; i<resultado.length; i++)
      if(Double.parseDouble(lista[i])>Double.parseDouble(ref[i])+umbral[i])
        resultado[i]="pos";
      else if(Double.parseDouble(lista[i])<Double.parseDouble(ref[i])-umbral[i])
        resultado[i]="neg";
        else resultado[i]="cero";
    return resultado;
  }

  // esta funcion traduce la particion en un conjunto de celdas 2D
  public static Vector getCeldas(Vector particiones, double[] recompensa,
                                 Dimension dim) {
    Vector celdas = new Vector();
    for (int i = 0; i < particiones.size(); i++) {
      Variable[] var = (Variable[]) particiones.elementAt(i);
      celdas.addElement(getCelda(var, recompensa[i], dim));
    }
    return celdas;
  }


  // produce una celda en funcion de una particion cualitativa
  public static Celda getCelda(Variable[] particion, double recompensa,
                               Dimension dim) {

    int xmin = 0, ymin = 0, xmax = dim.width, ymax = dim.height;

    Variable[] var = particion;
    Variable x = Variable.getVariable("x", var);
    Variable y = Variable.getVariable("y", var);

    if (! (x == null) && ! (y == null)) {
      if (x.min != Integer.MIN_VALUE)
        xmin = x.min;
      if (x.max != Integer.MAX_VALUE)
        xmax = x.max;
      if (y.min != Integer.MIN_VALUE)
        ymin = y.min;
      if (y.max != Integer.MAX_VALUE)
        ymax = y.max;

    }
    else

    if (x == null && ! (y == null)) {
      if (y.min != Integer.MIN_VALUE)
        ymin = y.min;
      if (y.max != Integer.MAX_VALUE)
        ymax = y.max;

    }
    else

    if (! (x == null) && y == null) {
      if (x.min != Integer.MIN_VALUE)
        xmin = x.min;
      if (x.max != Integer.MAX_VALUE)
        xmax = x.max;
    }
    int cx = xmin + (xmax - xmin) / 2;
    int cy = ymin + (ymax - ymin) / 2;
    Punto central = new Punto(cx, cy);

    return new Celda(central, new Dimension( (xmax - xmin), (ymax - ymin)), recompensa);
  }



  private static String[] listaCualitativa(String lista[],String ref[], double umbral){
    String resultado[]=new String[lista.length];

    for(int i=0; i<resultado.length; i++)
      if(Double.parseDouble(lista[i])>Double.parseDouble(ref[i])+umbral)
        resultado[i]="pos";
      else if(Double.parseDouble(lista[i])<Double.parseDouble(ref[i])-umbral)
        resultado[i]="neg";
        else resultado[i]="cero";
    return resultado;
  }

  // esta funcion se encarga de generar los archivos de ejemplos y atributos
  // cualitativos dados los archivos conteniendo datos continuos
  // usa referencias para generar una variable cualitativa por corte

public static String[] cualificaAuto(String ejemplosCrudos,
                                 String atributosCrudos,
                                 double umbral) {

  File f = new File(ejemplosCrudos);
  String arffFile = f.getParent() + "/dTreeCont.arff";

  Instancias ex = new Instancias(ejemplosCrudos, atributosCrudos,
                                     arffFile);

  ex.generate_arffFormat();

  miWeka arbol = new miWeka(arffFile);

  // del arbol continuo se extraen las referencias
  String[][] referencias = arbol.referencias();
  tablas.matrixToFile(f.getParent()+"/referencias.txt",referencias);

  String[][] tabla=ex.prepareElvFormat(referencias,umbral);
  String[] datos=Matrices.dameColumna(ex.ejemplos,ex.ejemplos[0].length-1);
  tabla=Matrices.insCol(tabla,datos,tabla[0].length);
  String filename1=f.getParent()+"/ejemplosQ.txt";
  tablas.matrixToFile(filename1,tabla);

  String[][] atrib=ex.atributos;
  String[] varsN=tabla[0];

  String texto="";

  for(int i=0; i<varsN.length; i++)

    if(!ex.isCont(varsN[i])) {
      String[] fila;
      // aqui busca la var en las variables originales
      boolean encontrada=false;
      for(int j=0; j<atrib.length; j++){

        if(atrib[j][0].equals(varsN[i])){
          encontrada=true;
          if(j!=atrib.length-1)
           texto=texto+formatAttrib(atrib[j])+"\n";
           else texto=texto+formatAttrib(atrib[j]);}
       }
       // si no la encuentra y no es la accion entonces es cualitativa
       if(!encontrada&&i!=varsN.length-2)
         texto=texto+varsN[i]+":cero,pos,neg\n";
    }

   String filename2=f.getParent()+"/atributosQ.txt";
   FileOutput fo=new FileOutput(filename2);
   fo.write(texto);
   fo.close();
   String[] files={filename1, filename2};
   return files;
}

 public static String[] cualificaAuto(String ejemplosCrudos,
                                  String atributosCrudos, miWeka arbol) {

    File f = new File(ejemplosCrudos);

    String arffFile = f.getParent() + "/dTreeCont.arff";

    Instancias ex = new Instancias(ejemplosCrudos, atributosCrudos,
                                       arffFile);

    ex.generate_arffFormat();

  //  miWeka arbol = new miWeka(arffFile);


   // del arbol continuo se extraen las referencias
   String[][] referencias = arbol.referencias();
   tablas.matrixToFile(f.getParent()+"/referencias.txt",referencias);

     // genera nuevo archivo de ejemplos cualificados
   String[][] tabla=ex.generateQFormat(arbol);
   // a la tabla se le agrega la columna de reward
   String[] datos=Matrices.dameColumna(ex.ejemplos,ex.ejemplos[0].length-1);
   tabla=Matrices.insCol(tabla,datos,tabla[0].length);

   // se guardan los nuevos ejemplos en un archivo
   String filename1=f.getParent()+"/ejemplosQ.txt";
   tablas.matrixToFile(filename1,tabla);

   String[][] atrib=ex.atributos;
   String[] varsN=tabla[0];

   String texto="";

   // con esto formo la lista de valores cualitativos para el archivo
  // int numValsQ=arbol.numOfLeaves();
   int numValsQ=arbol.numHojas();
   System.out.println("NUMERO DE HOJAS "+numValsQ);
   String valoresQ="";

   for(int i=0; i<numValsQ; i++)
     if(i!=numValsQ-1)
     valoresQ=valoresQ+"q"+i+",";
     else valoresQ=valoresQ+"q"+i;


   for(int i=0; i<varsN.length; i++)

     if(!ex.isCont(varsN[i])) {
       String[] fila;
       // aqui busca la var en las variables originales
       boolean encontrada=false;
       for(int j=0; j<atrib.length; j++){

         if(atrib[j][0].equals(varsN[i])){
           encontrada=true;
           if(j!=atrib.length-1)
            texto=texto+formatAttrib(atrib[j])+"\n";
            else texto=texto+formatAttrib(atrib[j]);}
        }
        // si no la encuentra y no es la accion entonces es cualitativa
        if(!encontrada&&i!=varsN.length-2)
          texto=texto+varsN[i]+":"+ valoresQ+ "\n";
     }

    String filename2=f.getParent()+"/atributosQ.txt";
    FileOutput fo=new FileOutput(filename2);
    fo.write(texto);
    fo.close();
    String[] files={filename1, filename2};
    return files;
 }


// esta funcion se encarga de generar los archivos de ejemplos y atributos
// cualitativos dados los archivos conteniendo datos continuos
// las hojas del arbol son los estados cualitativos

public static String[] cualificaAuto(String ejemplosCrudos,
                                 String atributosCrudos) {

  File f = new File(ejemplosCrudos);
  String arffFile = f.getParent() + "/dTreeCont.arff";

  Instancias ex = new Instancias(ejemplosCrudos, atributosCrudos,
                                     arffFile);

  ex.generate_arffFormat();

  miWeka arbol = new miWeka(arffFile);

  // del arbol continuo se extraen las referencias
  String[][] referencias = arbol.referencias();
  tablas.matrixToFile(f.getParent()+"/referencias.txt",referencias);

    // genera nuevo archivo de ejemplos cualificados
  String[][] tabla=ex.generateQFormat(arbol);
  // a la tabla se le agrega la columna de reward
  String[] datos=Matrices.dameColumna(ex.ejemplos,ex.ejemplos[0].length-1);
  tabla=Matrices.insCol(tabla,datos,tabla[0].length);

  // se guardan los nuevos ejemplos en un archivo
  String filename1=f.getParent()+"/ejemplosQ.txt";
  tablas.matrixToFile(filename1,tabla);

  String[][] atrib=ex.atributos;
  String[] varsN=tabla[0];

  String texto="";

  // con esto formo la lista de valores cualitativos para el archivo
  int numValsQ=arbol.numOfLeaves();
 // int numValsQ=arbol.numHojas();
  System.out.println("NUMERO DE HOJAS "+numValsQ);
  String valoresQ="";

  for(int i=0; i<numValsQ; i++)
    if(i!=numValsQ-1)
    valoresQ=valoresQ+"q"+i+",";
    else valoresQ=valoresQ+"q"+i;


  for(int i=0; i<varsN.length; i++)

    if(!ex.isCont(varsN[i])) {
      String[] fila;
      // aqui busca la var en las variables originales
      boolean encontrada=false;
      for(int j=0; j<atrib.length; j++){

        if(atrib[j][0].equals(varsN[i])){
          encontrada=true;
          if(j!=atrib.length-1)
           texto=texto+formatAttrib(atrib[j])+"\n";
           else texto=texto+formatAttrib(atrib[j]);}
       }
       // si no la encuentra y no es la accion entonces es cualitativa
       if(!encontrada&&i!=varsN.length-2)
         texto=texto+varsN[i]+":"+ valoresQ+ "\n";
    }

   String filename2=f.getParent()+"/atributosQ.txt";
   FileOutput fo=new FileOutput(filename2);
   fo.write(texto);
   fo.close();
   String[] files={filename1, filename2};
   return files;
}


 // esta le ayuda a la de arriba a formatear arreglos
 public static String formatAttrib(String[] lista){

   String texto="";
   for(int i=0; i<lista.length; i++)
     if(i==0) texto=texto+lista[i]+":";
     else if(i==lista.length-1)
       texto=texto+lista[i];
     else texto=texto+lista[i]+",";

    return texto;
 }
}