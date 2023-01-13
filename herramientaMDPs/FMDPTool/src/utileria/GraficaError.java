package utileria;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class GraficaError extends JPanel {

    BufferedImage grafica = null;
    private ArrayList<Integer> data = new ArrayList();
    private int ANCHO;
    private int ALTO;
    private String labelX;
    private String labelY;
    private int rechazo = 0;
    private double util;
    private String leyendas;

    public GraficaError(int ancho, int alto, String titulo, String labelX, String labelY) {

        JFrame j    = new JFrame("Grafica de Errores");
        j.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.ANCHO  = ancho;
        this.ALTO   = alto;
        this.labelX = labelX;
        this.labelY = labelY;
        j.setSize(ANCHO + 30, ALTO + 40);
        j.setLocation(0, 0);
        j.getContentPane().add(this);
        j.setVisible(true);

        

    }

     public void datos(ArrayList<Integer> datos){
        this.data = datos;
        repaint();
    }
    
     public BufferedImage planoImagen(){
        ChartPanel chart= creaPlano();
        BufferedImage image = chart.getChart().createBufferedImage(ANCHO, ALTO);
        return image;
    }

    private ChartPanel creaPlano() {

        final  XYDataset dataset1 = createDataset1();


        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Grafica de Errores", // titulo
                "Sesion", // titulo eje x
                "Porcentaje de error", // titulo eje y
                dataset1, // conjunto de datos primer linea
                PlotOrientation.VERTICAL,
                true, // incluir leyendas
                true, // incluir tooltips
                false // generador URL
        );


        chart.setBackgroundPaint(Color.white);



        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer= (XYLineAndShapeRenderer) plot.getRenderer();
        plot.getRangeAxis().setRange(0, 100);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseShapesVisible(true);

   

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //setContentPane(chartPanel);
        
        return chartPanel;

    }


   private XYDataset createDataset1() {
        XYSeries series = new XYSeries("Errores");

        for(int i = 0 ; i< data.size() ; i++){
            series.add(i,data.get(i));

        }
        XYDataset juegoDatos= new XYSeriesCollection(series);

        return juegoDatos;

    }

    public void paintComponent(Graphics g) {
      grafica = this.planoImagen();
      g.drawImage(grafica,0,0,null);
   }
   public void update(Graphics g){
        paint(g);
    }
  

}