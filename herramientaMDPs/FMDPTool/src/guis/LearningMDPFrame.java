package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ia.FMDP;
import com.borland.jbcl.layout.*;
import ia.FMDP;
import java.io.File;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class LearningMDPFrame extends JFrame{

  ButtonGroup buttonGroup1 = new ButtonGroup();
  JPanel jPanel1 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanel5 = new JPanel();
  JButton jButton3 = new JButton();
  JButton jButton2 = new JButton();
  JLabel jLabel4 = new JLabel();
  JPanel jPanel6 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JPanel jPanel4 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JPanel jPanel2 = new JPanel();
  JTextField jTextField3 = new JTextField();
  JPanel jPanel3 = new JPanel();
  public LearningMDPFrame() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setTitle("Factored MDP Learning Tool");
    jPanel1.setLayout(flowLayout1);
    jButton3.setText("Attributes");
    jButton3.addActionListener(new LearningMDPFrame_jButton3_actionAdapter(this));
    jButton2.setText("Cases");
    jButton2.addActionListener(new LearningMDPFrame_jButton2_actionAdapter(this));
    jLabel4.setText("Data Files");
    jLabel2.setText("number of actions");
    jTextField2.setText("4");
    jTextField2.setColumns(5);
    jLabel1.setText("threshold");
    jLabel3.setText("max number of parents in DBN");
    jTextField1.setText("0.01");
    jTextField1.setColumns(5);
    jTextField3.setText("10");
    jTextField3.setColumns(5);
    jButton1.setText("Learn MDP");
    jButton1.addActionListener(new LearningMDPFrame_jButton1_actionAdapter(this));
    jButton4.setText("Exit");
    jButton4.addActionListener(new LearningMDPFrame_jButton4_actionAdapter(this));
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel5, null);
    jPanel5.add(jLabel4, null);
    jPanel5.add(jButton2, null);
    jPanel5.add(jButton3, null);
    jPanel1.add(jPanel6, null);
    jPanel4.add(jLabel3, null);
    jPanel4.add(jTextField3, null);
    jPanel1.add(jPanel7, null);
    jPanel7.add(jButton1, null);
    jPanel7.add(jButton4, null);
    jPanel6.add(jPanel2, null);
    jPanel2.add(jLabel1, null);
    jPanel2.add(jTextField1, null);
    jPanel6.add(jPanel3, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(jTextField2, null);
    jPanel6.add(jPanel4, null);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    String ejemplosCrudos=casesFile;
    String atributosCrudos=attributeFile;

    double umbral=Double.parseDouble(jTextField1.getText());
    int noAcciones=Integer.parseInt(jTextField2.getText());
    int SNumMaxParents=Integer.parseInt(jTextField3.getText());


      FMDP.aprendeFMDP(ejemplosCrudos,
                                       atributosCrudos,
                                       noAcciones,
                                       SNumMaxParents
                                       );

  }

  public static void main(String[] args) {
    LearningMDPFrame lmdpf=new LearningMDPFrame();
    lmdpf.setSize(600,200);
    lmdpf.setLocation(400,200);
    lmdpf.setVisible(true);

  }
  String casesFile="../ejemplos/robotNoObst/sinAng/ejemplos.txt";
  void jButton2_actionPerformed(ActionEvent e) {
    JFileChooser chooser=new JFileChooser("../ejemplos/");

    int returnVal=chooser.showOpenDialog(this);
    if (returnVal == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      casesFile=file.getAbsolutePath();
      System.out.println(casesFile);

  }
  }
  String attributeFile="../ejemplos/robotNoObst/sinAng/atributos.txt";
  JPanel jPanel7 = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton4 = new JButton();
  void jButton3_actionPerformed(ActionEvent e) {
    JFileChooser chooser=new JFileChooser("../ejemplos/");

    int returnVal=chooser.showOpenDialog(this);
    if (returnVal == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      attributeFile=file.getAbsolutePath();
      System.out.println(attributeFile);

  }

  }

  void jButton4_actionPerformed(ActionEvent e) {
    dispose();
  }
}

class LearningMDPFrame_jButton2_actionAdapter implements java.awt.event.ActionListener {
  LearningMDPFrame adaptee;

  LearningMDPFrame_jButton2_actionAdapter(LearningMDPFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class LearningMDPFrame_jButton3_actionAdapter implements java.awt.event.ActionListener {
  LearningMDPFrame adaptee;

  LearningMDPFrame_jButton3_actionAdapter(LearningMDPFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class LearningMDPFrame_jButton4_actionAdapter implements java.awt.event.ActionListener {
  LearningMDPFrame adaptee;

  LearningMDPFrame_jButton4_actionAdapter(LearningMDPFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}

class LearningMDPFrame_jButton1_actionAdapter implements java.awt.event.ActionListener {
  LearningMDPFrame adaptee;

  LearningMDPFrame_jButton1_actionAdapter(LearningMDPFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}