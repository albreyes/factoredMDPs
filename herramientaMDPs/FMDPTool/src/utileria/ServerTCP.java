package utileria;

import java.lang.System;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import utileria.Listas;


public class ServerTCP {

 ServerSocket server; // el server
 Socket client; // el cliente
 BufferedReader inStream; // flujo de entrada para texto
 DataOutputStream outStream; // flujo de salida para datos
 DataInputStream inputStreamBytes; // flujo de entrada para datos
 int PuertoLocal, PuertoDestino;


 public ServerTCP(int port) {
   try {

     server = new ServerSocket(port); //Crea un objeto tipo servidor
     PuertoLocal = server.getLocalPort(); //Se obtine el puerto del servidor

     System.out.println("ServerSoquet esta escuchando en el puerto " +
                        PuertoLocal + ".");


     //Espera que un cliente se conecte y hace copia de su objeto en client
     client = server.accept();
     //Obtiene el identificador del cliente (nombre PC)
     String NombreDestino = client.getInetAddress().getHostName();
     //Obtiene el puerto del cliente
     PuertoDestino = client.getPort();
     System.out.println("Conexion aceptada por " + NombreDestino +
                        " en el puerto " + PuertoDestino + ".");


     //Prepara medios de entrada y salida segun enlace con el usuario
     inStream = new BufferedReader(new InputStreamReader(client.
         getInputStream()));
     outStream = new DataOutputStream(client.getOutputStream());
     inputStreamBytes=new DataInputStream(client.getInputStream());

   }
   catch (IOException ex) {
     System.out.println("Error durante la conexion con el cliente");
   }
 }

 public void close() {
   try {
     server.close();
   }
   catch (IOException ex) {
     System.out.println("No se pudo cerrar la conexion");
   }
 }

 public void testChat() {

   String inLine, outLine;
   boolean finished = false;
   Thread t=new Thread();

   try {
     ConsoleReader Cad = new ConsoleReader(System.in);
     while (!finished) {
       inLine = Recibe(); //Recibe o espera un mensaje del cliente
       System.out.print("Enviando: ");
       outLine = Cad.readLine();
       Envia(outLine); //Envia Mensaje de nuevo
       t.sleep(10000);
       if (inLine.equalsIgnoreCase("q") || outLine.equalsIgnoreCase("q")) { //Checa que el mensaje no sea de desconexion
         finished = true;
         System.out.print("Fin de conexion");
       }
     }
     inStream.close();
     outStream.close();
     client.close();
     server.close();
   }
   catch (InterruptedException ie) {}
   catch (IOException ioe) {}
 }


public void testStrings() {
  Thread t=new Thread();
  int[] inLine;
  String outLine;
  boolean finished = false;
  try {
    //ConsoleReader Cad = new ConsoleReader(System.in);
    while (!finished) {
      inLine = recibeArrayInt(8); //Recibe o espera un mensaje del cliente
      for(int i=0; i<inLine.length; i++)
        System.out.print(""+inLine[i]);
        System.out.print("Enviando: ");
     // outLine = Cad.readLine();
      enviaInt(inLine[0]); //Envia Mensaje de nuevo
      t.sleep(500);
      if (inLine[0]==-1) { //Checa que el mensaje no sea de desconexion
        finished = true;
        System.out.print("Fin de conexion");
      }
    }
    inStream.close();
    outStream.close();
    client.close();
    server.close();
  }
  catch (InterruptedException ie) {}
  catch (IOException ioe) {}
}


//Metodo que recibe una cadena de otra computadora (usuario)
public String Recibe() {
  String Mensaje = "";
  try {
    Mensaje = inStream.readLine(); //Espera que se envie algo el cliente
    System.out.println("Recibiendo: " + Mensaje); //Despliega en consola mensaje del cliente
  }
  catch (IOException ex) {
    System.out.println("Error al recibir la cadena");
  }
  return Mensaje;
}

//Metodo que recibe una cadena de otra computadora (usuario)
// en el cliente envia debe usar writeInt
public int recibeInt0() {
  int Mensaje=-1;
  try {
    Mensaje = inputStreamBytes.readShort(); //Espera que se envie algo el cliente
    //System.out.println("Recibiendo: " + Mensaje); //Despliega en consola mensaje del cliente
  }
  catch (IOException ex) {
    System.out.println("Error al recibir la cadena");
  }
  return Mensaje;
}

//Metodo que recibe una cadena de otra computadora (usuario)
// en el cliente envia debe usar writeInt
public int recibeByte() {
  int Mensaje=-1;
  try {
    Mensaje = inputStreamBytes.readByte(); //Espera que se envie algo el cliente
    //System.out.println("Recibiendo: " + Mensaje); //Despliega en consola mensaje del cliente
  }
  catch (IOException ex) {
    System.out.println("Error al recibir la cadena");
  }
  return Mensaje;
}

// recibe un entero desde spudd
  public int receiveIntSpecial() {

    byte[] datos={-1,-1,-1,-1,-1,-1,-1,-1};
    try {
      inputStreamBytes.read(datos);
    }

    catch (IOException ex) {
      System.out.println("Error reading from keyboard or socket");
    }
    return datos[0];
  }


//Metodo que recibe una cadena de otra computadora (usuario)
public int[] recibeArrayInt(int length) {
  int[] arreglo=new int[length];

    for(int i=0,j=0; i<length*4; i++){
      int aux=recibeByte();
      if(i%4==0)
      arreglo[j++]=aux;
    }
  return arreglo;
}

//Metodo que envia una cadena al usuario
private void Envia(String cad) {
  try {
    outStream.writeBytes(cad); //Envia mensaje usando el objeto de salida
    outStream.write(13); //Manda fin de carro y salto de linea
    outStream.write(10);
    outStream.flush(); //Forza el envio de datos
  }
  catch (IOException ex) {
    System.out.println("IOException occurred.");
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


// funciona perfecto con Spudd
public void send(int enteros[]) {

  byte datos[] = new byte[enteros.length * 4];
  for (int i = 0, j = 0; i < enteros.length; i++) {
    datos[j++] = (byte) (0xff & enteros[i]);
    datos[j++] = (byte) (0xff & (enteros[i]) >> 8);
    datos[j++] = (byte) (0x00);
    datos[j++] = (byte) (0x00);
  }
  try {
    //outStream.write(datos, 0, datos.length);
    outStream.write(datos);
    outStream.flush();

/*
    System.out.print("dato enviado: ");
    for (int i = 0; i < enteros.length; i++)
      System.out.print(enteros[i]);
      System.out.println();
*/
  }

  catch (IOException ex) {
    ex.getMessage(); // error("Error sending from socket");
  }
}

// el cliente recibe con readInt
public void enviaInt(int dato) {

  byte datos[] = new byte[4];
  int j=0;
 // for (int i = 0, j = 0; i < datos.length; i++) {
    datos[j++] = (byte) (0xff & dato);
    datos[j++] = (byte) (0x00);
    datos[j++] = (byte) (0x00);
    datos[j++] = (byte) (0x00);
//  }
  try {
    //outStream.write(datos, 0, datos.length);
    outStream.write(datos);
    outStream.flush();
  }

  catch (IOException ex) {
    //error("Error sending from socket");
  }
}

public static void main(String[] args){
 // ServerTCP server=new ServerTCP(6665);
 ServerTCP server=new ServerTCP(5400);
// server.
  //server.testChat();
/*
  int[] dato=server.recibeArrayInt(4);
  System.out.print("servidor recibiendo: ");
  Listas.imprimeLista(dato);
  System.out.print("servidor enviando: "+dato[2]);
  server.enviaInt(dato[2]);

        */
}

}