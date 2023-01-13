package planta;



import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import robotica.Graficas;
import javax.swing.JFrame;


import robotica.AreaNavegacion;
import robotica.Celda;
import robotica.Punto;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class RewardPanel extends AreaNavegacion{
    int escala2;
  public RewardPanel() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public RewardPanel(JFrame jframe) {
    super(jframe);
  }

  public RewardPanel(int ancho,int alto,int tamano,int escala) {
    super();
    actualizaDatos(ancho, alto, tamano, escala);
  }

  public void paint(Graphics g) {

      Dimension d = getSize();

    if(datosActualizados){
        this.escala2 = escala;
        Graficas.dibujaCeldasPorReward(g,d, celdas, escala);
        Graficas.graficaRobot(g, d, x, y,  escala);
        Graficas.graficaLimites(g,d,ancho,alto,escala);
        g.setColor(Color.black);
        dibujaLeyendas(g,"Steam flow", "Drum Pressure",0,alto, escala);
        drawScale( g, 30,60, 2000,6000,  escala,  8, 10);
    }

  }

  public void setGoal(Vector map, double reward, Punto punto) {

    for (int i = 0; i < map.size(); i++) {
      Celda c = (Celda) map.elementAt(i);
      if (Punto.miembro(punto, c))
        c.setReward(reward);
    }

    repaint();
  }

  void jbInit() throws Exception {
  }
}
