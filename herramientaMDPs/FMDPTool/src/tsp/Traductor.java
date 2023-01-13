package tsp;
import java.io.*;
import java.io.FileNotFoundException;
import java.lang.String;

/**
 * <p>Title: Traductor</p>
 * <p>Description: Obtiene un archivo MDPSPUDD y lo traduce a MDPSPI</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Instituto de Investigaciones El�ctricas</p>
 * @author: Christian Guti�rrez Bravo.
 * @version 1.0
 */

public class Traductor {

  /*Declaraciones */
  File name;
  /*String currFileName = null;  // Full path with filename. null means new/untitled.*/
  String currFileName = null;
  String fMDPspi;
  String fMDPspudd;
  String MDPspi;
  String MDPspudd;

   boolean dirty = false;



  public Traductor() {

  }

  public void traduceMDP(String MDPin,String MDPout)
   {
     /*Realiza la traducci�n, se crean objetos de
      la clase analizer y parser para realizarla*/
     /*Inicializando*/
     Analizador t;
     Parser a;

     /*El archivo abierto se asigna a un String global
      y enseguida a cadena*/
     String cadena = new String();

     OpenSPI(MDPin);
     cadena = MDPspi;
     /*Se crea un objeto analizer para poder analizar el
      String*/
     t = new Analizador(cadena);
     t.analizer(cadena);

      /*Si el an�lisis fue correcto se realiza
     la traducci�n*/
     if(t.verific_complete())
    {
      /*Se crea un nuevo objeto parser*/
     a = new Parser(t.memoria);
     a.parser(t.memoria);
     /*El resultado se asigna al String global MDPspudd*/
     MDPspudd = a.MDPspudd;

     /*Antes de guardar el archivo se asigna el nombre y ruta
      donde ser� creado el archivo*/
     currFileName = MDPout;

     /*Ahora es guardada la especificac�n para SPUDD*/
     saveSPUDD();
   }
  }

  boolean saveSPUDD() {
    try
    {
      // Abrir archivo y asignar rl nombre*/
      File file = new File (currFileName);

      // Creando la salida del archivo
      FileWriter out = new FileWriter(file);
      String text = MDPspudd;
      out.write(text);
      out.close();
      this.dirty = false;
      return true;
    }

    /*Se puede asignar alguna excepci�n para detectar que no se guardo el
     archivo*/
    catch (IOException e) {

    }
    return false;

  }


  /*Reutilizacion c�digo */
  void OpenSPI(String SPIname) {
      openFile(SPIname);
    }


  /*Reutilizacion de c�digo*/
// Abriendo el archivo a convertir
  void openFile(String fileName)  {
      try
      {
        // Creando un objeto File para abrir el archivo
        File file = new File(fileName);

        // Obteniendo el tama�o del archivo
        int size = (int)file.length();

        // Iniciando en el caracter a leer 0
        int chars_read = 0;

        // Creando una entrada de archivo a leer
        FileReader in = new FileReader(file);

        // Creando un buffer para obtener el archivo
        char[] data = new char[size];

        // Leyendo caracteres
        while(in.ready()) {
          // Avnzando el contador y obteniendo los caracteres en el buffer
          chars_read += in.read(data, chars_read, size - chars_read);
        }
        in.close();
        /*Ahora se asigna el arhcivo leido a un String*/
        MDPspi = new String(data, 0, chars_read);
           }
      /*Se puede anexar una excepci�n en caso de que el archivo no pueda abrir*/
           catch (IOException e)
      {

      }

    }





}