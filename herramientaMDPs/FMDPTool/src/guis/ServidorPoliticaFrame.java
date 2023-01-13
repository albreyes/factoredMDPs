package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.borland.jbcl.layout.*;
import ia.ServidorPolitica;
import java.io.File;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ServidorPoliticaFrame extends JFrame{
  JPanel jPanel1 = new JPanel();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JPanel jPanel2 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JButton jButton4 = new JButton();
  JButton jButton1 = new JButton();
  //-------------------------------
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JButton jButton5 = new JButton();
  static JLabel jLabel2 = new JLabel();
  //-------------------------------
  
  public ServidorPoliticaFrame() {
    this.setTitle("Servidor de SPI");
    setSize(400,200);

    try {
      jbInit();
      setVisible(true);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    ServidorPoliticaFrame servidorPoliticaFrame1 = new ServidorPoliticaFrame();
  }
  private void jbInit() throws Exception {
    this.getContentPane().setLayout(verticalFlowLayout1);
    jButton2.setText("Iniciar servidor");
    jButton2.addActionListener(new ServidorPoliticaFrame_jButton2_actionAdapter(this));
    jButton3.setText("Apagar");
    jButton3.addActionListener(new ServidorPoliticaFrame_jButton3_actionAdapter(this));
    jLabel1.setText("Puerto");
    //--------------------------------------------
    jLabel2.setText("Estado:");
    //--------------------------------------------

    jTextField1.setText("5000");
    jTextField1.setColumns(10);
    jButton4.setText("Cerrar ventana");
    jButton4.addActionListener(new ServidorPoliticaFrame_jButton4_actionAdapter(this));
    jButton1.setText("Archivo de Politica");
    jButton1.addActionListener(new ServidorPoliticaFrame_jButton1_actionAdapter(this));
    
    //------------------------------------
    jButton5.setText("Cliente");
    jButton5.addActionListener(new ServidorPoliticaFrame_jButton5_actionAdapter(this));
    
    //-----------------------------------
    

    jPanel2.add(jLabel1, null);
    jPanel2.add(jTextField1, null);
    jPanel2.add(jButton1, null);
    jPanel1.add(jButton2, null);
    jPanel1.add(jButton3, null);
    jPanel1.add(jButton4, null);
    //----------------------------------------
    jPanel3.add(jButton5,null);
    jPanel4.add(jLabel2, null);
    //----------------------------------------

    this.getContentPane().add(jPanel2, null);
    this.getContentPane().add(jPanel1, null);
    //---------------------------------------
    this.getContentPane().add(jPanel3, null);
    this.getContentPane().add(jPanel4, null);
    //---------------------------------------

  }

  public static String policyFile;
  //-----------------------------------------
  void jButton5_actionPerformed(ActionEvent e){
      ClienteFrame abrir = new ClienteFrame();
      abrir.setVisible(true);
      
      //this.setVisible(false);
  }
  //-----------------------------------------
  void jButton1_actionPerformed(ActionEvent e) {
    JFileChooser chooser=new JFileChooser(".");

    int returnVal=chooser.showOpenDialog(this);
    if (returnVal == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      policyFile=file.getAbsolutePath();

      System.out.println(policyFile);
      //jButton14.setEnabled(true);
    }

  }
 public static ServidorPolitica servidor;
  //-------------------------------------------
 public static int puerto;
  
  //-------------------------------------------
  
  void jButton2_actionPerformed(ActionEvent e) {
      puerto=Integer.parseInt(jTextField1.getText());
      
      //servidor=new ServidorPolitica(puerto,policyFile);
      //Server_iniFrame abrir = new Server_iniFrame();
      //abrir.setVisible(true);
    //int puerto=Integer.parseInt(jTextField1.getText());
    //ServidorPoliticaFrame.jLabel2.setText("Servidor Iniciado\nEscuchando...");
    
    System.out.println("Bienvenidos al servidor de politica del Sistema SPI");
    System.out.println("Sistema de Planificacion con Incertidumbre");
    System.out.println("Version 0.0 \n");

    servidor=new ServidorPolitica(puerto,policyFile);
    

    while(true)
      servidor.enviaPolitica();

  }

  void jButton3_actionPerformed(ActionEvent e) {
   servidor.close();
  }

  void jButton4_actionPerformed(ActionEvent e) {
    //System.exit(0);
    dispose();
  }

}

//----------------------------------------------------------

class ServidorPoliticaFrame_jButton5_actionAdapter implements java.awt.event.ActionListener {
  ServidorPoliticaFrame adaptee;

  ServidorPoliticaFrame_jButton5_actionAdapter(ServidorPoliticaFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}

//----------------------------------------------------------

class ServidorPoliticaFrame_jButton1_actionAdapter implements java.awt.event.ActionListener {
  ServidorPoliticaFrame adaptee;

  ServidorPoliticaFrame_jButton1_actionAdapter(ServidorPoliticaFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class ServidorPoliticaFrame_jButton2_actionAdapter implements java.awt.event.ActionListener {
  ServidorPoliticaFrame adaptee;

  ServidorPoliticaFrame_jButton2_actionAdapter(ServidorPoliticaFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class ServidorPoliticaFrame_jButton3_actionAdapter implements java.awt.event.ActionListener {
  ServidorPoliticaFrame adaptee;

  ServidorPoliticaFrame_jButton3_actionAdapter(ServidorPoliticaFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class ServidorPoliticaFrame_jButton4_actionAdapter implements java.awt.event.ActionListener {
  ServidorPoliticaFrame adaptee;

  ServidorPoliticaFrame_jButton4_actionAdapter(ServidorPoliticaFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}