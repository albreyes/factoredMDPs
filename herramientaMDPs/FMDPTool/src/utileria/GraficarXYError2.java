package utileria;

import DataBase.DatosPersistence;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import planta.OptionsDB;


public class GraficarXYError2 extends javax.swing.JFrame {

    int serie = 1;
    double a;
    double b;
    int n = 5;
    double sx=15,sy=0,sxy=0,sx2=0;
    double []y_ = new double[n];
    double [] x = {1,2,3,4,5},y = new double[n],xy = new double[n],x_2 = new double[n];
    
    final XYSeriesCollection dataset = new XYSeriesCollection();

    /** Creates new form GraficarXYError */
    public GraficarXYError2() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        x1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnCalcular = new javax.swing.JButton();
        x2 = new javax.swing.JTextField();
        x3 = new javax.swing.JTextField();
        x4 = new javax.swing.JTextField();
        x5 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        y1 = new javax.swing.JTextField();
        y2 = new javax.swing.JTextField();
        y3 = new javax.swing.JTextField();
        y4 = new javax.swing.JTextField();
        y5 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        xx1 = new javax.swing.JTextField();
        xx2 = new javax.swing.JTextField();
        xx3 = new javax.swing.JTextField();
        xx4 = new javax.swing.JTextField();
        xx5 = new javax.swing.JTextField();
        pnGraf = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtA = new javax.swing.JLabel();
        txtB = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        y_1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        y_2 = new javax.swing.JTextField();
        y_3 = new javax.swing.JTextField();
        y_4 = new javax.swing.JTextField();
        y_5 = new javax.swing.JTextField();
        xy1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        xy2 = new javax.swing.JTextField();
        xy3 = new javax.swing.JTextField();
        xy4 = new javax.swing.JTextField();
        xy5 = new javax.swing.JTextField();
        txtsx2 = new javax.swing.JTextField();
        txtsx = new javax.swing.JTextField();
        txtsy = new javax.swing.JTextField();
        txtsxy = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        x1.setText("1");
        getContentPane().add(x1);
        x1.setBounds(31, 27, 80, 27);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("x");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 10, 80, 17);

        btnCalcular.setText("Calcular");
        btnCalcular.setEnabled(false);
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalcular);
        btnCalcular.setBounds(30, 330, 120, 29);

        x2.setText("2");
        getContentPane().add(x2);
        x2.setBounds(30, 60, 80, 27);

        x3.setText("3");
        getContentPane().add(x3);
        x3.setBounds(30, 90, 80, 27);

        x4.setText("4");
        getContentPane().add(x4);
        x4.setBounds(30, 120, 80, 27);

        x5.setText("5");
        getContentPane().add(x5);
        x5.setBounds(30, 150, 80, 27);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("y");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(130, 10, 80, 17);
        getContentPane().add(y1);
        y1.setBounds(130, 30, 80, 27);
        getContentPane().add(y2);
        y2.setBounds(130, 60, 80, 27);
        getContentPane().add(y3);
        y3.setBounds(130, 90, 80, 27);
        getContentPane().add(y4);
        y4.setBounds(130, 120, 80, 27);
        getContentPane().add(y5);
        y5.setBounds(130, 150, 80, 27);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Y'");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(400, 10, 80, 17);

        xx1.setEnabled(false);
        getContentPane().add(xx1);
        xx1.setBounds(310, 30, 80, 27);

        xx2.setEnabled(false);
        getContentPane().add(xx2);
        xx2.setBounds(310, 60, 80, 27);

        xx3.setEnabled(false);
        getContentPane().add(xx3);
        xx3.setBounds(310, 90, 80, 27);

        xx4.setEnabled(false);
        getContentPane().add(xx4);
        xx4.setBounds(310, 120, 80, 27);

        xx5.setEnabled(false);
        getContentPane().add(xx5);
        xx5.setBounds(310, 150, 80, 27);

        pnGraf.setBackground(new java.awt.Color(254, 254, 254));
        pnGraf.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnGraf);
        pnGraf.setBounds(200, 240, 510, 300);

        jLabel4.setText("a =");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(530, 30, 40, 17);

        jLabel5.setText("b =");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(530, 60, 30, 17);

        txtA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtA);
        txtA.setBounds(560, 30, 120, 20);

        txtB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtB);
        txtB.setBounds(560, 60, 120, 20);

        jButton3.setText("NIVEL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(30, 290, 120, 29);

        jButton4.setText("CONN");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(30, 380, 120, 29);

        y_1.setEnabled(false);
        getContentPane().add(y_1);
        y_1.setBounds(400, 30, 80, 27);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("XY");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(220, 10, 80, 17);

        y_2.setEnabled(false);
        getContentPane().add(y_2);
        y_2.setBounds(400, 60, 80, 27);

        y_3.setEnabled(false);
        getContentPane().add(y_3);
        y_3.setBounds(400, 90, 80, 27);

        y_4.setEnabled(false);
        getContentPane().add(y_4);
        y_4.setBounds(400, 120, 80, 27);

        y_5.setEnabled(false);
        getContentPane().add(y_5);
        y_5.setBounds(400, 150, 80, 27);

        xy1.setEnabled(false);
        getContentPane().add(xy1);
        xy1.setBounds(220, 30, 80, 27);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("x2");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(310, 10, 80, 17);

        xy2.setEnabled(false);
        getContentPane().add(xy2);
        xy2.setBounds(220, 60, 80, 27);

        xy3.setEnabled(false);
        getContentPane().add(xy3);
        xy3.setBounds(220, 90, 80, 27);

        xy4.setEnabled(false);
        getContentPane().add(xy4);
        xy4.setBounds(220, 120, 80, 27);

        xy5.setEnabled(false);
        getContentPane().add(xy5);
        xy5.setBounds(220, 150, 80, 27);
        getContentPane().add(txtsx2);
        txtsx2.setBounds(310, 200, 77, 27);
        getContentPane().add(txtsx);
        txtsx.setBounds(30, 200, 77, 27);
        getContentPane().add(txtsy);
        txtsy.setBounds(130, 200, 77, 27);
        getContentPane().add(txtsxy);
        txtsxy.setBounds(220, 200, 77, 27);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(30, 186, 360, 10);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-732)/2, (screenSize.height-568)/2, 732, 568);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed

        CalcularSum();
        calularAB();
        calcularY_();
        final LineChartDemo6 demo = new LineChartDemo6();
        demo.crearDatos(dataset);
        pnGraf.add(BorderLayout.CENTER,demo);
        pnGraf.updateUI();
        
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
           String user = JOptionPane.showInputDialog("Introducir el nivel: ");
           manager.SesionesBean sb = new manager.SesionesBean(DatosPersistence.emf);
           List<entity.Sesiones> le = sb.getSesionesUsuarioByNivel(user);
           ArrayList<XYSeries> series = new ArrayList<XYSeries>();
           String userAux = "";
         //  if(le.size()>0)
         //      userAux= le.get(0).getUsuario().getNombreUsuario();
           dataset.removeAllSeries();
           int status = 0;
           XYSeries s = null;
           int cont = 0;
           for(int i = 0; i< le.size(); i++){
               
            String name= le.get(i).getUsuario().getNombreUsuario();

               if(!name.equals(userAux)){
                  s = new XYSeries("Usuario " +serie);
                  serie++;
                  status = 1;
               }

               if(status == 1){
                    double index= cont + 1.0;
                    double intentos = porcentajeError(le.get(i).getNumIntentos());
                   // if(intentos)
                    s.add(index , intentos );
                    cont++;
               }
               if( (i+1) % 5 == 0 &&  i > 0 ){
                   dataset.addSeries(s);
                   cont = 0;
               }
              userAux= le.get(i).getUsuario().getNombreUsuario();



           }

           for(int i = 0; i< le.size()/3; i++){
                int uno = i;
                int dos =  5 ;
                int tres = 10;

                double sum = porcentajeError(le.get(uno).getNumIntentos()) + porcentajeError(le.get(i + dos).getNumIntentos()) + porcentajeError(le.get(i +tres).getNumIntentos());
                double prom = sum /3;

                double round = Math.rint(prom * 10000.0d)/10000.0d;
                

                y[i] = round;



           }
           
           y1.setText(""+y[0]);
           y2.setText(""+y[1]);
           y3.setText(""+y[2]);
           y4.setText(""+y[3]);
           y5.setText(""+y[4]);

          // CrearSerie();
           btnCalcular.setEnabled(true);

           

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         OptionsDB opDB = new OptionsDB();
        opDB.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed


    public double porcentajeError(double num){
        double r;
        if(num == 0)
            r = 10;
        else
           r =  10 - num;
           return r;
    }

    private void CalcularSum(){
      // XYSeries s1 = dataset.getSeries(0);

      
       for(int i=0;i<5;i++){

        x_2[i] = Math.pow(i+1, 2);
        xy[i] = x[i] * y[i];

       }

       xy1.setText(""+xy[0]);
       xy2.setText(""+xy[1]);
       xy3.setText(""+xy[2]);
       xy4.setText(""+xy[3]);
       xy5.setText(""+xy[4]);


       xx1.setText(""+x_2[0]);
       xx2.setText(""+x_2[1]);
       xx3.setText(""+x_2[2]);
       xx4.setText(""+x_2[3]);
       xx5.setText(""+x_2[4]);


       for(int i=0;i<5;i++){
        sy = sy + y[i];
        sx2 = sx2 + x_2[i];
        sxy = sxy + xy[i];


       }
   
       txtsy.setText(""+sy);
       txtsx2.setText(""+sx2);
       txtsxy.setText(""+sxy);

    }

    private void calularAB(){

        a = (sy *sx2 -sx * sxy)/(n*sx2-Math.pow(sx, 2));
        b = (n * sxy - sx * sy)/(n*sx2-Math.pow(sx, 2));
        txtA.setText(""+a);
        txtB.setText(""+b);

    }

    private void calcularY_(){

       final XYSeries series = new XYSeries("pendiente");
       for(int i=0;i<5;i++){
           double valor = a +b*x[i];
           double round = Math.rint(valor * 10000.0d)/10000.0d;
           y_[i] = round;
           series.add(i+1,y_[i]);

       }
       dataset.addSeries(series);
       y_1.setText(""+y_[0]);
       y_2.setText(""+y_[1]);
       y_3.setText(""+y_[2]);
       y_4.setText(""+y_[3]);
       y_5.setText(""+y_[4]);


      
       
    }


    private void CrearSerie(List<XYSeries> series){

        /* final XYSeries series = new XYSeries("Serie" +serie);
         series.add(1.0,Double.parseDouble(y1.getText()));
         series.add(2.0,Double.parseDouble(y2.getText()));
         series.add(3.0,Double.parseDouble(y3.getText()));
         series.add(4.0,Double.parseDouble(y4.getText()));
         series.add(5.0,Double.parseDouble(y5.getText()));*/

         for(int i = 0;i<series.size();i++)
              dataset.addSeries(series.get(i));

        // serie++;

        
        // JOptionPane.showMessageDialog(this, "serie agregada");

    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraficarXYError2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnGraf;
    private javax.swing.JLabel txtA;
    private javax.swing.JLabel txtB;
    private javax.swing.JTextField txtsx;
    private javax.swing.JTextField txtsx2;
    private javax.swing.JTextField txtsxy;
    private javax.swing.JTextField txtsy;
    private javax.swing.JTextField x1;
    private javax.swing.JTextField x2;
    private javax.swing.JTextField x3;
    private javax.swing.JTextField x4;
    private javax.swing.JTextField x5;
    private javax.swing.JTextField xx1;
    private javax.swing.JTextField xx2;
    private javax.swing.JTextField xx3;
    private javax.swing.JTextField xx4;
    private javax.swing.JTextField xx5;
    private javax.swing.JTextField xy1;
    private javax.swing.JTextField xy2;
    private javax.swing.JTextField xy3;
    private javax.swing.JTextField xy4;
    private javax.swing.JTextField xy5;
    private javax.swing.JTextField y1;
    private javax.swing.JTextField y2;
    private javax.swing.JTextField y3;
    private javax.swing.JTextField y4;
    private javax.swing.JTextField y5;
    private javax.swing.JTextField y_1;
    private javax.swing.JTextField y_2;
    private javax.swing.JTextField y_3;
    private javax.swing.JTextField y_4;
    private javax.swing.JTextField y_5;
    // End of variables declaration//GEN-END:variables

}
