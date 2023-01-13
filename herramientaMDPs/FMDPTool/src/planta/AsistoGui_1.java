package planta;

import DataBase.DatosPersistence;
import DataStructuresQ.Cualificador;
import aprendizaje.Nodo;
import aprendizaje.ValorDiscreto;
import aprendizaje.miWeka;
import explicaciones.DatosMDP;
import explicaciones.Explanation;
import ia.FMDP;
import guis.CompilaFMDP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import utileria.Dialogos;
import utileria.Dual2_;
import utileria.ESObjetos;
import utileria.FileOutput01;
import utileria.Listas;
import utileria.Spudd;
import utileria.tablas;
import java.io.File;


public class AsistoGui_1 extends JFrame implements Runnable {

  public Thread t     = null;
  int sleep = 500;
  boolean statusHilo =true;
  boolean pause = false;
  boolean restart = true;
  boolean stop = true;

  public static double flujo,presion;
  public static int valorPotencia;
  public static String cadPotencia;
  int intentos = 0;
  int beginExplica = 0;

  private boolean alarma = false;
  private boolean alarmaNivel = false;
  private boolean asistenteActivo = false;
  private Spudd spudd;
  public static boolean activoGUI = false;

  private FMDP fmdp;
  private String[][] atributosContinuos = null;
  private String[][] atributosDiscretos = null;
  private String folder;
  private int tipoMDP;
  private int planningTool;
  private int setupVars;
  private int noAcciones;
  private String hostSpudd;
  private String archivoAtbsCont;
  private String archivoAtbsDisc;
  private String policyFilename;
  private JFrame jf1;
  private PanelControl pc;
  private Dual2_ grafUtilidades = null;
  private ArrayList<Double> datUtilidad = new ArrayList();
  private ArrayList<Double> datAcciones = new ArrayList();
  private ArrayList<Double> datAccionesExe = new ArrayList();
  private int politica;
  private double utilidad;
  static boolean rechazoValor = false;
  private boolean stateChange1 = false;
  int contReco=0;


  
  String ambienteFile;

  boolean activarExplicacion = false;
  boolean statusExplicacion = false;
  int AccionEjecutada=0;

   String[] acciones5={
            "Abra valvula de agua de alimentación FWV",
            "Cierre valvula de agua de alimentación FWV",
            "Abra valvula de vapor pricipal MSV",
            "Cierre valvula de vapor pricipal MSV",
            "Recomendacion Nula"};

    String[] acciones9={
            "Abra valvula de agua de alimentación FWV",
            "Cierre valvula de agua de alimentación FWV",
            "Abra valvula de vapor pricipal MSV",
            "Cierre valvula de vapor pricipal MSV",
            "Abra ambas valvulas FWV y MSV",
            "Abra FWV y cierre MSV",
            "Cierre FWV y abra MSV",
            "Cierre ambas valvulas FWV y MSV",
            "Recomendacion Nula"};

     private final String tiposCargaArreglo[] = {
        "Carga nominal",
        "Carga media",
        "Carga minima"};

   // FileOutput01 fo = new FileOutput01(folder+"datosMDPExe.txt");
    private final JPanel ctrlPanel;
    private String nombreUsuario;
    private String caso;
    private int idPerfil;
    DibujoValvula figValvula1;
    DibujoValvula figValvula2;

    private ArrayList<Double> utilidades = new ArrayList();
    private boolean statusSesion = false;
    private long idSesion = 0;
    int posLBRechazo = 0;
    int reiniciar = 0;
    int nuevoIntento = 0;
    int nuevaSesion = 0;
    int contadorSesion = 0;
    int statusColorRec = 0;

    entity.Usuario usuarioLog = null;

    /** Creates new form AsistoGui4 */
    public AsistoGui_1() {

//        fo=new FileOutput01(folder+"datosMDPExe.txt");
        
        ctrlPanel = new JPanel();
        pc = new PanelControl();
        datUtilidad = new ArrayList();
        datAcciones = new ArrayList();
        datAccionesExe = new ArrayList();
   
        initComponents();
        activoGUI = true;
        figValvula1 = new DibujoValvula("VFFW",new Color(255,255,255), new Color(176,196,222),Color.BLACK,Color.BLUE);
        panelValvFFW.add(figValvula1,BorderLayout.CENTER);
        figValvula2 = new DibujoValvula("VFMS",new Color(255,255,255), new Color(176,196,222),Color.BLACK,Color.RED);
        panelValvFMS.add(figValvula2,BorderLayout.CENTER);
        
        ctrlPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        LineBorder b = new LineBorder(Color.gray);


        ctrlPanel.add(simPanelLabel,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 3, 140,
            20));


        ctrlPanel.add(muestreo,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 150,
            20));

        tiempo.setValue(500);
        ctrlPanel.add(tiempo,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120,
            20));

        /**************ETIQUETA DEL TIEMPO*************************************/
        tiempoLabel.setHorizontalAlignment(JLabel.CENTER);
        tiempoLabel.setText("" + tiempo.getValue() + " mseg");
        ctrlPanel.add(tiempoLabel,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 129,
            20));
        /*********************************************************************/


        ctrlPanel.add(planningPanelLabel,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 100, 140,
            20));
        ctrlPanel.add(plannerTF,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 125,
            25));

        ctrlPanel.add(asistente,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1,
            -1));
        ctrlPanel.add(tipoMDPLabel,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(75,
            160, -1, -1));

        jrb1.setSelected(true);

        buttonGroup1.add(jrb1);
        buttonGroup1.add(jrb2);
        buttonGroup1.add(jrb3);

        ctrlPanel.add(jrb1,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(5,
            200, -1, -1));
        ctrlPanel.add(jrb2,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(75,
            200, -1, -1));
        ctrlPanel.add(jrb3,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(160,
            200, -1, -1));
        ctrlPanel.add(construirNuevoMDP,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(5,
            180, -1, -1));

        ctrlPanel.add(plannerLabel,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(10,
            230, -1, -1));

        jchb1.setSelected(true);
        ctrlPanel.add(jchb1,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(90,
            250, -1, -1));

        ctrlPanel.add(jchb2,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(160,
            250, -1, -1));

        ctrlPanel.add(spuddhostLabel,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(30,
            290, -1, -1));

        ctrlPanel.add(spuddhostTF,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(110,
            288, -1, -1));

        ctrlPanel.add(setupLabel,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(20,
            325, -1, -1));

        jrb4.setSelected(true);
        buttonGroup2.add(jrb4);
        buttonGroup2.add(jrb5);
        buttonGroup2.add(jrb6);
        ctrlPanel.add(jrb4,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(40,
            350, -1, -1));
        ctrlPanel.add(jrb5,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(40,
            370, -1, -1));
        ctrlPanel.add(jrb6,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(40,
            390, -1, -1));
        ctrlPanel.add(noAccionesLabel,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, -1,
            -1));

        noAccionesTextField.setColumns(5);
        ctrlPanel.add(noAccionesTextField,
               new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, -1, -1));
         textoReco.setSelected(true);
         ctrlPanel.add(textoReco,
               new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));
         AudioReco.setSelected(true);
         ctrlPanel.add(AudioReco,
               new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        plannerSpudd.setBackground(Color.green);
        //plannerSpudd.setForeground(Color.gray);
        ctrlPanel.add(plannerSpudd,
                      new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530,
            160, -1));

        ctrlPanel.add(salir,
               new org.netbeans.lib.awtextra.AbsoluteConstraints(30,570, -1, -1));


        pc.btnRechazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechazoActionPerformed(evt);
            }
        });

        pc.rbVerValvulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbVerValvulasActionPerformed(evt);
            }
        });


        salir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                salirActionPerformed(evt);
            }
        });

        tiempo.addChangeListener(new javax.swing.event.ChangeListener()
        {
          public void stateChanged(javax.swing.event.ChangeEvent changeEvt)
          {
                tiempoActionPerformed(changeEvt);
          }

        });

         asistente.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            asistenteActionPerformed(evt);
          }
        });

        plannerSpudd.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            plannerSpuddActionPerformed(evt);
          }
        });

        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnRefresh = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        panelInforUser = new javax.swing.JPanel();
        lbTipoUser = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        panelNivel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbCaso = new javax.swing.JLabel();
        lbModo = new javax.swing.JLabel();
        btnLogos = new javax.swing.JButton();
        panelFondo = new javax.swing.JPanel();
        panelLienzo = new javax.swing.JPanel();
        panelManetas = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnParo = new javax.swing.JButton();
        lbParo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        var9 = new javax.swing.JTextField();
        lbNivelDomo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbNivDear = new javax.swing.JLabel();
        txtDeareador = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sliderTime = new javax.swing.JSlider();
        lbTiempo = new javax.swing.JLabel();
        panelHeader = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mensajesOp = new javax.swing.JTextArea();
        panelFooter = new javax.swing.JPanel();
        panelValvFFW = new javax.swing.JPanel();
        panelBtnFWV = new javax.swing.JPanel();
        paso_fwv = new javax.swing.JTextField();
        labelPorcent1 = new javax.swing.JLabel();
        abrir_fwv = new javax.swing.JButton();
        cerrar_fwv = new javax.swing.JButton();
        panelValvFMS = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        paso_msv = new javax.swing.JTextField();
        labelPorcent2 = new javax.swing.JLabel();
        abrir_msv = new javax.swing.JButton();
        cerrar_msv = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        mensajes = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        tempRec = new javax.swing.JLabel();
        posValPaso2 = new javax.swing.JProgressBar();
        flujoRecirc = new javax.swing.JLabel();
        posValRecirc = new javax.swing.JProgressBar();
        nivelDeareador = new javax.swing.JProgressBar();
        presionAguaAlim = new javax.swing.JLabel();
        posAtemp = new javax.swing.JProgressBar();
        jProgressBar1 = new javax.swing.JProgressBar();
        var1 = new javax.swing.JLabel();
        var5 = new javax.swing.JLabel();
        var3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        var2 = new javax.swing.JLabel();
        jProgressBar3 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        tiposCarga = new javax.swing.JComboBox();
        txtVar9 = new javax.swing.JLabel();
        lbMW = new javax.swing.JLabel();
        nivelTanque = new javax.swing.JProgressBar();
        jLabel10 = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        menu1 = new javax.swing.JMenu();
        itemLogin = new javax.swing.JMenuItem();
        itemRegUser = new javax.swing.JMenuItem();
        itemUserList = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        itemPC = new javax.swing.JMenuItem();
        itemProbFalla = new javax.swing.JMenuItem();
        itemGrafDomo = new javax.swing.JMenuItem();
        itemGraficaUtilidad = new javax.swing.JMenuItem();
        itemReporte = new javax.swing.JMenuItem();
        menu3 = new javax.swing.JMenu();
        itemSettings = new javax.swing.JMenuItem();
        itemExplicaciones = new javax.swing.JMenuItem();
        itemExploration = new javax.swing.JMenuItem();
        itemSolver = new javax.swing.JMenuItem();
        menu4 = new javax.swing.JMenu();
        help = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        itemDominioA = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asistente de Operación de Centrales Termoeléctricas ");
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/logoAsisto2.gif")).getImage());

        jToolBar1.setRollover(true);

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/refresh.png"))); // NOI18N
        btnRefresh.setText("Reboot");
        btnRefresh.setFocusable(false);
        btnRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRefresh);

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/play.png"))); // NOI18N
        btnPlay.setText("Play");
        btnPlay.setFocusable(false);
        btnPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPlay);

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/stop.png"))); // NOI18N
        btnStop.setText("Stop");
        btnStop.setFocusable(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        jToolBar1.add(btnStop);

        panelInforUser.setLayout(null);

        lbTipoUser.setFont(new java.awt.Font("Tahoma", 1, 10));
        lbTipoUser.setForeground(new java.awt.Color(0, 0, 102));
        lbTipoUser.setText("User type");
        panelInforUser.add(lbTipoUser);
        lbTipoUser.setBounds(10, 0, 110, 14);
        //jLabel2.setVisible(false);

        lbNombre.setFont(new java.awt.Font("Tahoma", 0, 9));
        lbNombre.setForeground(new java.awt.Color(0, 0, 153));
        lbNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNombre.setText("-");
        panelInforUser.add(lbNombre);
        lbNombre.setBounds(10, 20, 150, 12);
        //lbNombre.setVisible(false);

        panelNivel.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Level");
        panelNivel.add(jLabel3);
        jLabel3.setBounds(10, 0, 40, 13);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel9.setText("Operation Mode");
        panelNivel.add(jLabel9);
        jLabel9.setBounds(10, 26, 110, 13);

        lbCaso.setFont(new java.awt.Font("Tahoma", 0, 9));
        lbCaso.setForeground(new java.awt.Color(0, 0, 153));
        lbCaso.setText("-");
        panelNivel.add(lbCaso);
        lbCaso.setBounds(120, 0, 130, 12);
        //lbCaso.setVisible(false);

        lbModo.setFont(new java.awt.Font("Tahoma", 0, 9));
        lbModo.setForeground(new java.awt.Color(0, 0, 153));
        lbModo.setText("-");
        panelNivel.add(lbModo);
        lbModo.setBounds(120, 26, 130, 12);

        panelInforUser.add(panelNivel);
        panelNivel.setBounds(160, 0, 290, 43);

        jToolBar1.add(panelInforUser);

        btnLogos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logos.png"))); // NOI18N
        btnLogos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnLogos.setDefaultCapable(false);
        btnLogos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnLogos);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        panelFondo.setLayout(new java.awt.BorderLayout());

        panelLienzo.setLayout(new java.awt.BorderLayout());

        panelManetas.setBackground(new java.awt.Color(255, 255, 255));
        panelManetas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.black, null, null));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(null);

        jLabel4.setText("Shut TV");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(10, 10, 120, 17);

        btnParo.setText("Turn on");
        btnParo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParoActionPerformed(evt);
            }
        });
        jPanel3.add(btnParo);
        btnParo.setBounds(45, 30, 100, 30);

        lbParo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola2.png"))); // NOI18N
        jPanel3.add(lbParo);
        lbParo.setBounds(10, 30, 31, 32);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(225, 5, 5), null));
        jPanel2.setLayout(null);

        var9.setBackground(new java.awt.Color(204, 204, 204));
        var9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
        jPanel2.add(var9);
        var9.setBounds(10, 60, 130, 20);

        lbNivelDomo.setBackground(new java.awt.Color(0, 255, 0));
        lbNivelDomo.setText("NORMAL");
        lbNivelDomo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
        jPanel2.add(lbNivelDomo);
        lbNivelDomo.setBounds(10, 130, 130, 21);

        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel2.add(jLabel11);
        jLabel11.setBounds(10, 20, 100, 0);

        jLabel12.setText("Main switch");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel2.add(jLabel12);
        jLabel12.setBounds(10, 40, 120, 17);

        lbNivDear.setText("Deareador Level");
        jPanel2.add(lbNivDear);
        lbNivDear.setBounds(10, 200, 130, 17);

        txtDeareador.setBackground(new java.awt.Color(0, 255, 0));
        txtDeareador.setText("NORMAL");
        txtDeareador.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue, 2));
        jPanel2.add(txtDeareador);
        txtDeareador.setBounds(10, 220, 130, 20);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel2.setText("Alarms");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 10, 80, 17);

        jLabel13.setText("Drum Level");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel2.add(jLabel13);
        jLabel13.setBounds(10, 110, 100, 17);

        jLabel5.setText("Delay:");

        sliderTime.setMaximum(10000);
        sliderTime.setMinimum(500);
        sliderTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderTimeStateChanged(evt);
            }
        });

        lbTiempo.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTiempo.setText("500");

        javax.swing.GroupLayout panelManetasLayout = new javax.swing.GroupLayout(panelManetas);
        panelManetas.setLayout(panelManetasLayout);
        panelManetasLayout.setHorizontalGroup(
            panelManetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManetasLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(panelManetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelManetasLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lbTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        panelManetasLayout.setVerticalGroup(
            panelManetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelManetasLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(panelManetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelManetasLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addGroup(panelManetasLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(sliderTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelLienzo.add(panelManetas, java.awt.BorderLayout.LINE_END);

        panelHeader.setLayout(new java.awt.BorderLayout());

        mensajesOp.setEditable(false);
        mensajesOp.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
        mensajesOp.setForeground(new java.awt.Color(219, 4, 15));
        mensajesOp.setRows(2);
        mensajesOp.setText("Welcome to AsistO version 1.0");
        mensajesOp.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Help panel operator", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        mensajesOp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(mensajesOp);

        panelHeader.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        panelLienzo.add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelValvFFW.setBackground(new java.awt.Color(255, 255, 255));
        panelValvFFW.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelValvFFW.setLayout(new java.awt.BorderLayout());

        panelBtnFWV.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paso_fwv.setText("2");
        panelBtnFWV.add(paso_fwv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 30, -1));

        labelPorcent1.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        labelPorcent1.setText("%");
        panelBtnFWV.add(labelPorcent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        abrir_fwv.setFont(new java.awt.Font("Tahoma", 1, 11));
        abrir_fwv.setForeground(new java.awt.Color(0, 153, 0));
        abrir_fwv.setText("+");
        abrir_fwv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrir_fwvActionPerformed(evt);
            }
        });
        panelBtnFWV.add(abrir_fwv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 45, -1));

        cerrar_fwv.setFont(new java.awt.Font("Tahoma", 1, 11));
        cerrar_fwv.setForeground(java.awt.Color.red);
        cerrar_fwv.setText("-");
        cerrar_fwv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrar_fwvActionPerformed(evt);
            }
        });
        panelBtnFWV.add(cerrar_fwv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 45, -1));

        panelValvFFW.add(panelBtnFWV, java.awt.BorderLayout.LINE_END);

        panelValvFMS.setBackground(new java.awt.Color(255, 255, 255));
        panelValvFMS.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelValvFMS.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paso_msv.setText("2");
        jPanel7.add(paso_msv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 30, -1));

        labelPorcent2.setText("%");
        jPanel7.add(labelPorcent2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        abrir_msv.setFont(new java.awt.Font("Tahoma", 1, 11));
        abrir_msv.setForeground(new java.awt.Color(0, 153, 0));
        abrir_msv.setText("+");
        abrir_msv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrir_msvActionPerformed(evt);
            }
        });
        jPanel7.add(abrir_msv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, -1));

        cerrar_msv.setFont(new java.awt.Font("Tahoma", 1, 11));
        cerrar_msv.setText("-");
        cerrar_msv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrar_msvActionPerformed(evt);
            }
        });
        jPanel7.add(cerrar_msv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 50, -1));

        panelValvFMS.add(jPanel7, java.awt.BorderLayout.LINE_END);

        mensajes.setColumns(20);
        mensajes.setRows(5);
        mensajes.setPreferredSize(new java.awt.Dimension(162, 94));
        jScrollPane3.setViewportView(mensajes);

        javax.swing.GroupLayout panelFooterLayout = new javax.swing.GroupLayout(panelFooter);
        panelFooter.setLayout(panelFooterLayout);
        panelFooterLayout.setHorizontalGroup(
            panelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelValvFFW, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelValvFMS, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFooterLayout.setVerticalGroup(
            panelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelValvFFW, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelValvFMS, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelLienzo.add(panelFooter, java.awt.BorderLayout.PAGE_END);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tempRec.setFont(new java.awt.Font("Tahoma", 1, 14));
        tempRec.setForeground(new java.awt.Color(255, 255, 255));
        tempRec.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tempRec.setText("0.0");
        tempRec.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(tempRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 1, 100, 15));

        posValPaso2.setForeground(new java.awt.Color(0, 0, 255));
        posValPaso2.setValue(60);
        posValPaso2.setStringPainted(true);
        jPanel4.add(posValPaso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 0, 40, -1));

        flujoRecirc.setFont(new java.awt.Font("Tahoma", 1, 14));
        flujoRecirc.setForeground(new java.awt.Color(255, 255, 255));
        flujoRecirc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        flujoRecirc.setText("0.0");
        flujoRecirc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(flujoRecirc, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 46, 90, 15));

        posValRecirc.setForeground(new java.awt.Color(0, 0, 255));
        posValRecirc.setValue(60);
        posValRecirc.setStringPainted(true);
        jPanel4.add(posValRecirc, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 40, -1));

        nivelDeareador.setForeground(new java.awt.Color(0, 0, 255));
        nivelDeareador.setOrientation(1);
        nivelDeareador.setValue(30);
        jPanel4.add(nivelDeareador, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 69, 23));

        presionAguaAlim.setFont(new java.awt.Font("Tahoma", 1, 14));
        presionAguaAlim.setForeground(new java.awt.Color(255, 255, 255));
        presionAguaAlim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        presionAguaAlim.setText("0.0");
        presionAguaAlim.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(presionAguaAlim, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 138, 90, 15));

        posAtemp.setForeground(new java.awt.Color(0, 0, 255));
        posAtemp.setValue(60);
        posAtemp.setStringPainted(true);
        jPanel4.add(posAtemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 40, -1));

        jProgressBar1.setForeground(new java.awt.Color(0, 0, 255));
        jProgressBar1.setValue(60);
        jProgressBar1.setStringPainted(true);
        jPanel4.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(496, 116, 40, -1));

        var1.setFont(new java.awt.Font("Tahoma", 1, 14));
        var1.setForeground(new java.awt.Color(255, 255, 255));
        var1.setText("0.0");
        var1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(var1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 110, 15));

        var5.setFont(new java.awt.Font("Tahoma", 1, 14));
        var5.setForeground(new java.awt.Color(255, 255, 255));
        var5.setText("0.0");
        var5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(var5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 100, 15));

        var3.setFont(new java.awt.Font("Tahoma", 1, 14));
        var3.setForeground(new java.awt.Color(255, 255, 255));
        var3.setText("0.0");
        var3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(var3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 100, 15));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Nivel");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 88, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Presion");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, -1, -1));

        var2.setFont(new java.awt.Font("Tahoma", 1, 14));
        var2.setForeground(new java.awt.Color(255, 255, 255));
        var2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var2.setText("0.0");
        var2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(var2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 100, 15));

        jProgressBar3.setForeground(new java.awt.Color(255, 51, 51));
        jProgressBar3.setValue(45);
        jProgressBar3.setStringPainted(true);
        jPanel4.add(jProgressBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(696, 278, 30, -1));

        jProgressBar2.setForeground(new java.awt.Color(255, 51, 51));
        jProgressBar2.setValue(45);
        jProgressBar2.setStringPainted(true);
        jPanel4.add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 160, 30, -1));

        tiposCarga.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 10));
        tiposCarga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Carga Nominal", "Carga Media", "Carga Minima", " ", " ", " ", " " }));
        tiposCarga.setSelectedIndex(1);
        tiposCarga.setSelectedItem(1);
        jPanel4.add(tiposCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 100, 20));

        txtVar9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtVar9, new org.netbeans.lib.awtextra.AbsoluteConstraints(746, 396, 90, 15));

        lbMW.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        lbMW.setText("MW ");
        jPanel4.add(lbMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(686, 396, 60, 20));

        nivelTanque.setForeground(new java.awt.Color(0, 0, 255));
        nivelTanque.setOrientation(1);
        nivelTanque.setValue(30);
        jPanel4.add(nivelTanque, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 57, 30, 100));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new2.png"))); // NOI18N
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 850, 430));

        panelLienzo.add(jPanel4, java.awt.BorderLayout.CENTER);

        panelFondo.add(panelLienzo, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelFondo, java.awt.BorderLayout.CENTER);

        menu1.setText("File");

        itemLogin.setText("Log Out");
        itemLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLoginActionPerformed(evt);
            }
        });
        menu1.add(itemLogin);

        itemRegUser.setText("User Registration");
        itemRegUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRegUserActionPerformed(evt);
            }
        });
        menu1.add(itemRegUser);

        itemUserList.setText("User List");
        itemUserList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUserListActionPerformed(evt);
            }
        });
        menu1.add(itemUserList);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        menu1.add(exit);

        barraMenu.add(menu1);

        menuView.setText("View");

        itemPC.setText("Control Panel");
        itemPC.setEnabled(false);
        itemPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPCActionPerformed(evt);
            }
        });
        menuView.add(itemPC);

        itemProbFalla.setText("Failure Probability");
        itemProbFalla.setEnabled(false);
        itemProbFalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProbFallaActionPerformed(evt);
            }
        });
        menuView.add(itemProbFalla);

        itemGrafDomo.setText("Reward Chart");
        itemGrafDomo.setEnabled(false);
        itemGrafDomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGrafDomoActionPerformed(evt);
            }
        });
        menuView.add(itemGrafDomo);

        itemGraficaUtilidad.setText("Utility Chart");
        itemGraficaUtilidad.setEnabled(false);
        itemGraficaUtilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGraficaUtilidadActionPerformed(evt);
            }
        });
        menuView.add(itemGraficaUtilidad);

        itemReporte.setText("Student Report");
        itemReporte.setEnabled(false);
        itemReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReporteActionPerformed(evt);
            }
        });
        menuView.add(itemReporte);

        barraMenu.add(menuView);

        menu3.setText("Tools");

        itemSettings.setText("Planner");
        itemSettings.setEnabled(false);
        itemSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSettingsActionPerformed(evt);
            }
        });
        menu3.add(itemSettings);

        itemExplicaciones.setText("Explanation");
        itemExplicaciones.setEnabled(false);
        itemExplicaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExplicacionesActionPerformed(evt);
            }
        });
        menu3.add(itemExplicaciones);

        itemExploration.setText("Exploration");
        itemExploration.setEnabled(false);
        itemExploration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExplorationActionPerformed(evt);
            }
        });
        menu3.add(itemExploration);

        itemSolver.setText("MDP Solver");
        itemSolver.setEnabled(false);
        itemSolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSolverActionPerformed(evt);
            }
        });
        menu3.add(itemSolver);

        barraMenu.add(menu3);

        menu4.setText("Help");

        help.setText("About AsistO");
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        menu4.add(help);

        jMenu1.setText("Documentation");

        jMenuItem1.setText("User Manual");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        itemDominioA.setText("Aplication Domain");
        itemDominioA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDominioAActionPerformed(evt);
            }
        });
        jMenu1.add(itemDominioA);

        menu4.add(jMenu1);

        barraMenu.add(menu4);

        setJMenuBar(barraMenu);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-1023)/2, (screenSize.height-725)/2, 1023, 725);
    }// </editor-fold>//GEN-END:initComponents


  
    private void escribirTipoCarga() {
        try{
           manager.TablaCtccBean tb = new manager.TablaCtccBean(DatosPersistence.emf);
           tb.UpdateValor("TIPOSIM", (double)tiposCarga.getSelectedIndex());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "El tipo de simulacion no se pudo actualizar \n consulte a su administrador");
        }
    }
    private void updateValorDB(String variable, double valor){
         try{
            manager.TablaCtccBean tb = new manager.TablaCtccBean(DatosPersistence.emf);
            tb.UpdateValor(variable, valor);
         }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error al actualizar la variable \n consulte a su administrador");
        }

    }

   public double[] obtenerDatos(int setup) throws Exception
   {
  
     double fwf = dameValor("GWAD");
     double msf = dameValor("GVR");
     double pd  = dameValor("PVR");
     double g   = dameValor("WET3");
     double d   = dameValor("INTERC"); // {cerrado=1 abierto=0}
     double nd  = dameValor("NDO");
     double fwv = dameValor("XFWV");
     double msv1= dameValor("XZ1");
     double msv2= dameValor("XZ2");

     double pv2 = dameValor("XALIV"); // pos valv paso 2"
     double pfwp= dameValor("P1WA"); // presion agua alim
     double deal= dameValor("NDEAR"); // nivel deareador
     double recv= dameValor("XDEAR"); // pos valv recirc
     double frec= dameValor("GDEAR"); // flujo recirc
     double thrsg=dameValor("TGSR"); // temp gases hrsg
     double atempv=dameValor("XBP"); // temp gases hrsg

     muestraEstado(fwf,msf,pd,g,d,nd,fwv,msv1,msv2,pv2,pfwp,deal,recv,frec,thrsg,atempv);
    // muestraEstado(fwf);

     double[] estado=null;

    switch(setup){
    case 0: estado=new double[5];
      estado[0]= fwf;
      estado[1]=pd;
      estado[2]=msf;
      estado[3]=g;
      estado[4]=d;
      break;
    case 1: estado=new double[7];
      estado[0]= msf;
      estado[1]=fwf;
      estado[2]=d;
      estado[3]=pd;
      estado[4]=g;
      estado[5]=msv1;
      estado[6]=fwv;
    case 2: estado=new double[6];
      estado[0]= msf;
      estado[1]=fwf;
      estado[2]=pd;
      estado[3]=g;
      estado[4]=msv1;
      estado[5]=fwv;
    default:
    }

     return estado;
   }

   private void muestraEstado(double fwf,double msf,double pd,double g,double d,
                              double nd,double fwv,double msv1,double msv2,double pv2,
                              double pfwp,double deal,double recv,double frec,double thrsg,
                              double atempv){


        try{

              var1.setText("" + Float.parseFloat(Double.toString(fwf)));
              var2.setText("" + Float.parseFloat(Double.toString(msf)));
              var3.setText("" + Float.parseFloat(Double.toString(nd)));
              var5.setText("" + Float.parseFloat(Double.toString(pd)));
              txtVar9.setText(""+ Float.parseFloat(Double.toString(g)));

              presionAguaAlim.setText("" + Float.parseFloat(Double.toString(pfwp)));
              flujoRecirc.setText("not available");
              flujoRecirc.setText("" +Float.parseFloat(Double.toString(frec)));
              tempRec.setText("" + Float.parseFloat(Double.toString(thrsg)));

              jProgressBar1.setValue((int)(Double.parseDouble(Double.toString(fwv))*100));
              jProgressBar2.setValue((int)(Double.parseDouble(Double.toString(msv1))*100));
              jProgressBar3.setValue((int)(Double.parseDouble(Double.toString(msv2))*100));

       if(!stateChange1){

              figValvula1.setValor(jProgressBar1.getValue());
              figValvula1.repaint();
              figValvula2.setValor(jProgressBar2.getValue());
              figValvula2.repaint();
              stateChange1 = false;
        }
             
             /* double PAROTV_R = dameValor("PAROTV");
              if(PAROTV_R != -1 ){
                   if(PAROTV_R == 0){
                        lbParo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola2.png")));
                        btnParo.setEnabled(true);
                    }
              }*/
              posValPaso2.setValue((int)(pv2 * 100));
              posValRecirc.setValue((int)(recv * 100));
              posAtemp.setValue((int)(atempv * 100));

              if(nd <= 15)
                nivelTanque.setValue((int) (nd/15*100));
              else nivelTanque.setValue(100);

              if(deal<=2)
                    nivelDeareador.setValue((int) (deal/2*100));
              else nivelDeareador.setValue(100);

              alarmas(Double.valueOf(var3.getText()),deal);

              valorPotencia = (int) (g/50000*100);
              cadPotencia = String.valueOf(g / 1000);


             if (d == 1){
                var9.setText("Cerrado");
                var9.setBackground(Color.green);
              }else{
                var9.setText("Abierto");
                if(alarma)
                  var9.setBackground(Color.red);
                else var9.setBackground(Color.white);
                  alarma=!alarma;
             }
       }catch(Exception e){
          System.err.println("error muestraEstado " + e.getLocalizedMessage());
       }
   }

   private double dameValor(String var) throws Exception {
       try{
            manager.TablaCtccBean tb = new manager.TablaCtccBean(DatosPersistence.emf);
            entity.TablaCtcc te = new entity.TablaCtcc();
            te= tb.getValor(var);
            if(te!=null){
                return te.getValor();
            }
           
            return 0;
       }catch(Exception e){
           System.out.print("Error en dameValor...."+e.getLocalizedMessage());
           return -1;
       }
     
   }

   public double[] leerActuadores() throws Exception
   {

     double fwv_  = dameValor("XFWV");
     double msv1_ = dameValor("XZ1");

     double[] lecturas={fwv_,msv1_};
     return lecturas;

   }

   public void setRecomendacion(int[] estado) {

      
       String[] recomed = null;
//        mensajesOp.setText("");

        if (planningTool == 0 || planningTool == 2)
            recomed = recomSpudd(estado);
        if (planningTool == 1 || planningTool == 2)
            recomed = recomSpi(estado);
       
        // forma mensaje compuesto

        String out = "";
          for (int i = 0; i < recomed.length; i++) {
            if (i == recomed.length - 1)
              out = out + recomed[i];
            else {
              out = out + recomed[i] + "\n";
            }
          }

      if(statusColorRec == 0){
//        mensajesOp.setForeground(Color.BLACK);
        statusColorRec =1;
      }else{
       //  mensajesOp.setForeground(Color.BLUE);
         statusColorRec =0;
      }
     // mensajesOp.setText(out);
      //mensajesOp.setForeground(Color.RED);

    }

   public String[] recomSpudd(int[] estado){
        //System.out.println("recuperaSpudd");
      String[] recomed=null;
        // vector acciones5 es para 5, vector acciones9 es para 9
        if (noAcciones == 9)
          recomed = spudd.politica(estado, acciones9);
        if (noAcciones == 5)
        recomed = spudd.politica(estado, acciones5);
      return recomed;
    }

    public String[] recomSpi(int[] estado){
      String[] recomed=new String[1];
      int a    = fmdp.consultaPolitica(estado);
     
     
        if (noAcciones == 9)
          recomed[0]=acciones9[a];
        if (noAcciones == 5)
        recomed[0]=acciones5[a];

     // System.out.println("politica: " + a);
        this.politica = a;

        

      return recomed;
    }

    public void imprimeDatosMDP(int[] estado, int accionExec){

        // impresion para paco en un archivo

        FileOutput01 fo=new FileOutput01(folder+"datosMDP.dat");
       
        fo.writeln("msf\tfwf\td\tpd\tg\tr\tu\tp");
        for(int i=0; i<fmdp.s.length; i++){

          String out = "";
          for (int j = 0; j < fmdp.s[i].length; j++)
              out = out + fmdp.s[i][j] + "\t";


          fo.writeln(out + fmdp.reward[i] + "\t" +
            fmdp.utilidad[i] + "\t" + fmdp.politica[i]);
        }
        fo.close();

        // 0: fms, ffw, d, pd, g
        // 1: fms, ffw, d, pd, g, msv, fwv
        // 2: fms, ffw, pd, g, msv, fwv

        // impresion para paco
        int ne=-1;
        for(int i=0; i<fmdp.s.length; i++)
          if(Listas.esIgual(estado,fmdp.s[i]))
            ne=i;

            float u=(float) fmdp.utilidad[ne];
            this.utilidad = u;

            System.out.println("UTILIDAD: " + utilidad);
           
   
            float r=(float) fmdp.reward[ne];
            int policy= fmdp.consultaPolitica(estado);
            String stdo=Listas.intArray2String(estado);
            
            String mdpVars=""+stdo+r+"\t"+policy+"\t"+u+"\t"+accionExec;
            fo.writeln(mdpVars);
        mensajes.setText(mdpVars);
    }

    int monitorEjecucion(double[] lectActuales,double[] lectAnteriores){
	   if(noAcciones==5)
		   return getLabelAction(lectActuales, lectAnteriores, 5);
	   else //if(noAcciones==9)
		   return getLabelAction(lectActuales, lectAnteriores, 9);
   }

    int getLabelAction(double[] lectActuales, double[] lectAnteriores, int actionDim){

	   int label=-1;

	   double fwvActual = lectActuales[0];
	   double fwvAnt    = lectAnteriores[0];
	   double msvActual = lectActuales[1];
	   double msvAnt    = lectAnteriores[1];

	   int tendFwv = cambio(fwvActual,fwvAnt);
	   int tendMsv = cambio(msvActual,msvAnt);

	   if(actionDim == 5 ){
		   if(tendFwv==1 && tendMsv==0) label = 0;
		   if(tendFwv==2 && tendMsv==0) label = 1;
		   if(tendFwv==0 && tendMsv==1) label = 2;
		   if(tendFwv==0 && tendMsv==2) label = 3;
		   if(tendFwv==0 && tendMsv==0) label = 4;
	   } else if(actionDim==9 ){
		   if(tendFwv==1 && tendMsv==0) label = 0;
		   if(tendFwv==2 && tendMsv==0) label = 1;
		   if(tendFwv==0 && tendMsv==1) label = 2;
		   if(tendFwv==0 && tendMsv==2) label = 3;
		   if(tendFwv==1 && tendMsv==1) label = 4;
		   if(tendFwv==1 && tendMsv==2) label = 5;
		   if(tendFwv==2 && tendMsv==1) label = 6;
		   if(tendFwv==2 && tendMsv==2) label = 7;
		   if(tendFwv==0 && tendMsv==0) label = 8;
	   }

           System.out.println("tendFwv: " + tendFwv+ "tendMsv: " +tendMsv);
	   return label;
   }

    int cambio(double actual, double anterior){
	   if((actual-anterior)>0)
	   return 1;
	   else if((actual-anterior)<0)
		   return 2;
	   else return 0;
   }

    private void rechazoActionPerformed(java.awt.event.ActionEvent evt){
      rechazoValor = !rechazoValor;
      rechazoActionReal(rechazoValor);
      mensajes.setText("Turbina Abierta");


    }

    private void rbVerValvulasActionPerformed(java.awt.event.ActionEvent evt){

           if(pc.rbVerValvulas.isSelected())
               panelManetas.setVisible(true);
           else
               panelManetas.setVisible(false);

    }

    private void rechazoActionReal(boolean rechazoValor){
          String rechazoTemp="";
          if(rechazoValor){
            rechazoTemp = "0.0";
            pc.btnRechazo.setText("Cerrar");
            pc.lbRechazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola1.png")));

          }
          else{
           rechazoTemp= "1.0";
           pc.btnRechazo.setText("Abrir");
           pc.lbRechazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola2.png")));

          }
        updateValorDB("INTERC_R", Double.parseDouble(rechazoTemp));

    }

  
    private void salirActionPerformed(java.awt.event.ActionEvent evt){

        asistenteActivo = false;
        activarExplicacion = false;
        sliderTime.setValue(500);
//        mensajesOp.setText("Welcome to AsistO version 1.0");
        mensajes.setText("Planificador Desactivado");

    }

    private void tiempoActionPerformed(javax.swing.event.ChangeEvent changeEvt){
        tiempoLabel.setText(""+tiempo.getValue() + " mseg");
    }

    private void asistenteActionPerformed(java.awt.event.ActionEvent evt) {

      // Aqui se deben actualizar todos los settings de planificacion
      // no es peligroso porque no se construyen objetos

      //asistente.setText("Iniciando el asistente");
      folder = Dialogos.dialogoAbrirDir("Abrir carpeta del planificador",
                                        "../ejemplos/powerPlant");

      if (!folder.equals("")) {

        plannerTF.setText(folder);
        archivoAtbsCont = folder + "/atributos.txt";
        archivoAtbsDisc = folder + "/atributosDis.txt";
        policyFilename = folder + "/fmdp.obj";
      }
    }

    private void plannerSpuddActionPerformed(java.awt.event.ActionEvent evt) {
            asistenteActivo = true;
            itemExplicaciones.setEnabled(true);
            itemGraficaUtilidad.setEnabled(true);

        try {
            updateValorDB("STSTART", 3);
            updateValorDB("INTERC_R", 0 );
            updateValorDB("XFWV_R", 0.50 );
            updateValorDB("XZ1_R",0.08 );
            
            Thread.sleep(1000);
            obtenerDatos(setupVars);
            updateValorDB("STSTART", 1);
        } catch (Exception ex) {
            Logger.getLogger(AsistoGui_1.class.getName()).log(Level.SEVERE, null, ex);
        }
            sleep = 4000;
            sliderTime.setValue(4000);

    }

    private void preparaSpi(){
       mensajes.setText("Iniciando planificador SPI ...\n");
       fmdp= (FMDP) ESObjetos.leeObjeto(policyFilename);
       mensajes.append("Planificador SPI iniciado ");

    }

    private void preparaSpudd(){
      mensajes.setText("Iniciando planificador SPUDD ...\n");
      spudd= new Spudd(hostSpudd, 5510, 5400);
      mensajes.append("Planificador SPUDD iniciado ");
    }
    public void actualizaSettingsPlaneacion(){

        switch(tiposCarga.getSelectedIndex()){
            case 0:
                plannerTF.setText("../ejemplos/powerPlant/5acc5vars2%/discreto/cargaNom2");
            break;
            case 1:
                plannerTF.setText("../ejemplos/powerPlant/5acc5vars2%/discreto/cargaMedia2/");
            break;
            case 2:
                plannerTF.setText("../ejemplos/powerPlant/5acc5vars2%/discreto/cargaMin2/");
            break;
        }


      folder=plannerTF.getText();

      archivoAtbsCont = folder + "/atributos.txt";
      archivoAtbsDisc = folder + "/atributosDis.txt";
      policyFilename = folder + "/fmdp.obj";

        // 0:discreto 1:qualitativo 2: hibrido
      if(jrb1.isSelected()) tipoMDP=0;
      if(jrb2.isSelected()) tipoMDP=1;
      if(jrb3.isSelected()) tipoMDP=2;

      // 0: spudd 1: spi 2: spudd + spi
      if(jchb1.isSelected()==true&&jchb2.isSelected()==false)
        planningTool=1;
      else if(jchb1.isSelected()==false&&jchb2.isSelected()==true)
        planningTool=0;
      else if(jchb1.isSelected()==true&&jchb2.isSelected()==true)
        planningTool=2;

      if(planningTool==0||planningTool==2)
      hostSpudd=spuddhostTF.getText();

    // 0: fms, ffw, d, pd, g
    // 1: fms, ffw, d, pd, g, msv, fwv
    // 2: fms, ffw, pd, g, msv, fwv
    
      if(jrb4.isSelected()) setupVars=0;
      if(jrb5.isSelected()) setupVars=1;
      if(jrb6.isSelected()) setupVars=2;

      // pueden ser 5 o 9 acciones
      noAcciones = Integer.parseInt( noAccionesTextField.getText());
    }





    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            // String path = "manualUso.pdf";
            File path = new File(getClass().getResource("/imagenes/manualUso.pdf").toURI());
            try {
                Desktop.getDesktop().open(path);
            } catch (IOException ex) {
                Logger.getLogger(AsistoGui_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(AsistoGui_1.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jMenuItem1ActionPerformed

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
        JOptionPane.showMessageDialog(null,
                "ASISTO version 0.2 " +
                "\nCopyright 2007" +
                "\n\nAuthor: Alberto Reyes" +
                "\n\nSpecial thanks to:" +
                "\nIvette Gonz�lez" +
                "\nChristian Campos",
                "About",JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_helpActionPerformed

    private void itemSolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSolverActionPerformed
        new CompilaFMDP();
}//GEN-LAST:event_itemSolverActionPerformed

    private void itemExplorationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExplorationActionPerformed
        RewardPlanta frame = new RewardPlanta(tiposCarga.getSelectedIndex());
        frame.setSize(520, 540);
        frame.setVisible(true);
}//GEN-LAST:event_itemExplorationActionPerformed

    

    private void itemGrafDomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGrafDomoActionPerformed

        RewardChart rewChart = null;
         switch(tiposCarga.getSelectedIndex()){
            case 0:
                rewChart = new RewardChart("../ejemplos/powerPlant/rewCargaNominal.amb");
            break;
            case 1:
                rewChart = new RewardChart("../ejemplos/powerPlant/rewCargaMedia.amb");
            break;
            case 2:
                rewChart = new RewardChart("../ejemplos/powerPlant/rewCargaMinima.amb");
            break;
        }

          rewChart.setVisible(true);
          rewChart.getHilo2().setDaemon(false);
          rewChart.getHilo2().start();

    }//GEN-LAST:event_itemGrafDomoActionPerformed

    private void itemProbFallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProbFallaActionPerformed
        try {
            new Diagnostico().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AsistoGui_1.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_itemProbFallaActionPerformed

    private void itemPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPCActionPerformed
        pc.setVisible(true);
        setColorBtnRechazoPC();
}//GEN-LAST:event_itemPCActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        updateValorDB("STSTART",4);
        statusHilo = false;
        try {
             obtenerDatos(setupVars);
        } catch (Exception ex) {
            ex.getStackTrace();
            System.out.println("Error: "+ ex.getMessage() +", "+ this.getClass());
        }
         btnPlay.setEnabled(true);
}//GEN-LAST:event_btnStopActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
            if(DatosPersistence.getConexion() != null){
                t = null;
                statusHilo = true;
                        habilitaOpPlay();   
                        escribirTipoCarga();
                        updateValorDB("TIPOSIM",(double)tiposCarga.getSelectedIndex());//ACTUALIZAR LA VARIABLE DE TIPO DE SIMULACION
                        if(t==null){
                            updateValorDB("STSTART", 1);//SIMULADOR AVANZA
                            t = new Thread(this);
                            t.start();
                        }

            }else{
                JOptionPane.showMessageDialog(this, "No existe conexion a la base de datos\n ");
            }
    
}//GEN-LAST:event_btnPlayActionPerformed

    private void itemGraficaUtilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGraficaUtilidadActionPerformed
            grafUtilidades = null;
            grafUtilidades = new Dual2_(950,650,"Grafica de Utilidades","Tiempo","Utilidad");
            grafUtilidades.datos(datAcciones);
            grafUtilidades.datos(datAccionesExe);
            grafUtilidades.datos(datUtilidad);
            
    }//GEN-LAST:event_itemGraficaUtilidadActionPerformed

    private void itemReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReporteActionPerformed

        JFrame f =  new JFrame("Reporte de Usuario");
        f.setLayout(new BorderLayout());
        ReporteUsuario r = new ReporteUsuario(this.nombreUsuario,lbNombre.getText(),lbCaso.getText(),lbModo.getText());
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds((screenSize.width-929)/2, (screenSize.height-480)/2, 929, 480);
  
        f.add(r,BorderLayout.CENTER);
        f.setVisible(true);
        
    }//GEN-LAST:event_itemReporteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        try {
                updateValorDB("STSTART", 3);
                t = null;
                statusHilo = false;

                datAcciones.clear();
                datUtilidad.clear();
                datAccionesExe.clear();
                btnParo.setEnabled(true);
                
                lbParo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola2.png")));
                this.updateValorDB("PAROTV_R", 0);
                mensajes.setText(" ");
                updateValorDB("STSTART", 2);

                Thread.sleep(1000);

                updateValorDB("STSTART", 3);


                if(activarExplicacion){
                    updateValorDB("INTERC_R", 0 );
                    updateValorDB("XFWV_R", 0.50 );
                    updateValorDB("XZ1_R",0.08 );
                    sleep = 4000;
                    sliderTime.setValue(sleep);
                }else{

                    asistenteActivo    = false;
                    activarExplicacion = false;
                    sleep = 500;
                    sliderTime.setValue(sleep);
                    mensajesOp.setText(" ");
                }
                Thread.sleep(1000);
                updateValorDB("STSTART", 1);
                obtenerDatos(setupVars);
                if(t == null){
                    t = new Thread(this);
                    statusHilo = true;
                    t.start();
                }

        } catch (Exception ex) {
            Logger.getLogger(AsistoGui_1.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void itemExplicacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExplicacionesActionPerformed
         
        if(itemExplicaciones.getText().equals("Explanation")){
            activarExplicacion = true;
            itemExplicaciones.setText("Enabled Explanation");
        }else if(itemExplicaciones.getText().equals("Enabled Explanation")){
            activarExplicacion = false;
            itemExplicaciones.setText("Explanation");
        }
        
    }//GEN-LAST:event_itemExplicacionesActionPerformed

   

    private void itemLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLoginActionPerformed
        new Login().setVisible(true);
        stop();
        this.dispose();
}//GEN-LAST:event_itemLoginActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        accionSalir();
}//GEN-LAST:event_exitActionPerformed

    private void itemRegUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRegUserActionPerformed
       if(DatosPersistence.emf == null){
            JOptionPane.showMessageDialog(this, "No existe conexion a la base de datos");
        }else{
            new RegistroUsuario().setVisible(true);
        }
    }//GEN-LAST:event_itemRegUserActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void itemUserListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUserListActionPerformed
        JFrame listaUser = new JFrame("User List");
        listaUser.setSize(526,363);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        listaUser.setBounds((screenSize.width-526)/2, (screenSize.height-363)/2, 526, 363);
        listaUser.setLayout(new BorderLayout());
        listaUser.setLayout(new BorderLayout());
        listaUser.add(new ListaUsuarios(), BorderLayout.CENTER);
        listaUser.setVisible(true);
    }//GEN-LAST:event_itemUserListActionPerformed

    private void abrir_fwvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrir_fwvActionPerformed
        try {
            stateChange1 = true;
            int nuevoValorXFWV = (int) (dameValor("XFWV_R") * 100) + Integer.parseInt(paso_fwv.getText().trim());
            if(nuevoValorXFWV <=100)
                figValvula1.setValor(nuevoValorXFWV);
            figValvula1.repaint();
            fwvActionReal(nuevoValorXFWV);
        } catch (Exception e) {
            System.out.println("error abrir_fwv" + e.getMessage());
        }
}//GEN-LAST:event_abrir_fwvActionPerformed

    private void cerrar_fwvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrar_fwvActionPerformed
        try {
            stateChange1 = true;
            int nuevoValorXFWV = (int) (dameValor("XFWV_R") * 100) - Integer.parseInt(paso_fwv.getText().trim());
            if(nuevoValorXFWV< 0)
                nuevoValorXFWV = 0;
            figValvula1.setValor(nuevoValorXFWV);
            figValvula1.repaint();
            fwvActionReal(nuevoValorXFWV);
        } catch (Exception e) {
            System.out.println("error cerrar_fwv" + e.getMessage());
        }
}//GEN-LAST:event_cerrar_fwvActionPerformed

    private void abrir_msvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrir_msvActionPerformed
        try {
            int nuevoValorXZ1 = (int) (dameValor("XZ1_R") * 100) + Integer.parseInt(paso_msv.getText().trim());
            if(nuevoValorXZ1 <=100)
                figValvula2.setValor(nuevoValorXZ1);
            figValvula2.repaint();
            msvActionReal(nuevoValorXZ1);
        } catch (Exception e) {
            System.out.println("error abrir_msv" + e.getMessage());
        }
}//GEN-LAST:event_abrir_msvActionPerformed

    private void cerrar_msvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrar_msvActionPerformed
        try {
            stateChange1 = true;
            int valorDB = (int) (dameValor("XZ1_R") * 100);

            int nuevoValorXZ1 = valorDB - Integer.parseInt(paso_msv.getText().trim());
            if(nuevoValorXZ1< 0)
                nuevoValorXZ1 = 0;
            //  if(nuevoValorXZ1 >=0)
            figValvula2.setValor(nuevoValorXZ1);
            figValvula2.repaint();
            msvActionReal(nuevoValorXZ1);
        } catch (Exception e) {
            System.out.println("error cerrar_msv" + e.getMessage());
        }
}//GEN-LAST:event_cerrar_msvActionPerformed

    private void btnParoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParoActionPerformed
        try {
            tiposCarga.setEnabled(true);
            tiempo.setEnabled(true);
            double PAROTV_R = dameValor("PAROTV"); // 0=abierto 1= cerrado
            if (PAROTV_R == 0) {
                lbParo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola1.png")));
                updateValorDB("PAROTV_R", 1);
                updateValorDB("XZ1_R", 0);
                System.out.println(dameValor("XZ1_R"));
                btnParo.setEnabled(false);
            }
        } catch (Exception ex) {
            Logger.getLogger(AsistoGui_1.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnParoActionPerformed

    private void sliderTimeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderTimeStateChanged
        lbTiempo.setText(String.valueOf(sliderTime.getValue()));
}//GEN-LAST:event_sliderTimeStateChanged

    private void itemDominioAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDominioAActionPerformed
        try {
            // String path = "manualUso.pdf";
            File path = new File(getClass().getResource("/imagenes/dominio.pdf").toURI());
            try {
                Desktop.getDesktop().open(path);
            } catch (IOException ex) {
                Logger.getLogger(AsistoGui_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(AsistoGui_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemDominioAActionPerformed

    private void itemSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSettingsActionPerformed
        jf1=new JFrame();
        jf1.setSize(250,700);
        jf1.setTitle("Options");
        jf1.setLayout(new BorderLayout());
        jf1.add(ctrlPanel, BorderLayout.CENTER);
        Dimension dim= ctrlPanel.getSize();
        jf1.setVisible(true);

    }//GEN-LAST:event_itemSettingsActionPerformed

    

    private void msvActionReal(int nuevoValorXZ1){
        updateValorDB("XZ1_R",(nuevoValorXZ1/100.0));
    }
    
    private void fwvActionReal(int nuevoValorXFWV){
        updateValorDB("XFWV_R",(nuevoValorXFWV/100.0));
    }
    
private void setColorBtnRechazoPC(){
        try {
            double valor = dameValor("INTERC");
            if (valor == 1) {
                pc.lbRechazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola2.png")));
                pc.btnRechazo.setText("Abrir");
            } else {
                pc.lbRechazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola1.png")));
                pc.btnRechazo.setText("Cerrar");
            }
        } catch (Exception ex) {
            Logger.getLogger(AsistoGui_1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @accionSalir
     */
    private void accionSalir(){
        int answer = JOptionPane.showConfirmDialog(this, "Realmente desea salir?");

        if (answer == JOptionPane.YES_OPTION){
         if(t.isAlive())
          t.stop();
          System.exit(0);
        }
        else if (answer == JOptionPane.NO_OPTION)
          return;

    }

    public void habilitaOpPlay(){
        if(!usuarioLog.getNombre().equals("")){
            tiposCarga.setEnabled(false);
            btnStop.setEnabled(true);
            btnRefresh.setEnabled(true);
            String tipoUsuario = lbTipoUser.getText().trim();
            if(tipoUsuario.equals("ADMINISTRADOR")){
                 //itemExploration.setVisible(true);
                 itemExploration.setEnabled(true);
                 itemPC.setVisible(true);
                 itemPC.setEnabled(true);
                 itemProbFalla.setEnabled(true);
                 itemGrafDomo.setEnabled(true);
                 itemSettings.setEnabled(true);
                 itemGraficaUtilidad.setEnabled(true);
            }else if(tipoUsuario.equals("ESTUDIANTE")){
                 itemProbFalla.setEnabled(true);
                 itemGrafDomo.setEnabled(true);
                 itemSettings.setEnabled(true);
            }else if(tipoUsuario.equals("OPERADOR")){
                 itemProbFalla.setEnabled(true);
                 itemGrafDomo.setEnabled(true);
                 itemSettings.setEnabled(true);
            }else if(tipoUsuario.equals("INSTRUCTOR")){
                 itemProbFalla.setEnabled(true);
                 itemGrafDomo.setEnabled(true);
                 itemReporte.setEnabled(true);
                 itemSettings.setEnabled(true);
                 itemPC.setVisible(true);
                 itemPC.setEnabled(true);
                 itemGraficaUtilidad.setEnabled(true);
            }
        }
    }

    public void habilitaOpSesion(){

        if(usuarioLog.getTipousuarios().getDescripcion().equals("ADMINISTRADOR")){
            itemExploration.setEnabled(true);
            itemSolver.setEnabled(true);
            itemReporte.setEnabled(true);

        }else if(usuarioLog.getTipousuarios().getDescripcion().equals("ESTUDIANTE") ||
                 usuarioLog.getTipousuarios().getDescripcion().equals("OPERADOR") ||
                 usuarioLog.getTipousuarios().getDescripcion().equals("INSTRUCTOR")){

                if(usuarioLog.getTipousuarios().getDescripcion().equals("OPERADOR"))
                    itemExplicaciones.setVisible(false);

                    itemExploration.setVisible(false);
                    itemSolver.setVisible(false);
                    itemPC.setVisible(false);
                    itemRegUser.setVisible(false);
                    itemUserList.setEnabled(true);
        }

    }

    public void setlogin(entity.Usuario nombreUsuario) {
        try{
            if(nombreUsuario != null){

                usuarioLog = nombreUsuario;
                manager.TablaCtccBean tb = new manager.TablaCtccBean(DatosPersistence.emf);
                //entity.TablaCtcc te = tb.getValor("TIPOSIM");
                //manager.UserBean ub = new manager.UserBean(DatosPersistence.emf);
                //int status = ub.UpdateNivel(usuarioLog.getNombreUsuario(), ((Double)te.getValor()).longValue());

                habilitaOpSesion();
                getTipoUsuario();
                manager.SesionesBean sb = new manager.SesionesBean(DatosPersistence.emf);
                Long total = sb.getSesionIdNivelNotNull(usuarioLog.getNombreUsuario());

                    if(total>0){
                        entity.Sesiones s = sb.getNivelActual(usuarioLog.getNombreUsuario());
                        lbCaso.setText(s.getNivelusuario().getDescripcion());
                        lbModo.setText(s.getNivelusuario().getModosoperacion().getDescripcion());
                    }else{
                        lbCaso.setText(usuarioLog.getNivelusuario().getDescripcion());
                        lbModo.setText(usuarioLog.getNivelusuario().getModosoperacion().getDescripcion());
                    }

                    if(lbCaso.getText().trim().equals("NOVATO")){
                        tiposCarga.setSelectedIndex(2);
                        idPerfil = 1;
                    }
                    else if(lbCaso.getText().trim().equals("INTERMEDIO")){
                        tiposCarga.setSelectedIndex(1);
                        idPerfil = 2;
                    }
                    else if(lbCaso.getText().trim().equals("AVANZADO")){
                        tiposCarga.setSelectedIndex(0);
                        idPerfil = 3;
                    }

                    tiposCarga.setEnabled(false);

                }else{
                    lbNombre.setText("Sin Descripcion");
                    lbCaso.setText("Sin Descripcion");
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
    }

    public void getTipoUsuario(){
            lbNombre.setText(usuarioLog.getNombre() + " " + usuarioLog.getApaterno() +" " + usuarioLog.getAmaterno());
            lbTipoUser.setText(usuarioLog.getTipousuarios().getDescripcion());
    }

    public void insertaNuevaSesion(){
        System.out.println("se inserto una nueva sesion");
        Date utilDate = new Date();
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date sqlDate = new  java.sql.Date(lnMilisegundos);

        manager.SesionesBean sb = new manager.SesionesBean(DatosPersistence.emf);
        entity.Sesiones se = new entity.Sesiones();
        entity.Usuario ue = new entity.Usuario();
        ue.setNombreUsuario(usuarioLog.getNombreUsuario());
        se.setUsuario(ue);
        se.setFecha(sqlDate);
        int status = sb.guardarSesion(se);

        if(status != 1)
            System.out.println("error al insertar una nueva sesion ");
        else{
             Object last;
            if(DatosPersistence.tipoServidor == 3 || DatosPersistence.tipoServidor == 0)
                last = sb.last_insert_idMSSQL();
            else
                last = sb.last_insert_idMySQL();

             if(last!= null)
                idSesion = Integer.parseInt(String.valueOf(last));

        }
 
       // System.out.println("S E S I O N " + idSesion);
    }

    public void alarmas(double domo,double deareador){
               //alarma del nivel de domo
              if(domo > 2.032){
                  setColorTxt("ALTO",Color.YELLOW,Color.WHITE, lbNivelDomo);
              }else if(domo < 0.381){ //domo bajo
                  setColorTxt("BAJO",Color.YELLOW,Color.WHITE, lbNivelDomo);
              }else
                  setColorTxt("NORMAL",Color.GREEN,Color.GREEN, lbNivelDomo);

              //alarma del nivel del deareador
              if(deareador < 0.4064){
                  setColorTxt("BAJO",Color.YELLOW,Color.WHITE, txtDeareador);
              }else if(deareador > 1.2192){
                  setColorTxt("ALTO",Color.YELLOW,Color.WHITE, txtDeareador);
              }else
                  setColorTxt("NORMAL",Color.GREEN,Color.GREEN, txtDeareador);

    }

    /**
 *@descripcion cambia los colores de las alarmas
 *@param mensaje de alarma(mensaje), Color de alarma(color1), nombre del componente
 */
    public void setColorTxt(String mensaje, Color color1,Color color2,JTextField nameComponent){

        nameComponent.setText(mensaje);

         if(alarmaNivel)
            nameComponent.setBackground(color1);
         else lbNivelDomo.setBackground(color2);
            alarmaNivel=!alarmaNivel;

    }


    public int[] activarRecomendacion(double estado[],double []actuadoresAnt){
 
       switch(tiposCarga.getSelectedIndex()){
            case 0:
                plannerTF.setText("../ejemplos/powerPlant/5acc5vars2%/discreto/cargaNom2/");
            break;
            case 1:
                plannerTF.setText("../ejemplos/powerPlant/5acc5vars2%/discreto/cargaMedia2/");
            break;
            case 2:
                plannerTF.setText("../ejemplos/powerPlant/5acc5vars2%/discreto/cargaMin2/");
            break;
        }


      folder = plannerTF.getText();


        // 0:discreto 1:qualitativo 2: hibrido
      if(jrb1.isSelected()) tipoMDP=0;
      if(jrb2.isSelected()) tipoMDP=1;
      if(jrb3.isSelected()) tipoMDP=2;

    // 0: fms, ffw, d, pd, g
    // 1: fms, ffw, d, pd, g, msv, fwv
    // 2: fms, ffw, pd, g, msv, fwv

      if(jrb4.isSelected()) setupVars=0;
      if(jrb5.isSelected()) setupVars=1;
      if(jrb6.isSelected()) setupVars=2;

      // pueden ser 5 o 9 acciones
      noAcciones = Integer.parseInt( noAccionesTextField.getText());


         DatosMDP dmdp  = new DatosMDP(folder +"datosMDP.dat");
        double[] estadoContinuo = estado;//convierte todos los estados continuos de String a double
        int[] estadoCualitativo={};

        archivoAtbsCont = folder + "/atributos.txt";
        archivoAtbsDisc = folder + "/atributosDis.txt";

        if (tipoMDP == 0 || tipoMDP == 2) { // si es discreto o hibrido
                atributosContinuos = tablas.fileToMatrix(archivoAtbsCont,
                    ":,\t ");
                atributosDiscretos = tablas.fileToMatrix(archivoAtbsDisc,
                 ":,\t ");
        }



        int[]   estadoDiscreto  = ValorDiscreto.discretAutoOnLineInt(
                     estadoContinuo, atributosContinuos, atributosDiscretos);
         int[] x = estadoDiscreto;

        int reco= dmdp.getEstado(estadoDiscreto);
        politica = reco;
        double ut = dmdp.getUtilidad(estadoDiscreto);

        datUtilidad.add(ut);
        datAcciones.add(Double.parseDouble(String.valueOf(reco)));
        double [] actuadores;
        int actionEjecutada=0;
        estadoDiscreto = Listas.concatena(estadoDiscreto,estadoDiscreto);//arreglo con los valores discretos actuales
         if(politica == 4){
            mensajesOp.setForeground(Color.BLACK);

         }else if(politica == 0 || politica == 2){
            mensajesOp.setForeground(Color.BLUE);

         }else if(politica == 1 || politica == 3){
            mensajesOp.setForeground(Color.RED);

         }
             if(!Listas.miembro(-1,estadoDiscreto)){

                 if(textoReco.isSelected()){
                    mensajesOp.setText( acciones5[reco]);
                    panelHeader.setVisible(true);
                    }
                 else
                     panelHeader.setVisible(false);
                 if(AudioReco.isSelected())
                    ReproduceAudio(reco);//recomendacion con audio

             }else {
                if(AudioReco.isSelected())
                  ReproduceAudio(reco);//recomendacion con audio
             }
        try {
            mensajes.setForeground(Color.red);
            mensajes.setText("E S P E R A N D O    A C C I O N");


                contReco++;
            sleep = sliderTime.getValue()-100;
            Thread.sleep(sleep);
            actuadores = leerActuadores();
            actionEjecutada = monitorEjecucion(actuadores,actuadoresAnt);
            AccionEjecutada = actionEjecutada;
            System.out.println(" Accion "+AccionEjecutada +"RECOMENDACION " +reco);
        } catch (Exception ex) {
            Logger.getLogger(AsistoGui_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        datAccionesExe.add(Double.parseDouble(String.valueOf(actionEjecutada)));

                         if(grafUtilidades != null){
                            grafUtilidades.datos(datUtilidad);
                            grafUtilidades.datosAc(datAcciones);
                            grafUtilidades.datosAcExe(datAccionesExe);
                         }



         return x;
        
       
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrir_fwv;
    private javax.swing.JButton abrir_msv;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton btnLogos;
    public javax.swing.JButton btnParo;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton cerrar_fwv;
    private javax.swing.JButton cerrar_msv;
    private javax.swing.JMenuItem exit;
    private javax.swing.JLabel flujoRecirc;
    private javax.swing.JMenuItem help;
    private javax.swing.JMenuItem itemDominioA;
    private javax.swing.JMenuItem itemExplicaciones;
    private javax.swing.JMenuItem itemExploration;
    private javax.swing.JMenuItem itemGrafDomo;
    private javax.swing.JMenuItem itemGraficaUtilidad;
    private javax.swing.JMenuItem itemLogin;
    private javax.swing.JMenuItem itemPC;
    private javax.swing.JMenuItem itemProbFalla;
    private javax.swing.JMenuItem itemRegUser;
    private javax.swing.JMenuItem itemReporte;
    private javax.swing.JMenuItem itemSettings;
    private javax.swing.JMenuItem itemSolver;
    private javax.swing.JMenuItem itemUserList;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelPorcent1;
    private javax.swing.JLabel labelPorcent2;
    private javax.swing.JLabel lbCaso;
    public javax.swing.JLabel lbMW;
    private javax.swing.JLabel lbModo;
    private javax.swing.JLabel lbNivDear;
    private javax.swing.JTextField lbNivelDomo;
    private javax.swing.JLabel lbNombre;
    public javax.swing.JLabel lbParo;
    private javax.swing.JLabel lbTiempo;
    private javax.swing.JLabel lbTipoUser;
    public static javax.swing.JTextArea mensajes;
    private javax.swing.JTextArea mensajesOp;
    private javax.swing.JMenu menu1;
    private javax.swing.JMenu menu3;
    private javax.swing.JMenu menu4;
    private javax.swing.JMenu menuView;
    private javax.swing.JProgressBar nivelDeareador;
    private javax.swing.JProgressBar nivelTanque;
    private javax.swing.JPanel panelBtnFWV;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelFooter;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelInforUser;
    private javax.swing.JPanel panelLienzo;
    private javax.swing.JPanel panelManetas;
    private javax.swing.JPanel panelNivel;
    private javax.swing.JPanel panelValvFFW;
    private javax.swing.JPanel panelValvFMS;
    private javax.swing.JTextField paso_fwv;
    private javax.swing.JTextField paso_msv;
    private javax.swing.JProgressBar posAtemp;
    private javax.swing.JProgressBar posValPaso2;
    private javax.swing.JProgressBar posValRecirc;
    private javax.swing.JLabel presionAguaAlim;
    private javax.swing.JSlider sliderTime;
    private javax.swing.JLabel tempRec;
    private javax.swing.JComboBox tiposCarga;
    private javax.swing.JTextField txtDeareador;
    public javax.swing.JLabel txtVar9;
    private javax.swing.JLabel var1;
    private javax.swing.JLabel var2;
    private javax.swing.JLabel var3;
    private javax.swing.JLabel var5;
    private javax.swing.JTextField var9;
    // End of variables declaration//GEN-END:variables

    
    
    
    private JLabel muestreo = new JLabel("Frecuencia de Muestreo");
    private JSlider tiempo = new JSlider(100, 3000);
    private JLabel imagen = new JLabel();
    private JButton salir = new JButton("Desactivar Planificador");
    private JTextField noAccionesTextField = new JTextField("5");
  //  private JPanel p1 = new JPanel(); // panel de opciones
    private JButton asistente = new JButton("Planner ..");
    private JCheckBox textoReco = new JCheckBox("Recomendaciones texto");
    private JCheckBox AudioReco = new JCheckBox("Recomendaciones Audio");
    private JButton plannerSpudd = new JButton("Conectar c/Planner");
    private JLabel tiempoLabel = new JLabel("");
    private JLabel simPanelLabel = new JLabel("Panel de Simulación");
    private JLabel planningPanelLabel = new JLabel("Panel de Planificación");
    
    private JFrame grafDomo;
    private JCheckBox construirNuevoMDP = new JCheckBox("Generar Nuevo MDP");
    private JRadioButton jrb1 = new JRadioButton("discreto");
    private JRadioButton jrb2 = new JRadioButton("cualitativo");
    private JRadioButton jrb3 = new JRadioButton("híbrido");
    private JRadioButton jrb4 = new JRadioButton("[msf,fwf,d,pd,g]");
    private JRadioButton jrb5 = new JRadioButton("[msf,fwf,d,pd,g,msv,fwv]");
    private JRadioButton jrb6 = new JRadioButton("[msf,fwf,pd,g,msv,fwv]");
    private JCheckBox jchb1 = new JCheckBox("SPI");
    private JCheckBox jchb2 = new JCheckBox("SPUDD");
    private JLabel tipoMDPLabel = new JLabel("Tipo de MDP");
    private ButtonGroup buttonGroup1 = new ButtonGroup();
    private ButtonGroup buttonGroup2 = new ButtonGroup();
    private JLabel plannerLabel = new JLabel("Herramienta");
    private JLabel setupLabel = new JLabel("Configuraci�n variables");
    private JLabel noAccionesLabel = new JLabel("No. acciones");
    private JTextField plannerTF = new JTextField("../ejemplos/powerPlant/5acc5vars2%/discreto/");
    private JTextField spuddhostTF = new JTextField("200.0.113.227");
    private JLabel spuddhostLabel = new JLabel("host SPUDD");



    ValorDiscreto  vdflujovapor= new ValorDiscreto(30.0, 60.0, 6);
    ValorDiscreto vdpresiondomo = new ValorDiscreto(2000.0, 6000.0, 8);

    public void stop(){
         if(t != null){
                t.stop();
                t = null;
         }
       
    }

    public void finalizarSesion(){


                     JOptionPane.showMessageDialog(this, " S E S I O N    F I N A L I Z A D A ");

                     updateValorDB("STSTART", 4);
                     intentos = 0;
                     statusHilo   = false;
                     statusSesion = false;
                     itemExplicaciones.setText("Explanation");
                     activarExplicacion = false;


                     //CONTEO DE INTENTOS
                     manager.IntentoSesionBean isb = new  manager.IntentoSesionBean(DatosPersistence.emf);

                     int numIntentos = 0;
                     numIntentos = Integer.parseInt( String.valueOf( isb.getCountIntentos(idSesion)));

                     //guarda en la sesion el numero de intentos
                     manager.SesionesBean sb = new manager.SesionesBean(DatosPersistence.emf);
                     entity.Sesiones se = new entity.Sesiones();
                     se.setNumIntentos(numIntentos);
                     int status = sb.UpdateIntentos(se,idSesion);


                     if(numIntentos <= 2){

                         long maxLevel = sb.getMaxLevel(nombreUsuario);
                         
                                 if(maxLevel==3)
                                     return;
                                 else

                           JOptionPane.showMessageDialog(this, " HAS AVANZADO A UN NUEVO NIVEL ");


                             long nuevoNivel = 0;
                             nuevoNivel = sb.getIdNivelNotNull(idSesion);

                            if(nuevoNivel <= 0){

                                 manager.UserBean ub = new manager.UserBean(DatosPersistence.emf);
                                 entity.Usuario ue = ub.getNivelInicial(nombreUsuario);

                                 if(ue.getNivelusuario().getIdnivel() == 3)
                                     nuevoNivel = 3;
                                 else
                                     nuevoNivel = nuevoNivel + 1;

                             }else{

                                nuevoNivel = sb.getMaxLevel(nombreUsuario) + 1;
                             }

                             // avanza un nuevo nivel
                             entity.Nivelusuario nv= new entity.Nivelusuario();
                             nv.setIdnivel(nuevoNivel);
                             se.setNivelusuario(nv);
                             sb.UpdateNivel(se, idSesion);

                             se = sb.getNivelActual(nombreUsuario);


                    lbCaso.setText(se.getNivelusuario().getDescripcion());
                    lbModo.setText(se.getNivelusuario().getModosoperacion().getDescripcion());

                            if(lbCaso.getText().trim().equals("NOVATO")){
                                tiposCarga.setSelectedIndex(2);
                                idPerfil = 1;
                            }
                            else if(lbCaso.getText().trim().equals("INTERMEDIO")){
                                tiposCarga.setSelectedIndex(1);
                                idPerfil = 2;
                            }
                            else if(lbCaso.getText().trim().equals("AVANZADO")){
                                tiposCarga.setSelectedIndex(0);
                                idPerfil = 3;
                            }

                    tiposCarga.setEnabled(false);


                     }//fin de intentos
        
    }

    public void contruirNuevoMDP(){
        actualizaSettingsPlaneacion();

        if (planningTool == 0 || planningTool == 2)
                preparaSpudd();
        if (planningTool == 1 || planningTool == 2)
                preparaSpi();


          // Con esto nos preparamos para discretizar datos
            if (tipoMDP == 0 || tipoMDP == 2) { // si es discreto o hibrido
                atributosContinuos = tablas.fileToMatrix(archivoAtbsCont,
                    ":,\t ");
                atributosDiscretos = tablas.fileToMatrix(archivoAtbsDisc,
                 ":,\t ");
            }
            if (tipoMDP == 1 || tipoMDP == 2) { // si es cualitativo o hibrido

                mensajes.append("Recuperando particion cualitativa ... \n");
                miWeka arbol = new miWeka(folder + "/dTreeCont.arff");
                arbol.reetiquetaHojas();
                Nodo.displayTree(arbol.nodo, "\t");
                Cualificador cual = new Cualificador(arbol); // particion simple
                mensajes.append("Particion cualitativa recuperada");
           }
    }

    public void ReproduceAudio(int numRecomendacion){
        ReproduceAudio reproduceAudio = new ReproduceAudio(numRecomendacion);
    }

   public void run()//start Thread
   {
        contadorSesion = 1;
        try{
             work();
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(this, "interrupted in Thread");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"error: "+this.getClass() +"--"+e.getMessage());
        }
   }


   public void work() throws InterruptedException, Exception{
      //1= iniciar, 2 = reiniciar, 3 = "pausar"
         
           double[]  actuadoresAnt;
      while (statusHilo){

                if (dameValor("STSTART") == 1) {//inicia calculos si el simulador ya ha iniciado
                   double estado[] = obtenerDatos(setupVars);
                   actuadoresAnt   = leerActuadores(); //alamacena los valores de XFWV y XZ1

                   //variables para graficar en RewardChart
                   flujo   =(estado[2])*100-3000;
                   presion =(estado[1])-2000;
                   int [] estadoDiscreto = null;
                   if(asistenteActivo){
                       
                        updateValorDB("STSTART", 3);//pausa el simulador
                        obtenerDatos(setupVars);
                        estadoDiscreto= activarRecomendacion(estado,actuadoresAnt);//obtiene valores discretizados
                        updateValorDB("STSTART", 1 );//avanza el simulador

                            if(activarExplicacion){
                             
                                    if(!statusSesion){
                                         statusSesion = true;
                                         insertaNuevaSesion();
                                         itemReporte.setEnabled(true);
                                        
                                    }
                                    if(intentos == 10){ //intentos durante la sesion
                                         finalizarSesion();
                                         return;
                                    }

                                    intentos++;

                                    if( AccionEjecutada != politica ){
                                        mensajes.setText("COMETISTE UN ERROR");
                                        Explanation x = new Explanation(politica,estadoDiscreto, this.idPerfil);
                                        x.setVisible(true);

                                        manager.IntentoSesionBean isb = new manager.IntentoSesionBean(DatosPersistence.emf);
                                        entity.IntentosSesion ise = new entity.IntentosSesion();
                                        entity.Sesiones se = new entity.Sesiones();
                                        entity.Variables v = new entity.Variables();
                                        v.setIdvariable(x.getiDVariable());
                                        se.setIdsesion(idSesion);
                                        ise.setSesiones(se);
                                        ise.setVariables(v);
                                        ise.setRecomendacion(politica);
                                        ise.setAccion(AccionEjecutada);
                                        int status = isb.guardarIntentoSesion(ise);

                                        updateValorDB("STSTART",4);//detenemos el simulador
                                        obtenerDatos(setupVars);
                                        statusHilo = false;
                                        return;

                                     }

                            }
                   }

                   
                 
                }
                 Thread.sleep(sleep);
            
       }//fin while

     }
  




}
