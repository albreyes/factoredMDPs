package planta;



import DataBase.DatosPersistence;
import java.awt.*;
import java.io.File;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import utileria.ESObjetos;
import robotica.Punto;
import robotica.Celda;
import guis.CompilaFMDP;
import utileria.FileOutput01;
import utileria.MiMath;
import java.awt.event.*;
import java.util.StringTokenizer;

/**
 * @(#)TestApp.java
 *
 * JFC Sample application
 *
 * @author
 * @version 1.00 05/11/10
 */
public class RewardPlanta extends JFrame implements Runnable, MouseListener{

  private int tipoCarga;
  RewardPanel panel=new RewardPanel(this);
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();



  
  JLabel jLabel3 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JPasswordField jTextField5 = new JPasswordField();
  JLabel lbPort = new JLabel();
  JTextField txtPort = new JTextField();
  JLabel lbDB = new JLabel();
  JTextField txtDB = new JTextField();
  

  //bdd datos;
  Thread t=new Thread(this);
  int opcion=0;
  int ALTO=4000; int ANCHO=3000;
  JPanel jPanel6 = new JPanel();
  JButton jButton3 = new JButton();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JPanel jPanel2 = new JPanel();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenuItem jMenuItem2 = new JMenuItem();
  JPanel jPanel11 = new JPanel();
  VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
  JTextField jTextField6 = new JTextField();
  JPanel jPanel3 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JButton jButton1 = new JButton();
  JPanel jPanel5 = new JPanel();
  JLabel jLabel7 = new JLabel();
  JPanel jPanel7 = new JPanel();
  JButton jButton4 = new JButton();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel6 = new JLabel();
  JPanel jPanel12 = new JPanel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JTextField jTextField9 = new JTextField();
  JTextField jTextField7 = new JTextField();
  JPanel jPanel10 = new JPanel();
  JTextField jTextField8 = new JTextField();
  JLabel jLabel9 = new JLabel();
  JButton jButton6 = new JButton();
  JButton datos = new JButton();
  JPanel jPanel8 = new JPanel();
  JButton jButton5 = new JButton();
  JLabel jLabel11 = new JLabel();
  JPanel jPanel9 = new JPanel();
  JLabel jLabel10 = new JLabel();
  VerticalFlowLayout verticalFlowLayout3 = new VerticalFlowLayout();
  JMenuItem jMenuItem3 = new JMenuItem();
  private int contador = 0;
  private OptionsDB op;
  

  String ambienteFile="../ejemplos/powerPlant/rewCargaMedia.amb";


  public void run(){
     contador = contador +1;
     
    try {
      if (opcion == 1) graficar();
      else if(opcion== 4) exploration();
    }
    catch (InterruptedException ie) {}
  }

  public void exploration() throws InterruptedException{
   
      if(!jRadioButton2.isSelected())
      exploration(0);
      else exploration(1);
  }



  // tipo=0 5 acciones, tipo=1 9 acciones
  public void exploration(int tipo) throws InterruptedException {
  System.out.println(dataFile);
    FileOutput01 fo = new FileOutput01(dataFile);
   // fo.writeln("fms\tffw\td\tpd\tg\tmsv\tfwv\ta\tr");
    fo.writeln("GVR\t GWAD \tINTERC\tPVR\tWET3\tXZ1\tXFWV\tXZ2\tXDEAR" +
               "\tXBP\tXALIV\tGDEAR\tNDEAR\tNDO\tP1WA\tTGSR" +
               "\tA  R");
    // fwf,msf,pd,g,d,nd,fwv,msv1,msv2,pfwp,deal,recv,thrsg,atempv,pv2
  
    int noMuestras = Integer.parseInt(jTextField7.getText()); //numero de muestras

    // paso fwv
    double magnitud1 = Double.parseDouble(jTextField9.getText()); //porcentajes de apertura
    // paso msv
    double magnitud2 = Double.parseDouble(jTextField8.getText()); //porcentajes de apertura


    for (int i = 0; i < noMuestras; i++) {

        if(i > 6666 && i < 13333)
            updateValorDB("INTERC_R", 0);
        if(i >= 13333)
             updateValorDB("INTERC_R", 1);
         
        contador = contador + 1;
      double estadoPlanta[]=leeEstado();

      double flujo=estadoPlanta[0]*100-3000;
      double presion=estadoPlanta[3]-2000;
      double generacion=estadoPlanta[4];


      panel.actualizaPosicion((int)flujo,(int)presion,0);
      repaint();

      double r = panel.getReward(panel.x, panel.y);

      // puntos adicionales cuando la generacion es alta
      // esto solo aplica para carga media

    //  if(r!=0){
        if (generacion > 10000 && generacion < 15000)
          r += 100;
        else if (generacion >= 15000)
          r += 250;
    //  }

      int accion;

      if (tipo == 0)
        accion = (int) (Math.random() * 5);
      else
        accion = (int) (Math.random() * 9);

      // fms,ffw,d,pd,g,msv,fwv

     // double[] ar = new double[18];

          fo.writeln(""+estadoPlanta[0]+"\t"+
                     ""+estadoPlanta[1]+"\t"+
                    ""+estadoPlanta[2]+"\t"+
                    ""+estadoPlanta[3]+"\t"+
                    ""+estadoPlanta[4]+"\t"+
                    ""+estadoPlanta[5]+"\t"+
                    ""+estadoPlanta[6]+"\t"+
                    ""+estadoPlanta[7]+"\t"+
                    ""+estadoPlanta[8]+"\t"+
                    ""+estadoPlanta[9]+"\t"+
                    ""+estadoPlanta[10]+"\t"+
                    ""+estadoPlanta[11]+"\t"+
                    ""+estadoPlanta[12]+"\t"+
                    ""+estadoPlanta[13]+"\t"+
                    ""+estadoPlanta[14]+"\t"+
                    ""+estadoPlanta[15]+"\t"+
                     +accion+"\t"+(int)r);

      double magnitudRuidosa1 =  MiMath.gaussianWhiteNoise(25,
           magnitud1,
           magnitud1 * Double.parseDouble(jTextField10.getText()) /
                                         100.0);

      double magnitudRuidosa2 =  MiMath.gaussianWhiteNoise(25,
           magnitud2,
           magnitud2 * Double.parseDouble(jTextField10.getText()) /
                                         100.0);

      if(!jRadioButton1.isSelected()) {
        magnitudRuidosa1=magnitud1;
        magnitudRuidosa2=magnitud2;
      }

      if (tipo == 0)
        ejecutaAccion01(accion, magnitudRuidosa1, magnitudRuidosa2);
      else
        ejecutaAccion02(accion, magnitudRuidosa1, magnitudRuidosa2);

       t.sleep(Integer.parseInt(jTextField1.getText()));
    }
    fo.close();
   // t=new Thread(this);
  }


  // devuelve un arreglo de doubles de la forma [fwv, fmsv1, fmsv2];
  public double[] leeValvulas() {
        try {
            // todas las unidades en fraccion porcentual
            double fwv = dameValor("XFWV");
            double fmsv1 = dameValor("XZ1");
            double fmsv2 = dameValor("XZ2");
            double[] estadoVals = {fwv, fmsv1, fmsv2};
            return estadoVals;
        } catch (Exception ex) {
            Logger.getLogger(RewardPlanta.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


  public double checaRango(double magnitud){
    double valor=magnitud;
    if(magnitud>1.0) valor=1.0;
      else if(magnitud<0) valor=0;
        return valor;
  }

  // dada la accion y la magnitud se actualiza la bdd. version para 5 acciones
  // magnitud1->fwv magnitud2->msv
  public void ejecutaAccion01(int accion, double magnitud1, double magnitud2) {

  // recibe un arreglo de doubles de la forma [fwv, msv1, msv2];
  double[] estadoValvs=leeValvulas();

    // abrir fwv
    if(accion==0){
      double nuevoValor=checaRango(estadoValvs[0]+magnitud1/100);
     
     /* if(nuevoValor>0.90 || nuevoValor<0.10 )
          nuevoValor = 0.51256546;*/

      updateValorDB("XFWV_R", nuevoValor);
    } else
    // cerrar fwv
    if(accion==1){
      double nuevoValor=checaRango(estadoValvs[0]-magnitud1/100);
      
     /* if(nuevoValor>0.90 || nuevoValor<0.10 )
          nuevoValor = 0.51256546;*/
       updateValorDB("XFWV_R", nuevoValor);
    } else
    // abrir msv
    if(accion==2){
      double nuevoValor1=checaRango(estadoValvs[1]+magnitud2/100);

      /*if(nuevoValor1>0.90 || nuevoValor1<0.10 )
          nuevoValor1 = 0.31256546;*/
     // System.out.println("XZ1_R" + nuevoValor1);
      updateValorDB("XZ1_R", nuevoValor1);

    } else
    // cerrar msv
    if(accion==3){
      double nuevoValor1=checaRango(estadoValvs[1]-magnitud2/100);

      /* if(nuevoValor1>0.90 || nuevoValor1<0.10 )
          nuevoValor1 = 0.31256546;*/
      updateValorDB("XZ1_R", nuevoValor1);
    }
    // si accion es 4 no hace nada
  }

  // dada la accion y las magnitudes de cada valvula se actualiza la bdd.
  // version para 9 acciones: magnitud1->fwv magnitud2->fmsv
  public void ejecutaAccion02(int accion, double magnitud1, double magnitud2) {

  // recibe un arreglo de doubles de la forma [fwv, fmsv1, fmsv2];
    double[] estadoValvs=leeValvulas();

    // abrir fwv
    if(accion==0){
      double nuevoValor=checaRango(estadoValvs[0]+magnitud1/100);
      /**************************************************************/
     /* if(nuevoValor>0.90 || nuevoValor<0.10 )
          nuevoValor = 0.51256546;*/

       updateValorDB("XFWV_R", nuevoValor);
    } else
    // cerrar fwv
    if(accion==1){
      double nuevoValor=checaRango(estadoValvs[0]-magnitud1/100);
    /*  if(nuevoValor>0.90 || nuevoValor<0.10 )
          nuevoValor = 0.51256546;*/

      updateValorDB("XFWV_R", nuevoValor);
    } else
    // abrir msv
    if(accion==2){
      double nuevoValor1=checaRango(estadoValvs[1]+magnitud2/100);
    /* if(nuevoValor1>0.90 || nuevoValor1<0.10 )
          nuevoValor1 = 0.31256546;*/
      updateValorDB("XZ1_R", nuevoValor1);
    } else
    // cerrar msv
    if(accion==3){
      double nuevoValor1=checaRango(estadoValvs[1]-magnitud2/100);
    /* if(nuevoValor1>0.90 || nuevoValor1<0.10 )
          nuevoValor1 = 0.31256546;*/

      updateValorDB("XZ1_R", nuevoValor1);

    } else
    // abrir fwv, abrir fmsv
    if(accion==4){
      double nuevoValor=checaRango(estadoValvs[0]+magnitud1/100);
      
     /* if(nuevoValor>0.90 || nuevoValor<0.10 )
          nuevoValor = 0.61256546;*/
      
      updateValorDB("XFWV_R", nuevoValor);
      double nuevoValor1=checaRango(estadoValvs[1]+magnitud2/100);
     
    /*  if(nuevoValor1>0.90 || nuevoValor1<0.10 )
          nuevoValor1 = 0.31256546;*/
       updateValorDB("XZ1_R", nuevoValor1);

    } else
    // abrir fwv, cerrar fmsv
    if(accion==5){
      double nuevoValor=checaRango(estadoValvs[0]+magnitud1/100);
      /*if(nuevoValor>0.90 || nuevoValor<0.10 )
          nuevoValor = 0.61256546;*/
       updateValorDB("XFWV_R", nuevoValor);
      double nuevoValor1=checaRango(estadoValvs[1]-magnitud2/100);
   /* if(nuevoValor1>0.90 || nuevoValor1<0.10 )
          nuevoValor1 = 0.31256546;*/

        updateValorDB("XZ1_R", nuevoValor1);
    } else
    // cerrar fwv, abrir fmsv
    if(accion==6){
      double nuevoValor=checaRango(estadoValvs[0]-magnitud1/100);
     
      /*if(nuevoValor>0.90 || nuevoValor<0.10 )
          nuevoValor = 0.61256546;*/

      updateValorDB("XFWV_R", nuevoValor);
      double nuevoValor1=checaRango(estadoValvs[1]+magnitud2/100);
      
      /*if(nuevoValor1>0.90 || nuevoValor1<0.10 )
          nuevoValor1 = 0.31256546;*/

      updateValorDB("XZ1_R", nuevoValor1);
    } else
    // cerrar fwv, cerrar fmsv
    if(accion==7){
      double nuevoValor=checaRango(estadoValvs[0]-magnitud1/100);
      
      /*if(nuevoValor>0.90 || nuevoValor<0.10 )
          nuevoValor = 0.61256546;*/

      updateValorDB("XFWV_R", nuevoValor);
      double nuevoValor1=checaRango(estadoValvs[1]-magnitud2/100);

       /*if(nuevoValor1>0.90 || nuevoValor1<0.10 )
          nuevoValor1 = 0.31256546;*/

      updateValorDB("XZ1_R", nuevoValor1);
    }
    // si accion es 8 no hace nada
  }

  // devuelve un arreglo de doubles de la forma [fms,ffw,d,pd,g]
  public double[] leeEstado() {
        try {
            //System.out.println(Conexion1.getCon());
            // unidades en Kg/s
            double ffw = dameValor("GWAD");
            // unidades logicas 1 = int cerrado
            double d = dameValor("INTERC");
            // generacion en MW
            double g = dameValor("WET3");
            // unidades en Kg/s
            double fms = dameValor("GVR");
            // unidades en KPa
            double pd = dameValor("PVR");
            // unidades en %
            double msv = dameValor("XZ1");
            // unidades en %
            double fwv = dameValor("XFWV");
            double xz2 = dameValor("XZ2");
            double xdear = dameValor("XDEAR");
            double xbp = dameValor("XBP");
            double xaliv = dameValor("XALIV");
            double gdar = dameValor("GDEAR");
            double ndear = dameValor("NDEAR");
            double ndo = dameValor("NDO");
            double pvra = dameValor("P1WA");
            double tgsr = dameValor("TGSR");
            double[] estado = {fms, ffw, d, pd, g, msv, fwv, xz2, xdear, xbp, xaliv, gdar, ndear, ndo, pvra, tgsr};
            return estado;
        } catch (Exception ex) {
            Logger.getLogger(RewardPlanta.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
    
   
  }


  public void graficar(){

    while(opcion==1) {
      double[] estado=leeEstado();
      double flujo=estado[0]*100-3000;
      double presion=estado[3]-2000;
      panel.actualizaPosicion((int)flujo,(int)presion,0);
      if (jTextField1.getText().equals(""))
        panel.timeDelay(500);
      else
        panel.timeDelay(Integer.parseInt(jTextField1.getText()));
      repaint();
      }
 
    t=new Thread(this);
  }


    /**
     * The constructor.
     */
     public RewardPlanta(int tipoCarga) {

        setTitle("Diagrama de Recompensa de Planta");
        // Add window listener.
        
        op = new OptionsDB();
        setTipoCarga(tipoCarga);
        
        show();
    try {
      jbInit();
       panel.addMouseListener(this);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    }

     public void setTipoCarga(int tipoCarga){
        this.tipoCarga = tipoCarga;//sql.consulta(Conexion1.getCon(),"tabla_ctcc","valor"," variable = 'TIPOSIM'" );

        switch(tipoCarga){
            case 0:
                ambienteFile="../ejemplos/powerPlant/rewCargaNominal.amb";
            break;
            case 1:
                ambienteFile="../ejemplos/powerPlant/rewCargaMedia.amb";
            break;
            case 2:
                ambienteFile="../ejemplos/powerPlant/rewCargaMinima.amb";
            break;
        }
       
     }


    /**
     * Shutdown procedure when run as an application.
     */
    protected void windowClosed() {

    	// TODO: Check if it is safe to close the application

        // Exit application.
        this.dispose();
    }

    public static void main(String[] args) {

        // Create application frame.
        RewardPlanta frame = new RewardPlanta(0);
        frame.setSize(520, 540);
        frame.setVisible(true);

    }

    
  private void jbInit() throws Exception {

     
    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    this.getContentPane().setLayout(borderLayout1);

    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu();
    JMenu menuConecta = new JMenu();
    JMenuItem menuFileExit = new JMenuItem();
    JMenuItem itemConecta = new JMenuItem();


    menuFile.setText("Archivo");
    menuConecta.setText("View");
    itemConecta.setText("Conexion");
    menuFileExit.setText("Salir");

    if(DatosPersistence.getConexion()!=null)
        menuConecta.setVisible(true);

    // Add action listener.for the menu button
    menuFileExit.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RewardPlanta.this.windowClosed();
            }
        }
    );
    jMenuItem1.setText("Guardar Problema");
    jMenuItem2.setText("Recuperar Problema");
    jPanel11.setLayout(verticalFlowLayout2);
    jTextField6.setText("500");
    jTextField6.setColumns(4);
    jLabel2.setText("Celda de Recompensa");
    jButton1.setText("aceptar");
    jButton1.addActionListener(new RewardPlanta_jButton1_actionAdapter(this));
    jLabel7.setText("dim");
    jButton4.setText("premiar");
    jButton4.addActionListener(new RewardPlanta_jButton4_actionAdapter(this));
    jTextField2.setText("500");
    jTextField2.setColumns(4);
    jLabel6.setText("valor");
    jPanel11.setBorder(BorderFactory.createEtchedBorder());
    jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel8.setText("Exploraci�n");
    jLabel12.setText("fwv");
    jTextField9.setText("2");
    jTextField9.setColumns(3);
    jTextField7.setText("20000");
    jTextField7.setColumns(5);
    jTextField8.setText("2");
    jTextField8.setColumns(3);
    jLabel9.setText("no. muestras");
    jButton6.setEnabled(false);
    jButton6.setRolloverEnabled(false);
    jButton6.setText("explorar");
    datos.setText("Ver datos");
    jButton6.addActionListener(new RewardPlanta_jButton6_actionAdapter(this));
    datos.addActionListener(new RewardPlanta_datos_actionAdapter(this));
    //jButton5.setEnabled(false);
    jButton5.setText("archivo");
    jButton5.addActionListener(new RewardPlanta_jButton5_actionAdapter(this));
    jLabel11.setText("msv");
    jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel10.setText("paso v�lvulas (%)");
    jPanel12.setLayout(verticalFlowLayout3);
    jPanel12.setBorder(BorderFactory.createEtchedBorder());
    jMenuItem3.setText("Compilar MDP");
    
    //jButton3.setEnabled(false);



    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });

    // recuperar problema
    jMenuItem2.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              //ambienteFile="";
              ambienteFile=dialogoAbrirArchivo();

                  if(!ambienteFile.equals("")){
                    panel.celdas = (Vector) ESObjetos.leeObjeto(ambienteFile);
                    repaint();
                  }
            }
        }
    );



    jButton7.setText("conectar");
    
    jButton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton7_actionPerformed(e);
      }
    });

    //jButton7.addActionListener(new RewardPlanta_jButton7_actionAdapter(this));
    jPanel1.setBorder(null);
    jLabel13.setText("ruido (%)");
    jTextField10.setText("10");
    jRadioButton1.setHorizontalAlignment(SwingConstants.CENTER);
    jRadioButton1.setSelected(true);
    jRadioButton1.setText("agregar ruido");
    jRadioButton2.setHorizontalAlignment(SwingConstants.CENTER);
    jRadioButton2.setSelected(true);
    jRadioButton2.setText("op. combinada valvs");
    menuFile.add(jMenuItem3);
    menuFile.add(jMenuItem2);
    menuFile.add(jMenuItem1);
    menuConecta.add(itemConecta);
    menuFile.add(menuFileExit);
    menuBar.add(menuFile);
    menuBar.add(menuConecta);

    // guardar problema
    jMenuItem1.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              ambienteFile=dialogoGuardarArchivo();
              if(!ambienteFile.equals(""))
              ESObjetos.escribeObjeto(panel.celdas,ambienteFile);
            }
        }
    );

    jMenuItem1.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              ambienteFile=dialogoGuardarArchivo();
              if(!ambienteFile.equals(""))
              ESObjetos.escribeObjeto(panel.celdas,ambienteFile);
            }
        }
    );

    itemConecta.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              op.setVisible(true);
            }
        }
    );
    
      jMenuItem3.addActionListener
    (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              new CompilaFMDP();
            }
        }
    );


    setJMenuBar(menuBar);


    jPanel1.setLayout(verticalFlowLayout1);
    
    jButton3.setText("graficar");


   // jButton3.addActionListener(new RewardPlanta_jButton3_actionAdapter(this));


    jLabel1.setText("frec. muestreo (ms)");
    jTextField1.setText("500");
    jTextField1.setColumns(3);
    jPanel6.setBorder(BorderFactory.createEtchedBorder());
   
    this.getContentPane().add(jPanel1, BorderLayout.EAST);
    jPanel1.add(jPanel11, null);
    jPanel5.add(jLabel7, null);
    jPanel5.add(jTextField2, null);
    jPanel5.add(jButton1, null);
    jPanel1.add(jPanel12, null);
    jPanel10.add(jLabel11, null);
    jPanel10.add(jTextField8, null);
    jPanel10.add(jLabel12, null);
    jPanel10.add(jTextField9, null);
    jPanel12.add(jLabel8, null);
    jPanel12.add(jRadioButton2, null);
    jPanel12.add(jRadioButton1, null);
    jPanel12.add(jPanel13, null);
    jPanel13.add(jLabel13, null);
    jPanel13.add(jTextField10, null);
    jPanel13.add(jPanel14, null);
    jPanel12.add(jPanel8, null);
    jPanel12.add(jLabel10, null);
    jPanel12.add(jPanel10, null);
    jPanel8.add(jLabel9, null);
    jPanel8.add(jTextField7, null);
    jPanel9.add(jButton5, null);
    jPanel9.add(jButton6, null);
   // jPanel9.add(datos, null);
    jPanel11.add(jPanel3, null);
    jPanel3.add(jLabel2, null);
    jPanel11.add(jPanel7, null);
    jPanel7.add(jLabel6, null);
    jPanel7.add(jTextField6, null);
    jPanel7.add(jButton4, null);
    jPanel11.add(jPanel5, null);
    this.getContentPane().add(panel,  BorderLayout.CENTER);
    
    this.getContentPane().add(jPanel6, BorderLayout.NORTH);
    jPanel2.add(jLabel1, null);
    jPanel2.add(jTextField1, null);
    jPanel12.add(jPanel9, null);
    jPanel6.add(jButton3, null);
    jPanel6.add(jPanel2, null);
    panel.actualizaDatos(ANCHO,ALTO,200,15);
    panel.celdas = (Vector) ESObjetos.leeObjeto(ambienteFile);

  }

  public String dialogoAbrirArchivo() {
    String filename = "";
    JFileChooser chooser = new JFileChooser("../ejemplos");

    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      filename = file.getAbsolutePath();
    }
    if(filename.equals("")){
        return filename = "";
    }
    else
        return filename;
  }


  public String dialogoGuardarArchivo() {
    String filename = "";
    JFileChooser chooser = new JFileChooser("../ejemplos");

    int returnVal = chooser.showSaveDialog(this);
    if (returnVal == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      filename = file.getAbsolutePath();
    }
    return filename;
  }


  // actualiza celdas
  void jButton1_actionPerformed(ActionEvent e) {
    panel.actualizaDatos(ANCHO,ALTO,Integer.parseInt(jTextField2.getText()),15);
    repaint();
  }

  void jButton3_actionPerformed(ActionEvent e) {


    if(jButton3.getText().equals("graficar")){
        opcion = 1;
        if(t==null)
            t = new Thread(this);

            t.start();


    } else {
        opcion=0;
        t.stop();
        t=new Thread(this);
    }

    if(jButton3.getText().equals("graficar"))
      jButton3.setText("detener");
      else jButton3.setText("graficar");
  }

  // premiar celda
  void jButton4_actionPerformed(ActionEvent e) {

    Punto p=new Punto(panel.x,panel.y );

    for(int i=0; i<panel.celdas.size();i++){
      Celda c=(Celda) panel.celdas.elementAt(i);
      if(Punto.miembro(p,c)){
        c.recompensa=Integer.parseInt(jTextField6.getText());
        break;
      }
    }
    repaint();
  }

  String dataFile;
  JButton jButton7 = new JButton();
  JPanel jPanel13 = new JPanel();
  JLabel jLabel13 = new JLabel();
  JTextField jTextField10 = new JTextField();
  JPanel jPanel14 = new JPanel();
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();

  void jButton5_actionPerformed(ActionEvent e) {
    
    dataFile=dialogoAbrirArchivo();
    if(dataFile.equals("")){
        JOptionPane.showMessageDialog(this,"Debe seleccionar un archivo");
    }else jButton6.setEnabled(true);
  }

  void jButton7_actionPerformed(ActionEvent e) {
    /*   // Conexion.getConexion();
        db = new Conexion1(jTextField3.getText(),jTextField4.getText(),jTextField5.getText(),"3306","simulacion2",1);

        AsistOGui.mensajes.setText(db.getMensaje());
        jButton3.setEnabled(true);
        jButton5.setEnabled(true);*/
  }

  void jButton6_actionPerformed(ActionEvent e) {
       
    if(jButton6.getText().equals("explorar")){
      opcion = 4;

      if(t.isAlive()) {
          t.stop();
          t=new Thread(this);
      }

      t.start();
    } else {

      opcion=0;
        t.stop();
        t=new Thread(this);
    }

    if(jButton6.getText().equals("explorar"))
      jButton6.setText("detener");
      else jButton6.setText("explorar");

  }

   void datos_actionPerformed(ActionEvent e) {
   
       System.out.println(contador);

   }

   private void updateValorDB(String variable, double valor){
         try{
            manager.TablaCtccBean tb = new manager.TablaCtccBean(DatosPersistence.emf);
            tb.UpdateValor(variable, valor);
         }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error al actualizar la variable \n consulte a su administrador");
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
   
   private void btConectaActionPerformed(java.awt.event.ActionEvent evt) {
                op.setDatos();
                DatosPersistence.getConexion();
                DatosPersistence.DatosPersistence_(op.getIP(),op.getUser(),op.getPass(),op.getPort(),op.getDataBase(),op.getInstancia(),op.getTipoServer());
 
                if( DatosPersistence.getConexion()!=null){
                        if(t==null){
                                t = new Thread(this);
                                t.start();
                        }
                        op.dispose();
                }

    }

    public void mouseClicked(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
       
    }

     public void mousePressed(MouseEvent e) {

          int x = e.getX();
          int y = e.getY();

         Dimension dim=panel.getSize();

        int h=(x-dim.width/4) * panel.escala2;
        int v=(7*dim.height/8-y) * panel.escala2;

   StringTokenizer st=new StringTokenizer(jTextField2.getText()," ");
   double[] reward=new double[st.countTokens()];

   int i=0;
   while(st.hasMoreTokens()){
     reward[i]=Double.parseDouble(st.nextToken());
     i++;
         }


   double rew=reward[0];
   System.out.println("X: " + e.getX());
   System.out.println("Y: " + e.getY());
   panel.setGoal(panel.celdas,  rew, new Punto(h,v));

     }


}

class RewardPlanta_jButton3_actionAdapter implements java.awt.event.ActionListener {
  RewardPlanta adaptee;

  RewardPlanta_jButton3_actionAdapter(RewardPlanta adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class RewardPlanta_jButton5_actionAdapter implements java.awt.event.ActionListener {
  RewardPlanta adaptee;

  RewardPlanta_jButton5_actionAdapter(RewardPlanta adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}

class RewardPlanta_jButton1_actionAdapter implements java.awt.event.ActionListener {
  RewardPlanta adaptee;

  RewardPlanta_jButton1_actionAdapter(RewardPlanta adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class RewardPlanta_jButton4_actionAdapter implements java.awt.event.ActionListener {
  RewardPlanta adaptee;

  RewardPlanta_jButton4_actionAdapter(RewardPlanta adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}

class RewardPlanta_jButton7_actionAdapter implements java.awt.event.ActionListener {
  RewardPlanta adaptee;

  RewardPlanta_jButton7_actionAdapter(RewardPlanta adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton7_actionPerformed(e);
  }
}

class RewardPlanta_jButton6_actionAdapter implements java.awt.event.ActionListener {
  RewardPlanta adaptee;

  RewardPlanta_jButton6_actionAdapter(RewardPlanta adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton6_actionPerformed(e);
  }
  
}

class RewardPlanta_datos_actionAdapter implements java.awt.event.ActionListener {
  RewardPlanta adaptee;

  RewardPlanta_datos_actionAdapter(RewardPlanta adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.datos_actionPerformed(e);
  }
  
  
}


