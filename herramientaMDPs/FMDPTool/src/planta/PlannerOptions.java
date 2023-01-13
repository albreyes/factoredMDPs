
/*
 * PlannerOptions.java
 *
 * Created on 5/05/2010, 01:15:54 PM
 */

package planta;

import ia.FMDP;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JOptionPane;
import utileria.Dialogos;
import utileria.ESObjetos;
import utileria.FileOutput01;
import DataStructuresQ.Cualificador;

/**
 *
 * @author liliana.sanchez
 */
public class PlannerOptions extends javax.swing.JFrame {

    private String policyFilename;
    private String folder;
    private String folderNew;
    private int statusArchivo;
    private FMDP fmdp;
    private String rutaPlannerDefault ="../ejemplos/powerPlant/5acc5vars2%/discreto/";
    TableData tabla=null;
    Cualificador cual;

    /** Creates new form PlannerOptions2 */
    public PlannerOptions() {
        
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        botonAbrir = new javax.swing.JButton();
        plannerSpudd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        panelDatos = new javax.swing.JPanel();
        panelOpciones = new javax.swing.JPanel();
        jrb1 = new javax.swing.JRadioButton();
        jrb2 = new javax.swing.JRadioButton();
        jrb3 = new javax.swing.JRadioButton();
        plannerTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        buttonGroup1.add(jrb1);
        buttonGroup1.add(jrb2);
        buttonGroup1.add(jrb3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos MDP");

        jToolBar1.setRollover(true);

        botonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/abrir.png"))); // NOI18N
        botonAbrir.setText("Abrir MDP...");
        botonAbrir.setToolTipText("Buscar Archivo Fmdp.obj");
        botonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrirActionPerformed(evt);
            }
        });
        jToolBar1.add(botonAbrir);

        plannerSpudd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/play.png"))); // NOI18N
        plannerSpudd.setText("Generar Datos");
        plannerSpudd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plannerSpuddActionPerformed(evt);
            }
        });
        jToolBar1.add(plannerSpudd);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnSave.setText("Guardar Datos");
        btnSave.setFocusable(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSave);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);
        getContentPane().add(panelDatos, java.awt.BorderLayout.PAGE_END);

        panelOpciones.setLayout(null);

        jrb1.setSelected(true);
        jrb1.setText("Discreto");
        panelOpciones.add(jrb1);
        jrb1.setBounds(10, 60, 90, 23);

        jrb2.setText("Cualitativo");
        panelOpciones.add(jrb2);
        jrb2.setBounds(130, 60, 110, 23);

        jrb3.setText("Híbrido");
        panelOpciones.add(jrb3);
        jrb3.setBounds(240, 60, 90, 23);

        plannerTF.setText("../ejemplos/powerPlant/5acc5vars2%/discreto/");
        panelOpciones.add(plannerTF);
        plannerTF.setBounds(350, 10, 130, 20);
        plannerTF.setVisible(false);

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel2.setForeground(new java.awt.Color(45, 23, 95));
        jLabel2.setText("Tipo de MDP");
        panelOpciones.add(jLabel2);
        jLabel2.setBounds(160, 10, 110, 18);

        getContentPane().add(panelOpciones, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-535)/2, (screenSize.height-308)/2, 535, 308);
    }// </editor-fold>//GEN-END:initComponents

    private void botonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrirActionPerformed
        folder = Dialogos.dialogoAbrirDir("Abrir carpeta del planificador",
                "../ejemplos/powerPlant");

        if (!folder.equals("")) {

            plannerTF.setText(folder);
            policyFilename = folder + "/fmdp.obj";

        }
        File existeArchivo= new File(policyFilename);

        statusArchivo = 1;
        if(!existeArchivo.exists()){
            policyFilename = rutaPlannerDefault;
            statusArchivo = 0;
            JOptionPane.showMessageDialog(this, "No existe el archivo fmdp.obj en esa ubicacion. \n");

        }
}//GEN-LAST:event_botonAbrirActionPerformed

    private void plannerSpuddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plannerSpuddActionPerformed

        actualizaSettingsPlaneacion();
        imprimeDatosMDP();
        panelDatos.removeAll();
        panelDatos.add(tabla,BorderLayout.CENTER);
        panelDatos.updateUI();

    }//GEN-LAST:event_plannerSpuddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

      
    folderNew = Dialogos.dialogoGuardarArchivo("Guardar Datos Generados", "../ejemplos");
    System.out.print(folderNew);

     File existeArchivo = new File(folderNew);
     FileOutput01 fo = new FileOutput01(folderNew);
        statusArchivo = 1;
        
        if(!existeArchivo.exists()){
            statusArchivo = 0;
            JOptionPane.showMessageDialog(this, "Los datos no han sido almacenados. \n");

        }else{

            
            if(jrb1.isSelected() || jrb3.isSelected() ){
                fo.writeln("fwf\tpd\tfms\tg\td\tr\tu\tp");
            }
            if(jrb2.isSelected()){
                fo.writeln("q\tr\tu\tp");
            }

            for(int i=0; i<fmdp.s.length; i++){

              String out = "";
              for (int j = 0; j < fmdp.s[i].length; j++)
                  out = out + fmdp.s[i][j] + "\t";


              fo.writeln(out + fmdp.reward[i] + "\t" +
                         fmdp.utilidad[i] + "\t" + fmdp.politica[i]);
            }
            fo.close();
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    public void actualizaSettingsPlaneacion(){

        if(statusArchivo ==1)
            folder=plannerTF.getText();
        else
            folder = rutaPlannerDefault;

      policyFilename = folder + "/fmdp.obj";

    }

    public void imprimeDatosMDP(){

        int columnas=0;
        fmdp= (FMDP) ESObjetos.leeObjeto(policyFilename);

        if(jrb1.isSelected() || jrb3.isSelected() ){
            columnas = 8;
        }
        if(jrb2.isSelected()){
             columnas = 4;
        }
        
            tabla = new TableData();
            tabla.setDimension( fmdp.utilidad.length, columnas);
            tabla.setDatos(fmdp);
            tabla.contruyeTabla();
    }

    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlannerOptions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAbrir;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton jrb1;
    private javax.swing.JRadioButton jrb2;
    private javax.swing.JRadioButton jrb3;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelOpciones;
    public static javax.swing.JButton plannerSpudd;
    private javax.swing.JTextField plannerTF;
    // End of variables declaration//GEN-END:variables

}
