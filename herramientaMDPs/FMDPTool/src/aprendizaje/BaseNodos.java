package aprendizaje;

import java.io.*;
import utileria.Matrices;


/**
 * <p>Title: BaseNodos</p>
 * <p>Description: Base de Nodos para archivo de aprendizaje con Elvira</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class BaseNodos {

  String nombreBaseNodos;
  String numeroCasos;
  Nodos n[];
  String[][] ejemplos;

  // este constructor pide informacion de cada nodo directamente
  // al usuario a traves de consola. Solo se especifica el nombre
  // de la base de nodos, el numero de nodos, y el numero de casos

  public BaseNodos(String nombreBaseNodos, int noNodos, int noC) {

    this.nombreBaseNodos="data-base "+nombreBaseNodos;
    numeroCasos="number-of-cases = "+noC+";";
    try{
      leeNodos(noNodos); // aqui se instancia el arreglo n
  }
  catch(IOException ioe){}
  }

  //
  public BaseNodos(String nombreBaseNodos, String ejemplos[][], ValorDiscreto[] vd) {

    this.nombreBaseNodos="data-base "+nombreBaseNodos;
    numeroCasos="number-of-cases = "+(ejemplos.length-1)+";";
    this.ejemplos=ejemplos;
    generaNodos(vd); // aqui se instancia el arreglo n
  }

  public BaseNodos(String nombreBaseNodos, String ejemplos[][], String[][] vd) {

    this.nombreBaseNodos="data-base "+nombreBaseNodos;
    numeroCasos="number-of-cases = "+(ejemplos.length-1)+";";
    this.ejemplos=ejemplos;
    generaNodos(vd); // aqui se instancia el arreglo n
  }

  // variables discretas o continuas
  public void generaNodos(String[][] vd){

    n=new Nodos[vd.length];

    for(int i=0;i<n.length;i++)
       n[i]=new Nodos(vd[i]);
 }

 // este hace lo mismo que el de arriba pero con otros parametros
  public void generaNodos(ValorDiscreto[] vd){

    n=new Nodos[vd.length];

    for(int i=0;i<n.length;i++)
       n[i]=new Nodos(vd[i].atributo, vd[i].noPartes);
  }

  // este lo vamos a generar a partir de los datos del archivo de atributos
  public void generaNodos(String attributeFile){
/*
    n=new Nodos[vd.length];

    for(int i=0;i<n.length;i++)
       n[i]=new Nodos(vd[i].atributo, vd[i].noPartes);*/
  }


  public void leeNodos(int noNodos) throws IOException{
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    String id="";
    String desc="";
    int noE=0;
    n=new Nodos[noNodos];
    for(int i=0;i<noNodos;i++){
       System.out.println("Datos para el Nodo "+i);
       System.out.print("DESCRIPCION: ");
       desc=stdIn.readLine();
       System.out.print("ID: ");
       id=stdIn.readLine();
       System.out.print("NUMERO DE VALORES DISCRETOS: ");
       noE=Integer.parseInt(stdIn.readLine());
       System.out.println();
       n[i]=new Nodos(id, desc, noE);
     }
  }
  public String getDatos(){
    String out = nombreBaseNodos + "{\n\n" +
         numeroCasos + "\n\n" +
        "// Network Variables\n\n" +
        datosNodos() +
        "\n}";
  return out;
  }

  public String getDatosCompletos(){
    String out = nombreBaseNodos + "{\n\n" +
         numeroCasos + "\n\n" +
        "// Network Variables\n\n" +
        datosNodos() + "\n"+
        "\nrelation {\n\nmemory = true;\n\ncases = (\n\n"+
        formateaTabla()+");\n}"+
        "\n}";
  return out;
  }
/*
  public String getDatosCompletos(String attributeFile){
    String out = nombreBaseNodos + "{\n\n" +
         numeroCasos + "\n\n" +
        "// Network Variables\n\n" +
        datosNodos(attributeFile) + "\n"+
        "\nrelation {\n\nmemory = true;\n\ncases = (\n\n"+
        formateaTabla()+");\n}"+
        "\n}";
  return out;
  }

*/
  private String formateaTabla(){
    // como es cuadrada la tabla entonces
    String[][] s=new String[ejemplos.length-1][ejemplos[0].length];

    for(int i=1;i<ejemplos.length;i++)
      for(int j=0;j<ejemplos[i].length;j++)
        if(j<ejemplos[i].length-1)
        s[i-1][j]=ejemplos[i][j]+",";
        else s[i-1][j]=ejemplos[i][j];

    for(int i=0;i<s.length;i++){
      s[i][0]="["+s[i][0];
      s[i][s[i].length-1]=s[i][s[i].length-1]+"]";
    }

  return Matrices.tablaToString(s);
  }

/*
  private String datosNodos(String attributeFile){
    // se extrae la informacion de cada variable de un arreglo de nodos
    // el truco es formar correctamente esta base de nodos durante la construccion
    String cadena="";
    for(int i=0;i<n.length;i++)
      if(i<n.length-1)
      cadena+=n[i].getDatos()+"\n\n";
      else cadena+=n[i].getDatos();
    return cadena;
  }
*/

  private String datosNodos(){
    String cadena="";
    for(int i=0;i<n.length;i++)
      if(i<n.length-1)
      cadena+=n[i].getDatos()+"\n\n";
      else cadena+=n[i].getDatos();
    return cadena;
  }
}