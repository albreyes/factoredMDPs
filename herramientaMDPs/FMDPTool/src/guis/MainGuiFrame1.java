package guis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import planta.SimuladorA;
import planta.AsistoGui_1;
import robotica.GridFrame;
//import robotica.PlayerStageTestFrame;
//import robotica.IHMAplic;
//import PlanTemporal.CargaPolitica;
import utileria.Spudd;
import tsp.tpanel;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MainGuiFrame1 extends JFrame {
  JPanel contentPane;
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JToolBar jToolBar = new JToolBar();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  ImageIcon image1;
  ImageIcon image2;
  ImageIcon image3;
  ImageIcon logotec;
  JLabel statusBar = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JMenu jMenu1 = new JMenu();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenuItem jMenuItem2 = new JMenuItem();
  JMenuItem jMenuItem3 = new JMenuItem();
  JMenuItem jMenuItem5 = new JMenuItem();
  JLabel jLabel1 = new JLabel();
  JMenu jMenu3 = new JMenu();
  JMenuItem jMenuItem7 = new JMenuItem();
  JMenuItem jMenuItem8 = new JMenuItem();
  JMenu jMenu4 = new JMenu();
  JMenuItem jMenuItem9 = new JMenuItem();
  JMenuItem jMenuItem4 = new JMenuItem();
  JMenuItem jMenuItem6 = new JMenuItem();
  JMenuItem jMenuItem10 = new JMenuItem();
  JMenuItem jMenuItem11 = new JMenuItem();
  JMenuItem jMenuItem12 = new JMenuItem();
  JMenuItem jMenuItem13 = new JMenuItem();

  //Construct the frame
  public MainGuiFrame1() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    image1 = new ImageIcon(guis.MainGuiFrame1.class.getResource("/Imagenes/openFile.png"));
    image2 = new ImageIcon(guis.MainGuiFrame1.class.getResource("/Imagenes/closeFile.png"));
    image3 = new ImageIcon(guis.MainGuiFrame1.class.getResource("/Imagenes/help.png"));
    logotec = new ImageIcon(guis.MainGuiFrame1.class.getResource("/Imagenes/logo2.png"));


    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(410, 283));
    this.setTitle("Sistema de Planificacion con Incertidumbre");
    statusBar.setText(" ");
    jMenuFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(new MainGuiFrame1_jMenuFileExit_ActionAdapter(this));
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.addActionListener(new MainGuiFrame1_jMenuHelpAbout_ActionAdapter(this));
    jButton1.setIcon(image1);
    jButton1.setToolTipText("Open File");
    jButton2.setIcon(image2);
    jButton2.setToolTipText("Close File");
    jButton3.setIcon(image3);
    jButton3.setToolTipText("Help");
    jMenu1.setText("Test");
    jMenu1.addActionListener(new MainGuiFrame1_jMenu1_actionAdapter(this));
    jMenuItem1.setText("Spudd Client");
    jMenuItem1.addActionListener(new MainGuiFrame1_jMenuItem1_actionAdapter(this));
    jMenuItem2.setText("Player-Stage Client");
    jMenuItem2.addActionListener(new MainGuiFrame1_jMenuItem2_actionAdapter(this));
    jMenuItem3.setText("ODBC conexion");
    jMenuItem5.setText("Client Server");
    jLabel1.setBackground(Color.lightGray);
    jLabel1.setFont(new java.awt.Font("Serif", 3, 60));
    jLabel1.setForeground(Color.lightGray);
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setIcon(logotec);
    jLabel1.setText("SPI");
    jMenu3.setText("Simulation");
    jMenuItem7.setText("Robotics");
    jMenuItem7.addActionListener(new MainGuiFrame1_jMenuItem7_actionAdapter(this));
    jMenuItem8.setText("Data Acquisition");
    jMenuItem8.addActionListener(new MainGuiFrame1_jMenuItem8_actionAdapter(this));
    jMenu4.setText("MDP");
    jMenuItem9.setText("Visual Interface");
    jMenuItem9.addActionListener(new MainGuiFrame1_jMenuItem9_actionAdapter(this));
    jMenuItem4.setText("Learning");
    jMenuItem4.addActionListener(new MainGuiFrame1_jMenuItem4_actionAdapter(this));
    jMenuItem6.setText("Policy Server");
    jMenuItem6.addActionListener(new MainGuiFrame1_jMenuItem6_actionAdapter(this));
    jMenuItem10.setText("CC Power Plant");
    jMenuItem10.addActionListener(new MainGuiFrame1_jMenuItem10_actionAdapter(this));
    jMenuItem11.setText("Turbogas Unit");
    jMenuItem11.addActionListener(new MainGuiFrame1_jMenuItem11_actionAdapter(this));
    contentPane.setBackground(Color.white);
    jMenuItem12.setText("Explanation Sys");
    jMenuItem12.addActionListener(new MainGuiFrame1_jMenuItem12_actionAdapter(this));
    jMenuItem13.setText("Traductor SPI-SPUDD");
    jMenuItem13.addActionListener(new MainGuiFrame1_jMenuItem13_actionAdapter(this));
    jToolBar.add(jButton1);
    jToolBar.add(jButton2);
    jToolBar.add(jButton3);
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenu1);
    jMenuBar1.add(jMenu4);
    jMenuBar1.add(jMenu3);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(jToolBar, BorderLayout.NORTH);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    contentPane.add(jLabel1, BorderLayout.CENTER);
    jMenu1.add(jMenuItem1);
    jMenu1.add(jMenuItem2);
    jMenu1.add(jMenuItem3);
    jMenu1.add(jMenuItem5);
    jMenu3.add(jMenuItem8);
    jMenu3.add(jMenuItem7);
    jMenu3.add(jMenuItem10);
    jMenu3.add(jMenuItem11);
    jMenu3.add(jMenuItem12);
    jMenu4.add(jMenuItem9);
    jMenu4.add(jMenuItem4);
    jMenu4.add(jMenuItem6);
    jMenu4.add(jMenuItem13);
  }
  //File | Exit action performed
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }
  //Help | About action performed
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    MainGuiFrame1_AboutBox dlg = new MainGuiFrame1_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }

   void jMenu1_actionPerformed(ActionEvent e) {
    new SpuddTestFrame();
  }

  void jMenuItem1_actionPerformed(ActionEvent e) {
    System.out.println("Spudd Test");
    SpuddTestFrame sptf =new SpuddTestFrame();
    sptf.main(new String[0]);
  }

  void jMenuItem2_actionPerformed(ActionEvent e) {
    System.out.println("player test");
    //PlayerStageTestFrame pstf=new PlayerStageTestFrame();
   // pstf.main(new String[0]);
  }

// policy server
  void jMenuItem6_actionPerformed(ActionEvent e) {
   new ServidorPoliticaFrame();
  }

// Data Acquisition
  void jMenuItem8_actionPerformed(ActionEvent e) {
    //new IHMAplic();
  }

  void jMenuItem7_actionPerformed(ActionEvent e) {
    new GridFrame().main(new String[0]);
  }

  void jMenuItem9_actionPerformed(ActionEvent e) {
    new CompilaFMDP();
  }

  void jMenuItem4_actionPerformed(ActionEvent e) {
    new LearningMDPFrame().main(new String[0]);
  }

// policy server
  void jMenuItem10_actionPerformed(ActionEvent e) {
    System.out.println("Iniciando GUI planta ...");
  //  new IHMPlanta();
  //new GuiRecuperador().main(new String[0]);
  new AsistoGui_1().setVisible(true);
  }

  void jMenuItem11_actionPerformed(ActionEvent e) {
    //SimuladorA.main(new String[0]);
  }

  // Inicia demo sistema de explicaciones
  void jMenuItem12_actionPerformed(ActionEvent e) {
    System.out.println("Iniciando sistema de explicaciones ... ");
    Spudd.ejecutar("../explicaciones/IntelligentAssistantW","");
    //new CargaPolitica();
  }

  public void jMenuItem13_actionPerformed(ActionEvent e) {
    System.out.println("Traductor");
    new tpanel();

  }
}

class MainGuiFrame1_jMenuItem13_actionAdapter
    implements ActionListener {
  private MainGuiFrame1 adaptee;
  MainGuiFrame1_jMenuItem13_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem13_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuFileExit_ActionAdapter implements ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuFileExit_ActionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuHelpAbout_ActionAdapter implements ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuHelpAbout_ActionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenu1_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenu1_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenu1_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuItem1_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuItem1_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem1_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuItem2_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuItem2_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem2_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuItem8_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuItem8_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem8_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuItem7_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuItem7_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem7_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuItem9_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuItem9_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem9_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuItem4_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuItem4_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem4_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuItem6_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuItem6_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem6_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuItem10_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuItem10_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem10_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuItem11_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuItem11_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem11_actionPerformed(e);
  }
}

class MainGuiFrame1_jMenuItem12_actionAdapter implements java.awt.event.ActionListener {
  MainGuiFrame1 adaptee;

  MainGuiFrame1_jMenuItem12_actionAdapter(MainGuiFrame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem12_actionPerformed(e);
  }
}
