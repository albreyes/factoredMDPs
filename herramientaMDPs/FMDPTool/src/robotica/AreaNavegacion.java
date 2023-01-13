package robotica;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Vector;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AreaNavegacion extends JPanel {

  protected BorderLayout borderLayout1 = new BorderLayout();
  public Vector celdas;
  protected int ancho,alto,tamano,escala;
  public int x,y,ang;
  protected boolean datosActualizados=false;
  protected Movimientoc mov=new Movimientoc();

  public AreaNavegacion() {
    try {

      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public AreaNavegacion(JFrame ventana) {
    try {
      mov = new Movimientoc(ventana,1);
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public void timeDelay(int velGiro) {
    try {
      Thread.sleep(velGiro);
    }
    catch (Exception ex) {}
  }

  public double getReward(int x, int y){

    double reward=Double.NaN;
    Punto p=new Punto(x,y);

    for(int i=0; i<celdas.size();i++){
      Celda aux=(Celda) celdas.elementAt(i);
      if(Punto.miembro(p,aux)){
        reward=aux.recompensa;
        break;
      }
    }
    return reward;
  }

  public void actualizaDatos(int ancho,int alto,int tamano,int escala){

    this.ancho=ancho;
    this.alto=alto;
    this.tamano=tamano;
    this.escala=escala;
    celdas= Celda.generaCeldas(tamano, ancho, alto);
    datosActualizados=true;
  }

  public void actualizaPosicion(int x, int y, int ang){
    this.x   = x;
    this.y   = y;
    this.ang = ang;

  }

  public void drawScale(Graphics g,
          double limInfX,double limSupX, double limInfY,double limSupY, int escala, int m, int n){

         double anchoCelda=(double)(ancho/escala)/n;
         double altoCelda=(double)(alto/escala)/m;

          double deltaX=(limSupX-limInfX)/(double)n;
          double deltaY=(limSupY-limInfY)/(double)m;
/*
          int cx=x;
          int h=(int)((double)this.getSize().height/escala);
          int cy=y+h;

          g.setFont(new Font("",9,9));
          // eje de las y
          for(int i=0; i<=m; i++, cy-=altoCelda)
          g.drawString(""+(int)(limInfY+deltaY*i),x-30,cy);

          // eje de las x
          for(int j=0; j<=n; j++, cx+=anchoCelda)
          g.drawString(""+(int)(limInfX+deltaX*j),cx-15,y+h+20);
*/



Dimension dim = getSize();
int cx = dim.width / 4 + 0 / escala;
int cy = 7 * dim.height / 8 - 0 / escala;

g.setFont(new Font("",9,9));
// eje de las y
for(int i=0; i<=m; i++, cy-=altoCelda)
g.drawString(""+(int)(limInfY+deltaY*i),cx/2,cy);

// eje de las x
for(int j=0; j<=n; j++, cx+=anchoCelda)
g.drawString(""+(int)(limInfX+deltaX*j),cx,cy);

/*
          g.setFont(new Font("",9,9));
          // eje de las y
          for(int i=0; i<=m; i++, cy-=altoCelda)
          g.drawString(""+(int)(limInfY+deltaY*i),cx-1200/escala,cy);

          // eje de las x
          for(int j=0; j<=n; j++, cx+=anchoCelda)
          g.drawString(""+(int)(limInfX+deltaX*j),cx,cy+400/escala);
        */
  }


  private void drawLeyends(Graphics g, int x, int y, String mensaje, int pos){

          // horizontal
          if(pos==0) g.drawString(mensaje,x,y);
          else {

          int i,cy,cx;
          for(i=0, cx=x, cy=y; i<mensaje.length(); i++,cy+=10)
          g.drawString(""+mensaje.charAt(i),cx,cy);
          }
  }

  public void dibujaLeyendas(Graphics g, String mensajex, String mensajey, int h, int v, int escala){

    Dimension dim = getSize();
  //  int cx=dim.width/8+0/escala;
  //  int cy=7*dim.height/9-0/escala;

  int cx = dim.width / 4 + h / escala;
int cy = 7 * dim.height / 8 - v / escala;

    g.setColor(Color.black);
    g.setFont(new Font("Dialog",Font.BOLD,10));

     drawLeyends(g,cx,cy-60,mensajex,0);
    drawLeyends(g,cx/2-30,cy,mensajey,1);

   // drawLeyends(g,cx*4,cy,mensajex,0);
   // drawLeyends(g,cx/2,cy*2,mensajey,1);

  //  drawLeyends(g,cx,cy-1500/escala,mensajex,0);
  //  drawLeyends(g,cx-2300/escala,cy,mensajey,1);
  }


  void jbInit() throws Exception {
    this.setBackground(SystemColor.controlText);
    this.setLayout(borderLayout1);
  }

  public void paint(Graphics g) {
    Dimension d = getSize();

    if(datosActualizados){

      Graficas.dibujaCeldasPorReward(g,d, celdas, escala);
      Graficas.graficaRobot(g, x, y, ang, escala, d);
      Graficas.graficaLimites(g,d,ancho,alto,escala);

         dibujaLeyendas(g,"Coordenada x", "Coordenada y",0,alto, escala);
         //g.setColor(Color.yellow);
         drawScale( g, 0,ancho, 0,alto,  escala,  10, 10);
    }

  }

}