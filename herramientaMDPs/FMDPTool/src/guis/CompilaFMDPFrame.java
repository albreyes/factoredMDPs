package guis;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import utileria.ESObjetos;
import utileria.Dialogos;
import com.borland.jbcl.layout.*;
import ia.FMDP;
import planta.AsistoGui_1;
import planta.PlannerOptions;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class CompilaFMDPFrame extends JFrame{


  ButtonGroup opciones=new ButtonGroup();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel3 = new JPanel();

  Border border1;
  JPanel jPanel4 = new JPanel();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JLabel jLabel5 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField6 = new JTextField();
  JLabel jLabel9 = new JLabel();
  JPanel jPanel9 = new JPanel();
  JTextField jTextField7 = new JTextField();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel7 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JLabel jLabel17 = new JLabel();
  JTextField jTextField8 = new JTextField();
  JLabel jLabel4 = new JLabel();
  FMDP sis = new FMDP();


  public CompilaFMDPFrame() {
    try {

      jbInit();
      setTitle("Compilador de FMDPs");
      setSize(730,400);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createMatteBorder(6,6,6,6,Color.white);
    getContentPane().setLayout(borderLayout1);
    //jLabel1.setIcon(imagen);
    jPanel3.setLayout(verticalFlowLayout2);

    borderLayout1.setHgap(5);
    borderLayout1.setVgap(5);
 /*   getContentPane().addWindowListener(new java.awt.event.WindowAdapter() {

      public void windowClosing(WindowEvent e) {
        this_windowClosing(e);
      }
    });*/


    //jLabel3.setToolTipText("");
   // jLabel3.setIcon(imagen);
    jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel5.setText("Estado Actual");
    jTextField2.setColumns(3);
    jTextField5.setEditable(false);
    jTextField5.setColumns(10);
    jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel8.setText("Probabilidad");
    jTextField6.setEditable(false);
    jTextField6.setColumns(3);
    jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel9.setText("Utilidad");
    jTextField7.setEditable(false);
    jTextField7.setColumns(10);
    jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel10.setText("Politica");
    jLabel6.setToolTipText("");
    jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel6.setText("Estado Siguiente");
    jTextField3.setColumns(3);
    jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel7.setText("Accion");
    jTextField4.setColumns(2);

    jPanel7.setPreferredSize(new Dimension(627, 31));
    jLabel17.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel17.setText("Recompensa");
    jTextField8.setEditable(false);
    jTextField8.setColumns(10);
    jLabel4.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel4.setText("Prueba");
    jLabel14.setText("Iterac. Max.");
    jTextField13.setColumns(3);
    jTextField13.setText("50");
    jTextField14.setColumns(3);
    jTextField14.setText("4");
    jTextField14.setEditable(true);
    jButton2.setText("Consulta");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jTextField9.setColumns(3);
    jLabel11.setText("Estado Siguiente");
    jLabel11.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel11.setToolTipText("");
    jLabel15.setText("Accion");
    jLabel15.setHorizontalAlignment(SwingConstants.CENTER);
    jTextField15.setColumns(3);
    jTextField16.setColumns(2);
    jPanel13.setPreferredSize(new Dimension(627, 31));
    jLabel16.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel16.setText("Estado Actual");
    jButton7.setText("Atributos");
    jButton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton7_actionPerformed(e);
      }
    });
    jLabel18.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabel18.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel18.setText("Aprendizaje");
    jPanel14.setLayout(verticalFlowLayout3);
    jButton8.setText("Ejemplos");
    jButton8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton8_actionPerformed(e);
      }
    });
    jButton9.setText("Atrib. Discretos");
    jButton9.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton9_actionPerformed(e);
      }
    });
    jButton10.setBackground(Color.orange);
    jButton10.setText("Compila MDP");
    jButton10.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton10_actionPerformed(e);
      }
    });
    jButton12.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton12_actionPerformed(e);
      }
    });
    jButton12.setText("Salir");
    jButton6.setText("Salir");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    jRadioButton3.setFont(new java.awt.Font("Dialog", 0, 11));
    jRadioButton3.setText("hmdp");
    jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton3_actionPerformed(e);
      }
    });
    jRadioButton1.setFont(new java.awt.Font("Dialog", 0, 11));
    jRadioButton1.setText("qmdp");
    jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton1_actionPerformed(e);
      }
    });
    jRadioButton2.setFont(new java.awt.Font("Dialog", 0, 11));
    jRadioButton2.setSelected(true);
    jRadioButton2.setText("fmdp");
    jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton2_actionPerformed(e);
      }
    });
    jButton1.setBackground(Color.blue);
    jButton1.setForeground(SystemColor.activeCaptionBorder);
    jButton1.setText("Refina MDP");
    btnDatosMDP.setBackground(Color.GREEN);
    btnDatosMDP.setText("Obtener Datos MDP");
    btnDatosMDP.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnDatosMDP_actionPerformed(e);
      }
    });
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jLabel2.setText("Factor Desc");
    jLabel12.setText("Iteraciones");
    jTextField10.setText("1e-6");
    jTextField10.setColumns(5);
    jLabel1.setText("no. acciones");
    jLabel3.setText("Epsilon");
    jTextField1.setColumns(5);
    jTextField1.setText("0.9");
    jLabel13.setText("Parámetros");
    jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel13.setFont(new java.awt.Font("Dialog", 1, 14));
    jPanel15.setLayout(verticalFlowLayout4);
    jTextField12.setText("500");
    jTextField12.setColumns(5);
    jTextField11.setEditable(true);
    jTextField11.setText("5");
    jTextField11.setColumns(4);
    jLabel19.setText("etapas");
    jTextField17.setText("25");
    jTextField17.setColumns(4);
    jRadioButton4.setText("greedy");
    opciones.add(jRadioButton1);
    opciones.add(jRadioButton2);
    opciones.add(jRadioButton3);
    jButton3.setText("Carga MDP");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    getContentPane().add(jPanel3,  BorderLayout.CENTER);
    jPanel9.add(jLabel10, null);
    jPanel9.add(jTextField6, null);
    jPanel9.add(jLabel9, null);
    jPanel9.add(jTextField7, null);
    jPanel7.add(jLabel5, null);
    jPanel7.add(jTextField2, null);
    jPanel7.add(jLabel6, null);
    jPanel7.add(jTextField3, null);
    jPanel7.add(jLabel7, null);
    jPanel7.add(jTextField4, null);
    jPanel4.add(jLabel8, null);
    jPanel4.add(jTextField5, null);
    jPanel4.add(jLabel17, null);
    jPanel4.add(jTextField8, null);
    jPanel6.add(jLabel4, null);
    jPanel3.add(jPanel6, null);
    jPanel3.add(jPanel7, null);
    jPanel3.add(jPanel4, null);
    jPanel3.add(jPanel9, null);
    jPanel3.add(jPanel10, null);
    jPanel10.add(jButton3, null);
    jPanel10.add(jButton2, null);
    this.getContentPane().add(jPanel14, BorderLayout.EAST);
    jPanel14.add(jLabel18, null);
    jPanel14.add(jButton7, null);
    jPanel14.add(jButton9, null);
    jPanel14.add(jButton8, null);
    jPanel14.add(jButton10, null);
    jPanel14.add(jPanel18, null);
    jPanel18.add(jRadioButton4, null);
    jPanel14.add(jPanel11, null);
    jPanel11.add(jLabel19, null);
    jPanel11.add(jTextField17, null);
    jPanel14.add(jButton1, null);
    jPanel14.add(btnDatosMDP, null);

    this.getContentPane().add(jPanel16,  BorderLayout.SOUTH);
    jPanel16.add(jButton6, null);
    this.getContentPane().add(jPanel17,  BorderLayout.NORTH);
    jPanel17.add(jRadioButton2, null);
    jPanel17.add(jRadioButton1, null);
    jPanel17.add(jRadioButton3, null);
    this.getContentPane().add(jPanel15,  BorderLayout.WEST);
    jPanel15.add(jLabel13, null);
    jPanel15.add(jPanel12, null);
    jPanel12.add(jLabel1, null);
    jPanel12.add(jTextField11, null);
    jPanel15.add(jPanel2, null);
    jPanel2.add(jLabel2, null);
    jPanel2.add(jTextField1, null);
    jPanel15.add(jPanel8, null);
    jPanel8.add(jLabel12, null);
    jPanel8.add(jTextField12, null);
    jPanel15.add(jPanel5, null);
    jPanel5.add(jLabel3, null);
    jPanel5.add(jTextField10, null);
    jPanel1.add(jLabel14, null);
    jPanel1.add(jTextField13, null);
    jPanel13.add(jLabel16, null);
    jPanel13.add(jTextField9, null);
    jPanel13.add(jLabel11, null);
    jPanel13.add(jTextField15, null);
    jPanel13.add(jLabel15, null);
    jPanel13.add(jTextField16, null);

  }
  /*
  void this_windowClosing(WindowEvent e) {
    System.exit(0);
  }
  */

  void jButton2_actionPerformed(ActionEvent e) {
     
    System.out.println("consultar");
    int j=Integer.parseInt(jTextField3.getText());
    int a=Integer.parseInt(jTextField4.getText());
    int i=Integer.parseInt(jTextField2.getText());

    jTextField5.setText(""+(float) sis.modelo[a][i][j]);
    jTextField8.setText(""+(float) sis.reward[i]);
    jTextField6.setText(""+(float) sis.politica[i]);
    jTextField7.setText(""+(float) sis.utilidad[i]);
  }

/*
  public String dialogoAbrirArchivo() {
    String filename = "";
    JFileChooser chooser = new JFileChooser("../ejemplos");

    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      filename = file.getAbsolutePath();
    }
    return filename;
  }

*/
// genera mdp
  String ejemplosFile, atributosFile;
  JPanel jPanel1 = new JPanel();
  JLabel jLabel14 = new JLabel();
  JTextField jTextField13 = new JTextField();
  JTextField jTextField14 = new JTextField();
  JPanel jPanel10 = new JPanel();
  JButton jButton2 = new JButton();
  VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
  JTextField jTextField9 = new JTextField();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JTextField jTextField15 = new JTextField();
  JTextField jTextField16 = new JTextField();
  JPanel jPanel13 = new JPanel();
  JLabel jLabel16 = new JLabel();
  JPanel jPanel14 = new JPanel();
  JButton jButton7 = new JButton();
  JLabel jLabel18 = new JLabel();
  VerticalFlowLayout verticalFlowLayout3 = new VerticalFlowLayout();
  JButton jButton8 = new JButton();
  JButton jButton9 = new JButton();
  JButton jButton10 = new JButton();
  JButton jButton12 = new JButton();
  JPanel jPanel16 = new JPanel();
  JButton jButton6 = new JButton();

  // selecciona archivo de ejemplos
/*
  void jButton5_actionPerformed(ActionEvent e) {

    ejemplosFile=Dialogos.dialogoAbrirArchivo("archivo de ejemplos","../ejemplos");

  }
*/
  void jButton6_actionPerformed(ActionEvent e) {
    dispose();
  }
// compila MDP
  void jButton4_actionPerformed(ActionEvent e) {

    double fd= Double.parseDouble(jTextField1.getText());
    double epsilon=Double.parseDouble(jTextField10.getText());
    int maxIteraciones=Integer.parseInt(jTextField12.getText());
    int noAcciones=Integer.parseInt(jTextField11.getText());
    int noMaxPadresRed=10;

    sis = FMDP.aprendeResuelveFMDPCont_Cualitativos(ejemplosFile,
    atributosFile, atributosFile,
    noMaxPadresRed, noAcciones, fd, maxIteraciones, epsilon);

  }

  // selecciona archivo de atributos
  void jButton1_actionPerformed(ActionEvent e) {
   // atributosFile=dialogoAbrirArchivo();

   double fd= Double.parseDouble(jTextField1.getText());
   double epsilon=Double.parseDouble(jTextField10.getText());
   int maxIteraciones=Integer.parseInt(jTextField12.getText());
   int noAcciones=Integer.parseInt(jTextField11.getText());
   int noMaxPadresRed=10;
   int etapas=Integer.parseInt(jTextField17.getText());


   if(jRadioButton1.isSelected()) // refina un mdp qualitativo

     if(!jRadioButton4.isSelected())
     sis = FMDP.refinaMDP(ejemplosFile,
         atributosFile, atributosFile,
         noMaxPadresRed, noAcciones, fd, maxIteraciones, epsilon, etapas);
        else
          sis = FMDP.refinaMDPGreedy(ejemplosFile,
                   atributosFile, atributosFile,
                   noMaxPadresRed, noAcciones, fd, maxIteraciones, epsilon, etapas);

/*
   if(jRadioButton3.isSelected()) // refina un mdp hibrido ? no creo !
   sis = FMDP.refinaMDP(ejemplosFile,
       atributosFile, atributosDisFile,
       noMaxPadresRed, noAcciones, fd, maxIteraciones, epsilon, etapas);
*/
  }

  void btnDatosMDP_actionPerformed(ActionEvent e) {
         PlannerOptions p =new PlannerOptions();
         p.setVisible(true);
  }
  void jButton3_actionPerformed(ActionEvent e) {
    sis=(FMDP) ESObjetos.leeObjeto(Dialogos.dialogoAbrirArchivo("selecciona mdp","../ejemplos"));
  }
  void jButton12_actionPerformed(ActionEvent e) {

  }
  // archivo de atributos continuos para MDP discreto
  void jButton7_actionPerformed(ActionEvent e) {
    atributosFile=Dialogos.dialogoAbrirArchivo("archivo de atributos","../ejemplos");
  }
  String atributosDisFile;
  JRadioButton jRadioButton3 = new JRadioButton();
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();
  JPanel jPanel17 = new JPanel();
  JButton jButton3 = new JButton();
  JButton jButton1 = new JButton();
  JButton btnDatosMDP = new JButton();
  VerticalFlowLayout verticalFlowLayout4 = new VerticalFlowLayout();
  JLabel jLabel2 = new JLabel();
  JPanel jPanel12 = new JPanel();
  JLabel jLabel12 = new JLabel();
  JPanel jPanel8 = new JPanel();
  JTextField jTextField10 = new JTextField();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JPanel jPanel2 = new JPanel();
  JLabel jLabel13 = new JLabel();
  JPanel jPanel15 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JTextField jTextField12 = new JTextField();
  JTextField jTextField11 = new JTextField();
  JPanel jPanel11 = new JPanel();
  JLabel jLabel19 = new JLabel();
  JTextField jTextField17 = new JTextField();
  JPanel jPanel18 = new JPanel();
  JRadioButton jRadioButton4 = new JRadioButton();
  void jButton9_actionPerformed(ActionEvent e) {
    atributosDisFile=Dialogos.dialogoAbrirArchivo("archivo de atributos discretos","../ejemplos");
  }

  void jButton8_actionPerformed(ActionEvent e) {
    ejemplosFile=Dialogos.dialogoAbrirArchivo("archivo de ejemplos","../ejemplos");
  }


  // compila MDP factorizado discreto
  void jButton10_actionPerformed(ActionEvent e) {

    double fd= Double.parseDouble(jTextField1.getText());
    double epsilon=Double.parseDouble(jTextField10.getText());
    int maxIteraciones=Integer.parseInt(jTextField12.getText());
    int noAcciones=Integer.parseInt(jTextField11.getText());
    int noMaxPadresRed=10;


    if(jRadioButton2.isSelected()) // aprende un fmdp discreto
    sis = FMDP.aprendeResuelveFMDPCont_Dis(ejemplosFile,
                                           atributosFile,
                                           atributosDisFile,
                                           noMaxPadresRed,
                                           noAcciones,
                                           fd,
                                           maxIteraciones,
                                           epsilon);

    if(jRadioButton1.isSelected()) // aprende un mdp qualitativo
      sis = FMDP.aprendeResuelveFMDPCont_Cualitativos(ejemplosFile,
          atributosFile, atributosFile,
          noMaxPadresRed, noAcciones, fd, maxIteraciones, epsilon);

    if(jRadioButton3.isSelected()) // aprende un mdp hibrido
    sis = FMDP.aprendeResuelveFMDPCont_Cualitativos(ejemplosFile,
        atributosFile, atributosDisFile,
        noMaxPadresRed, noAcciones, fd, maxIteraciones, epsilon);

    if(AsistoGui_1.activoGUI)
          AsistoGui_1.mensajes.setText("Exito MDP Compilado...");

  }

  void jRadioButton2_actionPerformed(ActionEvent e) {
    jButton9.setEnabled(true);
  }

  void jRadioButton1_actionPerformed(ActionEvent e) {
    jButton9.setEnabled(false);
  }

  void jRadioButton3_actionPerformed(ActionEvent e) {
    jButton9.setEnabled(true);
  }

}

