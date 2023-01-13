package tsp;
import ia.FMDP;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.io.FileNotFoundException;
import javax.swing.border.*;
import java.lang.String;
import java.util.Locale;
import utileria.Dialogos;
import utileria.Matrices;
import utileria.tablas;

/**
 * <p>Title: Interfaz </p>
 * <p>Description: Interfaz para ejemplificar el uso del package tsp</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: IIE Instituto de Investigaciones El�ctricas</p>
 * @author Christian Guti�rrez Bravo
 * @version 1.0
 */

public class Frame1 extends JFrame {



  boolean dirty = false;
  JPanel contentPane;
  JToolBar jToolBar = new JToolBar();
  JButton jButton2 = new JButton();
  ImageIcon image2;


  JLabel statusBar = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  String folder;
  
  ButtonGroup grupo = new ButtonGroup();
  JRadioButton op1 = new JRadioButton("S.Perseus");
  JRadioButton op2 = new JRadioButton("MDP Toolbox");

  
  
  //Construct the frame
  private Object jFileChooser1;
  public Frame1() {
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

    image2 = new ImageIcon(tsp.Frame1.class.getResource("traducirs.png"));

    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setEnabled(true);
    this.setIconImage(null);
    this.setResizable(false);
    this.setSize(new Dimension(330, 254));
    this.setTitle("Traductor");
    statusBar.setText(" ");
    statusBar.setBounds(new Rectangle(1, 770, 1024, 24));
    jButton2.setBounds(new Rectangle(162, 49, 115, 29));
    jButton2.setToolTipText("Traducir SPI");
    jButton2.setText("Carpeta");
    jButton2.addActionListener(new Frame1_jButton2_actionAdapter(this));
    jButton2.setIcon(image2);


    jPanel1.setLayout(null);
    jPanel1.setAlignmentX((float) 0.5);
    jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel1.setDebugGraphicsOptions(0);
    jPanel1.setMinimumSize(new Dimension(1, 1));
    jPanel1.setOpaque(true);
    jPanel1.setPreferredSize(new Dimension(1, 1));
    jPanel1.setToolTipText("");
    jPanel1.setActionMap(null);

    jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 12));
    jLabel1.setText("Traductor de Formatos SPI-SP-MDP Toolbox");
    jLabel1.setBounds(new Rectangle(30, 14, 250, 16));
    jLabel2.setText("Seleccione carpeta");
    jLabel2.setBounds(new Rectangle(44, 52, 122, 22));
    jButton1.setBounds(new Rectangle(50, 170, 115, 26));
    jButton1.setText("Iniciar traducción");
    jButton1.addActionListener(new Frame1_jButton1_actionAdapter(this));
    jButton1.addActionListener(new Frame1_jButton1_actionAdapter(this));
   // jLabel3.setText(
     //   "El archivo de entrada es mdp.txt y el de salida es mdp.dat");
    //jLabel3.setBounds(new Rectangle(20, 125, 295, 25));
    jButton3.setBounds(new Rectangle(235, 152, 70, 26));
    jButton3.setText("cerrar");
    jButton3.addActionListener(new Frame1_jButton3_actionAdapter(this));
    jButton4.setBounds(new Rectangle(234, 182, 72, 25));
    jButton4.setText("salir");
    jButton4.addActionListener(new Frame1_jButton4_actionAdapter(this));
    jPanel1.add(statusBar, null);
    jPanel1.add(jLabel1);
    jPanel1.add(jButton2);
    jPanel1.add(jLabel2);
    jPanel1.add(jButton1);
    //jPanel1.add(jLabel3);
    jPanel1.add(jButton3);
    jPanel1.add(jButton4);
    contentPane.add(jToolBar, BorderLayout.NORTH);
    contentPane.add(jPanel1, java.awt.BorderLayout.CENTER);
    // Crea opciones mdp toolbox o sp--------------------------------
    
    //ButtonGroup grupo = new ButtonGroup();
    
    jLabel3.setText("Seleccionar formato: ");
    jLabel3.setBounds(new Rectangle(50, 85, 295, 25));
   
    //JRadioButton op1 = new JRadioButton("S.Perseus");
    op1.setBounds(new Rectangle(50, 105, 295, 25));
    
    //JRadioButton op2 = new JRadioButton("MDP Toolbox");
    op2.setBounds(new Rectangle(50, 130, 295, 25));
    
    grupo.add(op1);
    grupo.add(op2);

    jPanel1.add(op1);
    jPanel1.add(op2);
    jPanel1.add(jLabel3);
    
     

    
    //---------------------------------------------------------------
  }

String dir=".";
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JButton jButton1 = new JButton();
  JLabel jLabel3 = new JLabel();
  JButton jButton3 = new JButton();
  JButton jButton4 = new JButton();
  
  
  void jButton2_actionPerformed(ActionEvent e) {
  /*Traduce el archivo SPI a SPUDD, la entrada se
   encuentra en textArea1*/

   folder=Dialogos.dialogoAbrirDir("Directorio problema SPI",dir);
   //t.traduceMDP("C:/mdp.txt","C:/mdp.dat");
   dir=folder;


  }
  
  
  
 
  public void jButton1_actionPerformed(ActionEvent e) {
    
    if (op1.isSelected()==true){
        jLabel3.setText("Formato: SPI-SP traducido");

        jButton1.setText("processing");
        Traductor t = new Traductor();
        t.traduceMDP(folder+"/mdp.txt",folder+"/mdp.dat");
        jButton1.setText("Iniciar traducción");
    }
    else if (op2.isSelected()==true){
        jLabel3.setText("Formato: SPI-MDPToolbox traducido");
        String policyFilename=folder+"\\fmdp.obj";
        
        jButton1.setText("processing");
        
        FMDP fmdp=FMDP.retrieveMDP(policyFilename);
        String path=folder+"\\";
        double[][] recompensaSXA=new double[fmdp.s.length][fmdp.modelo.length];
      
        for(int a=0; a<fmdp.modelo.length; a++){
        System.out.println("modelo accion "+a);
        Matrices.imprimeTabla(fmdp.modelo[a]);
        tablas.matrixToFile(path+"modeloAccion"+a+".csv", fmdp.modelo[a]);
        
        for(int s=0;s<fmdp.s.length;s++)
        recompensaSXA[s][a]= fmdp.reward[s];
        }
        
        
        
        tablas.matrixToFile(path+"recompensaSXA.csv", recompensaSXA);
        jButton1.setText("Iniciar traducción");
        
        System.out.print(folder);


    }
    else{
        jLabel3.setText("No hay Formato");
    }
    
    /*jButton1.setText("processing");
    Traductor t = new Traductor();
    t.traduceMDP(folder+"/mdp.txt",folder+"/mdp.dat");
    jButton1.setText("Iniciar traducción");
*/
  
}

  public void jButton3_actionPerformed(ActionEvent e) {
    dispose();
  }

  public void jButton4_actionPerformed(ActionEvent e) {
    if(Dialogos.panelOpciones()==0)
    System.exit(0);
  }

}

class Frame1_jButton4_actionAdapter
    implements ActionListener {
  private Frame1 adaptee;
  Frame1_jButton4_actionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}

class Frame1_jButton3_actionAdapter
    implements ActionListener {
  private Frame1 adaptee;
  Frame1_jButton3_actionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class Frame1_jButton1_actionAdapter
    implements ActionListener {
  private Frame1 adaptee;
  Frame1_jButton1_actionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.jButton1_actionPerformed(e);
  }
}

class Frame1_jButton2_actionAdapter implements java.awt.event.ActionListener {
  Frame1 adaptee;

  Frame1_jButton2_actionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
    }

    public static void main(String[] args){
      Frame1 f=new Frame1();
    }
}

