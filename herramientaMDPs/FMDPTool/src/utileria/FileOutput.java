package utileria;

import java.io.FileOutputStream;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class FileOutput {

  FileOutputStream fo;

  public FileOutput(String archivo) {
    try{
      fo = new FileOutputStream(archivo);
    }
    catch(Exception exc){}

  }

  public void writeStringln(String dato){
    dato+="\n";
    try{
      fo.write(dato.getBytes());
  }
    catch(Exception exc){}
  }

  public void write(String dato){

    try{
      fo.write(dato.getBytes());
  }
    catch(Exception exc){}
  }



  public void close(){
    try{
      fo.close();
  }
    catch(Exception exc){}
  }

}
