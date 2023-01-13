package utileria;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Tec-IIE</p>
 * @author Alberto Reyes
 * @version 1.0

*/

import java.lang.System;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.*;
import java.util.*;
import utileria.Listas;
import ia.FMDP;

/**
* La clase ClientTCP contiene los metodos necesarios
* para intercambiar datos con un servidor TCP/IP dados
* el IP del host y un puerto de conexion. Nota:
* el puerto debe estar abierto para que exista comunicacion
*@author Alberto Reyes Ballesteros
*@version 1.0
*/

public class ClientTCP {

 Socket connection;
 DataOutputStream outStream;
 BufferedReader inStream;
 DataInputStream dataInStream;

String destination;
int port=0;

/** Este constructor  recibe el host y el puerto
* en 2 parametros
*@param destination nombre del host o dir IP
*@param portSt numero de puerto en texto
*/

 public ClientTCP(String destination, String portSt) {

   this.destination=destination;

   // cambia de formato
   try { port = Integer.valueOf(portSt).intValue(); }
   catch (NumberFormatException ex) {
     error("Invalid port number");
   }
   createSocket();
 }

 /************************************************************
  cierra la conexion con el host
  */

 private void createSocket() {

   // crea el socket
   try {
     connection = new Socket(destination, port);

   }
   catch (UnknownHostException ex) {
     error("Unknown host");
   }
   catch (IOException ex) {
     error("IO error creating socket");
   }
   // crea los streams de entrada y salida
   try {
     inStream = new BufferedReader(
         new InputStreamReader(connection.getInputStream()));
     outStream = new DataOutputStream(connection.getOutputStream());
     dataInStream = new DataInputStream(connection.getInputStream());

   }
   catch (IOException ex) {
     error("IO error getting streams");
   }
 }

 public void shutdown() {
   try {

     //connection.setKeepAlive(true);
     connection.close();
   }
   catch (IOException ex) {
     error("IO error closing socket");
   }
 }

 /*************************************
  METODOS PARA EL ENVIO DE DATOS
  ************************************/

 /** Envia un dato leido desde consola */

 public void send() {

   BufferedReader keyboardInput = new BufferedReader(
       new InputStreamReader(System.in));

   try {
     String sendLine = keyboardInput.readLine();
     send(sendLine);
   }
   catch (IOException ioe) {}
 }



 /** Envia un dato con paso de parametros
  *@param message mensaje a enviar
  */

 public void send(String message) {
   try {
     outStream.writeBytes(message);
     outStream.write(13);
     outStream.write(10);
     outStream.flush();
   }
   catch (IOException ex) {
     error("Error sending from socket");
   }
 }

 public void sendInt(int dato) {
   try {
     outStream.writeInt(dato);
     //outStream.write(13);
     //outStream.write(10);
     outStream.flush();
   }
   catch (IOException ex) {
     error("Error sending from socket");
   }
 }


public void send(int enteros[]) {

  byte datos[] = new byte[enteros.length * 4];
  for (int i = 0, j = 0; i < enteros.length; i++) {
    datos[j++] = (byte) (0xff & enteros[i]);
    datos[j++] = (byte) (0xff & (enteros[i]) >> 8);
    datos[j++] = (byte) (0x00);
    datos[j++] = (byte) (0x00);
  }
  try {
    outStream.write(datos);
    outStream.flush();
 }

  catch (IOException ex) {
    error("Error sending from socket");
  }
}

// funciona perfecto con Spudd
public void send(int entero) {

  byte datos[] = new byte[4];

    datos[0] = (byte) (0xff & entero);
    datos[1] = (byte) (0xff & (entero) >> 8);
    datos[2] = (byte) (0x00);
    datos[3] = (byte) (0x00);

  try {
    outStream.write(datos);
    outStream.flush();
  }

  catch (IOException ex) {
    ex.getMessage();
  }
}



 /*************************************
  METODOS PARA RECIBIR DATOS
  ************************************/

// recibe un entero 32 bits
  public int receiveInt() {
    int inByte=-1;
    try {
      inByte = dataInStream.readInt();
    }

    catch (IOException ex) {
      error("Error reading from keyboard or socket");
    }
    return inByte;
  }


// recibe un entero desde spudd

  public int receiveIntSpecial() {

    int[] datos=new int[4];
    try {

      for(int i=0; i<datos.length; i++)
        datos[i]=dataInStream.readUnsignedByte();
      //  datos[i]=dataInStream.readUnsignedShort();
        //datos[i] = (int) (dataInStream.readInt() & 0xffffffffL);

    }
    catch (IOException ex) {
      error("Error reading from keyboard or socket");
    }
    return datos[0];
  }


/*
  public int receiveIntSpecial() {

  //  byte[] datos={-1,-1,-1,-1,-1,-1,-1,-1};
    byte[] datos={-1,-1,-1,-1};

    try {
    dataInStream.read(datos);
    }

    catch (IOException ex) {
      error("Error reading from keyboard or socket");
    }

    return datos[0];
  }
*/

// recibe un short 16 bits
  public short receiveShort() {
    short inByte=-1;
    try {

      inByte = dataInStream.readShort();
    }

    catch (IOException ex) {
      error("Error reading from keyboard or socket");
    }
    return inByte;
  }


// recibe un byte 8 bits
  public byte receiveByte() {
    byte inByte=-1;
    try {
      inByte = dataInStream.readByte();
    }

    catch (IOException ex) {
      error("Error reading from keyboard or socket");
    }
    return inByte;
  }


 /*************************************************************
 Metodo de consulta de datos. Envia una cadena (comando) y espera
 una respuesta del host.
 */

   public String request(String id){
     send(id);
//    System.out.println("sending: "+id);
     return receiveStr();
     }
/*
     public String request(int id){
       send(id);
//    System.out.println("sending: "+id);
       return receiveStr();

       }
*/
 /*********************************************************
Recibe un dato del host y lo regresa como sting
*/

 public String receiveStr() {
   int inByte;
   String mensaje = "";

   try {
     while ( (inByte = inStream.read()) != '\n')
       mensaje = mensaje + (char) inByte;
   }

   catch (IOException ex) {
     error("Error reading from keyboard or socket");
   }
   mensaje = mensaje.substring(0, mensaje.length() - 1);
   return mensaje;
 }

 /*************************************************************
 Un chat simple
 **************************************************************/

  public void chat(){

   BufferedReader keyboardInput = new BufferedReader(
    new InputStreamReader(System.in));
   boolean finished = false;
   String line;

   do {
    try{

     System.out.print("client-> ");
     line=keyboardInput.readLine();

     System.out.println("host-> "+request(line));
     if(line.equalsIgnoreCase("q")) finished=true;
    }
    catch (IOException ex){
     error("Error reading from keyboard or socket");
    }
   } while(!finished);
  }

  /**********************************************************************
   METODOS DE IMPRESION DE INFORMACION
   **********************************************************************/

  private void error(String s) {
    System.out.println(s);
    System.exit(1);
  }

  /** Este metodo despliega los paramtros del host
  */
   public void displayDestinationParameters() {
     InetAddress destAddress = connection.getInetAddress();
     String name = destAddress.getHostName();
     byte ipAddress[] = destAddress.getAddress();
     int port = connection.getPort();
     displayParameters("Destination ", name, ipAddress, port);
   }

   /** Este metodo despliega los paramtros locales*/

   public void displayLocalParameters() {
     InetAddress localAddress = null;
     try {
       localAddress = InetAddress.getLocalHost();
     }
     catch (UnknownHostException ex) {
       error("Error getting local host information");
     }
     String name = localAddress.getHostName();
     byte ipAddress[] = localAddress.getAddress();
     int port = connection.getLocalPort();
     displayParameters("Local ", name, ipAddress, port);
   }

    /** Este metodo despliega los parametros
  *@param s
  *@param name nombre del host
  *@param ipAddress lista de direcciones IP
  *@param port numero de puerto
  */

    public void displayParameters(String s, String name, byte ipAddress[],
                                  int port) {
      System.out.println(s + "host is " + name + ".");
      System.out.print(s + "IP address is ");
      for (int i = 0; i < ipAddress.length; ++i)
        System.out.print( (ipAddress[i] + 256) % 256 + ".");
      System.out.println();
      System.out.println(s + "port number is " + port + ".");
    }

   public static void main(String[] args){

/* prueba de envios con spudd

     ClientTCP client=new ClientTCP("newton","5510");
     ServerTCP server=new ServerTCP(5400);

     int[] estados = {2,2,2,1,1,1,1,2,2,2,2};
     int[] estados1= {2,2,2,2,2,2,0,2,2,2,0};
     int[] estados2= {2,0,1,2,2,1,1,2,2,2,0};
     int[] estados3= {1,1,1,2,2,2,2,2,0,1,1};

     server.send(estados3);
     int entero=client.receiveInt();
     System.out.println("cliente recibiendo: "+entero);
*/

// prueba de consultas de politica con SPI

//String policyFilename="../ejemplos/Funcion2/fmdp.obj";
String policyFilename="C:\\Users\\Alejandro\\Documents\\Pasantia\\INEEL\\herramienta\\herramientaMDPs\\ejemplos\\Ej6\\fmdp.obj";
FMDP fmdp=FMDP.retrieveMDP(policyFilename);

ClientTCP client=new ClientTCP("localhost","5000");

for(int i=0; i<fmdp.s.length; i++){
  System.out.print("estado ");
  Listas.imprimeLista(fmdp.s[i]);
  client.send(fmdp.s[i]);
  int pol=client.receiveIntSpecial();
  System.out.println("politica: "+pol);
}

   }
}
