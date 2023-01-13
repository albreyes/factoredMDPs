/*
 * RegistroUsuario.java
 *
 * Created on 11/05/2010, 03:14:33 PM
 */

package planta;

import DataBase.DatosPersistence;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author liliana.sanchez
 */
public class RegistroUsuario extends javax.swing.JFrame  {

    private String nombre;
    private String apaterno;
    private String amaterno;
    private long perfil;
    private long tipoUser;
    private String nombreUsuario;
    private String contra;
    private List<entity.Tipousuarios> Datos;
    List<entity.Nivelusuario> nue;
    private String [] perfiles;
    private String [] tiposUsuario;


    /** Creates new form RegistroUsuario */
    public RegistroUsuario() {

            manager.TipoUsuarioBean tu = new manager.TipoUsuarioBean(DatosPersistence.emf);
            Datos = tu.findAll();
            initComponents();

            for(int i = 0; i < Datos.size();i++){
               cmbTipoUsuario.addItem(Datos.get(i).getDescripcion());
            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegistrar = new javax.swing.JButton();
        pnSurnames = new javax.swing.JPanel();
        txtApaterno = new javax.swing.JTextField();
        txtAmaterno = new javax.swing.JTextField();
        pnNames = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        pnProfile = new javax.swing.JPanel();
        cmbTipoUsuario = new javax.swing.JComboBox();
        lbProfile = new javax.swing.JLabel();
        cmbPerfil = new javax.swing.JComboBox();
        pnUserpass = new javax.swing.JPanel();
        lbUsername = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtContra = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("User registration");
        getContentPane().setLayout(null);

        btnRegistrar.setText("Ok");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar);
        btnRegistrar.setBounds(200, 420, 90, 29);

        pnSurnames.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Surname", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        pnSurnames.setLayout(null);
        pnSurnames.add(txtApaterno);
        txtApaterno.setBounds(10, 20, 130, 20);
        pnSurnames.add(txtAmaterno);
        txtAmaterno.setBounds(10, 50, 130, 20);

        getContentPane().add(pnSurnames);
        pnSurnames.setBounds(20, 80, 240, 90);

        pnNames.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Names", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        pnNames.setLayout(null);
        pnNames.add(txtNombre);
        txtNombre.setBounds(10, 30, 130, 27);

        getContentPane().add(pnNames);
        pnNames.setBounds(20, 10, 240, 60);

        pnProfile.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Profile", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        pnProfile.setLayout(null);

        cmbTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoUsuarioActionPerformed(evt);
            }
        });
        pnProfile.add(cmbTipoUsuario);
        cmbTipoUsuario.setBounds(10, 20, 130, 27);

        lbProfile.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbProfile.setText("Profile");
        pnProfile.add(lbProfile);
        lbProfile.setBounds(10, 50, 50, 13);
        pnProfile.add(cmbPerfil);
        cmbPerfil.setBounds(10, 70, 130, 27);

        getContentPane().add(pnProfile);
        pnProfile.setBounds(20, 180, 240, 110);

        pnUserpass.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N
        pnUserpass.setLayout(null);

        lbUsername.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbUsername.setText("Username:");
        pnUserpass.add(lbUsername);
        lbUsername.setBounds(20, 30, 80, 13);

        txtNombreUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreUsuarioFocusLost(evt);
            }
        });
        pnUserpass.add(txtNombreUsuario);
        txtNombreUsuario.setBounds(90, 30, 130, 27);

        lbPass.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbPass.setText("Password");
        pnUserpass.add(lbPass);
        lbPass.setBounds(20, 60, 90, 13);
        pnUserpass.add(txtContra);
        txtContra.setBounds(90, 60, 130, 27);

        getContentPane().add(pnUserpass);
        pnUserpass.setBounds(20, 300, 240, 100);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-308)/2, (screenSize.height-485)/2, 308, 485);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(validaRequeridos() == 1){

            if(comprobarDisponibilidad() == 0){

               manager.UserBean ub = new manager.UserBean(DatosPersistence.emf);
               entity.Usuario ue = new entity.Usuario();

               ue.setNombre(nombre);
               ue.setAmaterno(amaterno);
               ue.setApaterno(apaterno);
               ue.setNombreUsuario(nombreUsuario);
               ue.setContra(contra);
              
               entity.Tipousuarios tue= new entity.Tipousuarios();
               tue.setIdTipoUsuario(tipoUser);

               entity.Nivelusuario nve = new entity.Nivelusuario();
               nve.setIdnivel(perfil);

               ue.setTipousuarios(tue);
               ue.setNivelusuario(nve);

               int status = ub.guardarUsuario(ue);

                if(status!=1){
                    JOptionPane.showMessageDialog(this, "La información no ha sido almacenada\n");
                    return;
                }else{
                    JOptionPane.showMessageDialog(this, "La información ha sido almacenada\n");
                    this.dispose();
                }

            }else{
                JOptionPane.showMessageDialog(this, "El usuario ya ha sido registrado");
            }

        }else{
            JOptionPane.showMessageDialog(this, "Todos los campos son requeridos");
        }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtNombreUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreUsuarioFocusLost

    }//GEN-LAST:event_txtNombreUsuarioFocusLost

    private void cmbTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoUsuarioActionPerformed
    cmbPerfil.removeAllItems();
    String tipo = cmbTipoUsuario.getSelectedItem().toString();
        if(tipo.equals("ESTUDIANTE")){//si es estudiante puede escoger un nivel
                cmbPerfil.setEnabled(true);
                manager.NivelUsuarioBean nub = new manager.NivelUsuarioBean(DatosPersistence.emf);
                nue = nub.findAll(); //obtiene todos los niveles de usuario
                for(int i = 0; i < nue.size();i++){
                    cmbPerfil.addItem(nue.get(i).getDescripcion());
                }
           }else{//si no es estudiante se desactiva el combo de nivel estudiante "perfil".
               cmbPerfil.setEnabled(false);
               lbProfile.setEnabled(false);
          }
    }//GEN-LAST:event_cmbTipoUsuarioActionPerformed


    public int  comprobarDisponibilidad(){
        manager.UserBean ub = new manager.UserBean(DatosPersistence.emf);

       if(ub.findByUserName(nombreUsuario)!=null)
            return 1;//existe
       else
           return 0;//no existe

    }

    /**
     * @validaRequeridos()
     * @descripcion: funcion para obtener los campos de campturados por el usuario
     */
    
    public int validaRequeridos(){
        nombre   = txtNombre.getText().trim();
        apaterno = txtApaterno.getText().trim();
        amaterno = txtAmaterno.getText().trim();

        manager.TipoUsuarioBean tub = new manager.TipoUsuarioBean(DatosPersistence.emf);
        tipoUser = tub.descripcionToID(cmbTipoUsuario.getSelectedItem().toString());


      Pattern p = Pattern.compile("INSTRUCTOR|OPERADOR|ADMINISTRADOR");
      Matcher m = p.matcher(cmbTipoUsuario.getSelectedItem().toString());

      manager.NivelUsuarioBean nvu = new manager.NivelUsuarioBean(DatosPersistence.emf);

      if (m.find())
            perfil = nvu.descripcionToID("AVANZADO");
      else 
            perfil =  nvu.descripcionToID(cmbPerfil.getSelectedItem().toString());

        nombreUsuario = txtNombreUsuario.getText().trim();
        char [] pwd = txtContra.getPassword();
        contra = String.valueOf(pwd);

        if(nombre.equals("") ||
           apaterno.equals("") || amaterno.equals("") ||
           nombreUsuario.equals("") || contra.equals(""))
                return 0; //algún campo vacío
        else
                return 1;//campos llenos
     

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox cmbPerfil;
    private javax.swing.JComboBox cmbTipoUsuario;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbProfile;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JPanel pnNames;
    private javax.swing.JPanel pnProfile;
    private javax.swing.JPanel pnSurnames;
    private javax.swing.JPanel pnUserpass;
    private javax.swing.JTextField txtAmaterno;
    private javax.swing.JTextField txtApaterno;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables



}
