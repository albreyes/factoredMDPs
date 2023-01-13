package utileria;

import java.io.*;
import java.awt.*;

public class FileOutput01
{
    DataOutputStream fos;

   public FileOutput01(String fileName) {
     open(fileName);
   }

   public void open(String fileName) {
      try {
         fos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName),128));
      }
      catch ( IOException e ) {
         System.err.println("No se pudo abrir archivo " + e.toString());
         System.exit( 1 );
      }
   }

   public void write(String line) {
      try {
         fos.writeBytes( line );
      }
      catch ( IOException e ) {
         System.err.println("Error en salida.");
         System.exit( 1 );
      }
   }

   public void writeln(String line) {
      try {
         fos.writeBytes( line );
         fos.writeBytes( "\n" );
      }
      catch ( IOException e ) {
         System.err.println("Error en salida.");
         System.exit( 1 );
      }
   }

   public void close() {
      try {
         fos.close();
      }
      catch ( IOException e ) {
         System.err.println("Error en salida.");
         System.exit( 1 );
      }

   }

} // class FileOutput01