package utileria;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Arrays;
import java.util.ArrayList;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Listas {

    public static int promedio(int[] lista){

      int suma=0;
     for (int j = 0; j < lista.length; j++)
       suma += lista[j];
     return suma / lista.length;

    }

    public static double promedio(double[] lista){

      double suma=0;
     for (int j = 0; j < lista.length; j++)
       suma += lista[j];
     return suma / (double)lista.length;
    }


    public static String[] string2Array(String s){
      StringTokenizer st=new StringTokenizer(s);
      int dim=st.countTokens();
      String[] array=new String[dim];
      for(int i=0; i<dim; i++)
        array[i]=st.nextToken();
      return array;
    }

    public static String intArray2String(int[] array){
      String s="";

      for(int i=0; i<array.length; i++)
        s=s+array[i]+"\t";

      s.trim();

      return s;
    }

    public static String[] concatena(String[] lista1, String[] lista2){
      String[] aux=new String[lista1.length+lista2.length];

      for(int i=0;i<lista1.length;i++)
        aux[i]=lista1[i];

      for(int i=lista1.length,j=0;i<aux.length;i++,j++)
        aux[i]=lista2[j];

      return aux;

    }

    public static int[] concatena(int[] lista1, int[] lista2){
      int[] aux=new int[lista1.length+lista2.length];

      for(int i=0;i<lista1.length;i++)
        aux[i]=lista1[i];

      for(int i=lista1.length , j=0 ; i < aux.length ; i++ ,j++)
        aux[i]=lista2[j];

      return aux;

    }

    public static double[] concatena(double[] lista1, double[] lista2){
      double[] aux=new double[lista1.length+lista2.length];

      for(int i=0;i<lista1.length;i++)
        aux[i]=lista1[i];

      for(int i=lista1.length,j=0;i<aux.length;i++,j++)
        aux[i]=lista2[j];

      return aux;

    }

    public static int[] getIndexes(String[] varsEdo, String[] varsCarac){
      int[] aux=new int[varsCarac.length];

      for(int i=0; i<varsCarac.length; i++)
        aux[i]=indice(varsEdo, varsCarac[i]);

        return aux;
    }


    public static boolean esIgual(String[] lista1, String[] lista2){
    boolean igual=true;

    for(int i=0;i<lista1.length;i++)
      if(!lista1[i].equals(lista2[i])){
        igual=false;
        break;
      }

        return igual;

    }

    public static boolean esIgual(int[] lista1, int[] lista2){
    boolean igual=true;

    for(int i=0;i<lista1.length;i++)
      if(lista1[i]!=lista2[i]){
        igual=false;
        break;
      }

        return igual;

    }


    public static double max(double[] a){
     double[] b=new double[a.length];

     for(int i=0; i<a.length;i++)  b[i]=a[i];
     Arrays.sort(b);
     return b[b.length-1];
    }

    public static double min(double[] a){
     double[] b=new double[a.length];

     for(int i=0; i<a.length;i++)  b[i]=a[i];
     Arrays.sort(b);
     return b[0];
    }


    public static int indice(double[] l, double e){
      int ind=-1;
      for(int i=0; i<l.length; i++)
        if(l[i]==e) ind=i;
      return ind;
    }

    public static int indice(String[] l, String e){
      int ind=-1;
      for(int i=0; i<l.length; i++)
        if(l[i].equals(e)) ind=i;
      return ind;
    }


    public static int indice(Object[] l, Object e){
      int ind=-1;
      for(int i=0; i<l.length; i++)
        if(l[i].equals(e)) ind=i;
      return ind;
    }

    public static String[] reverse(String[] lista){
      String[] aux=new String[lista.length];
      for(int i=0,j=aux.length-1; i<aux.length; i++,j--)
        aux[i]=lista[j];

        return aux;
    }

    public static int[] dameElementos(int[] lista, int[] indices){

       int[] v=new int[indices.length];

       for(int i=0, c=0; i<indices.length;i++)
         v[i]=lista[indices[i]];

         return v;
    }
    // entrega una lista de elementos diferentes
    public static String[] diferencia(String[] lista1, String[] lista2){

      Vector v=new Vector();
      Vector v1=new Vector();

      for(int i=0; i<lista1.length; i++)
        if(!miembro(lista1[i],lista2))
        v.addElement(lista1[i]);

    for(int i=0; i<lista2.length; i++)
      if(!miembro(lista2[i],lista1))
      v.addElement(lista2[i]);


      String[] faltantes=stringVectorToStringArray(v);

      return conjunto(faltantes);

    }

    public static int cuentaTokens(String s, String separador){
      StringTokenizer st=new StringTokenizer(s, separador);
      //String cadena;
      int i=0;

        while(st.hasMoreElements()){
        st.nextToken();
        i++;}

    return i;
    }

    public static String[] generaArreglo(String cadena, String separador){

      int longitud=cuentaTokens(cadena,separador);

      String[] arreglo=new String[longitud];
      StringTokenizer reader = new StringTokenizer (cadena,separador);
      //String s;

      int pos=0;
      while(reader.hasMoreTokens()){
        arreglo[pos] = reader.nextToken().trim();
        pos++;

      }
      return arreglo;
    }

    public static int[] stringVectorToIntArray(Vector v){
      int size=v.size();
      int[] arreglo=new int[size];

      for(int i=0;i<size;i++)
        arreglo[i]=Integer.parseInt((String)v.elementAt(i));
      return arreglo;
    }

    public static String[] stringVectorToStringArray(Vector v){
      int size=v.size();
      String[] arreglo=new String[size];

      for(int i=0;i<size;i++)
        arreglo[i]=(String)v.elementAt(i);
      return arreglo;
    }


    public static int ocurrencias(String s, String [] lista){
      int count = 0;

      for (int i = 0; i < lista.length; i++)
        if(s.equals(lista[i])) count++;
      return count;
    }

    public static boolean miembro(String s, String [] lista){
      boolean ocurre = false;

      for (int i = 0; i < lista.length; i++)
        if(s.equals(lista[i])) ocurre=true;
      return ocurre;
    }

    public static boolean miembro(Object s, Vector v){
      boolean ocurre = false;

      for (int i = 0; i < v.size(); i++)
        if(s.equals(v.elementAt(i))) ocurre=true;
      return ocurre;
    }

    public static boolean miembro(int entero, int [] lista){
      boolean ocurre = false;

      for (int i = 0; i < lista.length; i++)
        if(entero==lista[i]) ocurre=true;
      return ocurre;
    }

//imprime el contenido de una lista de enteros
   public static void imprimeLista(int[] lista){
    for(int i=0; i<lista.length; i++)
      System.out.print(lista[i]+"  ");
      System.out.println("");
   }

// copy un objeto arreglo en otro. devuelve el otro
   public static String[] copy(String[] lista){
     String[] listaN=new String[lista.length];
     for(int i=0; i<lista.length; i++)
       listaN[i]=lista[i];

      return listaN;
   }

 // copy un objeto arreglo en otro. devuelve el otro
    public static int[] copy(int[] lista){
      int[] listaN=new int[lista.length];
      for(int i=0; i<lista.length; i++)
        listaN[i]=lista[i];

       return listaN;
    }

    // copy un objeto arreglo en otro. devuelve el otro
       public static double[] copy(double[] lista){
         double[] listaN=new double[lista.length];
         for(int i=0; i<lista.length; i++)
           listaN[i]=lista[i];

          return listaN;
       }

 //imprime el contenido de una lista de doubles
    public static void imprimeLista(double[] lista){
     for(int i=0; i<lista.length; i++)
       System.out.print(lista[i]+"  ");
       System.out.println("");
    }


//imprime el contenido de una lista de objetos
   public static void imprimeLista(Object[] lista){
    for(int i=0; i<lista.length; i++)
      System.out.print(lista[i]+"  ");
      System.out.println("");
   }

 //imprime el contenido de una lista de objetos
    public static void escribeLista(Object[] lista, FileOutput fo){
     for(int i=0; i<lista.length; i++)
      fo.write(lista[i]+"  ");
      fo.writeStringln("");
    }


 //convierte una lista de Strings a doubles
    public static double[] stringToDouble(String[] lista){
      double[] listaOut=new double[lista.length];
     for(int i=0; i<lista.length; i++)
       listaOut[i]=Double.parseDouble(lista[i]);
       return listaOut;
    }

    //convierte una lista de double a String
    public static String[] doubleToString(double[] lista){
      String[] listaOut=new String[lista.length];
     for(int i=0; i<lista.length; i++){
       listaOut[i]=String.valueOf(lista[0]);
      System.out.println("String: " + lista[i]);}
       return listaOut;
    }

    

    //convierte una lista de Strings a doubles
       public static double[] intToDouble(int[] lista){
         double[] listaOut=new double[lista.length];
        for(int i=0; i<lista.length; i++)
          listaOut[i]=lista[i];
          return listaOut;
       }


    //convierte una lista de Strings a doubles
       public static int[] stringToInt(String[] lista){
         int[] listaOut=new int[lista.length];
        for(int i=0; i<lista.length; i++)
          listaOut[i]=Integer.parseInt(lista[i]);
          return listaOut;
       }

   //convierte una lista de Strings a String
      public static String toString(String[] lista){
       // int[] listaOut=new int[lista.length];
       String texto="";
       for(int i=0; i<lista.length; i++)
         //listaOut[i]=Integer.parseInt(lista[i]);
      texto=texto+lista[i];
         return texto;
      }


 //imprime el contenido de una lista de objetos
    public static void imprimeLista(boolean[] lista){
     for(int i=0; i<lista.length; i++)
       System.out.print(lista[i]+"  ");
       System.out.println("");
    }


 //imprime el contenido de un vector de objetos
    public static void imprimeLista(Vector lista){
     for(int i=0; i<lista.size(); i++)
       System.out.print(lista.elementAt(i)+"  ");
       System.out.println("");
    }

    // inserta un elemento al frente
    public static Object[] inserta(Object o, Object[] lo){
      Object[] loN=new Object[lo.length+1];
      loN[0]=o;
      for(int i=1;i<loN.length;i++)
        loN[i]=lo[i-1];

        return loN;
    }
    // inserta un elemento al final
    public static int[] inserta(int o, int[] lo){
      int[] loN=new int[lo.length+1];
      loN[loN.length-1]=o;
      for(int i=0;i<loN.length-1;i++)
        loN[i]=lo[i];

        return loN;
    }


    // inserta un elemento al frente
    public static String[] inserta(String o, String[] lo){
      String[] loN=new String[lo.length+1];
      loN[0]=o;
      for(int i=1;i<loN.length;i++)
        loN[i]=lo[i-1];

        return loN;
    }


   // elimina un elemento de la lista
    public static String[] reduceLista(String[] lista, int elemento){
      String[] listaNueva=new String[lista.length-1];
      int pos=0;

      for(int i=0; i<lista.length; i++)
        if(i!=elemento){
          listaNueva[pos]=lista[i];
          pos++;
        }
      return listaNueva;
    }

    public static double cuentaCeros(double[] lista){
      int counter=0;
      for(int i=0; i<lista.length; i++)
        if(lista[i]==0) counter++;

      return counter;
    }

    // vuelve una lista en conjunto
    public static String[] conjunto(String[] lista){
      Vector v=new Vector();
      for(int i=0; i<lista.length; i++)
        if(!miembro(lista[i],v)) v.add(lista[i]);

        return stringVectorToStringArray(v);
    }

    // elimina la lista de enteros elemento de la lista de enteros lista
    public static int[] eliminaElementos(int[] elemento, int[] lista){
      for(int i=0; i<elemento.length; i++)
        lista=eliminaElemento(elemento[i],lista);

        return lista;
    }

    // elimina un elemento entero de una lista de enteros
    public static int[] eliminaElemento(int elemento, int[] lista){
      ArrayList al=new ArrayList();

      for(int i=0; i<lista.length; i++)
        if(!(lista[i]==elemento)){
          al.add(new Integer(lista[i]));
        }
          return toIntArray(al);
    }

    
    // elimina un elemento entero de una lista de enteros
    public static int[] eliminaElem(int elemento, int[] lista){
      ArrayList al=new ArrayList();

      for(int i=0; i<lista.length; i++)
        if(!(i==elemento)){
          al.add(new Integer(lista[i]));
        }
          return toIntArray(al);
    }    
    
    // transforma un lista arreglo de Integers en un arreglo de ints
    public static int[] toIntArray(ArrayList al) {
      int[] resp = new int[al.size()];
      for (int i = 0; i < resp.length; i++)
        resp[i] = ( (Integer) al.get(i)).intValue();
      return resp;
    }


    public static void main(String[] args){
      /*
      String[] l1={"2","4","6","4"};
      String[] l2={"2","4","7","4"};

      int[] li1={2,4,7,4};
      int[] li2={2,4,7,7};

      imprimeLista(concatena(l1,l2));
      System.out.println(esIgual(l1,l2));
      System.out.println(esIgual(li1,li2));

      System.out.println(indice(l1,"4"));
      // ejemplo de caracteristicas
      String[] l3={"6","4","10"};
      imprimeLista(getIndexes(l1,l3));
      System.out.println("diferencias");
      imprimeLista(diferencia(l3,l1));
*/

  int[] li1={};
  int[] li2={4};
   int[] li3={3,5,6};

      li1=concatena(li1,li2);
      li1=concatena(li1,li3);
      imprimeLista(li1);
    }

}
