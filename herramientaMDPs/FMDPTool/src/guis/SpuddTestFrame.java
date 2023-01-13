package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.borland.jbcl.layout.*;
import utileria.Spudd;
import utileria.Listas;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class SpuddTestFrame extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JButton jButton1 = new JButton();
  JTextField jTextField2 = new JTextField();
  JButton jButton2 = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JLabel jLabel1 = new JLabel();
  JPanel jPanel5 = new JPanel();
  JButton jButton3 = new JButton();
  JTextField jTextField3 = new JTextField();
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenu1 = new JMenu();
  JMenuItem jMenuItem1 = new JMenuItem();

  public SpuddTestFrame() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    SpuddTestFrame spuddTestFrame = new SpuddTestFrame();
    spuddTestFrame.setSize(350,175);
    spuddTestFrame.setLocation(500,200);
    spuddTestFrame.setVisible(true);
  }
  private void jbInit() throws Exception {
    jPanel1.setLayout(verticalFlowLayout1);
    jLabel2.setText("Host");
    jButton1.setText("connect");
    jButton1.addActionListener(new SpuddTestFrame_jButton1_actionAdapter(this));
    jButton2.setText("query");
    jButton2.addActionListener(new SpuddTestFrame_jButton2_actionAdapter(this));
    jPanel2.setLayout(flowLayout1);
    jTextField1.setText("10.49.147.145");
    jTextField1.setColumns(15);
    jTextField2.setText("");
    jTextField2.setColumns(15);
    jLabel1.setText("State");
    verticalFlowLayout1.setHgap(5);
    verticalFlowLayout1.setVgap(0);
    this.setTitle("Spudd Client Test");
    jButton3.setText("exit");
    jButton3.addActionListener(new SpuddTestFrame_jButton3_actionAdapter(this));
    jTextField3.setEditable(false);
    jTextField3.setText("");
    jTextField3.setColumns(20);
    jMenu1.setText("menu");
    jMenuItem1.setText("item");
    jLabel3.setText("Output");
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel2, null);
    jPanel2.add(jLabel2, null);
    jPanel2.add(jTextField1, null);
    jPanel2.add(jButton1, null);
    jPanel1.add(jPanel3, null);
    jPanel3.add(jLabel1, null);
    jPanel3.add(jTextField2, null);
    jPanel3.add(jButton2, null);
    jPanel1.add(jPanel4, null);
    jPanel4.add(jLabel3, null);
    jPanel4.add(jTextField3, null);
    jPanel1.add(jPanel5, null);
    jPanel5.add(jButton3, null);
    jMenuBar1.add(jMenu1);
    jMenu1.add(jMenuItem1);
  }

  Spudd sp;
  JLabel jLabel3 = new JLabel();
  int clientPort=5510;
  int serverPort=5400;

  void jButton1_actionPerformed(ActionEvent e) {
    System.out.println("Spudd Test");
    String host=jTextField1.getText();

    //sp=new Spudd(host);
    sp=new Spudd(host,clientPort,serverPort);

  }

  void jButton3_actionPerformed(ActionEvent e) {

    dispose();
  }

  void jMenuItem1_actionPerformed(ActionEvent e) {
    System.out.println("item");
  }

  void jButton2_actionPerformed(ActionEvent e) {
    int estado[] = Listas.stringToInt(Listas.generaArreglo(jTextField2.getText(),
        "\t, "));

    Listas.imprimeLista(estado);

    if (sp.conectado) {
      int politica = sp.politica(estado);
      System.out.println(politica);
     // System.out.println(sp.ajustaBinario(politica,9));
      //System.out.println(""+Integer.toBinaryString(politica));
      jTextField3.setText("" + politica);
    }
  }
}

class SpuddTestFrame_jMenuItem1_actionAdapter implements java.awt.event.ActionListener {
  SpuddTestFrame adaptee;

  SpuddTestFrame_jMenuItem1_actionAdapter(SpuddTestFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem1_actionPerformed(e);
  }
}

class SpuddTestFrame_jButton1_actionAdapter implements java.awt.event.ActionListener {
  SpuddTestFrame adaptee;

  SpuddTestFrame_jButton1_actionAdapter(SpuddTestFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class SpuddTestFrame_jButton3_actionAdapter implements java.awt.event.ActionListener {
  SpuddTestFrame adaptee;

  SpuddTestFrame_jButton3_actionAdapter(SpuddTestFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class SpuddTestFrame_jButton2_actionAdapter implements java.awt.event.ActionListener {
  SpuddTestFrame adaptee;

  SpuddTestFrame_jButton2_actionAdapter(SpuddTestFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}