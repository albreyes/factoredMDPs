package utileria;

import java.io.*;

public class fileInput
{
   DataInputStream dis;
   boolean eof;

   public fileInput(String fileName) {
     open(fileName);
   }

   public void open(String fileName) {
      try {
         dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName),128));
      }
      catch ( IOException e ) {
         System.err.println("No se pudo abrir archivo " + e.toString());
         System.exit( 1 );
      }
   }

   public String readln() {
      String line = new String();
      try {
         line = dis.readLine();
         eof = (line == null);
       }
      catch ( IOException e ) {
         System.err.println("Error en archivo de entrada.");
         System.exit( 1 );
      }
      return line;
   }

   public void close() {
      try {
         dis.close();
      }
      catch ( IOException e ) {
         System.err.println("Error en archivo de entrada.");
         System.exit( 1 );
      }

   }

} // class fileInput
