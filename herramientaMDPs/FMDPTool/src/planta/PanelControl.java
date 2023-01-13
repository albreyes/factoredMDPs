package planta;

import DataBase.DatosPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author  liliana.sanchez & alondra.nava
 */
public class PanelControl extends javax.swing.JFrame implements Runnable {

    private boolean rechazoValor = false;
    double dato;
    public Thread hilo = null;
    public boolean inicia=true;
    private String nomVar;

    /** Creates new form PanelControl */
    public PanelControl()
    {
        initComponents();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sensor = new javax.swing.ButtonGroup();
        pnContenedor = new javax.swing.JPanel();
        pnControles = new javax.swing.JPanel();
        pnRechazo = new javax.swing.JPanel();
        lbRec = new javax.swing.JLabel();
        btnRechazo = new javax.swing.JButton();
        lbRechazo = new javax.swing.JLabel();
        pnSensores = new javax.swing.JPanel();
        lbSensores = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        XFWV = new javax.swing.JRadioButton();
        GWAD = new javax.swing.JRadioButton();
        NDO = new javax.swing.JRadioButton();
        XZ1 = new javax.swing.JRadioButton();
        XALIV = new javax.swing.JRadioButton();
        GDEAR = new javax.swing.JRadioButton();
        WET3 = new javax.swing.JRadioButton();
        TGSR1 = new javax.swing.JRadioButton();
        GVR = new javax.swing.JRadioButton();
        XZ2 = new javax.swing.JRadioButton();
        PVR = new javax.swing.JRadioButton();
        NDEAR = new javax.swing.JRadioButton();
        rbVerValvulas = new javax.swing.JRadioButton();
        soper = new javax.swing.JToggleButton();
        soper2 = new javax.swing.JToggleButton();
        lbPanelOper = new javax.swing.JLabel();
        pnInformacion = new javax.swing.JPanel();
        lbRuido = new javax.swing.JLabel();
        lbInconRuido = new javax.swing.JLabel();
        lbFalla = new javax.swing.JLabel();
        lbIconFalla = new javax.swing.JLabel();
        lbSensor = new javax.swing.JLabel();
        lbDescSensor = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        sname = new javax.swing.JLabel();
        sdesc = new javax.swing.JLabel();
        oper = new javax.swing.JLabel();

        sensor.add(XFWV);
        sensor.add(GWAD);
        sensor.add(NDO);
        sensor.add(PVR);
        sensor.add(XALIV);
        sensor.add(XZ1);
        sensor.add(XZ2);
        sensor.add(GVR);
        sensor.add(TGSR1);
        sensor.add(GDEAR);
        sensor.add(WET3);
        sensor.add(TGSR1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panel de operación");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnContenedor.setLayout(null);

        pnControles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Controles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        pnControles.setLayout(null);

        pnRechazo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnRechazo.setLayout(null);

        lbRec.setText("Rechazo de Carga");
        pnRechazo.add(lbRec);
        lbRec.setBounds(10, 10, 160, 17);

        btnRechazo.setText("Cerrar");
        btnRechazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechazoActionPerformed(evt);
            }
        });
        pnRechazo.add(btnRechazo);
        btnRechazo.setBounds(81, 33, 90, 30);

        lbRechazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola2.png"))); // NOI18N
        pnRechazo.add(lbRechazo);
        lbRechazo.setBounds(20, 30, 31, 32);

        pnControles.add(pnRechazo);
        pnRechazo.setBounds(20, 60, 180, 80);

        pnSensores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnSensores.setLayout(null);

        lbSensores.setFont(new java.awt.Font("Verdana", 0, 14));
        lbSensores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSensores.setText("Selección de sensores");
        pnSensores.add(lbSensores);
        lbSensores.setBounds(90, 10, 230, 18);
        pnSensores.add(jSeparator1);
        jSeparator1.setBounds(11, 36, 365, 10);

        XFWV.setFont(new java.awt.Font("Verdana", 0, 14));
        XFWV.setSelected(true);
        XFWV.setText("XFWV");
        XFWV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XFWVActionPerformed(evt);
            }
        });
        pnSensores.add(XFWV);
        XFWV.setBounds(21, 53, 90, 22);

        GWAD.setFont(new java.awt.Font("Verdana", 0, 14));
        GWAD.setText("GWAD");
        GWAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GWADActionPerformed(evt);
            }
        });
        pnSensores.add(GWAD);
        GWAD.setBounds(21, 83, 90, 22);

        NDO.setFont(new java.awt.Font("Verdana", 0, 14));
        NDO.setText("NDO");
        NDO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NDOActionPerformed(evt);
            }
        });
        pnSensores.add(NDO);
        NDO.setBounds(21, 110, 90, 22);

        XZ1.setFont(new java.awt.Font("Verdana", 0, 14));
        XZ1.setText("XZ1");
        XZ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XZ1ActionPerformed(evt);
            }
        });
        pnSensores.add(XZ1);
        XZ1.setBounds(112, 110, 90, 22);

        XALIV.setFont(new java.awt.Font("Verdana", 0, 14));
        XALIV.setText("XALIV");
        XALIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XALIVActionPerformed(evt);
            }
        });
        pnSensores.add(XALIV);
        XALIV.setBounds(112, 83, 80, 22);

        GDEAR.setFont(new java.awt.Font("Verdana", 0, 14));
        GDEAR.setText("GDEAR");
        GDEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GDEARActionPerformed(evt);
            }
        });
        pnSensores.add(GDEAR);
        GDEAR.setBounds(292, 53, 110, 22);

        WET3.setFont(new java.awt.Font("Verdana", 0, 14));
        WET3.setText("WET3");
        WET3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WET3ActionPerformed(evt);
            }
        });
        pnSensores.add(WET3);
        WET3.setBounds(290, 80, 110, 22);

        TGSR1.setFont(new java.awt.Font("Verdana", 0, 14));
        TGSR1.setText("TGSR");
        TGSR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TGSR1ActionPerformed(evt);
            }
        });
        pnSensores.add(TGSR1);
        TGSR1.setBounds(199, 110, 90, 22);

        GVR.setFont(new java.awt.Font("Verdana", 0, 14));
        GVR.setText("GVR");
        GVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GVRActionPerformed(evt);
            }
        });
        pnSensores.add(GVR);
        GVR.setBounds(199, 83, 90, 22);

        XZ2.setFont(new java.awt.Font("Verdana", 0, 14));
        XZ2.setText("XZ2");
        XZ2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XZ2ActionPerformed(evt);
            }
        });
        pnSensores.add(XZ2);
        XZ2.setBounds(199, 53, 90, 22);

        PVR.setFont(new java.awt.Font("Verdana", 0, 14));
        PVR.setText("PVR");
        PVR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PVRActionPerformed(evt);
            }
        });
        pnSensores.add(PVR);
        PVR.setBounds(112, 53, 90, 22);

        NDEAR.setFont(new java.awt.Font("Verdana", 0, 14));
        NDEAR.setText("NDEAR");
        NDEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NDEARActionPerformed(evt);
            }
        });
        pnSensores.add(NDEAR);
        NDEAR.setBounds(290, 110, 110, 22);

        pnControles.add(pnSensores);
        pnSensores.setBounds(230, 20, 410, 160);

        rbVerValvulas.setSelected(true);
        rbVerValvulas.setText("Ver llaves de valvulas");
        pnControles.add(rbVerValvulas);
        rbVerValvulas.setBounds(20, 170, 200, 22);

        soper.setFont(new java.awt.Font("Verdana", 0, 12));
        soper.setText("Aplicar Ruido");
        soper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soperActionPerformed(evt);
            }
        });
        pnControles.add(soper);
        soper.setBounds(310, 190, 120, 28);

        soper2.setFont(new java.awt.Font("Verdana", 0, 12));
        soper2.setText("Aplicar Falla");
        soper2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soper2ActionPerformed(evt);
            }
        });
        pnControles.add(soper2);
        soper2.setBounds(450, 190, 120, 28);

        pnContenedor.add(pnControles);
        pnControles.setBounds(10, 60, 690, 240);

        lbPanelOper.setFont(new java.awt.Font("Tahoma", 1, 12));
        lbPanelOper.setForeground(new java.awt.Color(0, 0, 204));
        lbPanelOper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/control.png"))); // NOI18N
        lbPanelOper.setText("PANEL DE OPERACIÓN");
        pnContenedor.add(lbPanelOper);
        lbPanelOper.setBounds(30, 0, 190, 50);

        pnInformacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del sensor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 14))); // NOI18N
        pnInformacion.setLayout(null);

        lbRuido.setFont(new java.awt.Font("Verdana", 0, 14));
        lbRuido.setText("Ruido Gaussiano");
        pnInformacion.add(lbRuido);
        lbRuido.setBounds(200, 50, 118, 18);

        lbInconRuido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola3.png"))); // NOI18N
        pnInformacion.add(lbInconRuido);
        lbInconRuido.setBounds(150, 40, 31, 32);

        lbFalla.setFont(new java.awt.Font("Verdana", 0, 14));
        lbFalla.setText("Falla de sensor");
        pnInformacion.add(lbFalla);
        lbFalla.setBounds(380, 50, 105, 18);

        lbIconFalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola3.png"))); // NOI18N
        pnInformacion.add(lbIconFalla);
        lbIconFalla.setBounds(330, 40, 31, 32);

        lbSensor.setFont(new java.awt.Font("Verdana", 0, 14));
        lbSensor.setText("Nombre del sensor:");
        pnInformacion.add(lbSensor);
        lbSensor.setBounds(20, 110, 139, 18);

        lbDescSensor.setFont(new java.awt.Font("Verdana", 0, 14));
        lbDescSensor.setText("Descripción del sensor:");
        pnInformacion.add(lbDescSensor);
        lbDescSensor.setBounds(20, 150, 164, 18);

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 14));
        jLabel8.setText("Operación realizada:");
        pnInformacion.add(jLabel8);
        jLabel8.setBounds(20, 190, 142, 18);

        sname.setFont(new java.awt.Font("Verdana", 0, 14));
        pnInformacion.add(sname);
        sname.setBounds(190, 110, 454, 18);

        sdesc.setFont(new java.awt.Font("Verdana", 0, 14));
        pnInformacion.add(sdesc);
        sdesc.setBounds(190, 150, 480, 18);

        oper.setFont(new java.awt.Font("Verdana", 0, 14));
        pnInformacion.add(oper);
        oper.setBounds(190, 190, 301, 19);

        pnContenedor.add(pnInformacion);
        pnInformacion.setBounds(10, 300, 690, 260);

        getContentPane().add(pnContenedor, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-723)/2, (screenSize.height-613)/2, 723, 613);
    }// </editor-fold>//GEN-END:initComponents

    private void XFWVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XFWVActionPerformed
        sdesc.setText(String.valueOf(Consulta("XFWV")));
    }//GEN-LAST:event_XFWVActionPerformed

    private void GWADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GWADActionPerformed
        sdesc.setText(String.valueOf(Consulta("GWAD")));
    }//GEN-LAST:event_GWADActionPerformed

    private void NDOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NDOActionPerformed
        sdesc.setText(String.valueOf(Consulta("NDO")));
}//GEN-LAST:event_NDOActionPerformed

    private void XZ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XZ1ActionPerformed
        sdesc.setText(String.valueOf(Consulta("XZ1")));
}//GEN-LAST:event_XZ1ActionPerformed

    private void XALIVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XALIVActionPerformed
        sdesc.setText(String.valueOf(Consulta("XALIV")));
}//GEN-LAST:event_XALIVActionPerformed

    private void GDEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GDEARActionPerformed
        sdesc.setText(String.valueOf(Consulta("GDEAR")));
}//GEN-LAST:event_GDEARActionPerformed

    private void WET3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WET3ActionPerformed
        sdesc.setText(String.valueOf(Consulta("WET3")));
}//GEN-LAST:event_WET3ActionPerformed

    private void TGSR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TGSR1ActionPerformed
        sdesc.setText(String.valueOf(Consulta("TGSR")));
}//GEN-LAST:event_TGSR1ActionPerformed

    private void GVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GVRActionPerformed
        sdesc.setText(String.valueOf(Consulta("GVR")));
}//GEN-LAST:event_GVRActionPerformed

    private void XZ2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XZ2ActionPerformed
        sdesc.setText(String.valueOf(Consulta("XZ2")));
}//GEN-LAST:event_XZ2ActionPerformed

    private void PVRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PVRActionPerformed
        sdesc.setText(String.valueOf(Consulta("PVR")));
}//GEN-LAST:event_PVRActionPerformed

    private void NDEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NDEARActionPerformed
        sdesc.setText(String.valueOf(Consulta("NDEAR")));
    }//GEN-LAST:event_NDEARActionPerformed


    private String Consulta(String cad){
       try{
           
            nomVar = cad;
            sname.setText(cad);
            manager.TablaCtccBean tb = new manager.TablaCtccBean(DatosPersistence.emf);
            entity.TablaCtcc te = new entity.TablaCtcc();
            te= tb.getValor(cad);
            if(te!=null){
                return te.getDescripcion();
            }

            return "";
       }catch(Exception e){
           System.out.print("Error en dameValor...."+e.getLocalizedMessage());
           return null;
       }

   }
    

    private void ActualizarDato(String variable,double dato){
         try{
            manager.TablaCtccBean tb = new manager.TablaCtccBean(DatosPersistence.emf);
            tb.UpdateValor(variable, dato);
         }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error al actualizar la variable \n consulte a su administrador");
        }

    }

     private void ActualizarDesc(String variable,String dato){
         try{
            manager.TablaCtccBean tb = new manager.TablaCtccBean(DatosPersistence.emf);
            tb.UpdateDescripcion(variable, dato);
         }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error al actualizar la variable \n consulte a su administrador");
        }

    }


    private void rechazoActionReal()
    {
          double rechazoTemp = 0;
          
          if(rechazoValor)
          {
            rechazoTemp = 0;
            btnRechazo.setText("Cerrar");
            lbRechazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola1.png")));

          }
          
          else
          {
           rechazoTemp= 1;
           btnRechazo.setText("Abrir");
           lbRechazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola2.png")));

          }

          ActualizarDato("INTERC_R", rechazoTemp);
          ActualizarDato("XFWV_R", 75 );
          ActualizarDato("XZ1_R", 8 );
          ActualizarDato("VAR", dato);
          ActualizarDesc("VAR", nomVar);

    }

    private void soperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soperActionPerformed

        if(soper.isSelected())
        {
            inicia = true;

            if(hilo==null)
            {
                hilo = new Thread(this);
                hilo.start();
            }
        }

        else
        {
            inicia = false;
            hilo   = null;
            dato=0;
            oper.setText("");
            ActualizarDato("VAR", dato);
            ActualizarDesc("VAR", "");
            lbInconRuido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola3.png")));
            lbIconFalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola3.png")));
        }
    }//GEN-LAST:event_soperActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void btnRechazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechazoActionPerformed
      rechazoValor = !rechazoValor;
      rechazoActionReal();
    
    }//GEN-LAST:event_btnRechazoActionPerformed

    private void soper2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soper2ActionPerformed
        if(soper2.isSelected())
        {
           inicia = true;

            if(hilo==null)
            {
                hilo = new Thread(this);
                hilo.start();
            }
        }

        else
        {
            inicia = false;
            hilo= null;
            dato=0;
            oper.setText("");
            ActualizarDato("VAR", dato);
            ActualizarDesc("VAR", "");
            lbInconRuido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola3.png")));
            lbIconFalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola3.png")));
        }
    }//GEN-LAST:event_soper2ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton GDEAR;
    private javax.swing.JRadioButton GVR;
    private javax.swing.JRadioButton GWAD;
    private javax.swing.JRadioButton NDEAR;
    private javax.swing.JRadioButton NDO;
    private javax.swing.JRadioButton PVR;
    private javax.swing.JRadioButton TGSR1;
    private javax.swing.JRadioButton WET3;
    private javax.swing.JRadioButton XALIV;
    private javax.swing.JRadioButton XFWV;
    private javax.swing.JRadioButton XZ1;
    private javax.swing.JRadioButton XZ2;
    public javax.swing.JButton btnRechazo;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbDescSensor;
    private javax.swing.JLabel lbFalla;
    public javax.swing.JLabel lbIconFalla;
    public javax.swing.JLabel lbInconRuido;
    private javax.swing.JLabel lbPanelOper;
    private javax.swing.JLabel lbRec;
    public javax.swing.JLabel lbRechazo;
    private javax.swing.JLabel lbRuido;
    private javax.swing.JLabel lbSensor;
    private javax.swing.JLabel lbSensores;
    public javax.swing.JLabel oper;
    private javax.swing.JPanel pnContenedor;
    private javax.swing.JPanel pnControles;
    private javax.swing.JPanel pnInformacion;
    private javax.swing.JPanel pnRechazo;
    private javax.swing.JPanel pnSensores;
    public javax.swing.JRadioButton rbVerValvulas;
    public javax.swing.JLabel sdesc;
    public javax.swing.ButtonGroup sensor;
    public javax.swing.JLabel sname;
    public javax.swing.JToggleButton soper;
    public javax.swing.JToggleButton soper2;
    // End of variables declaration//GEN-END:variables

    public void run()
    {
        while(inicia)
        {
             if(soper.isSelected())
             {
                    try
                    {
                        dato=1;
                        oper.setText("Generación de ruido en el sensor");
                        ActualizarDato("VAR", dato);
                        ActualizarDesc("VAR", sname.getText() );


                        lbInconRuido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola2.png")));
                        lbIconFalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola3.png")));
                        Thread.sleep(500);
                    }

                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(Diagnostico.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }

            else if(soper2.isSelected())
            {
                try
                {
                   dato=2;
                   oper.setText("Generación de falla en el sensor");
                   ActualizarDato("VAR", dato);
                   ActualizarDesc("VAR", sname.getText() );

                   lbIconFalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola2.png")));
                   lbInconRuido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bola3.png")));
                   Thread.sleep(500);
                }

                catch (InterruptedException ex)
                {
                    Logger.getLogger(Diagnostico.class.getName()).log(Level.SEVERE, null, ex);
                }
                          
            }
      }
  }
  
}
