package utileria;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Tec-IIE</p>
 * @author Alberto Reyes
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/**
   Clase para leer strings y numeros en la entrada
   Esta clase es adecuada para programadores principiantes de Java
   Esta construye los lectores con buffers necesarios
   maneja excepciones I/O , y convierte strings a numeros
*/

public class ConsoleReader
{
  private BufferedReader reader;

        /**
      Construye un lectore de consola para un flujo de entrada
      tal como System.in
      @param inStream como un flujo de entrada (instream)
   */
   public ConsoleReader(InputStream inStream)
   {  reader = new BufferedReader
         (new InputStreamReader(inStream));
   }

   /**
      Lee una linea de una entrada y la convierte a un entero.
      @return el entero que se teclee
   */
   public int readInt()
   {  String inputString = readLine();
      int n = Integer.parseInt(inputString);
      return n;
   }

   /**
      Lee una linea de entrada y la convierte a un float
      @return el numero que el usuario teclee
   */
   public double readDouble()
   {  String inputString = readLine();
      double x = Double.parseDouble(inputString);
      return x;
   }

   /**
      Lee una linea de entrada. Maneja excepciones IOException,

      @return la linea que se teclee

   */
   public String readLine()
   {  String inputLine = "";

      try
      {  inputLine = reader.readLine();
      }
      catch(IOException e)
      {  System.out.println(e);
         System.exit(1);
      }

      return inputLine;
   }

}