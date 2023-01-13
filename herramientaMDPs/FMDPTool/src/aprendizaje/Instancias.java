package aprendizaje;

import utileria.Matrices;
import utileria.tablas;
import utileria.Listas;
import utileria.FileOutput01;
import java.io.File;
import utileria.FileOutput;
import java.util.Vector;
import java.util.ArrayList;
import DataStructuresQ.Cualitativos;
import ia.RBD;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Instancias {

  public String[][] ejemplos;
  public String[][] atributos;
  String filename;

  public Instancias(String exFilename, String atFilename){
    ejemplos=tablas.fileToMatrix(exFilename,"\t:, ");
    atributos=tablas.fileToMatrix(atFilename,"\t:, ");
    filename=exFilename.substring(0,exFilename.length()-4) +"Weka.arff";

  }

  public Instancias(String exFilename, String atFilename, String outputFile){
    ejemplos=tablas.fileToMatrix(exFilename,"\t:, ");
    atributos=tablas.fileToMatrix(atFilename,"\t:, ");
    filename=outputFile;
  }

  public String[] getContVars(){
    Vector v=new Vector();

    for(int i=0; i<atributos.length; i++)
      if(atributos[i][1].equals("real"))
        v.addElement(atributos[i][0]);

        return Listas.stringVectorToStringArray(v);
  }

  public boolean isCont(String var){
    boolean aux=false;

    for(int i=0; i<atributos.length; i++)
      if(atributos[i][1].equals("real")&& atributos[i][0].equals(var))
        aux=true;

        return aux;
  }

  public double[] getUmbrales(String[] ref,double fraccion){
    double umbral[]=new double[ref.length];
    for(int i=0; i<ref.length; i++)
      umbral[i]=getRango(ref[i])*fraccion;

      return umbral;
  }

  public double getRango(String var) {
    double rango = 0;
    if(isCont(var))
    for (int i = 0; i < atributos.length; i++)
      if (atributos[i][0].equals(var))
        rango = Double.parseDouble(atributos[i][3]) -
            Double.parseDouble(atributos[i][2]);
    return rango;
  }

  public int no_cortes(String var, String[][] referencias){
    String[] vars=Matrices.dameColumna(referencias,0);
    return Listas.ocurrencias(var,vars);
  }

  public String[] getDiscreteVars(){
    Vector v=new Vector();

    for(int i=0; i<atributos.length; i++)
      if(!atributos[i][1].equals("real"))
        v.addElement(atributos[i][0]);

        return Listas.reduceLista( Listas.stringVectorToStringArray(v),v.size()-1);
  }
  // devuelve toda la columna de la variable var
  public String[] getColumna(String var){
    int indice=Listas.indice(ejemplos[0],var);
    return Matrices.dameColumna(ejemplos,indice);
  }

  // devuelve el indice de la columna de la variable var
  public int getIndexColumna(String var){
        return Listas.indice(ejemplos[0],var);
  }

  public static String[] renombraCampos(String[] referencias) {
    String[] aux = new String[referencias.length];

    for (int i = 1, j = 0; i <= referencias.length; i++, j++) {
      aux[i - 1] = "q"+referencias[i - 1] + j;
      if (i != referencias.length) {
        if (!referencias[i].equals(referencias[i - 1]))
          j = -1;
      }
    }
    return aux;
  }

  // devuelve columna de acciones
  public String[] colAcciones() {
    return Matrices.dameColumna(ejemplos, ejemplos[0].length - 2);
  }

  // devuelve tabla de variables continuas
  public String[][] getTablaVarsCont(){

    String[] varsCont=getContVars();
    String[][] aux=new String[ejemplos.length][0];

    for(int i=0; i<varsCont.length;i++)
      aux=Matrices.insCol(aux, getColumna(varsCont[i]),i);

      return aux;

  }

  // devuelve tabla de variables discretas
  public String[][] getTablaVarsDiscr(){

    String[] varsDiscr=getDiscreteVars();
    String[][] aux=new String[ejemplos.length][0];

    for(int i=0; i<varsDiscr.length;i++)
      aux=Matrices.insCol(aux, getColumna(varsDiscr[i]),i);

      return aux;

  }
  // genera una tabla de datos aumentada con copias de los datos para los
  // atributos de referencia. No contiene campos
  public String[][] generaDatosParaCualificar(String[][] referencias) {

    String[][] datos = new String[ejemplos.length][0];
    for (int ref = 0; ref < referencias.length; ref++)
      datos = Matrices.aumenta(datos, getColumna(referencias[ref][0]),
                               datos[0].length, 1);
    return datos;
  }

  public String[][] prepareElvFormat(){
    /*
    String[][] tabla=Matrices.eliminaFila( generaDatosParaCualificar(referencias),0);

      String[] ref=Matrices.dameColumna(referencias,1);
      String[] campos=Matrices.dameColumna(referencias,0);
      double[] umbrales=getUmbrales(campos,umbral);
      campos=renombraCampos(campos);

      tabla=Cualitativos.matrizCualitativa(tabla,ref,umbrales);
      tabla=Matrices.insertaFila(tabla,campos,0);
     */
    //String[][] tabla=
      String[][] tablaD=getTablaVarsDiscr();
      String[][] tabla=tablaD;
      tabla=Matrices.insCol(tabla,colAcciones(),tabla[0].length);

      return tabla;
  }


  public String[][] prepareElvFormat(String[][] referencias, double umbral){

    //elimina la fila de campos en la tabla con copias de los datos
    String[][] tabla=Matrices.eliminaFila( generaDatosParaCualificar(referencias),0);

      String[] ref=Matrices.dameColumna(referencias,1);
      String[] campos=Matrices.dameColumna(referencias,0);
      double[] umbrales=getUmbrales(campos,umbral);
      campos=renombraCampos(campos);

      tabla=Cualitativos.matrizCualitativa(tabla,ref,umbrales);
      tabla=Matrices.insertaFila(tabla,campos,0);
      String[][] tablaD=getTablaVarsDiscr();
      tabla=Matrices.concatena(tabla,tablaD);
      tabla=Matrices.insCol(tabla,colAcciones(),tabla[0].length);

      return tabla;
  }

  // genra tabla de datos cualitativos. devuelve datos y campos
  // se asume que todas las vars continuas se cualifican
  public String[][] generateQFormat(miWeka arbol){

    // genero tabla con atributos de instancia del arbol
    String[] atributosArbol=arbol.getAtributos();
    String[][] instancia=new String[ejemplos.length][0];

    for(int i=0; i<atributosArbol.length;i++)
      instancia=Matrices.insCol(instancia,getColumna(atributosArbol[i]),i);

    // inicio una lista con consultas donde primer elemento es nombre del campo
    String[] colN=new String[instancia.length];
    colN[0]="Q";

    // el resto de la lista contiene los valores cualitativos
    for(int i=1; i<instancia.length; i++)
      colN[i]=arbol.consultaArbol(instancia[i]);

      // inserto la nueva columna al frente de los datos discretos
      String[][] tablaD=getTablaVarsDiscr();
      String[][] tabla=Matrices.insCol(tablaD,colN,0);
      tabla=Matrices.insCol(tabla,colAcciones(),tabla[0].length);

      return tabla;
  }

/*
  public Vector separaDatosElvFormat(String[][] referencias, double umbral, int noAcciones){
    Vector v=new Vector();

    String[][] t=prepareElvFormat(referencias,umbral);


String[][] t1;
String[] campos=t[0];
campos=Listas.inserta("",campos);
int colxelim=campos.length;
t=marcaCambios(t);

for (int i = 0; i < noAcciones; i++) {

  t1 = mismaClase(t, "" + i);
  t1 = Matrices.insertaFila(t1, campos, 0);
  t1 = Matrices.aumentaMatriz(t1);
  t1 = Matrices.eliminaColumna(t1, colxelim);
  t1 = Matrices.eliminaColumna(t1, colxelim - 1);
  t1 = Matrices.eliminaColumna(t1, t1[0].length - 1);
  t1 = Matrices.reduceTablaDif(t1, 0, "*");



  v.addElement(t1);
  System.out.println("tabla accion " + i);
  Matrices.imprimeTabla(t1);
}
return v;

}
*/

// esta funcion separa datos por accion y ademas aumenta cada matriz
// para tener variables en el tiempo t+1 (_prime)

public Vector separaDatosElvFormat(int noAcciones) {
  Vector v = new Vector();

  // en t se guardan los datos cualificados
  String[][] t = prepareElvFormat();

  String[] campos=Listas.reduceLista(t[0],t[0].length-1);
  String[] camposN=new String[2*campos.length];


    for(int j=0; j<camposN.length; j++)
      if(j<campos.length) camposN[j]=campos[j];
      else camposN[j]=campos[j-campos.length]+"_prime";

  String[] ejemplo;
  for (int accion = 0; accion < noAcciones; accion++) {

    ArrayList t1 = new ArrayList();
    for (int i = 1; i < t.length - 2; i++) {

      int a = Integer.parseInt(t[i][t[i].length - 1]);
      if (a == accion) {
        ejemplo = Listas.concatena(Listas.reduceLista(t[i], t[i].length - 1),
                                   Listas.reduceLista(t[i + 1],
            t[i + 1].length - 1));
        t1.add(ejemplo);
      }
    }
    Object[] aux = t1.toArray();
    String[][] aux0 = new String[aux.length][];

    for (int j = 0; j < aux0.length; j++)
      aux0[j] = (String[]) aux[j];

    aux0=Matrices.insertaFila(aux0,camposN,0);
    v.addElement(aux0);
  }

  return v;

}


// esta funcion separa datos por accion y ademas aumenta cada matriz
// de datos para tener variables en el tiempo t+1 (_prime)

public Vector separaDatosElvFormat(String[][] referencias, double umbral,
                                   int noAcciones) {
  Vector v = new Vector();

  // en t se guardan los datos cualificados
  String[][] t = prepareElvFormat(referencias, umbral);

  String[] campos=Listas.reduceLista(t[0],t[0].length-1);
  String[] camposN=new String[2*campos.length];


    for(int j=0; j<camposN.length; j++)
      if(j<campos.length) camposN[j]=campos[j];
      else camposN[j]=campos[j-campos.length]+"_prime";

  String[] ejemplo;
  for (int accion = 0; accion < noAcciones; accion++) {

    ArrayList t1 = new ArrayList();
    for (int i = 1; i < t.length - 2; i++) {

      int a = Integer.parseInt(t[i][t[i].length - 1]);
      if (a == accion) {
        ejemplo = Listas.concatena(Listas.reduceLista(t[i], t[i].length - 1),
                                   Listas.reduceLista(t[i + 1],
            t[i + 1].length - 1));
        t1.add(ejemplo);
      }
    }
    Object[] aux = t1.toArray();
    String[][] aux0 = new String[aux.length][];

    for (int j = 0; j < aux0.length; j++)
      aux0[j] = (String[]) aux[j];

    aux0=Matrices.insertaFila(aux0,camposN,0);

    v.addElement(aux0);
  //  System.out.println("tabla accion " + accion);
  //  Matrices.imprimeTabla(aux0);
  }

  return v;

}

  // genera archivo de ejemplos para weka
  public boolean generate_arffFormat(){
    File f=new File(filename);
    FileOutput fo=new FileOutput(filename);
    fo.writeStringln("@relation "+f.getName().substring(0,f.getName().length()-5));
    fo.writeStringln("");



    String[][] ejemplos0=Matrices.eliminaFila(ejemplos,0);
   // Matrices.imprimeTabla(ejemplos0);
    String[][] ejemplos1=Matrices.eliminaColumna(ejemplos0,ejemplos0[0].length-2);

    for(int i=0;i<atributos.length;i++){
       if(atributos[i][1].equals("real"))
         fo.writeStringln("@attribute "+atributos[i][0]+" real");
         else
      for(int j=0;j<atributos[i].length;j++){
        if (j == 0)
          fo.write("@attribute "+atributos[i][0]+" {");

        else if (j == atributos[i].length - 1)
          fo.writeStringln(atributos[i][j]+"}");

        else
          fo.write(atributos[i][j]+", ");
      }
    }

    fo.writeStringln("");
    fo.writeStringln("@data");
    fo.writeStringln("");

    for(int i=0;i<ejemplos1.length;i++)
      for(int j=0; j<ejemplos1[i].length;j++){
        if(j==ejemplos1[i].length-1)
          fo.writeStringln(ejemplos1[i][j]);
          else fo.write(ejemplos1[i][j]+",");

      }
    fo.close();

  return true;
}

public String[][] mismaClase(String[][] tabla, String clase){
  ArrayList al=new ArrayList();


  for(int i=0; i<tabla.length; i++)
    if(tabla[i][tabla[0].length-1].equals(clase))
    al.add(tabla[i]);

  String[][] a=new String[al.size()][];
  for(int i=0;i<al.size();i++)
    a[i]=(String[])al.get(i);


  return a;
}

public String[][] marcaCambios(String[][] tabla) {
  String[] columna=new String[tabla.length];
  String[][] tablaM=Matrices.insCol(tabla,columna,0);

  for(int i=1; i<tablaM.length; i++){
    if(!tablaM[i-1][tablaM[0].length-1].equals(tablaM[i][tablaM[0].length-1]))
      tablaM[i-1][0]="*";
      else tablaM[i-1][0]="";
  }
  tablaM[tabla.length-1][0]="";

  return tablaM;
}

  public static void main(String[] args) {
    Instancias ex=new Instancias("../ejemplos/crudos/robot/robotFalsosEj.txt","../ejemplos/crudos/robot/robotFalsosAt.txt");
    Matrices.imprimeTabla(ex.ejemplos);
    Matrices.imprimeTabla(ex.atributos);
    ex.generate_arffFormat();
  //  Listas.imprimeLista(ex.getDiscreteVars());
  //  Listas.imprimeLista(ex.getContVars());
  //  Matrices.imprimeTabla(ex.getTablaVarsCont());
  String[][] referencias=

      {{"x",  "748"},
{"x",  "5253"},
{"x",  "6749"},
{"y" , "2244"},
{"y" , "746"},
{"y" , "2228"},
{"y" , "8255"},
{"y" , "6758"}};

 Vector v= ex.separaDatosElvFormat(referencias,0.05,3);

 int SNumMaxParents=5;
 String path="../ejemplos/elvira/robot/";

RBD.aprendeRBD(v,path,SNumMaxParents);


 String[] archivo=new String[v.size()];
 int[] noCasos=new int[v.size()];

 for(int i=0; i<v.size();i++){
   String[][] ejemplosAumentados = (String[][]) v.elementAt(i);

   BaseNodos bn = new BaseNodos("base_nodos", ejemplosAumentados,
                                Matrices.conjunto(ejemplosAumentados));
   FileOutput01 fo = new FileOutput01(path + "casos/a" + i + "Elv.dbc");
   fo.write(bn.getDatosCompletos());
   fo.close();
   archivo[i] = path + "casos/a" + i + "Elv.dbc";
   noCasos[i] = ejemplosAumentados.length - 1;
 }


for(int i=0; i<v.size();i++){
String ArchivoELV=path+"redes/a"+i+"Elv.elv";

int SNumCasos=noCasos[i];
  System.out.println(SNumCasos);

AprendeELV aprende=new AprendeELV();
  aprende.generaRB(archivo[i],
                   "" + SNumMaxParents,
                   ArchivoELV,
                  "" + SNumCasos);
}




  System.out.println(""+ex.isCont("y"));
    System.out.println(""+ex.getRango("y"));




  }
}