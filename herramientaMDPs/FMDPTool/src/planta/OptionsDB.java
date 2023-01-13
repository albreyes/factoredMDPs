package planta;

import DataBase.DatosPersistence;
import javax.swing.JOptionPane;

/**
 *
 * @author  liliana.sanchez
 */
public class OptionsDB extends javax.swing.JFrame{
    
    private String IP;
    private String user;
    private String pass;
    private String port;
    private String dataBase;
    private String instancia;
    private int tipoServer;
  

    public OptionsDB() {
        initComponents();
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btConecta = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        rbMysql = new javax.swing.JRadioButton();
        rbServerODBC = new javax.swing.JRadioButton();
        rbServer = new javax.swing.JRadioButton();
        rbMysqlODBC = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        lbIP = new javax.swing.JLabel();
        txtIP = new javax.swing.JTextField();
        lbUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        lbPort = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        lbDB = new javax.swing.JLabel();
        txtDB = new javax.swing.JTextField();
        lbInstancia = new javax.swing.JLabel();
        txtInstancia = new javax.swing.JTextField();

        buttonGroup1.add(rbMysql);
        buttonGroup1.add(rbServer);
        buttonGroup1.add(rbServerODBC);
        buttonGroup1.add(rbMysqlODBC);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Connection");
        getContentPane().setLayout(null);

        btConecta.setText("Connect");
        btConecta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        btConecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConectaActionPerformed(evt);
            }
        });
        getContentPane().add(btConecta);
        btConecta.setBounds(190, 420, 100, 30);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Server type"));
        jPanel1.setLayout(null);

        rbMysql.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rbMysql.setSelected(true);
        rbMysql.setText("MYSQL");
        rbMysql.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMysqlActionPerformed(evt);
            }
        });
        jPanel1.add(rbMysql);
        rbMysql.setBounds(10, 20, 90, 21);

        rbServerODBC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rbServerODBC.setText("ODBC SQL SERVER");
        rbServerODBC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbServerODBCActionPerformed(evt);
            }
        });
        jPanel1.add(rbServerODBC);
        rbServerODBC.setBounds(130, 60, 130, 21);

        rbServer.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rbServer.setText("SQL SERVER");
        rbServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbServerActionPerformed(evt);
            }
        });
        jPanel1.add(rbServer);
        rbServer.setBounds(130, 20, 110, 21);

        rbMysqlODBC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rbMysqlODBC.setText("ODBC MYSQL");
        rbMysqlODBC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMysqlODBCActionPerformed(evt);
            }
        });
        jPanel1.add(rbMysqlODBC);
        rbMysqlODBC.setBounds(10, 60, 110, 21);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 30, 280, 100);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Server Data"));
        jPanel2.setLayout(null);

        lbIP.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbIP.setText("IP ");
        jPanel2.add(lbIP);
        lbIP.setBounds(20, 30, 70, 14);

        txtIP.setText("200.4.7.130");
        jPanel2.add(txtIP);
        txtIP.setBounds(90, 30, 130, 20);

        lbUser.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbUser.setText("User:");
        jPanel2.add(lbUser);
        lbUser.setBounds(20, 60, 70, 14);

        txtUser.setText("sa");
        jPanel2.add(txtUser);
        txtUser.setBounds(90, 60, 130, 20);

        lbPass.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbPass.setText("Pass:");
        jPanel2.add(lbPass);
        lbPass.setBounds(20, 90, 70, 14);

        txtPass.setText("simulador");
        jPanel2.add(txtPass);
        txtPass.setBounds(90, 90, 130, 20);

        lbPort.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbPort.setText("Port:");
        jPanel2.add(lbPort);
        lbPort.setBounds(20, 120, 70, 14);

        txtPort.setText("3306");
        jPanel2.add(txtPort);
        txtPort.setBounds(90, 120, 130, 20);

        lbDB.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbDB.setText("Data Base:");
        jPanel2.add(lbDB);
        lbDB.setBounds(20, 150, 70, 14);

        txtDB.setText("simulacion");
        jPanel2.add(txtDB);
        txtDB.setBounds(90, 150, 130, 20);

        lbInstancia.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbInstancia.setText("Nombre de Instancia:");
        jPanel2.add(lbInstancia);
        lbInstancia.setBounds(20, 190, 120, 14);
        lbInstancia.setVisible(false);

        txtInstancia.setText("A03221");
        jPanel2.add(txtInstancia);
        txtInstancia.setBounds(130, 180, 140, 20);
        txtInstancia.setVisible(false);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 150, 280, 230);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-313)/2, (screenSize.height-483)/2, 313, 483);
    }// </editor-fold>//GEN-END:initComponents

   
    
    private void rbServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbServerActionPerformed
        setDatosSeleccion();
        lbInstancia.setVisible(true);
        txtInstancia.setVisible(true);
        txtPort.setText("1433");
        txtInstancia.setText("A03221");
    }//GEN-LAST:event_rbServerActionPerformed

    private void rbMysqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMysqlActionPerformed
        setDatosSeleccion();
        lbInstancia.setVisible(false);
        txtInstancia.setVisible(false);
        txtPort.setText("3306");
    }//GEN-LAST:event_rbMysqlActionPerformed

    private void rbServerODBCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbServerODBCActionPerformed
        setDatosSeleccionODBC();
        lbInstancia.setVisible(false);
        txtInstancia.setVisible(false);
         txtIP.setText("simulador");
        
    }//GEN-LAST:event_rbServerODBCActionPerformed

    private void rbMysqlODBCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMysqlODBCActionPerformed
        setDatosSeleccionODBC();
        lbInstancia.setVisible(false);
        txtInstancia.setVisible(false);
         txtIP.setText("simulacion");
    }//GEN-LAST:event_rbMysqlODBCActionPerformed

    private void btConectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConectaActionPerformed
        setDatos();
        DatosPersistence.getConexion();
                DatosPersistence.DatosPersistence_(IP,user,pass,port,dataBase, getInstancia(),tipoServer);

                if(DatosPersistence.getStatusConexion() != 0){
                    JOptionPane.showMessageDialog(this, DatosPersistence.getMensaje());
                }else{
                    this.dispose();
                }

    }//GEN-LAST:event_btConectaActionPerformed

  /**
  * @name setDatos()
  * @descripcion establece los datos para la conexion IP,usuario,password,puerto,
  *              nombre de la DB, tipo de manejador  
  *              0 =SQL SERVER (DRIVER), 1 = MYSQL, 2 = SQL SERVER (ODBC)
  */
    public void setDatos(){
        setIP(txtIP.getText());
        setUser(txtUser.getText());
        setPass(txtPass.getText());
        setPort(txtPort.getText());
        setDataBase(txtDB.getText());
        setInstancia(txtInstancia.getText());
        if(rbMysql.isSelected())tipoServer  = 1;
        if(rbServer.isSelected())tipoServer = 0;
        if(rbMysqlODBC.isSelected())tipoServer = 2;
        if(rbServerODBC.isSelected())tipoServer = 3;
        
        setTipoServer(tipoServer);
    }
 /**
  * @name setDatosSeleccion()
  * @descripcion cambia los datos de las textField segun el manejados 
  */
private void setDatosSeleccion(){
    
        txtPort.setVisible(true);
        lbPort.setVisible(true);
        txtDB.setVisible(true);
        lbDB.setVisible(true);

        txtIP.setText(" ");
        lbIP.setText("IP Servidor:");
     

}

public void setDatosSeleccionODBC(){

        txtPort.setVisible(false);
        lbPort.setVisible(false);

        txtDB.setVisible(false);
        lbDB.setVisible(false);
        lbIP.setText("ODBC:");
       
}

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConecta;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbDB;
    private javax.swing.JLabel lbIP;
    private javax.swing.JLabel lbInstancia;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbPort;
    private javax.swing.JLabel lbUser;
    private javax.swing.JRadioButton rbMysql;
    private javax.swing.JRadioButton rbMysqlODBC;
    private javax.swing.JRadioButton rbServer;
    private javax.swing.JRadioButton rbServerODBC;
    private javax.swing.JTextField txtDB;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtInstancia;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the IP
     */
    public String getIP() {
        return IP;
    }

    /**
     * @param IP the IP to set
     */
    public void setIP(String IP) {
        this.IP = IP;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the dataBase
     */
    public String getDataBase() {
        return dataBase;
    }

    /**
     * @param dataBase the dataBase to set
     */
    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    /**
     * @return the tipoServer
     */
    public int getTipoServer() {
        return tipoServer;
    }

    /**
     * @param tipoServer the tipoServer to set
     */
    public void setTipoServer(int tipoServer) {
        this.tipoServer = tipoServer;
    }

    /**
     * @return the instancia
     */
    public String getInstancia() {
        return instancia;
    }

    /**
     * @param instancia the instancia to set
     */
    public void setInstancia(String instancia) {
        this.instancia = instancia;
    }

 
    
}
