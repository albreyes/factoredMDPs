/*
 * Login.java
 *
 * Created on 11/05/2010, 03:08:26 PM
 */

package planta;

import DataBase.DatosPersistence;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.geom.Rectangle2D;


/**
 *
 * @author liliana.sanchez
 */
public class Login extends javax.swing.JFrame {
  
    private String nameUser;
    private String contra;
    static SplashScreen mySplash;
    static Graphics2D splashGraphics;
    static Rectangle2D.Double splashTextArea;
    static Rectangle2D.Double splashProgressArea;
    static Font font;


    public Login() {
        initComponents();
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioGrupo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNameUser = new javax.swing.JTextField();
        btnEntrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnOpenOpDB = new javax.swing.JButton();
        txtContra = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Session Start ASISTO");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("User Name:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 90, 150, 14);

        jLabel2.setText("Password:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 140, 150, 14);

        txtNameUser.setText("ADMIN");
        getContentPane().add(txtNameUser);
        txtNameUser.setBounds(200, 90, 180, 20);

        btnEntrar.setText("Sign in");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntrar);
        btnEntrar.setBounds(260, 170, 120, 23);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Session Start ASISTO ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(110, 20, 290, 14);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bannerlog.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 420, 50);

        btnOpenOpDB.setText("Connection Panel");
        btnOpenOpDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenOpDBActionPerformed(evt);
            }
        });
        getContentPane().add(btnOpenOpDB);
        btnOpenOpDB.setBounds(40, 170, 210, 23);

        txtContra.setText("ADMIN");
        txtContra.setToolTipText("");
        getContentPane().add(txtContra);
        txtContra.setBounds(200, 130, 180, 20);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-431)/2, (screenSize.height-276)/2, 431, 276);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenOpDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenOpDBActionPerformed
        OptionsDB opDB = new OptionsDB();
        opDB.setVisible(true);
    }//GEN-LAST:event_btnOpenOpDBActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed

        if(DatosPersistence.getStatusConexion()!= 0){
            JOptionPane.showMessageDialog(this, "The database connection has not been stablished");
        }else{

            nameUser = txtNameUser.getText().trim();
            char[] pass = txtContra.getPassword();
            contra   = String.valueOf(pass);
            if(nameUser.equals("") || contra.equals("")){
                JOptionPane.showMessageDialog(this, "The fields: \n username and password are required");
            }else{
                manager.UserBean usr = new manager.UserBean(DatosPersistence.emf);
                entity.Usuario existe = usr.findByUsuarioPass(nameUser, contra);
                if(existe != null){
                   
                        this.dispose();
                        AsistoGui_1 frame;
                        frame = new AsistoGui_1();
                        frame.setlogin(existe);
                        frame.setVisible(true);

                   // System.out.println("*******CONEXION CREADA******" + existe.getNombre());
                 
                }else{
                    JOptionPane.showMessageDialog(this, "invalid username or password");
                }
            }
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    private static void appInit(){
        String puntos = "*";

        for (int i = 1; i <= 10; i++){
            int pctDone = i * 10;

            splashText("Wait Please " + puntos);
            splashProgress(pctDone);
            puntos = puntos + " *";
            try{
                Thread.sleep(500);
            }catch (InterruptedException ex){
                break;
            }
        }
    }

     private static void splashInit(){


        mySplash = SplashScreen.getSplashScreen();


        if (mySplash != null){

            Dimension ssDim = mySplash.getSize();
            int height = ssDim.height;
            int width = ssDim.width;


            splashTextArea = new Rectangle2D.Double(15., height*0.88, width * .45, 32.);
            splashProgressArea = new Rectangle2D.Double(width * .55, height*.92, width*.4, 12 );

            // create the Graphics environment for drawing status info
            splashGraphics = mySplash.createGraphics();
            font = new Font("Dialog", Font.PLAIN, 14);
            splashGraphics.setFont(font);

            splashText("Starting");
            splashProgress(0);
        }
    }

    public static void splashText(String str){
        if (mySplash != null && mySplash.isVisible()){

            // erase the last status text
            splashGraphics.setPaint(Color.LIGHT_GRAY);
            splashGraphics.fill(splashTextArea);

            // draw the text
            splashGraphics.setPaint(Color.BLACK);
            splashGraphics.drawString(str, (int)(splashTextArea.getX() + 10),(int)(splashTextArea.getY() + 15));

            // make sure it's displayed
            mySplash.update();
        }
    }

    public static void splashProgress(int pct){
        if (mySplash != null && mySplash.isVisible()){

            // Note: 3 colors are used here to demonstrate steps
            // erase the old one
            splashGraphics.setPaint(Color.LIGHT_GRAY);
            splashGraphics.fill(splashProgressArea);

            // draw an outline
            splashGraphics.setPaint(Color.BLUE);
            splashGraphics.draw(splashProgressArea);

            // Calculate the width corresponding to the correct percentage
            int x = (int) splashProgressArea.getMinX();
            int y = (int) splashProgressArea.getMinY();
            int wid = (int) splashProgressArea.getWidth();
            int hgt = (int) splashProgressArea.getHeight();

            int doneWidth = Math.round(pct*wid/100.f);
            doneWidth = Math.max(0, Math.min(doneWidth, wid-1));  // limit 0-width

            // fill the done part one pixel smaller than the outline
            splashGraphics.setPaint(Color.GREEN);
            splashGraphics.fillRect(x, y+1, doneWidth, hgt-1);

            // make sure it's displayed
            mySplash.update();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnOpenOpDB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.ButtonGroup radioGrupo;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtNameUser;
    // End of variables declaration//GEN-END:variables

}
