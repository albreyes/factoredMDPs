package utileria;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Dialogos {

  public static String dialogoAbrirArchivo(String tituloDialogo, String folderInicial) {
    String filename = "";
    JFileChooser chooser = new JFileChooser(folderInicial);
    chooser.setDialogTitle(tituloDialogo) ;

    int returnVal = chooser.showOpenDialog(new JFrame());
    if (returnVal == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      filename = file.getAbsolutePath();
    }
    return filename;
  }


  public static int panelOpciones() {

    //default icon, custom title
    int n = JOptionPane.showConfirmDialog(
        new JFrame(), "Are you sure you want to exit?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION);

    return n;

  }

  public static String dialogoAbrirDir(String tituloDialogo, String folderInicial) {

    String filename = "";
    JFileChooser chooser = new JFileChooser(folderInicial);
    chooser.setDialogTitle(tituloDialogo) ;
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int returnVal = chooser.showOpenDialog(new JFrame());
    if (returnVal == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      filename = file.getAbsolutePath();
    }
    return filename;
  }

  public static String dialogoGuardarArchivo(String tituloDialogo, String folderInicial) {
    String filename = "";
    JFileChooser chooser = new JFileChooser(folderInicial);
    chooser.setDialogTitle(tituloDialogo);
    int returnVal = chooser.showSaveDialog(new JFrame());
    if (returnVal == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      filename = file.getAbsolutePath();
    }
    return filename;
  }



  public static String dialogoGuardarDir(String tituloDialogo, String folderInicial) {

    String filename = "";
    JFileChooser chooser = new JFileChooser(folderInicial);
    chooser.setDialogTitle(tituloDialogo);
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int returnVal = chooser.showSaveDialog(new JFrame());
    if (returnVal == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      filename = file.getAbsolutePath();
    }
    return filename;
  }

}
