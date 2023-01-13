package utileria;

import java.util.StringTokenizer;
import java.util.Vector;
//import dataStructures.Matrices;

/**
 * <p>T�tulo: </p>
 * <p>Descripci�n: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Empresa: Tec-IIE</p>
 * @author Alberto Reyes Ballesteros
 * @version 1.0
 */

public class tablas {

  public static int cuentaRegistros(String filename) {
    fileInput fi = new fileInput(filename);
    String linea;
    int c = 0;

    do {
      linea = fi.readln();
      if (linea != null)
        c++;
    }
    while (linea != null);
    fi.close();
    return c++;
  }

  public static int cuentaCampos(String filename, String separador){

    fileInput fi = new fileInput(filename);
    String cadena=fi.readln();
    StringTokenizer reader = new StringTokenizer (cadena,separador);
    fi.close();
    return reader.countTokens();
  }

  public static String[][] fileToMatrix(String filename, String separador){
    int profundidad = cuentaRegistros(filename);
    String[][] tabla =
        new String[profundidad][cuentaCampos(filename, separador)];
    String cadena;
    fileInput fi = new fileInput(filename);

    for (int i = 0; i < profundidad; i++) {
      cadena = fi.readln();
      tabla[i] = Matrices.generaArreglo(cadena, separador);
    }
    fi.close();
  return tabla;
  }

  public static void matrixToFile(String filename, String[][] matrix){

    FileOutput01 fo = new FileOutput01(filename);

    for (int i = 0; i < matrix.length; i++){
      for(int j=0;j<matrix[i].length;j++)
        if(j<matrix[i].length-1)
        fo.write(matrix[i][j]+"\t");
        else fo.write(matrix[i][j]);

        fo.writeln("");
    }
    fo.close();
  }

  public static void matrixToFile(String filename, double[][] matrix){

    FileOutput01 fo = new FileOutput01(filename);

    for (int i = 0; i < matrix.length; i++){
      for(int j=0;j<matrix[i].length;j++)
        if(j<matrix[i].length-1)
        fo.write(matrix[i][j]+",");
        else fo.write(""+matrix[i][j]);

        fo.writeln("");
    }
    fo.close();
  }

  public static void matrixToFile(String filename, int[][] matrix){

    FileOutput01 fo = new FileOutput01(filename);
    

    for (int i = 0; i < matrix.length; i++){
      for(int j=0;j<matrix[i].length;j++)
        if(j<matrix[i].length-1)
        fo.write(matrix[i][j]+",");
        else fo.write(""+matrix[i][j]);

        fo.writeln("");
    }
    fo.close();
  }

}