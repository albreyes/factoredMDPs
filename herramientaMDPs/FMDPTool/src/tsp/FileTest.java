package tsp;

import java.awt.*;
import java.io.*;
import java.io.RandomAccessFile;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class FileTest extends Frame {
  File name;
  TextField enter;
  TextArea output;

  public FileTest() {

    super("Prueba de la clase File");
    setLayout( new BorderLayout());
    enter = new TextField("Teclee aqui el nombre del archivo o directorio", 40);
    output = new TextArea( 20, 30 );
    add("North", enter );
    add("Center",output );
    resize( 400, 400);
    show();

  }

  public boolean handleEvent( Event e )
  {
    if( e.id == Event.WINDOW_DESTROY){
      hide();
      dispose();
      System.exit( 0 );
          }
          return super.handleEvent( e );

  }

  public boolean action( Event e, Object o )
  {
    output.setText( "" );

    name = new File( o.toString() );

    if( name.exists() ) {
      output.appendText(
          name.getName() + "existe\n" +
          (name.isFile() ? "es un archivo\n " :
                          "no es un directorio\n" ) +
           (name.isAbsolute() ? "es trayectoria absoluta\n":
                           "no es trayectoria absoluta\n") +
            "Ultima modificacion: " + name.lastModified() +
            "\nLongitud:" + name.length() +
            "\nTrayectoria:" + name.getPath() +
            "\nTrayectoria absoluta:" + name.getAbsolutePath() +
            "\nPadre:" + name.getParent());

        if( name.isFile() ) {
         // try{

            // quien sabe que quizo hacer aqui
          //  RandomAccessFile( name, "r" );

            output.appendText("\n\n");

            do{
          //    output.appendText( r.readLine() + "\n" );
              }while(
              //r.getFilePointer()< r.length()
              true
              );
       //   }
        //  catch( IOException e2){

         // }
        }
         else if ( name.isDirectory()){
           String dir[ ] =  name.list();

           output.appendText( "\n\nContenido del directorio:\n");

           for (int i = 0; i< dir.length; i++)
             output.appendText( dir[ i ] + "\n" );

         }

    }

  else{
    output.appendText( o.toString() + " no existe\n" );
  }
    return true;
  }

  public static void main( String args[] )
  {
    FileTest f = new FileTest();
  }



}