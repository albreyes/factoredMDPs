package utileria;

import java.io.*;
/**
 * <p>Title: Sistema Inteligente de Planificacion</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Alberto Reyes Ballesteros
 * @version 1.0
 */

public class ESObjetos {
    // Object serialization
  public static void escribeObjeto(Object objeto, String nombreArchivo) {

    try {
      //System.out.println("object1: " + objeto);
      FileOutputStream fos = new FileOutputStream(nombreArchivo);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(objeto);
      oos.flush();
      oos.close();
    }
    catch(Exception e) {
      System.out.println("Exception during serialization: " + e);
      System.exit(0);
    }
  }

    // Object deserialization
  public static Object leeObjeto(String nombreArchivo) {
        Object object2=null;
      try {

        FileInputStream fis = new FileInputStream(nombreArchivo);
        ObjectInputStream ois = new ObjectInputStream(fis);
        object2 = ois.readObject();
        ois.close();
        //System.out.println("object2: " + object2);

      }
      catch(Exception e) {
        System.out.println("Exception during deserialization: " + e);
        System.exit(0);
      }
                    return object2;
  }
}