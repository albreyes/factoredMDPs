/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RewardChart.java
 *
 * Created on 18/10/2010, 02:26:52 PM
 */

package planta;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.JOptionPane;


import utileria.ESObjetos;

/**
 *
 * @author Lilith
 */
public class RewardChart extends javax.swing.JFrame implements Runnable{

    private RewardPanel panel;
    private Thread hilo2;
    private int valor;
    private boolean statusHilo = true;
    private static  int status = 0;
    private Thread hilo = null;
    
    public RewardChart(String archivoAMB) {
        
        initComponents();
        panel=new RewardPanel(this);
        panel.celdas = (Vector) ESObjetos.leeObjeto(archivoAMB);

        int ALTO=4000; int ANCHO=3000;
        panel.actualizaDatos(ANCHO,ALTO,200,15);
        panel.celdas = (Vector) ESObjetos.leeObjeto(archivoAMB);
        panel.timeDelay(500);
        this.add(panel,BorderLayout.CENTER);
        hilo2 = new Thread(this);
    }

    public void setValor(){
        potencia2.setValue(AsistoGui_1.valorPotencia);
        potencia2.setString(AsistoGui_1.cadPotencia);
    }
    public static int getStatus(){
        return status;
    }
    public static void setStatus(){
        status = 0;
    }

   public void update(Graphics g){
        paint(g);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        potencia2 = new javax.swing.JProgressBar();
        lbMW = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reward function");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        potencia2.setForeground(new java.awt.Color(51, 102, 255));
        potencia2.setOrientation(1);
        potencia2.setString("Generation");
        potencia2.setStringPainted(true);
        jPanel1.add(potencia2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 30, -1));

        lbMW.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        lbMW.setText("MW generated");
        jPanel1.add(lbMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-428)/2, (screenSize.height-400)/2, 428, 400);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        statusHilo = false;
        try{
        hilo2.setDaemon(true);
        }catch(Exception e){}
        
    }//GEN-LAST:event_formWindowClosing


    public RewardPanel getRewardPanel() {
        return panel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lbMW;
    private javax.swing.JProgressBar potencia2;
    // End of variables declaration//GEN-END:variables

    public void run() {
        try{
            while(statusHilo){
                 this.getRewardPanel().actualizaPosicion((int)AsistoGui_1.flujo,(int)AsistoGui_1.presion,0);
                 this.getRewardPanel().repaint();
                 this.repaint();
                 setValor();
                 Thread.sleep(500);
            }
        }catch (InterruptedException e) {
            JOptionPane.showMessageDialog(this, "interrupted in Thread");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"error: "+this.getClass() +"--"+e.getMessage());
        }
    }

    /**
     * @return the hilo2
     */
    public Thread getHilo2() {
        return hilo2;
    }

    /**
     * @param hilo2 the hilo2 to set
     */
    public void setHilo2(Thread hilo2) {
        this.hilo2 = hilo2;
    }

}
