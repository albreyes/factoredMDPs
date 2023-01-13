package utileria;

/**
 * <p>T�tulo: </p>
 * <p>Descripci�n: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Tec-IIE</p>
 * @author Alberto Reyes Ballesteros
 * @version 1.0
 */


import java.util.Arrays;
import java.util.Vector;

public class Matrices extends Listas{

  public static boolean mismoValorEnCol(String[][] tabla, int columna){

    boolean resp=true;

   for(int fila=1; fila<tabla.length; fila++){
    if(!tabla[fila-1][columna].equals(tabla[fila][columna])) resp=false;
   }
    return resp;
  }


  /***********************************************************/
    // tablas
  /***********************************************************/


  public static double[][] ordenaFilas(double[][] tabla, String ordenReal[],String ordenDeseado[] ){
    double[][] aux=new double[tabla.length][];
    int indice;
    for(int i=0; i<tabla.length; i++){
      indice=Listas.indice(ordenReal,ordenDeseado[i]);
        aux[i]=tabla[indice];
    }
    return aux;
  }



  public static String[] dameColumna(String[][] tabla, int columna){

    String[] col=new String[tabla.length];

   for(int fila=0; fila<tabla.length; fila++)
     col[fila]=tabla[fila][columna];

    return col;
  }

  public static double[] dameColumna(double[][] tabla, int columna){

    double[] col=new double[tabla.length];

   for(int fila=0; fila<tabla.length; fila++)
     col[fila]=tabla[fila][columna];

    return col;
  }


  // devuelve el indice de la fila donde se encuentra dato.
  public static int filaPrimeraCol(String[][] tabla, String dato){
    int fila=-1;
    for(int i=0;i<tabla.length;i++)
      if(tabla[i][0].equals(dato)) fila=i;
    return fila;
  }

//imprime el contenido de una matriz de enteros
 public static void imprimeTabla(int[][] tabla){
  for(int fila=0; fila<tabla.length; fila++){
    System.out.print(fila+"  ");
    for(int col=0; col<tabla[fila].length; col++)
    System.out.print(tabla[fila][col]+"  ");
    System.out.println();
  }
 }

 public static double[][] StringArrayToDoubleArray(String[][] arreglo){
   double[][] arreglo1=new double[arreglo.length][arreglo[0].length];
   for(int fila=0; fila<arreglo.length; fila++)
     for(int col=0; col<arreglo[fila].length; col++)
       arreglo1[fila][col]=Double.parseDouble(arreglo[fila][col]);

      return arreglo1;
 }


//Convierte el contenido de una matriz en una cadena
 public static String tablaToString(Object[][] tabla){
   StringBuffer s= new StringBuffer("");
   //s.
  for(int fila=0; fila<tabla.length; fila++){
    //System.out.print(fila+"  ");
    for(int col=0; col<tabla[fila].length; col++)
   // s=s+tabla[fila][col];
        s.append(tabla[fila][col]);
    s.append("\n");
  }
  return s.toString();
 }


//imprime el contenido de una matriz de Strings
 public static void imprimeTabla(String[][] tabla){
  for(int fila=0; fila<tabla.length; fila++){
    //System.out.print(fila+"  ");
    for(int col=0; col<tabla[fila].length; col++)
    System.out.print(tabla[fila][col]+"  ");
    System.out.println();
  }
 }

//escribe el contenido de una matriz de Strings en un archivo
 public static void escribeTabla(String[][] tabla, FileOutput fo){

  for(int fila=0; fila<tabla.length; fila++){
    for(int col=0; col<tabla[fila].length; col++)
      fo.write(tabla[fila][col]+"  ");
      fo.writeStringln("");

  }
 }


//escribe el contenido de una matriz de Strings en un archivo
 public static void escribeTabla(double[][] tabla, FileOutput fo){

  for(int fila=0; fila<tabla.length; fila++){
    for(int col=0; col<tabla[fila].length; col++)
      fo.write(""+tabla[fila][col]+"  ");
      fo.writeStringln("");

  }
 }


//imprime el contenido de una matriz de dobles
  public static void imprimeTabla(double[][] tabla){
  for(int fila=0; fila<tabla.length; fila++){
    for(int col=0; col<tabla[fila].length; col++)
      System.out.print(MiMath.round(tabla[fila][col],2)+"  ");
  //  System.out.print((float)tabla[fila][col]+"  ");
    System.out.println();
  }
 }

 public static int[][] copy(int[][] tabla){
   int[][] tablaN=new int[tabla.length][];

   for(int i=0; i<tabla.length; i++)
     tablaN[i]=copy(tabla[i]);

  return tablaN;
 }

 public static double[][] copy(double[][] tabla){
   double[][] tablaN=new double[tabla.length][];

   for(int i=0; i<tabla.length; i++)
     tablaN[i]=copy(tabla[i]);

  return tablaN;
 }


 public static double[][][] copy(double[][][] tabla){
   double[][][] tablaN=new double[tabla.length][][];

   for(int i=0; i<tabla.length; i++)
     tablaN[i]=copy(tabla[i]);

  return tablaN;
 }


  //reduce una tabla dejando con valores coincidentes con dato
  public static String[][] reduceTabla(String[][] tabla, int columna,
                                       String dato){
    String[] col=dameColumna(tabla,columna);
    int ocurrencias=ocurrencias(dato,col);
    String[][] tablaNueva=new String[ocurrencias][tabla[0].length-1];
    int pos=0;

    for(int i=0;i<tabla.length; i++)
      if(tabla[i][columna].equals(dato)){
        tablaNueva[pos]=reduceLista(tabla[i],columna);
        pos++;
      }
    return tablaNueva;
  }

  public static boolean miembro(String[] lista, Vector v){
    boolean miembro=false;
    for(int i=0; i<v.size();i++)
      if(esIgual((String[])v.elementAt(i),lista))
        miembro=true;

        return miembro;
  }


  //reduce una tabla dejando con valores diferentes de dato
  public static String[][] reduceTablaDif(String[][] tabla, int columna,
                                       String dato){
    String[] col=dameColumna(tabla,columna);
    int ocurrencias=ocurrencias(dato,col);
    ocurrencias=tabla.length- ocurrencias;
    String[][] tablaNueva=new String[ocurrencias][tabla[0].length-1];
    int pos=0;

    for(int i=0;i<tabla.length; i++)
      if(!tabla[i][columna].equals(dato)){
        tablaNueva[pos]=reduceLista(tabla[i],columna);
        pos++;
      }
    return tablaNueva;
  }


  //elimina una fila de una tabla cuadrada
  public static String[][] eliminaFila(String[][] tabla, int fila){
    String[][] tablaNueva=new String[tabla.length-1][tabla[0].length];

    int pos=0;

    for(int i=0; i<tabla.length; i++)
      if(i!=fila){
        tablaNueva[pos]=tabla[i];
        pos++;
    }
    return tablaNueva;
  }

  //elimina una fila de una tabla cuadrada
  public static int[][] eliminaFila(int[][] tabla, int fila){
    int[][] tablaNueva=new int[tabla.length-1][tabla[0].length];

    int pos=0;

    for(int i=0; i<tabla.length; i++)
      if(i!=fila){
        tablaNueva[pos]=tabla[i];
        pos++;
    }
    return tablaNueva;
  }


  //elimina varias filas de una tabla cuadrada
  public static String[][] eliminaFilas(String[][] tabla, int[] fila){
    Arrays.sort(fila);
    String[][] tablaNueva=new String[tabla.length-fila.length][tabla[0].length];

    int pos=0;
    int pos1=0;

    for(int i=0; i<tabla.length; i++)
      if(i!=fila[pos1]){
        tablaNueva[pos]=tabla[i];
        pos++;
    } else if(pos1<fila.length-1) pos1++;
    return tablaNueva;
  }

  //conserva las filas dadas de una tabla cuadrada
  public static Object[][] conservaFilas(Object[][] tabla, int[] fila){
    Arrays.sort(fila);
    Object[][] tablaNueva=new Object[fila.length][tabla[0].length];

    int pos=0;

    for(int i=0; i<tabla.length; i++)
      if(pos<fila.length)
      if(i==fila[pos]){
        tablaNueva[pos]=tabla[i];
        pos++;
    }

    return tablaNueva;
  }

  // aumenta la matriz en la ultima columna de elemeno de
  // la fila i+1 de la columna pos para una RBD

  private static String[][] aumentaMatriz(String matriz[][], int pos){
    String[][] m=new String[matriz.length-1][matriz[0].length+1];

    for(int i=0; i<matriz.length-1; i++)
      for(int j=0; j<matriz[i].length;j++)
        m[i][j]=matriz[i][j];

      m[0][matriz[0].length]=matriz[0][pos]+"_prime";
    for(int i=1; i<matriz.length-1; i++)
      m[i][matriz[0].length]=matriz[i+1][pos];

      return m;
  }

  public static double[][] roundMatrix(double m[][], int noDecimales){
    double[][] mN = new double[m.length][m[0].length];

    for(int i=0;i<m.length;i++)
      for(int j=0; j<m[i].length;j++)
        mN[i][j]=MiMath.round(m[i][j],noDecimales);

    return mN;

  }

  // aumenta la matriz en la ultima columna de elemeno de
  // la fila i+1 de la columna pos para una RBD

  public static String[][] aumentaMatriz(String matriz[][]){
   // Matrices.imprimeTabla(matriz);

    String[][] m=new String[matriz.length-1][matriz[0].length*2];

    for(int i=0; i<matriz.length-1; i++)
      for(int j=0; j<matriz[i].length;j++)
        m[i][j]=matriz[i][j];

    for(int j=matriz[0].length; j<m[0].length; j++)
      m[0][j]=matriz[0][j-matriz[0].length]+"_prime";


    for(int i=1; i<matriz.length-1; i++)
      for(int j=matriz[0].length;j<m[0].length;j++)
      m[i][j]=matriz[i+1][j-matriz[0].length];

      return m;
  }


  public static String[][] conjunto(String[][] m){
    String[][] s=new String[m[0].length][];
    for(int i=0;i<s.length; i++){
    s[i]=conjunto(dameColumna(m,i));
  }
    return s;
  }

  // elimina la columna c de la matriz
  public static String[][] eliminaColumna(String[][] matriz, int c) {

      String[][] aux = new String[matriz.length][matriz[0].length - 1];

      int indice;
      for (int i = 0; i < matriz.length; i++) {
        indice = 0;
        for (int j = 0; j < matriz[0].length; j++)
          if (j != c) {
            aux[i][indice] = matriz[i][j];
            indice++;
          }
      }
      return aux;
  }


  public static String[][] matrizC4_5(String[][] m){
    for(int i=0; i<m.length; i++)
      for(int j=0; j<m[i].length; j++){
        if(j==0) m[i][j]=m[i][j]+":";
          else if(j==m[i].length-1) m[i][j]=m[i][j]+";";
      }
    return m;

  }
  public static String[][] agregaComas(String[][] m){
    String m0[][]=new String[m.length][];
    for(int i=0; i<m.length; i++)
      m0[i]=m[i];

    for(int i=0; i<m.length; i++)
      for(int j=0; j<m[i].length; j++)
        if(j==m[i].length-1) m0[i][j]=m[i][j]+";";

    return m0;

  }
  // ordena una tabla de acuerdo con una columna
  public static String[][] ordena(String tabla[][], int columna){
    String[][] tablaOrdenada=new String[tabla.length][];
    String[] valores=Listas.conjunto(Matrices.dameColumna(tabla,columna));
    int fila=0;
    for(int v=0; v<valores.length; v++)
      for(int i=0; i<tablaOrdenada.length;i++)
        if(valores[v].equals(tabla[i][columna])){
          tablaOrdenada[fila]=tabla[i];
          fila++;
        }
    return tablaOrdenada;
  }

  public static void updateColumn(double[] col, int pos, double[][] tabla){
    for(int i=0; i<col.length; i++)
      tabla[i][pos]=col[i];
  }

  // intercambia ceros por valores peque�os

  public static double[][] intercambiaCeros(double[][] tabla){

    double ceros,noCeros,menor,cero, col[], correccion;
    double[][] tablaN=new double[tabla.length][tabla[0].length];

    for(int i=0; i<tabla.length; i++)
      tablaN[i]=tabla[i];


    for(int j=0; j<tabla[0].length; j++){
      col=Matrices.dameColumna(tabla,j);
       ceros=cuentaCeros(col);
       noCeros=col.length-ceros;

       if (ceros != 0) { // solo procesa si hay ceros en la col analizada
         menor = min(col);
         if (menor == 0 || (menor / 2.0) > 0.00001)
           cero = 0.00001;
        // else
        // if ( )
          // cero = 0.00001;
         else
           cero = menor / 2.0;

        correccion=cero*ceros/noCeros;

      for(int i=0; i<col.length;i++){
        if(col[i]==0) col[i]=cero;
          else col[i]=col[i]-correccion;
      }
      updateColumn(col,j,tablaN);
      }
    }
    return tablaN;
  }


  public static String[][] matrizC4_5Con(String[] l){
    String[][] m=new String[l.length][2];
    for(int i=0; i<m.length; i++)
      for(int j=0; j<m[i].length; j++){
        if(j==0) m[i][j]=l[i]+":";
          else m[i][j]="(FLOAT)";
      }
    return m;

  }
// consulta sobre el ultimo campo
  public static String[][] query(String tabla[][], String key){
    String[][] s=null;
    for(int i=0; i<tabla.length;i++)
      if(tabla[i][tabla[0].length-1].equals(key))
        s=insertaRegistro(s, tabla[i]);

    return s;
  }

  public static String[][] insertaRegistro(String[][] tabla, String[] registro){

    if(tabla==null){
    String[][] s=new String[1][registro.length];
    s[0]=registro;
      return s;
    } else {
    String[][] m=new String[tabla.length+1][registro.length];

    for(int i=0; i<tabla.length; i++)
      m[i]=tabla[i];

      m[tabla.length]=registro;
      return m;
    }
  }

  public static double[][] insertaFila(double[][] tabla, double[] registro,
                                       int pos) {

    if (tabla == null) {
      double[][] s = new double[1][registro.length];
      s[0] = registro;
      return s;
    }
    else {
      double[][] m = new double[tabla.length + 1][registro.length];

      boolean ya = false;
      for (int i = 0; i < tabla.length + 1; i++) {
        if (pos == i) {
          m[pos] = registro;
          ya = true;
          continue;
        }
        if (ya)
          m[i] = tabla[i - 1];
        else
          m[i] = tabla[i];
      }

      return m;
    }
  }


  public static String[][] insertaFila(String[][] tabla, String[] registro,
                                       int pos) {

    if (tabla == null) {
      String[][] s = new String[1][registro.length];
      s[0] = registro;
      return s;
    }
    else {
      String[][] m = new String[tabla.length + 1][registro.length];

      boolean ya = false;
      for (int i = 0; i < tabla.length + 1; i++) {
        if (pos == i) {
          m[pos] = registro;
          ya = true;
          continue;
        }
        if (ya)
          m[i] = tabla[i - 1];
        else
          m[i] = tabla[i];
      }

      return m;
    }
  }


  public static double[][] obtenSubtabla(double[][] tabla, int colInicial, int colFinal){

   // falta validar que la subtabla sea mas chica que la tabla

    int dim=Math.abs(colFinal-colInicial)+1;
    double[][] tablaN=new double[tabla.length][dim];

    for(int i=0;i<tablaN.length;i++)
      for(int j=0; j<dim; j++)
        tablaN[i][j]=tabla[i][j+colInicial];

        return tablaN;
  }

  public static int indiceSublista(int[][] tabla, int[] sublista){
    int indice=-1;

    for(int i=0; i<tabla.length; i++)
      if(Arrays.equals(tabla[i],sublista)) indice=i;

    return indice;
  }
  

  //devuelve un entero positivo denotando fila donde se encueentra
  // lista dentro de tabla. devuelve -1 si no se encuentra
  public static int miembro(int[] lista, int[][] tabla){
    int aux=-1;

    for(int i=0; i<tabla.length; i++)
      if(Listas.esIgual(lista,tabla[i])){
        aux = i;
        break;
      }
    return aux;
  }


  public static double[][] normalizaMatriz(double[][] matriz){

   double[][] m=new double[matriz.length][];

   // obtiene una copia de matriz en m
   for(int i=0; i<matriz.length; i++)
     m[i]=matriz[i];
   //  for(int j=0; j<matriz[i].length; j++)
   //    m[i][j]=matriz[i][j];

   // calcula la suma
   double[] sumas=new double[matriz.length];
   double suma;

   for(int i=0; i<matriz.length; i++){
     suma=0;
     for(int j=0; j<matriz[i].length; j++)
       suma=suma+matriz[i][j];
     sumas[i]=suma;
   }
   // normaliza matriz
   for(int i=0; i<matriz.length; i++)
     for(int j=0; j<matriz[i].length; j++)
       m[i][j]=matriz[i][j]/sumas[i];

   return m;
  }

  public static double[][] ordenaColumnas(double[][] subtabla, String[] ordenReal,
                                          String[] ordenDeseado, int increm) {
    double aux[][] = new double[subtabla.length][subtabla[0].length];
    double aux2[][];
    int col;

    //increm=increm/ordenReal.length;

    for(int j=0,indice=0; j<subtabla[0].length;j+=increm,indice++){
      aux2=obtenSubtabla(subtabla,j,j+increm-1);
      col=indice(ordenDeseado,ordenReal[indice]);
      Matrices.inserta(aux,aux2,subtabla[0].length/ordenReal.length*col);
    }

    return aux;
  }

  public static String[][] insCol(String[][] tabla, String[] datos, int pos) {
    String[][] tablaN = new String[tabla.length][tabla[0].length + 1];

    // copiamos contenido de tabla desde 0 hasta pos-1
    for (int i = 0; i < tabla.length; i++)
      for (int j = 0; j < pos; j++)
        tablaN[i][j] = tabla[i][j];

        // copiamos el resto de tabla de pos + 1 hasta ultima columna de tablaN
    for (int i = 0; i < tabla.length; i++)
      for (int j = pos + 1; j < tablaN[0].length; j++)
        tablaN[i][j] = tabla[i][j - 1];

        // en pos copiamos los datos
    for (int i = 0; i < tabla.length; i++)
      tablaN[i][pos] = datos[i];

    return tablaN;
  }

  public static int[][] insCol(int[][] tabla, int[] datos, int pos) {
    int[][] tablaN = new int[tabla.length][tabla[0].length + 1];

    // copiamos contenido de tabla desde 0 hasta pos-1
    for (int i = 0; i < tabla.length; i++)
      for (int j = 0; j < pos; j++)
        tablaN[i][j] = tabla[i][j];

        // copiamos el resto de tabla de pos + 1 hasta ultima columna de tablaN
    for (int i = 0; i < tabla.length; i++)
      for (int j = pos + 1; j < tablaN[0].length; j++)
        tablaN[i][j] = tabla[i][j - 1];

        // en pos copiamos los datos
    for (int i = 0; i < tabla.length; i++)
      tablaN[i][pos] = datos[i];

    return tablaN;
  }

    public static double[][] insCol(double[][] tabla, double[] datos, int pos) {
    double[][] tablaN = new double[tabla.length][tabla[0].length + 1];

    // copiamos contenido de tabla desde 0 hasta pos-1
    for (int i = 0; i < tabla.length; i++)
      for (int j = 0; j < pos; j++)
        tablaN[i][j] = tabla[i][j];

        // copiamos el resto de tabla de pos + 1 hasta ultima columna de tablaN
    for (int i = 0; i < tabla.length; i++)
      for (int j = pos + 1; j < tablaN[0].length; j++)
        tablaN[i][j] = tabla[i][j - 1];

        // en pos copiamos los datos
    for (int i = 0; i < tabla.length; i++)
      tablaN[i][pos] = datos[i];

    return tablaN;
  }

  public static void inserta(double[][] tabla, double[][] subtabla, int columna) {
    int colFinal = columna + subtabla[0].length;

    for (int i = 0; i < tabla.length; i++)
      for (int j = columna, j0 = 0; j < colFinal; j++, j0++)
        tabla[i][j] = subtabla[i][j0];
  }
  // esta funcion aumementa recursivamente una matriz desplazando
  // los elementos de una columna a la derecha

  public static String[][] aumenta(String[][] tabla, String[] datos, int pos, int veces){
    if(veces==0) return tabla;
    else { return aumenta(Matrices.insCol(tabla,datos,pos) ,datos,pos,--veces);
    }
  }

  // concatena dos tablas
  public static String[][] concatena(String[][] tabla1, String[][] tabla2){
    String[][] aux=new String[tabla1.length][tabla1[0].length+tabla2[0].length];

    for(int i=0; i<aux.length; i++)
      aux[i]=concatena(tabla1[i],tabla2[i]);

    return aux;
  }


  public static void main(String[] args){

    /*
    double[][] tablaO=new double[3][10];

    double[][] tabla={{1,2,3},
    {4,5,6},
{7,8,9}};
String[] ordenReal={"pos","neg","cero"};
String[] ordenDeseado={"neg","cero","pos"};
Matrices.imprimeTabla(tabla);
    Matrices.imprimeTabla(Matrices.ordenaFilas(tabla, ordenReal,ordenDeseado ));
    Matrices.imprimeTabla(obtenSubtabla( tabla,  2,  2));
    Matrices.inserta(tablaO,tabla,4);
    Matrices.imprimeTabla(tablaO);
    */
/*
   String[] ordenReal={"neg","cero","pos"};
String[] ordenDeseado={"cero","pos","neg"};

 double[][] t={{   0.0045045046 , 0.5714286 , 3.7936267E-4  ,0.25 , 0.9586777,  0.33333334,  0.33333334 , 0.012738854 , 0.33333334  },
{0.0045045046,  0.14285715 , 3.7936267E-4 , 0.25 , 0.016528925 , 0.33333334 , 0.33333334 , 0.9808917,  0.33333334 } ,
{0.990991 , 0.2857143,  0.9992413,  0.5 , 0.024793388,  0.33333334 , 0.33333334 , 0.006369427 , 0.33333334 }};
double[][] tn=Matrices.ordenaColumnas(t,ordenReal,ordenDeseado,3);
 Matrices.imprimeTabla(tn);
        */
String[][] m={{"x","b","c"},
     {"y","y","z"},
     {"x","v","w"}};

String[] fila={"23","56","45"};
 String[][] n={{"23","56"},
      {"56","33"},
      {"77","44"}};


 Matrices.imprimeTabla( Matrices.eliminaColumna(m,0));
 Matrices.imprimeTabla( Matrices.ordena(m,0));
 String[] lista={"x","5253"};
 String[] lista1={"x","5253"};
  String[] lista3={"x","5253","45"};
 Vector v=new Vector();
 System.out.println(Matrices.miembro(lista,v));
 v.addElement(lista1);
System.out.println(Matrices.miembro(lista,v));
 //System.out.println(lista. lista1);
// Matrices.imprimeTabla( Matrices.insCol(m,lista3,3));

 Matrices.imprimeTabla(aumenta(m,lista3,2,5));
  Matrices.imprimeTabla(concatena(m,n));
Matrices.imprimeTabla(insertaFila(m,fila,3));
/*
double[][] tabla={{0,0.8,0.8,1},
                  {1,0.1,0.0,0},
                  {0,0.1,0.2,0},
                  {1e-7,0.0,0.0,0},
              };
*/

double[][] tabla={{1,0,0,0},
                  {0,1,0,0},
                  {0,0,1,0},
                  {0,0,0,1},
              };

Matrices.imprimeTabla(Matrices.intercambiaCeros(tabla));

  }



}