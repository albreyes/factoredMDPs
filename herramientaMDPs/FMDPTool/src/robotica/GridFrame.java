package robotica;

import com.borland.jbcl.layout.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Vector;
import java.util.StringTokenizer;
import ia.FMDP;
import guis.CompilaFMDP;
import DataStructuresQ.Cualificador;
import DataStructuresQ.Cualitativos;
import aprendizaje.miWeka;
import utileria.Listas;
import utileria.MiMath;
import utileria.tablas;
import utileria.ESObjetos;
import utileria.FileOutput01;
import utileria.Dialogos;
import robotica.AreaNavegacion;
import robotica.Graficas;
import robotica.Estado;
import robotica.Celda;
import robotica.Punto;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class GridFrame extends JFrame implements Runnable, MouseListener {
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  public AreaNavegacion jPanel2 = new AreaNavegacion(this);
  int[] estadoD;
  String dataFile, atributosFile;
  int opcion=0; // default es 0
  int ancho;
  int alto;
  int tamano;
  int escala;
  FMDP fmdp;
  Cualificador cual;
  String folder="";
  miWeka arbol;


  Thread t=new Thread(this);

  public GridFrame() {
    try {
      jbInit();

    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

// eventos de MouseListener
  public void mousePressed(MouseEvent e) {


    int x = e.getX();
    int y = e.getY();

    Dimension dim=jPanel2.getSize();

    int h=(x-dim.width/4)*escala;
    int v=(7*dim.height/8-y)*escala;

    // formo arrglo con valores de reward
   StringTokenizer st=new StringTokenizer(jTextField3.getText()," ");
   double[] reward=new double[st.countTokens()];

   int i=0;
   while(st.hasMoreTokens()){
     reward[i]=Double.parseDouble(st.nextToken());
     i++;
         }


   double rew=reward[0];

    System.out.println("h: "+ h+ " v: "+v);
    setGoal(jPanel2.celdas,  rew, new Punto(h,v));

  }

  // se quedan para usos futuros
  public void mouseReleased(MouseEvent e){}
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mouseClicked(MouseEvent e){}



  public static void main(String[] args) {

    GridFrame gridFrame = new GridFrame();
    gridFrame.setSize(1000, 650);
    gridFrame.setVisible(true);

  }


  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenu1 = new JMenu();
  JMenu jMenu2 = new JMenu();
  JMenu jMenu3 = new JMenu();
  JMenu jMenu4 = new JMenu();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenuItem jMenuItem2 = new JMenuItem();
  JMenuItem jMenuItem3 = new JMenuItem();
  JMenuItem jMenuItem4 = new JMenuItem();

  ButtonGroup buttonGroup1 = new ButtonGroup();
  ButtonGroup buttonGroup2 = new ButtonGroup();
  ButtonGroup buttonGroup3 = new ButtonGroup();


  private void jbInit() throws Exception {

    jPanel2.addMouseListener(this);
    this.getContentPane().setLayout(borderLayout1);

    jMenu1.setText("Archivo");
    jMenu2.setText("Ambiente");
    jMenu3.setText("Planificador");
    jMenu4.setText("Acerca de");
    jMenuItem1.setText("cerrar");
    jMenuItem2.setText("salir");

    jMenuItem3.setText("guardar");
    jMenuItem4.setText("recuperar");

    jMenuItem5.setText("compilar");
    jMenu5.setText("ver");
    jMenuItem7.setActionCommand("política");
    jMenuItem7.setText("política");
    jMenuItem8.setText("utilidad");
    jMenuItem9.setText("recompensa");
    jMenuItem10.setText("estados");
    jLabel12.setText("alto (cm)");
    jButton6.setToolTipText("");
    jButton6.setText("Genera Problema");

    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });

    jTextField9.setText("50");
    jTextField9.setColumns(3);
    jLabel11.setText("ancho (cm)");
    jTextField10.setText("30");
    jTextField10.setColumns(3);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("Datos Ambientales");
    jTextField7.setText("1000");
    jTextField7.setColumns(4);
    jLabel13.setText("tamaño celda (cm)");
    jLabel14.setText("escala (%)");
    jTextField8.setText("1000");
    jTextField8.setColumns(4);
    jPanel19.setLayout(verticalFlowLayout3);
    jPanel19.setBorder(BorderFactory.createEtchedBorder());
    jPanel21.setLayout(verticalFlowLayout4);
    jTextField6.setText("50");
    jTextField6.setColumns(3);
    jLabel8.setText("paso lineal (cm)");
    jTextField13.setColumns(3);
    jTextField13.setEditable(false);
    jTextField13.setText("0");
    jLabel16.setText("ang");
    jLabel7.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel7.setText("Datos Robot");
    jLabel17.setText("paso ang (grados)");
    jButton12.setText("Cambiar posición-orientación");
    jButton12.addActionListener(new GridFrame_jButton12_actionAdapter(this));
    jLabel6.setText("x");
    jTextField5.setText("0");
    jTextField5.setColumns(4);
    jTextField14.setText("45");
    jTextField14.setColumns(3);
    jTextField4.setText("0");
    jTextField4.setColumns(4);
    jLabel10.setText("cm");
    jLabel5.setText("y");
    jPanel21.setBorder(BorderFactory.createEtchedBorder());
    jRadioButton4.setText("refinada");
    jRadioButton1.setText("discreta");
    jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton1_actionPerformed(e);
      }
    });

    jLabel18.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel18.setText("partición");
    jRadioButton2.setSelected(true);
    jRadioButton2.setText("cualitativa");
    jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton2_actionPerformed(e);
      }
    });
    jRadioButton3.setEnabled(true);
    jRadioButton3.setSelected(true);
    jRadioButton3.setText("simple");
    jPanel24.setLayout(verticalFlowLayout5);
    jLabel19.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel19.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel19.setText("Exploración");
    jPanel25.setLayout(verticalFlowLayout6);
    jRadioButton6.setText("angular");
    jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton6_actionPerformed(e);
      }
    });
    jRadioButton5.setSelected(true);
    jRadioButton5.setText("ortogonal");
    jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton5_actionPerformed(e);
      }
    });
    jTextField12.setText("10000");
    jTextField12.setColumns(5);
    jLabel2.setText("time step (ms)");
    jTextField1.setText("25");
    jTextField1.setColumns(5);
    jLabel9.setText("No. muestras");
    jPanel25.setBorder(BorderFactory.createEtchedBorder());
    jButton4.setEnabled(false);
    jButton4.setText("Detener");

    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });

    jButton18.setText("Archivo");

    jButton18.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton18_actionPerformed(e);
      }
    });

    jTextField15.setEditable(false);
    jTextField15.setColumns(10);
    jPanel24.setBorder(BorderFactory.createEtchedBorder());
    jButton5.setText("Restablecer");

    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });

    jButton24.setEnabled(false);
    jButton24.setText("Borrar");

    jButton24.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton24_actionPerformed(e);
      }
    });

    jButton24.addActionListener(new GridFrame_jButton24_actionAdapter(this));
    jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel4.setText("recompensas");
    jTextField3.setText("300 -300");
    jTextField3.setColumns(8);
    jTextField16.setEditable(false);
    jTextField16.setText("");
    jTextField16.setColumns(10);


    jLabel20.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel20.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel20.setText("Planificador SPI");
    jButton2.setEnabled(false);
    jButton2.setText("Ver Rastro");

    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });

    jButton2.addActionListener(new GridFrame_jButton2_actionAdapter(this));
    jButton1.setEnabled(false);
    jButton1.setText("Explorar");

    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });

    jButton12.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton12_actionPerformed(e);
      }
    });

    jButton1.addActionListener(new GridFrame_jButton1_actionAdapter(this));
    jTextField17.setText("10");
    jTextField17.setColumns(4);
    jLabel21.setText("(%)");
    jButton3.setText("Carpeta");

    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });


    jButton13.setText("Cargar");
    jButton13.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton13_actionPerformed(e);
      }
    });
    jButton16.setEnabled(false);
    jButton16.setText("Simular");
    jButton16.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton16_actionPerformed(e);
      }
    });
    jRadioButton7.setText("ortogonal2");
    jRadioButton8.setText("angular2");
    jRadioButton9.setSelected(true);
    jRadioButton9.setText("ruido");
    jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton9_actionPerformed(e);
      }
    });
    jMenuBar1.add(jMenu1);
    jMenuBar1.add(jMenu2);
    jMenuBar1.add(jMenu3);
    jMenuBar1.add(jMenu4);
    jMenu1.add(jMenuItem1);
    jMenu1.add(jMenuItem2);
    jMenu2.add(jMenuItem3);
    jMenu2.add(jMenuItem4);

    buttonGroup1.add(jRadioButton1);     buttonGroup1.add(jRadioButton2);
    buttonGroup2.add(jRadioButton3);     buttonGroup2.add(jRadioButton4);
    buttonGroup3.add(jRadioButton5);     buttonGroup3.add(jRadioButton6);
    buttonGroup3.add(jRadioButton7);     buttonGroup3.add(jRadioButton8);

    jPanel1.setLayout(verticalFlowLayout1);
    this.setTitle("Ambiente de Navegacion");
    jLabel3.setText("Time Step");

    jTextField2.setColumns(5);
    jTextField2.setText("25");

    jPanel4.setLayout(verticalFlowLayout2);
    jTextField11.setText("1000");
    jLabel15.setText("alto (cm)");


    this.getContentPane().add(jPanel1, BorderLayout.WEST);
    jPanel1.add(jPanel19, null);
    jPanel8.add(jLabel12, null);
    jPanel8.add(jTextField8, null);
    jPanel19.add(jLabel1, null);
    jPanel19.add(jPanel7, null);
    jPanel7.add(jLabel11, null);
    jPanel7.add(jTextField7, null);
    jPanel19.add(jPanel8, null);
    jPanel19.add(jPanel10, null);
    jPanel10.add(jLabel14, null);
    jPanel10.add(jTextField10, null);
    jPanel19.add(jPanel9, null);
    jPanel9.add(jLabel13, null);
    jPanel9.add(jTextField9, null);
    jPanel19.add(jPanel27, null);
    jPanel27.add(jLabel4, null);
    jPanel27.add(jTextField3, null);
    jPanel19.add(jButton6, null);
    jPanel19.add(jPanel17, null);
    jPanel17.add(jButton24, null);
    jPanel17.add(jButton5, null);
    jPanel1.add(jPanel21, null);
    jPanel6.add(jLabel8, null);
    jPanel6.add(jTextField6, null);

    jPanel21.add(jLabel7, null);
    jPanel21.add(jPanel5, null);
    jPanel21.add(jButton12, null);
    jPanel21.add(jPanel6, null);
    jPanel21.add(jPanel20, null);

    jPanel20.add(jLabel17, null);
    jPanel20.add(jTextField14, null);
    jPanel21.add(jPanel14, null);
    jPanel14.add(jRadioButton9, null);
    jPanel14.add(jLabel21, null);
    jPanel14.add(jTextField17, null);
    jPanel5.add(jLabel6, null);
    jPanel5.add(jTextField5, null);
    jPanel5.add(jLabel5, null);
    jPanel5.add(jTextField4, null);
    jPanel5.add(jLabel10, null);
    jPanel5.add(jLabel16, null);
    jPanel5.add(jTextField13, null);

    this.getContentPane().add(jPanel2,  BorderLayout.CENTER);
    this.getContentPane().add(jPanel4,  BorderLayout.EAST);


    jPanel12.add(jTextField16, null);
    jPanel12.add(jButton3, null);
    jPanel22.add(jRadioButton1, null);
    jPanel22.add(jRadioButton2, null);

    jPanel24.add(jLabel20, null);
    jPanel24.add(jPanel12, null);
    jPanel24.add(jLabel18, null);
    jPanel24.add(jPanel22, null);
    jPanel24.add(jPanel23, null);

    jPanel24.add(jPanel16, null);
    jPanel16.add(jButton13, null);
    jPanel16.add(jButton16, null);

    jPanel23.add(jRadioButton3, null);
    jPanel23.add(jRadioButton4, null);

    jPanel4.add(jPanel25, null);
    jPanel4.add(jPanel24, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(jTextField1, null);

    jPanel13.add(jButton1, null);
    jPanel13.add(jButton2, null);
    jPanel25.add(jLabel19, null);
    jPanel25.add(jPanel18, null);
    jPanel18.add(jTextField15, null);
    jPanel18.add(jButton18, null);
    jPanel11.add(jLabel9, null);
    jPanel11.add(jTextField12, null);
    jPanel25.add(jPanel26, null);
    jPanel26.add(jRadioButton5, null);
    jPanel26.add(jRadioButton6, null);
    jPanel25.add(jPanel15, null);
    jPanel15.add(jRadioButton7, null);
    jPanel15.add(jRadioButton8, null);

    jPanel25.add(jPanel11, null);
    jPanel25.add(jPanel3, null);
    jPanel25.add(jPanel13, null);
    jPanel25.add(jButton4, null);

    jMenu3.add(jMenuItem5);
    jMenu3.add(jMenu5);
    jMenu5.add(jMenuItem7);
    jMenu5.add(jMenuItem8);
    jMenu5.add(jMenuItem9);
    jMenu5.add(jMenuItem10);

    // cerrar
    jMenuItem1.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              if(t.isAlive()) t.stop();
              dispose();
            }
        }
    );

    // salir
    jMenuItem2.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              System.exit(0);
            }
        }
    );

    // guardar ambiente
    jMenuItem3.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              String ambienteFolder = Dialogos.dialogoGuardarDir("","../ejemplos");
              if(!ambienteFolder.equals("")){
                ESObjetos.escribeObjeto(jPanel2.celdas, ambienteFolder+"/ambiente");
                dataFile = ambienteFolder + "/ejemplos.dat";
                jTextField15.setText(dataFile);
                jButton1.setEnabled(true);
                jButton4.setEnabled(true);

                escribeAtFileCont(ancho, alto, Listas.string2Array(jTextField3.getText()),ambienteFolder);
                escribeAtFileDis(ancho, alto, ancho/tamano, alto/tamano,
                        Listas.string2Array(jTextField3.getText()),ambienteFolder);
              }
            }
        }
    );
    // recuperar ambiente
    jMenuItem4.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {

              String ambienteFile = Dialogos.dialogoAbrirArchivo("", "../ejemplos");
              String ambienteFolder=new File(ambienteFile).getParent();
              if(!ambienteFile.equals("")){

                recuperarDatosAmbiente(ambienteFolder)  ;
                inicia();

                jPanel2.celdas = (Vector) ESObjetos.leeObjeto(ambienteFile);
                dataFile = ambienteFolder + "/ejemplos.dat";
                jTextField15.setText(dataFile);
                jButton1.setEnabled(true);
                jButton4.setEnabled(true);

                repaint();
              }
            }
        }
    );

    // compilar
    jMenuItem5.addActionListener
    (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              new CompilaFMDP();
            }
        }
    );

    // ver politica
    jMenuItem7.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String[] polSt;
               String[] polSt1 = {
                    "→", "←", "↑", "↓", ""};
                String[] polSt2 = {
                    "go", "rt", "lt"};

              if(jRadioButton5.isSelected())
                polSt=polSt1;
                else polSt=polSt2;


              if(jRadioButton1.isSelected()){ // caso discreto

                for (int i = 0; i < jPanel2.celdas.size(); i++)
                  Graficas.graficaLetrero(jPanel2, polSt[fmdp.politica[estadoD[i]]],
                                          ( (Celda) jPanel2.celdas.elementAt(i)).
                                          puntoCentral, escala);
                  // registra las celdas en disco
                for(int i=0; i<jPanel2.celdas.size();i++){
                  Celda c=(Celda)jPanel2.celdas.elementAt(i);
                  c.recompensa=fmdp.politica[estadoD[i]];
                }
                ESObjetos.escribeObjeto(jPanel2.celdas,folder+"/celdasDiscPol.obj");

              }
              else {// caso cualitativo
                Graficas.graficaPolitica(jPanel2, ancho, alto, escala,
                                         arbol.getConstrains(),
                                         fmdp.politica, polSt);
                // registra las celdas en disco
                Vector  celdasPol= Cualitativos.getCeldas(arbol.getConstrains(),
                                                        Listas.intToDouble(fmdp.politica),
                                                        new Dimension(ancho, alto));
                ESObjetos.escribeObjeto(celdasPol,folder+"/celdasQPol.obj");
              }

            }
        }
    );

    // ver utilidad
    jMenuItem8.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {

              if(jRadioButton2.isSelected()){ // caso cualitativo
                jPanel2.celdas = Cualitativos.getCeldas(arbol.getConstrains(),
                                                        fmdp.utilidad,
                                                        new Dimension(ancho, alto));
                ESObjetos.escribeObjeto(jPanel2.celdas,folder+"/celdasQUtil.obj");
              }
              else{ // caso discreto
                for(int i=0; i<jPanel2.celdas.size();i++){
                  Celda c=(Celda)jPanel2.celdas.elementAt(i);
                  c.recompensa=fmdp.utilidad[estadoD[i]];
                }
                ESObjetos.escribeObjeto(jPanel2.celdas,folder+"/celdasDiscUtil.obj");
              }

              repaint();
            }
        }
    );

    // ver recompensa
    jMenuItem9.addActionListener
    (
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {

              if(jRadioButton2.isSelected())
              jPanel2.celdas=Cualitativos.getCeldas(arbol.getConstrains(),fmdp.reward,new Dimension(ancho, alto)) ;
              else{
                for(int i=0; i<jPanel2.celdas.size();i++){
                  Celda c=(Celda)jPanel2.celdas.elementAt(i);
                  c.recompensa=fmdp.reward[estadoD[i]];
                }

              }
              repaint();
            }
        }
    );

    // ver estados
    jMenuItem10.addActionListener
        (
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jRadioButton1.isSelected()) { // caso discreto

          for (int i = 0; i < jPanel2.celdas.size(); i++)
            Graficas.graficaLetrero(jPanel2, ""+estadoD[i],
                                    ( (Celda) jPanel2.celdas.elementAt(i)).
                                    puntoCentral, escala);


        }
        else // caso cualitativo
          Graficas.graficaParticiones(jPanel2, ancho, alto, escala,
                                      arbol.getConstrains());
      }
    }
    );

    setJMenuBar(jMenuBar1);
    inicia();
  }


  void inicia(){

    ancho = 10 * Integer.parseInt(jTextField7.getText());
    alto = 10 * Integer.parseInt(jTextField8.getText());
    tamano = 10 * Integer.parseInt(jTextField9.getText());
    escala = Integer.parseInt(jTextField10.getText());

    jPanel2.actualizaDatos(ancho, alto, tamano, escala);

  }

  // recuperar datos ambiente
  void recuperarDatosAmbiente(String folderAmbiente){
    String[][] datos=tablas.fileToMatrix(folderAmbiente+"/atributos.txt",":, /t");
    ancho= Integer.parseInt(datos[0][3])/10;
    jTextField7.setText(""+ancho);
    alto= Integer.parseInt(datos[1][3])/10;
    jTextField8.setText(""+alto);
    tamano=((Celda)jPanel2.celdas.elementAt(0)).size/10;
    jTextField9.setText(""+tamano);
    String s="";
    for(int i=2; i<datos[datos.length-1].length; i++)
      s=s+datos[datos.length-1][i]+" ";
    jTextField3.setText(s);
  }

  // escribe archivo de atributos
  void escribeAtFileDis(int maxX, int maxY, int partesX, int partesY,
                        String[] recompensas, String ambienteFolder) {
    FileOutput01 fo = new FileOutput01(ambienteFolder + "/atributosDis.txt");

    if(partesX>1 && partesY>1){
      fo.write("x: S0");
      for (int i = 1; i < partesX; i++)
        fo.write(",S" + i);
      fo.writeln("");
      fo.write("y: S0");
      for (int i = 1; i < partesY; i++)
        fo.write(",S" + i);
      fo.writeln("");
      fo.write("r: 0");

      for (int i = 0; i < recompensas.length; i++)
        fo.write("," + recompensas[i]);

      fo.close();
    }
  }

  // escribe archivo de atributos continuos
  void escribeAtFileCont(int maxX, int maxY, String[] recompensas, String ambienteFolder){
    FileOutput01 fo=new FileOutput01(ambienteFolder+"/atributos.txt");

    fo.writeln("x:real,0,"+maxX);
    fo.writeln("y:real,0,"+maxY);
    fo.write("r: 0");

    for(int i=0; i<recompensas.length; i++)
      fo.write(","+recompensas[i]);

    fo.close();
  }
  // actualiza area de navegacion
  void jButton5_actionPerformed(ActionEvent e) {

    inicia();
    repaint();

  }

  JPanel jPanel4 = new JPanel();
  JLabel jLabel3 = new JLabel();

  JTextField jTextField2 = new JTextField();
  VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();

  // muestra el rastro del agente navegando
  public void runFile() throws InterruptedException{

  String[][] datos = tablas.fileToMatrix(dataFile, "\t");
  for (int i = 1; i < datos.length; i++) {

    int x = Integer.parseInt(datos[i][0]);
    int y = Integer.parseInt(datos[i][1]);
    int ang = Integer.parseInt(datos[i][2]);

    System.out.println("x " + x + " y " + y + " ang " + ang);
    Graficas.graficaRastro(jPanel2,x,y,escala);
    t.sleep(Integer.parseInt(jTextField1.getText()));
  }
  t=new Thread(this);
}

// acciones: 0 der, 1 izq 2 arriba 3 abajo 4 nada
public boolean enRango1(int x, int y, int accion, int magnitud){

  int ancho=Integer.parseInt(jTextField7.getText())*10;
  int alto=Integer.parseInt(jTextField8.getText())*10;

  if(accion==0) x+=magnitud;
    else if(accion==1) x-=magnitud;
      else if(accion==2) y+=magnitud;
        else if(accion==3) y-=magnitud;

  if(x>=0&&x<=ancho&&y>=0&&y<=alto) return true;
  else return false;
}

// acciones: 0 avanzar, 1 rotar_der 2 rotar_izq 3 nula
public boolean enRango2(int x, int y, int ang, int accion, int magnitud){

  int ancho=Integer.parseInt(jTextField7.getText())*10;
  int alto=Integer.parseInt(jTextField8.getText())*10;

  if(accion==0){
    x=x+(int)(((double)magnitud)*Math.cos(Math.toRadians(ang)));
    y=y+(int)(((double)magnitud)*Math.sin(Math.toRadians(ang)));
  }

  if(x>=0&&x<=ancho&&y>=0&&y<=alto) return true;
  else return false;
}

// para acciones der, izq, arriba, abajo con giro
public void actualizaEstado1(int accionRobot, int magnitud) {

  if (accionRobot == 0)
    jPanel2.mov.derecha(magnitud);
  else if (accionRobot == 1)
    jPanel2.mov.izquierda(magnitud);
  else if (accionRobot == 2)
    jPanel2.mov.arriba(magnitud);
  else if (accionRobot == 3)
    jPanel2.mov.abajo(magnitud);

  jPanel2.actualizaPosicion(jPanel2.mov.x, jPanel2.mov.y, jPanel2.mov.ang);
}

// para acciones avanzar, rotar-der, rotar-izq, nula
public void actualizaEstado2(int accionRobot, int magnitudLineal, int magnitudAngular) {

  if (accionRobot == 0)
    jPanel2.mov.avanzar(magnitudLineal);
  else if (accionRobot == 1)
    jPanel2.mov.rotarDer(magnitudAngular);
  else if (accionRobot == 2)
    jPanel2.mov.rotarIzq(magnitudAngular);

  jPanel2.actualizaPosicion(jPanel2.mov.x, jPanel2.mov.y, jPanel2.mov.ang);
}


public void randomWalk1() throws InterruptedException {

  FileOutput01 fo=new FileOutput01(dataFile);
    fo.writeln("x\ty\ta\tr");
    //fo.writeln("x\ty\tang\ta\tr");
  int noMuestras = Integer.parseInt(jTextField12.getText());
  int magnitud = Integer.parseInt(jTextField6.getText()) * 10;

  for (int i = 0; i < noMuestras; i++) {

    int accion = (int) (Math.random() * 5);

    int magnitudRuidosa = (int) MiMath.gaussianWhiteNoise(25,
        (double) magnitud,
        (double) magnitud * Double.parseDouble(jTextField17.getText()) /
                                       100.0);

    if(!jRadioButton9.isSelected()) magnitudRuidosa=magnitud;

    if (enRango1(jPanel2.x, jPanel2.y, accion, magnitudRuidosa)) {
      double r = jPanel2.getReward(jPanel2.x, jPanel2.y);
      /*
      System.out.println("x " + jPanel2.x + " y " + jPanel2.y + " ang " +
                         jPanel2.ang + " accion " +
                         accion + " r " + r);
      fo.writeln(jPanel2.x + "\t" + jPanel2.y + "\t" + jPanel2.ang + "\t" +
                 accion + "\t" + (int) r);
*/

  //    System.out.println("x " + jPanel2.x + " y " + jPanel2.y + " accion " +
             //            accion + " r " + r);
      fo.writeln(jPanel2.x+"\t"+jPanel2.y+"\t"+accion+"\t"+(int)r);

     actualizaEstado1(accion, magnitudRuidosa);
      repaint();
      t.sleep(Integer.parseInt(jTextField1.getText()));
    }
    else i--;
  }
  fo.close();
  t=new Thread(this);
}

  public void randomWalk2(){

    FileOutput01 fo=new FileOutput01(dataFile);
    fo.writeln("x\ty\tang\ta\tr");
    int noMuestras = Integer.parseInt(jTextField12.getText());
    int magnitudLineal = Integer.parseInt(jTextField6.getText()) * 10;
    int magnitudAngular = Integer.parseInt(jTextField14.getText());

    for (int i = 0; i < noMuestras; i++) {
      int accionRobot=(int)(Math.random()*4.0);

      int magnitudRuidosaLineal = (int) MiMath.gaussianWhiteNoise(25,
          (double) magnitudLineal,
          (double) magnitudLineal * Double.parseDouble(jTextField17.getText()) /
          100.0);
     // System.out.println("magnitud ruidosa " + magnitudRuidosaLineal);

      int magnitudRuidosaAngular = (int) MiMath.gaussianWhiteNoise(25,
          (double) magnitudAngular,
          (double) magnitudAngular * Double.parseDouble(jTextField17.getText()) /
          100.0);
    //  System.out.println("magnitud ruidosa " + magnitudRuidosaAngular);

        if(!jRadioButton9.isSelected()) {
          magnitudRuidosaLineal = magnitudLineal;
          magnitudRuidosaAngular = magnitudAngular;
        }

      if(enRango2(jPanel2.mov.x,jPanel2.mov.y,jPanel2.mov.ang,accionRobot,magnitudRuidosaLineal)){

        double r = jPanel2.getReward(jPanel2.x, jPanel2.y);
    //    System.out.println("x " + jPanel2.x + " y " + jPanel2.y + " ang " +
                 //          jPanel2.ang + " accion " +
                  //         accionRobot + " r " + r);
        fo.writeln(jPanel2.x + "\t" + jPanel2.y + "\t" + jPanel2.ang + "\t" +
                   accionRobot + "\t" + (int) r);
        actualizaEstado2(accionRobot,magnitudRuidosaLineal, magnitudRuidosaAngular);
        jPanel2.timeDelay(Integer.parseInt(jTextField1.getText()));
        repaint();
      }
      else i--;
    }
    fo.close();
    t=new Thread(this);
  }

  public void randomWalk3() throws InterruptedException {

    FileOutput01 fo=new FileOutput01(dataFile);
    fo.writeln("x\ty\tang\ta\tr");
    int noMuestras = Integer.parseInt(jTextField12.getText());
    int magnitud = Integer.parseInt(jTextField6.getText()) * 10;

    for (int i = 0; i < noMuestras; i++) {

      int accion = (int) (Math.random() * 5);

      int magnitudRuidosa = (int) MiMath.gaussianWhiteNoise(25,
          (double) magnitud,
          (double) magnitud * Double.parseDouble(jTextField17.getText()) /
                                         100.0);

      if(!jRadioButton9.isSelected()) magnitudRuidosa=magnitud;

      if (enRango1(jPanel2.x, jPanel2.y, accion, magnitudRuidosa)) {
        double r = jPanel2.getReward(jPanel2.x, jPanel2.y);

        if(jPanel2.ang==0) r+=2*r;
          else if(jPanel2.ang==180) r+=0.5*r;
            else if(jPanel2.ang==270) r+=0.25*r;

        System.out.println("x " + jPanel2.x + " y " + jPanel2.y + " ang " +
                           jPanel2.ang + " accion " +
                           accion + " r " + r);
        fo.writeln(jPanel2.x + "\t" + jPanel2.y + "\t" + jPanel2.ang + "\t" +
                   accion + "\t" + (int) r);

       actualizaEstado1(accion, magnitudRuidosa);
        repaint();
        t.sleep(Integer.parseInt(jTextField1.getText()));
      }
      else i--;
    }
    fo.close();
    t=new Thread(this);
  }

  public void randomWalk4(){

    FileOutput01 fo=new FileOutput01(dataFile);
    fo.writeln("x\ty\tang\ta\tr");
    int noMuestras = Integer.parseInt(jTextField12.getText());
    int magnitudLineal = Integer.parseInt(jTextField6.getText()) * 10;
    int magnitudAngular = Integer.parseInt(jTextField14.getText());

    for (int i = 0; i < noMuestras; i++) {
      int accionRobot=(int)(Math.random()*4.0);

      int magnitudRuidosaLineal = (int) MiMath.gaussianWhiteNoise(25,
          (double) magnitudLineal,
          (double) magnitudLineal * Double.parseDouble(jTextField17.getText()) /
          100.0);
     // System.out.println("magnitud ruidosa " + magnitudRuidosaLineal);

      int magnitudRuidosaAngular = (int) MiMath.gaussianWhiteNoise(25,
          (double) magnitudAngular,
          (double) magnitudAngular * Double.parseDouble(jTextField17.getText()) /
          100.0);
    //  System.out.println("magnitud ruidosa " + magnitudRuidosaAngular);

    if(!jRadioButton9.isSelected()) {
      magnitudRuidosaLineal = magnitudLineal;
      magnitudRuidosaAngular = magnitudAngular;
    }


      if(enRango2(jPanel2.mov.x,jPanel2.mov.y,jPanel2.mov.ang,accionRobot,magnitudRuidosaLineal)){

        double r = jPanel2.getReward(jPanel2.x, jPanel2.y);
        if(jPanel2.ang>=0&&jPanel2.ang<180) r=2*r;

        /*
        if(jPanel2.ang>=315&&jPanel2.ang<45) r=5*r;
          else if(jPanel2.ang>=45&&jPanel2.ang<135) r=4*r;
            else if(jPanel2.ang>=135&&jPanel2.ang<225) r=3*r;
              else if(jPanel2.ang>=225&&jPanel2.ang<315) r=2*r;*/

    //    System.out.println("x " + jPanel2.x + " y " + jPanel2.y + " ang " +
                 //          jPanel2.ang + " accion " +
                  //         accionRobot + " r " + r);
        fo.writeln(jPanel2.x + "\t" + jPanel2.y + "\t" + jPanel2.ang + "\t" +
                   accionRobot + "\t" + (int) r);
        actualizaEstado2(accionRobot,magnitudRuidosaLineal, magnitudRuidosaAngular);
        jPanel2.timeDelay(Integer.parseInt(jTextField1.getText()));
        repaint();
      }
      else i--;
    }
    fo.close();
    t=new Thread(this);
  }



  public void ejecutaPolitica() throws
      InterruptedException {

    String[] acciones = { "right", "left","up", "down", "none" };

    int magnitud = Integer.parseInt(jTextField6.getText()) * 10;

    boolean enMeta = false;

    while (!enMeta) {

      String[] estadoSt = {"" + jPanel2.x, "" + jPanel2.y};
      int edoDis=-1;

      int[] estado=null;

      if(jRadioButton1.isSelected()){

        //ValorDiscreto.discretizVar()
      // es discreto
      Punto pun= new Punto(jPanel2.x, jPanel2.y);

      for(int i=0; i<jPanel2.celdas.size(); i++){
        Celda cell=(Celda) jPanel2.celdas.elementAt(i);

        if(Punto.miembro(pun, cell)){
          edoDis=i;
          break;
        }
      }
       int[] edoD= fmdp.s[estadoD[edoDis]];
      // pasar edoDis a modo arreglo y guardarlo en estadoD.
       //int[] estadoD={1,1};
       estado=edoD;
      }
      else{
        // es cualitativo
        int[] estadoQ= {cual.cualifica(estadoSt)};
        estado=estadoQ;
      }

      System.out.println("estado "+ edoDis);
      Listas.imprimeLista(estado);

      int aux = fmdp.consultaPolitica(estado);
      if(aux==4) enMeta=true;

      String pol = acciones[aux];
      System.out.println("politica "+aux+"  "+pol);

      actualizaEstado1(aux, magnitud);
      repaint();
      t.sleep(Integer.parseInt(jTextField1.getText()));

    }

    t=new Thread(this);
  }

  public void run() {

    try {

      if (opcion == 0) randomWalk1();
      else if (opcion == 1) runFile();
      else if(opcion== 2) ejecutaPolitica();
      else if(opcion== 3) randomWalk2();
      else if(opcion== 4) randomWalk3();
      else if(opcion== 5) randomWalk4();

    }
    catch (InterruptedException ie) {}

  }
  // random walk
  void jButton1_actionPerformed(ActionEvent e) {

    jButton2.setEnabled(true);
    if(jRadioButton5.isSelected()) opcion=0;
    else if(jRadioButton6.isSelected()) opcion=3;
      else if(jRadioButton7.isSelected()) opcion=4;
        else if(jRadioButton8.isSelected()) opcion=5;
      try{
        t.start();
      }
      catch(IllegalThreadStateException itse){}
      folder=new File(dataFile).getParent();
      jTextField16.setText(folder);

  }



  // graficar rastro
  void jButton2_actionPerformed(ActionEvent e) {
    opcion=1;

    try{
      t.start();
    }
    catch(IllegalThreadStateException itse){}
}

// seleccionar carpeta del MDP
  void jButton3_actionPerformed(ActionEvent e) {
    folder = Dialogos.dialogoAbrirDir("","../ejemplos");
    if(!folder.equals("")){
      jButton16.setEnabled(true);
      jButton24.setEnabled(true);
      jTextField16.setText(folder);

   //   setMDP();
    }

  }

  void jButton4_actionPerformed(ActionEvent e) {
    t.stop ();
    t=new Thread(this);
    opcion=0;
    jButton2.setEnabled(true);
  }


  // genera problema
  void jButton6_actionPerformed(ActionEvent e) {

    // formo arrglo con valores de reward
    StringTokenizer st=new StringTokenizer(jTextField3.getText()," ");
    double[] reward=new double[st.countTokens()];

    int i=0;
    while(st.hasMoreTokens()){
      reward[i]=Double.parseDouble(st.nextToken());
      i++;
          }

    // genera problema
    Celda.setGoals(jPanel2.celdas, reward, new Vector());

   //setGoals();
    repaint();
  }

  // asigna valor de recompensa a celda del mapa que contenga al punto
  public void setGoal(Vector map, double reward, Punto punto) {

    for (int i = 0; i < map.size(); i++) {
      Celda c = (Celda) map.elementAt(i);
      if (Punto.miembro(punto, c))
        c.setReward(reward);
    }

    repaint();
  }

  // modifica las celdas del ambiente
  void setGoals(){


    Vector v=new Vector();

/*
    // 1 sola region en la esquina inferior izquierda
    v.addElement(new Celda( new Punto(500,4500), new Dimension(1000,1000),  300));
*/
/*
    // este caso es para poner celdas en la frontera
    v.addElement(new Celda( new Punto(1500,0), new Dimension(1000,100),  -300));
    v.addElement(new Celda( new Punto(5000,500), new Dimension(100,1000),  100));
    v.addElement(new Celda( new Punto(5000,1500), new Dimension(100,1000),  -100));
*/

 /*
    // esto hace un espejo del problema
    v.addElement(new Celda( new Punto(1500,500), new Dimension(1000,1000),  -300));
    v.addElement(new Celda( new Punto(4500,500), new Dimension(1000,1000),  100));
    v.addElement(new Celda( new Punto(4000,1500), new Dimension(2000,1000),  -100));
 */


    v.addElement(new Celda( new Punto(2000,2000), new Dimension(4000,2000),  985));

    v.addElement(new Celda( new Punto(4500,3500), new Dimension(1000,1000),  839));
    v.addElement(new Celda( new Punto(4500,2500), new Dimension(1000,1000),  1008));
   v.addElement(new Celda( new Punto(4500,1500), new Dimension(1000,1000),  1544));

   v.addElement(new Celda( new Punto(500,500), new Dimension(1000,1000),  1828));
   v.addElement(new Celda( new Punto(1500,500), new Dimension(1000,1000),  1695));
   v.addElement(new Celda( new Punto(2500,500), new Dimension(1000,1000),  1622));
   v.addElement(new Celda( new Punto(3500,500), new Dimension(1000,1000),  1622));
   v.addElement(new Celda( new Punto(4500,500), new Dimension(1000,1000),  2102));
//   v.addElement(new Celda( new Punto(5500,500), new Dimension(1000,1000),  1694));

    // aqui eliminamos celdas anteriores
    for (int j = 0; j<jPanel2.celdas.size(); j++) {
      Celda cell = (Celda) jPanel2.celdas.elementAt(j);

      for (int k = 0; k < v.size(); k++) {
        Celda cellN = (Celda) v.elementAt(k);

        if (Punto.miembro(cell.puntoCentral, cellN)){
          jPanel2.celdas.removeElementAt(j);
          j--;
        }
      }
    }

    // aqui agregamos celdas nuevas
    for (int j = 0; j < v.size(); j++)
      jPanel2.celdas.insertElementAt(((Celda) v.elementAt(j)), 0);

  }

  // cargar planificador
  void jButton13_actionPerformed(ActionEvent e) {
    if(!folder.equals(""))
      setMDP();
  }

  // recupera ambiente

  JTextField jTextField11 = new JTextField();
  JLabel jLabel15 = new JLabel();

  // posicion robot
  void jButton12_actionPerformed(ActionEvent e) {
    int x=Integer.parseInt(jTextField5.getText())*10;
    int y=Integer.parseInt(jTextField4.getText())*10;
    int ang=Integer.parseInt(jTextField13.getText());
    jPanel2.mov.actualizaCoordenadas(x,y,ang);
    jPanel2.actualizaPosicion(x,y,ang);
    repaint();
  }




  // Muestra particion


  JMenuItem jMenuItem5 = new JMenuItem();
  JMenu jMenu5 = new JMenu();
  JMenuItem jMenuItem7 = new JMenuItem();
  JMenuItem jMenuItem8 = new JMenuItem();
  JMenuItem jMenuItem9 = new JMenuItem();
  JMenuItem jMenuItem10 = new JMenuItem();
  JPanel jPanel19 = new JPanel();
  JLabel jLabel12 = new JLabel();
  JButton jButton6 = new JButton();
  JPanel jPanel8 = new JPanel();
  JTextField jTextField9 = new JTextField();
  JLabel jLabel11 = new JLabel();
  JTextField jTextField10 = new JTextField();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField7 = new JTextField();
  JLabel jLabel13 = new JLabel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JLabel jLabel14 = new JLabel();
  JTextField jTextField8 = new JTextField();
  JPanel jPanel9 = new JPanel();
  VerticalFlowLayout verticalFlowLayout3 = new VerticalFlowLayout();
  JPanel jPanel21 = new JPanel();
  VerticalFlowLayout verticalFlowLayout4 = new VerticalFlowLayout();
  JTextField jTextField6 = new JTextField();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField13 = new JTextField();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JPanel jPanel6 = new JPanel();
  JButton jButton12 = new JButton();
  JLabel jLabel6 = new JLabel();
  JPanel jPanel20 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField14 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JPanel jPanel24 = new JPanel();
  JRadioButton jRadioButton4 = new JRadioButton();
  JPanel jPanel22 = new JPanel();
  JRadioButton jRadioButton1 = new JRadioButton();
  JLabel jLabel18 = new JLabel();
  JRadioButton jRadioButton2 = new JRadioButton();
  JRadioButton jRadioButton3 = new JRadioButton();
  JPanel jPanel23 = new JPanel();
  VerticalFlowLayout verticalFlowLayout5 = new VerticalFlowLayout();
  JPanel jPanel25 = new JPanel();
  JLabel jLabel19 = new JLabel();
  VerticalFlowLayout verticalFlowLayout6 = new VerticalFlowLayout();
  JPanel jPanel26 = new JPanel();
  JRadioButton jRadioButton6 = new JRadioButton();
  JRadioButton jRadioButton5 = new JRadioButton();
  JTextField jTextField12 = new JTextField();
  JPanel jPanel3 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JPanel jPanel11 = new JPanel();
  JLabel jLabel9 = new JLabel();
  JButton jButton4 = new JButton();
  JPanel jPanel18 = new JPanel();
  JButton jButton18 = new JButton();
  JTextField jTextField15 = new JTextField();
  JPanel jPanel17 = new JPanel();
  JButton jButton5 = new JButton();
  JButton jButton24 = new JButton();
  JPanel jPanel27 = new JPanel();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JPanel jPanel12 = new JPanel();
  JTextField jTextField16 = new JTextField();
  JPanel jPanel16 = new JPanel();
  JLabel jLabel20 = new JLabel();
  JButton jButton2 = new JButton();
  JPanel jPanel13 = new JPanel();
  JButton jButton1 = new JButton();
  JPanel jPanel14 = new JPanel();
  JTextField jTextField17 = new JTextField();
  JLabel jLabel21 = new JLabel();
  JButton jButton3 = new JButton();
  JButton jButton13 = new JButton();
  JButton jButton16 = new JButton();
  JPanel jPanel15 = new JPanel();
  JRadioButton jRadioButton7 = new JRadioButton();
  JRadioButton jRadioButton8 = new JRadioButton();
  JRadioButton jRadioButton9 = new JRadioButton();


  // ejecuta politica
  void jButton16_actionPerformed(ActionEvent e) {
    jButton4.setEnabled(true);
    opcion=2;
    try{
      t.start();
    }
    catch(IllegalThreadStateException itse){}
  }

  void setMDP(){

      fmdp = (FMDP) ESObjetos.leeObjeto(folder + "/fmdp.obj");
      jPanel2.celdas = (Vector) ESObjetos.leeObjeto(folder+"/ambiente");
      repaint();

      if(jRadioButton2.isSelected()){ // caso cualitativo

        if(jRadioButton3.isSelected())
        arbol = new miWeka(folder + "/dTreeCont.arff"); // particion simple
        else // particion refinada
          arbol = new miWeka(folder + "/dTreeCont.arff", folder + "/particion.obj");
        cual = new Cualificador(arbol);
      }
      else{ // caso discreto
       // String atContFilename=dialogoAbrirArchivo();
       // String atDisFilename=dialogoAbrirArchivo();

        estadoD=Estado.getEstadosDiscretos(jPanel2.celdas,fmdp.s, folder+"/atributos.txt", folder+"/atributosDis.txt");

      }
  }

  // set archivo de exploracion
  void jButton18_actionPerformed(ActionEvent e) {

    dataFile=Dialogos.dialogoAbrirArchivo("","../ejemplos");
    jButton2.setEnabled(true);
    jButton4.setEnabled(true);
    jTextField15.setText(dataFile);
    folder=new File(dataFile).getParent();
    jTextField16.setText(folder);
  }

  // borrar
  void jButton24_actionPerformed(ActionEvent e) {
    repaint();
  }

  void jRadioButton1_actionPerformed(ActionEvent e) {
    jRadioButton3.setEnabled(false);
    jRadioButton4.setEnabled(false);
  }

  void jRadioButton2_actionPerformed(ActionEvent e) {
    jRadioButton3.setEnabled(true);
    jRadioButton4.setEnabled(true);
  }

  void jRadioButton5_actionPerformed(ActionEvent e) {
    jTextField13.setEditable(false);
  }

  void jRadioButton6_actionPerformed(ActionEvent e) {
    jTextField13.setEditable(true);
  }

  void jRadioButton9_actionPerformed(ActionEvent e) {
     if(jRadioButton9.isSelected())
       jTextField17.setEditable(true);
       else jTextField17.setEditable(false);
  }

}

class GridFrame_jButton13_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton13_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton13_actionPerformed(e);
  }
}

class GridFrame_jButton12_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton12_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton12_actionPerformed(e);
  }

}

class GridFrame_jButton16_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton16_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton16_actionPerformed(e);
  }
}

class GridFrame_jButton1_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton1_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class GridFrame_jButton2_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton2_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class GridFrame_jButton18_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton18_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton18_actionPerformed(e);
  }
}

class GridFrame_jButton4_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton4_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}

class GridFrame_jButton3_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton3_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class GridFrame_jButton5_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton5_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}

class GridFrame_jButton24_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton24_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton24_actionPerformed(e);
  }
}

class GridFrame_jButton6_actionAdapter implements java.awt.event.ActionListener {
  GridFrame adaptee;

  GridFrame_jButton6_actionAdapter(GridFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton6_actionPerformed(e);
  }
}
