package explicaciones;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import DataBase.DatosPersistence;
import java.util.List;


/**
 *
 * @author liliana.sanchez
 */

public class Explanation extends JFrame {

    private int politicaOptima;
    private int[] estadosDis;
    private String NameVariableRelevante;
    private int variableRelevante;
    private String iDVariable = "";
    private int idPerfil;

    /** Creates new form Explanation */
    public Explanation(int politicaOptima,int [] estadosDis,int idPerfil) {
           
        this.politicaOptima = politicaOptima;
        this.estadosDis     = estadosDis;
        this.idPerfil       = idPerfil;
        
        initComponents();
      
        ocultarPerfil();
        setDatos();
    }

    public void setDatos(){

      VariableRelevante algoritmo = new VariableRelevante();

      //paso de valores discretizados
      algoritmo.setValoresBase(estadosDis);
  
      //numero de variable relevante
      setVariableRelevante(algoritmo.ejecutarAlgoritmo());
     
      System.out.println("var:-----> " + variableRelevante);
      if(getVariableRelevante() == 0)
            setiDVariable("FFW");
      else if(getVariableRelevante() == 1)
            setiDVariable("D");
      else if(getVariableRelevante() == 2)
            setiDVariable("PD");
      else if(getVariableRelevante() == 3)
            setiDVariable("FMS");
      else if(getVariableRelevante() == 4)
            setiDVariable("G");
      
      informacionDB();
    }

    /**
     * @return sin valor de retorno
     * @descripción Pinta en el panel la informacion de la variable,
     *              accion y componentes obtenidad de la DB
     */
    public void informacionDB() {

      loadVariables();
      loadAccion();
      loadComponente();


  }

   private void loadVariables() {
        try {

            manager.VariablesBean vb = new manager.VariablesBean(DatosPersistence.emf);
            entity.Variables ve = vb.findOne(getiDVariable());
            String tipo = ve.getTipo();

            if(tipo.equals("FLOW")){

                 entity.Flow flow =vb.findOneFlow(getiDVariable());

                 txtVariable.setText(flow.getDescripcion());
                 Image imagen = vb.getImagen(flow.getFigura());
                 Icon icon = new ImageIcon(imagen);
                 lbFigura.setIcon(icon);

            }else if(tipo.equals("GENERATION")){

                 entity.Generation gen =vb.findOneGeneration(getiDVariable());

                 txtVariable.setText(gen.getDescripcion());
                 Image imagen = vb.getImagen(gen.getFigura());
                 Icon icon = new ImageIcon(imagen);
                 lbFigura.setIcon(icon);

            }else if(tipo.equals("PRESSURE")){

                 entity.Pressure gen =vb.findOnePressure(getiDVariable());

                 txtVariable.setText(gen.getDescripcion());
                 Image imagen = vb.getImagen(gen.getFigura());
                 Icon icon = new ImageIcon(imagen);
                 lbFigura.setIcon(icon);

            }else if(tipo.equals("LEVEL")){

                 entity.Level gen =vb.findOneLevel(getiDVariable());

                 txtVariable.setText(gen.getDescripcion());
                 Image imagen = vb.getImagen(gen.getFigura());
                 Icon icon = new ImageIcon(imagen);
                 lbFigura.setIcon(icon);

            }


        } catch(Exception ex) {
            System.out.println("error al cargar variables"+ex.getLocalizedMessage());
        }
      }

   public void loadAccion(){

       manager.AccionesBean accB = new manager.AccionesBean(DatosPersistence.emf);
       entity.Acciones accE = accB.findOne(""+politicaOptima);

       txtAction.setText(accE.getAction());
       txtProtection.setText(accE.getProtectionSystem());
       txtProcedure.setText(accE.getProcedure());

   }

   public void loadComponente(){

       manager.ComponentesBean cb = new manager.ComponentesBean(DatosPersistence.emf);
       List<entity.Componentes> cve = cb.findByIdVariable(getiDVariable());
      // String x = cve.get(0).getDescription();
       entity.Componentes ce = cb.findOne(cve.get(0).getIdcomponente());
       byte[] imagen = ce.getFigure();
       if(ce.getDescription().equals("SHUTDOWN_VALVE")){
          entity.ShutdownValve sh = cb.findOneShutDownValve(cve.get(0).getIdcomponente());

          txtCompInvolved.setText(sh.getDescription());
          txtComponent.setText(sh.getTipo());
          txtSize.setText(sh.getSize());
          txtActuation.setText(sh.getActuation());
          txtResponse.setText(sh.getResponse());
          Image imagenC = cb.getImagen(imagen);
          Icon iconC = new ImageIcon(imagenC);
          lbFiguraComp.setIcon(iconC);

       }else if(ce.getDescription().equals("CONTROL_VALVE")){

          entity.ControlValve crtlValve = cb.findOneControlValve(cve.get(0).getIdcomponente());

          txtCompInvolved.setText(crtlValve.getDescription());
          txtComponent.setText(crtlValve.getTipo());
          txtSize.setText(crtlValve.getSize());
          txtActuation.setText(crtlValve.getActuation());
          txtResponse.setText(crtlValve.getResponse());
          Image imagenC = cb.getImagen(imagen);
          Icon iconC = new ImageIcon(imagenC);
          lbFiguraComp.setIcon(iconC);


       }else if(ce.getDescription().equals("TURBINE")){

           entity.Turbine turb = cb.findOneTurbine(cve.get(0).getIdcomponente());

          txtCompInvolved.setText(turb.getDescription());
          txtComponent.setText(turb.getTipo());
          txtSize.setText(turb.getSize());
          txtResponse.setText(turb.getResponse());
          Image imagenC = cb.getImagen(imagen);
          Icon iconC = new ImageIcon(imagenC);
          lbFiguraComp.setIcon(iconC);


       }else if(ce.getDescription().equals("DRUM")){

          entity.Drum drum = cb.findOneDrum(cve.get(0).getIdcomponente());

          txtCompInvolved.setText(drum.getDescription());
          txtComponent.setText(drum.getTipo());
          txtResponse.setText(drum.getResponse());
          Image imagenC = cb.getImagen(imagen);
          Icon iconC = new ImageIcon(imagenC);
          lbFiguraComp.setIcon(iconC);


       }

   }
    public void ocultarPerfil(){

        if(idPerfil ==2 || idPerfil == 3){
            panelStateDescrip.setVisible(false);
            panelComponentDetail.setVisible(false);
        }
        if(idPerfil ==3){
            panelComponent.setVisible(false);
        }

        
   }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelExplanation = new javax.swing.JPanel();
        lbExplanation = new javax.swing.JLabel();
        panelInformacion = new javax.swing.JPanel();
        lbProtection = new javax.swing.JLabel();
        txtProtection = new javax.swing.JTextField();
        lbAccion = new javax.swing.JLabel();
        txtAction = new javax.swing.JTextField();
        lbVariable = new javax.swing.JLabel();
        lbPorpuse = new javax.swing.JLabel();
        lbProcedure = new javax.swing.JLabel();
        txtProcedure = new javax.swing.JTextField();
        txtVariable = new javax.swing.JTextField();
        panelComponent = new javax.swing.JPanel();
        lbCompInvolved = new javax.swing.JLabel();
        txtCompInvolved = new javax.swing.JTextField();
        lbComponent = new javax.swing.JLabel();
        txtComponent = new javax.swing.JTextField();
        panelStateDescrip = new javax.swing.JPanel();
        lbStateDescrip = new javax.swing.JLabel();
        lbS1 = new javax.swing.JLabel();
        txtS1 = new javax.swing.JTextField();
        lbS2 = new javax.swing.JLabel();
        txtS2 = new javax.swing.JTextField();
        panelComponentDetail = new javax.swing.JPanel();
        lbDetail = new javax.swing.JLabel();
        txtResponse = new javax.swing.JLabel();
        txtSize = new javax.swing.JLabel();
        txtActuation = new javax.swing.JLabel();
        lbFiguraComp = new javax.swing.JLabel();
        panelFigura = new javax.swing.JPanel();
        lbDomain = new javax.swing.JLabel();
        lbFigura = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Explanation System");
        getContentPane().setLayout(null);

        panelExplanation.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelExplanation.setLayout(null);

        lbExplanation.setFont(new java.awt.Font("Tahoma", 1, 14));
        lbExplanation.setText("EXPLANATION");
        panelExplanation.add(lbExplanation);
        lbExplanation.setBounds(260, 10, 120, 20);

        panelInformacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelInformacion.setLayout(null);

        lbProtection.setText("And the protection mechanims is");
        panelInformacion.add(lbProtection);
        lbProtection.setBounds(10, 160, 250, 17);

        txtProtection.setEditable(false);
        txtProtection.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelInformacion.add(txtProtection);
        txtProtection.setBounds(240, 160, 100, 21);

        lbAccion.setText("The Action that must be taken is:");
        panelInformacion.add(lbAccion);
        lbAccion.setBounds(10, 10, 330, 17);

        txtAction.setEditable(false);
        txtAction.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelInformacion.add(txtAction);
        txtAction.setBounds(10, 30, 330, 21);

        lbVariable.setText("The affected variable at the current state is:");
        panelInformacion.add(lbVariable);
        lbVariable.setBounds(10, 50, 330, 17);

        lbPorpuse.setFont(new java.awt.Font("Tahoma", 1, 11));
        lbPorpuse.setText("The porpuse of the action given is because that");
        panelInformacion.add(lbPorpuse);
        lbPorpuse.setBounds(0, 100, 350, 20);

        lbProcedure.setText("The procedure is");
        panelInformacion.add(lbProcedure);
        lbProcedure.setBounds(10, 130, 140, 17);

        txtProcedure.setEditable(false);
        txtProcedure.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelInformacion.add(txtProcedure);
        txtProcedure.setBounds(160, 130, 180, 21);

        txtVariable.setEditable(false);
        txtVariable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelInformacion.add(txtVariable);
        txtVariable.setBounds(10, 70, 330, 21);

        panelComponent.setLayout(null);

        lbCompInvolved.setText("The component involved is");
        panelComponent.add(lbCompInvolved);
        lbCompInvolved.setBounds(6, 0, 240, 17);

        txtCompInvolved.setEditable(false);
        txtCompInvolved.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelComponent.add(txtCompInvolved);
        txtCompInvolved.setBounds(6, 20, 330, 21);

        lbComponent.setText("This component is");
        panelComponent.add(lbComponent);
        lbComponent.setBounds(6, 50, 140, 17);

        txtComponent.setEditable(false);
        txtComponent.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtComponent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComponentActionPerformed(evt);
            }
        });
        panelComponent.add(txtComponent);
        txtComponent.setBounds(146, 50, 160, 21);

        panelInformacion.add(panelComponent);
        panelComponent.setBounds(4, 185, 340, 80);

        panelStateDescrip.setLayout(null);

        lbStateDescrip.setFont(new java.awt.Font("Tahoma", 1, 11));
        lbStateDescrip.setText("State description");
        panelStateDescrip.add(lbStateDescrip);
        lbStateDescrip.setBounds(30, 0, 130, 14);

        lbS1.setText("[S1]");
        panelStateDescrip.add(lbS1);
        lbS1.setBounds(10, 20, 30, 17);

        txtS1.setEditable(false);
        txtS1.setText("Load = ascent");
        txtS1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelStateDescrip.add(txtS1);
        txtS1.setBounds(60, 20, 180, 21);

        lbS2.setText("[S2]");
        panelStateDescrip.add(lbS2);
        lbS2.setBounds(10, 50, 30, 17);

        txtS2.setEditable(false);
        txtS2.setText("Load rejection = none");
        txtS2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelStateDescrip.add(txtS2);
        txtS2.setBounds(60, 50, 180, 21);

        panelInformacion.add(panelStateDescrip);
        panelStateDescrip.setBounds(10, 270, 290, 73);

        panelExplanation.add(panelInformacion);
        panelInformacion.setBounds(10, 50, 350, 350);

        panelComponentDetail.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelComponentDetail.setLayout(null);

        lbDetail.setFont(new java.awt.Font("Tahoma", 1, 11));
        lbDetail.setText("Component Detail");
        panelComponentDetail.add(lbDetail);
        lbDetail.setBounds(10, 0, 150, 14);

        txtResponse.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelComponentDetail.add(txtResponse);
        txtResponse.setBounds(10, 60, 230, 20);

        txtSize.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelComponentDetail.add(txtSize);
        txtSize.setBounds(10, 20, 230, 20);

        txtActuation.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelComponentDetail.add(txtActuation);
        txtActuation.setBounds(10, 40, 230, 20);

        lbFiguraComp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFiguraComp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelComponentDetail.add(lbFiguraComp);
        lbFiguraComp.setBounds(470, 10, 140, 110);

        panelExplanation.add(panelComponentDetail);
        panelComponentDetail.setBounds(10, 410, 700, 130);

        panelFigura.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelFigura.setLayout(null);

        lbDomain.setFont(new java.awt.Font("Tahoma", 1, 12));
        lbDomain.setText("Domain affected variable");
        panelFigura.add(lbDomain);
        lbDomain.setBounds(80, 10, 210, 14);

        lbFigura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelFigura.add(lbFigura);
        lbFigura.setBounds(13, 50, 293, 237);

        panelExplanation.add(panelFigura);
        panelFigura.setBounds(380, 50, 330, 350);

        getContentPane().add(panelExplanation);
        panelExplanation.setBounds(10, 10, 730, 550);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-757)/2, (screenSize.height-605)/2, 757, 605);
    }// </editor-fold>//GEN-END:initComponents

    private void txtComponentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComponentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComponentActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbAccion;
    private javax.swing.JLabel lbCompInvolved;
    private javax.swing.JLabel lbComponent;
    private javax.swing.JLabel lbDetail;
    private javax.swing.JLabel lbDomain;
    private javax.swing.JLabel lbExplanation;
    private javax.swing.JLabel lbFigura;
    private javax.swing.JLabel lbFiguraComp;
    private javax.swing.JLabel lbPorpuse;
    private javax.swing.JLabel lbProcedure;
    private javax.swing.JLabel lbProtection;
    private javax.swing.JLabel lbS1;
    private javax.swing.JLabel lbS2;
    private javax.swing.JLabel lbStateDescrip;
    private javax.swing.JLabel lbVariable;
    private javax.swing.JPanel panelComponent;
    private javax.swing.JPanel panelComponentDetail;
    private javax.swing.JPanel panelExplanation;
    private javax.swing.JPanel panelFigura;
    private javax.swing.JPanel panelInformacion;
    private javax.swing.JPanel panelStateDescrip;
    private javax.swing.JTextField txtAction;
    private javax.swing.JLabel txtActuation;
    private javax.swing.JTextField txtCompInvolved;
    private javax.swing.JTextField txtComponent;
    private javax.swing.JTextField txtProcedure;
    private javax.swing.JTextField txtProtection;
    private javax.swing.JLabel txtResponse;
    private javax.swing.JTextField txtS1;
    private javax.swing.JTextField txtS2;
    private javax.swing.JLabel txtSize;
    private javax.swing.JTextField txtVariable;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the variableRelevante
     */
    public int getVariableRelevante() {
        return variableRelevante;
    }

    /**
     * @param variableRelevante the variableRelevante to set
     */
    public void setVariableRelevante(int variableRelevante) {
        this.variableRelevante = variableRelevante;
    }

    /**
     * @return the iDVariable
     */
    public String getiDVariable() {
        return iDVariable;
    }

    /**
     * @param iDVariable the iDVariable to set
     */
    public void setiDVariable(String iDVariable) {
        this.iDVariable = iDVariable;
    }

}
