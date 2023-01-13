package utileria;

import javax.swing.JOptionPane;
import java.util.StringTokenizer;
import java.util.Vector;
import utileria.Listas;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Spudd {

  private ServerTCP server;
  private  ClientTCP client;
  private String[][] acciones, variables;
  public boolean conectado=false;

  public Spudd(String host, int clientPort, int serverPort, String actionFile,
               String varFile) {
    if (JOptionPane.showConfirmDialog(null,
        "Ya le indico al cliente de Spudd la direccion local ?") == 0) {
      client = new ClientTCP(host, "" + clientPort);
      server = new ServerTCP(serverPort);
      acciones = tablas.fileToMatrix(actionFile, "\t");
      variables = tablas.fileToMatrix(varFile, "\t");
      conectado=true;
    }
  }

  public Spudd(String host, int clientPort, int serverPort) {
    if (JOptionPane.showConfirmDialog(null,
        "Ya le indico al cliente de Spudd la direccion local ?") == 0) {
      client = new ClientTCP(host, "" + clientPort);
      server = new ServerTCP(serverPort);
      conectado=true;
    }
  }

  public Spudd(String actionFile, String varFile) {

    if (JOptionPane.showConfirmDialog(null,
        "Ya le indico al cliente de Spudd la direccion local ?") == 0) {
      client = new ClientTCP("10.49.147.145", "5510");
      server = new ServerTCP(5400);
      acciones = tablas.fileToMatrix(actionFile, "\t");
      variables = tablas.fileToMatrix(varFile, "\t");
      conectado=true;
    }
  }

  public Spudd() {
    if (JOptionPane.showConfirmDialog(null,
        "Ya le indico al cliente de Spudd la direccion local ?") == 0) {
      client = new ClientTCP("200.0.113.227", "5510");
      server = new ServerTCP(5400);
      conectado=true;
    }
  }

  public Spudd(String host) {
    if (JOptionPane.showConfirmDialog(null,
        "Ya le indico al cliente de Spudd la direccion local ?") == 0) {
      client = new ClientTCP(host, "5510");
      server = new ServerTCP(5400);
      conectado=true;
    }
  }

  public void desconectar(){
    client.shutdown();
  }

  String[] stringToArray(String s){

    StringTokenizer st=new StringTokenizer(s,",");
    String[] resp=new String[st.countTokens()];

    while(st.hasMoreTokens())
    for(int i=0; st.hasMoreTokens(); i++)
      resp[i]=st.nextToken().trim();
    return resp;
  }

  public int politica(int[] estado){
    server.send(estado);
    return client.receiveIntSpecial();
  //return client.receiveByte();
  }



  public String[] politicaString(int[] estado){
    server.send(estado);
    return getAction(client.receiveInt());
  }

  // devuelve un arreglo con las acciones optimas
  public String[] politica(int[] estado, String[] acciones){
    return politica(acciones,politica(estado));
  }

  // no estoy muy seguro si conservar este metodo
  public static  String ajustaBinario(int entero, int tamano){
    String bin=Integer.toBinaryString(entero);

    // si el dato es neg y mayor que tamano -> truncar
      if(entero<0 && bin.length()>tamano)
        bin=bin.substring(bin.length()-tamano,bin.length());

    return bin;
  }

  public static String[] politica(String[] acciones,int accion){

    acciones=Listas.reverse(acciones);
    Vector v=new Vector();
    String binario=Integer.toBinaryString(accion);
   // System.out.println("entero: "+accion+"  binario: "+binario);
    System.out.println("accion: "+accion);

    String[] accionesN=new String[binario.length()];

    for(int i=acciones.length-1,j=binario.length()-1;i>=acciones.length-binario.length();i--,j--)
      accionesN[j]=acciones[i];

   // Listas.imprimeLista(acciones);

    for(int a=binario.length()-1;a>=0;a--){
     // System.out.println("caracter "+binario.charAt(a)+" en pos "+a);
      if(binario.charAt(a)=='1')
        v.addElement(accionesN[a]);
       // v.insertElementAt(accionesN[a],0);
    }
    if(v.isEmpty()) v.addElement("No se encontro recomendacion");

    return Listas.stringVectorToStringArray(v);
  }


  public String[] getAction(int action){
    return stringToArray(getData(action, acciones));
  }

  public int getActionIndex(String action){
    return getIndex(action, acciones);
  }

  public String getVar(int variable) {
    return getData(variable, variables);
  }

  public int getVarIndex(String variable){
    return getIndex(variable, variables);
  }

  private String getData(int indice, String[][] tabla) {
    String s = "";
    for (int i = 0; i < tabla.length; i++)
      if (tabla[i][0].equals("" + indice))
      s = tabla[i][1];

    return s;
  }

  private int getIndex(String data, String[][] tabla) {
    String s = "";
    for (int i = 0; i < tabla.length; i++)
      if (tabla[i][1].equals( data))
      s = tabla[i][0];

    return Integer.parseInt(s);
  }

  public static void ejecutar(String comando, String parametros) {

    Runtime c = Runtime.getRuntime();
    Process q = null;

    try {
      if(!parametros.equals(""))
      q = c.exec(comando+" "+parametros);
      else q = c.exec(comando);
    }
    catch (Exception e) {
      System.out.println("Error al ejecutar comando "+comando+" "+parametros);
    }
  }

  public static void main(String[] args){
/*
    Spudd spudd=new Spudd();

    int estado[]={1};

    int p=spudd.politica(estado);
*/

    int p=-13;

    String binario=Integer.toBinaryString(p);
    System.out.println(binario);
        System.out.println(Integer.toBinaryString(243));


   // String[] acciones={"abrir fwv","cerrar fwv","abrir msv", "cerrar msv", "operacion optima"};
   // Listas.imprimeLista(politica(acciones,p) );
  }

}
