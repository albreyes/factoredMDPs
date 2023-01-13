package utileria;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class Dual2_ extends JPanel {

    BufferedImage grafica = null;
    private ArrayList<Double> data = new ArrayList();
    private ArrayList<Double> dataAc = new ArrayList();
    private ArrayList<Double> dataAcExe = new ArrayList();
    private int ANCHO;
    private int ALTO;
    private String labelX;
    private String labelY;
    private int rechazo = 0;
    private double util;
    private String leyendas;

    public Dual2_(int ancho, int alto, String titulo, String labelX, String labelY) {

        JFrame j    = new JFrame("Grafica de Utilidades");
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

     public void datos(ArrayList<Double> datos){
        this.data = datos;
        repaint();
    }
    public void datosAc(ArrayList<Double> datos){
        this.dataAc = datos;
        repaint();
    }
    public void datosAcExe(ArrayList<Double> datos){
        this.dataAcExe = datos;
        repaint();
    }

     public BufferedImage planoImagen(){
        ChartPanel chart= creaPlano();
        BufferedImage image = chart.getChart().createBufferedImage(ANCHO, ALTO);
        return image;
    }

    private ChartPanel creaPlano() {

        final CategoryDataset dataset1 = createDataset1();


        final JFreeChart chart = ChartFactory.createLineChart3D(
                "Grafica de Utilidad", // titulo
                "Tiempo", // titulo eje x
                "Utilidad", // titulo eje y
                dataset1, // conjunto de datos primer linea
                PlotOrientation.VERTICAL,
                true, // incluir leyendas
                true, // incluir tooltips
                false // generador URL
        );


        chart.setBackgroundPaint(Color.white);



        // get a reference to the plot for further customisation...
       // final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        final CategoryPlot plot = chart.getCategoryPlot();

        plot.setBackgroundPaint(new Color(255, 255, 255));
        plot.getDomainGridlinePaint();
        plot.getRenderer().setBaseSeriesVisible(true);
        


        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        final CategoryDataset dataset2 = createDataset2();
       
        plot.setDataset(1, dataset2);

        plot.mapDatasetToRangeAxis(1, 1);


        

      //  final CategoryAxis domainAxis = plot.getDomainAxis();
       // domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        final ValueAxis axis2 = new NumberAxis("Acciones");

        plot.setRangeAxis(1, axis2);

        
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setBaseItemLabelsVisible(true);
        renderer2.setSeriesLinesVisible(1, false);

         renderer2.setSeriesLinesVisible(0, false);
        renderer2.setSeriesShapesVisible(1, true);
       
        
        plot.setRenderer(1, renderer2);
        

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //setContentPane(chartPanel);
        
        return chartPanel;

    }


    private CategoryDataset createDataset1() {

        final String series1 = "Utilidad";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0 ; i< data.size() ; i++){
            dataset.addValue(data.get(i), series1, ""+i);

        }

    return dataset;

    }


    private CategoryDataset createDataset2() {

        final String series1 = "Politica Recomendada";
        final String series2 = "Acciones Ejecutadas";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0 ; i< dataAc.size() ; i++){
            dataset.addValue(dataAc.get(i), series1, ""+i);
        }

         for(int i = 0 ; i< dataAcExe.size() ; i++){
            dataset.addValue(dataAcExe.get(i), series2, ""+i);
        }

    return dataset;

    }
   


    public void paintComponent(Graphics g) {
      grafica = this.planoImagen();
      g.drawImage(grafica,0,0,null);
   }
   public void update(Graphics g){
        paint(g);
    }


    public static void main(final String[] args) {

        final Dual2_ demo = new Dual2_(950,400,"Grafica de Utilidades","Tiempo","Utilidad");
        ArrayList<Double> d = new ArrayList();
        ArrayList<Double> a = new ArrayList();
        ArrayList<Double> c = new ArrayList();
        int i=0;
        while(true){
            Random r = new Random();
            d.add(r.nextDouble() * 500.0);
            a.add(r.nextDouble() * 8);
            c.add(r.nextDouble() * 8);
          
            demo.datosAc(a);
            demo.datosAcExe(c);
            demo.datos(d);
            
            demo.repaint();
            i++;
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){;}
        }

    }

}