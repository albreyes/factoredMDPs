package robotica;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Vector;
import ia.Variable;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Graficas {
  Graphics g;
  int height, width;

  public Graficas(Graphics g, int width, int height ) {
    this.g=g;
    this.height=height;
    this.width=width;
  }

  public static void imprimeTexto(JTextField campo, String dato){
       campo.setText(dato);
  }

  public static void graficaRastro(JPanel grafica, int x, int y, int escala) {

    int width = grafica.getSize().width;
    int height = grafica.getSize().height;

    Graphics g = grafica.getGraphics();
    g.setColor(Color.green);
    g.fillOval( width/4+x/escala , 7*height/8-y/escala , 2, 2);
  }

  public static void graficaLetrero(JPanel grafica, String pol, Punto p, int escala) {

    int width = grafica.getSize().width;
    int height = grafica.getSize().height;

    int x=p.x;
    int y=p.y;

    Graphics g = grafica.getGraphics();
    g.setColor(Color.LIGHT_GRAY);
    g.drawString( pol, width/4+x/escala , 7*height/8-y/escala );
  }


  public static void graficaObstaculos( Graphics g, boolean[] obstaculo){

    int[][] coords={{70,20},{130, 70}, {70, 130}, {20, 70}};
    g.setColor(Color.BLACK);
    for(int i=0; i<obstaculo.length; i++){
      if(obstaculo[i]) g.fillRect(coords[i][0], coords[i][1], 10,10);
        else g.drawRect(coords[i][0], coords[i][1], 10,10);
    }
  }

  public static void dibujaCeldas(JPanel grafica, Vector celdas, int escala) {

    int width = grafica.getSize().width;
    int height = grafica.getSize().height;

    Graphics g = grafica.getGraphics();

    dibujaCeldas(g, new Dimension(width,height),celdas,escala);
  }

  public static void dibujaCeldas(Graphics g, Dimension d, Vector celdas, int escala) {

    Celda c;
    for (int i = 0; i < celdas.size(); i++) {
      c = (Celda) celdas.elementAt(i);
      graficaCelda(g, d, c, escala);
    }
  }

  // la celda se grafica de acuerdo al color indicado
  public static void graficaCelda(Graphics g, Dimension d, Celda c, int escala, Color color) {

    int width = d.width;
    int height = d.height;

    Punto puntoCentral = c.puntoCentral;

    int sizex,sizey;

    if (c.dim == null)
      sizex = sizey = c.size;
    else {
      sizex = c.dim.width;
      sizey = c.dim.height;
    }

    byte estado = c.estado;

    g.setColor(color);
    fillCell(g, new Dimension(width, height), puntoCentral, sizex, sizey, escala);
    // comentarlo para tomar foto
    g.setColor(Color.DARK_GRAY);
    drawCell(g, new Dimension(width, height), puntoCentral, sizex, sizey, escala);
  }


  // este es para los variar el color de la celda en funcion del valor

  public static void dibujaCeldasPorValor(JPanel grafica, Vector celdas, int escala) {
    int width = grafica.getSize().width;
    int height = grafica.getSize().height;

    Graphics g = grafica.getGraphics();
    double normalizado;
    Color color=Color.red;
    double max=Celda.valorMaximo(celdas);

    Celda c;
    for (int i = 0; i < celdas.size(); i++) {
      c = (Celda) celdas.elementAt(i);
      normalizado=c.valor*255/max;
      color=new Color(255,255,(int)normalizado);
     // if(!(c.estado==3)||!(c.estado==4))
      graficaCelda(grafica, c, escala, color);
    }
  }


  public static void dibujaCeldasPorReward(Graphics g, Dimension dim,  Vector celdas, int escala) {

    double normalizado;
    Color color = Color.red;

    double max=Double.MIN_VALUE;
    for(int i=0; i<celdas.size();i++){
      Celda c=(Celda)celdas.elementAt(i);
      if(c.recompensa>max)
        max=c.recompensa;
    }

    Celda c;

    for (int i = 0; i < celdas.size(); i++) {
      c = (Celda) celdas.elementAt(i);
      normalizado = c.recompensa * 255 / max;
      color = new Color( (int) normalizado);

      Graficas.graficaCelda(g, dim ,c, escala, color);
    }
  }


  public static void dibujaCeldasPorReward(JPanel grafica, Vector celdas, int escala) {
    int width = grafica.getSize().width;
    int height = grafica.getSize().height;

    Dimension dim=new Dimension(width,height);

    Graphics g = grafica.getGraphics();
    dibujaCeldasPorReward(g,dim,  celdas,  escala);
  }


  public static void graficaCelda(JPanel grafica, Celda c, int escala, Color color) {

    int width = grafica.getSize().width;
    int height = grafica.getSize().height;

    Graphics g = grafica.getGraphics();

    Punto puntoCentral = c.puntoCentral;
    int size = c.size;

    graficaCelda(g,new Dimension(width,height), c,escala,color);
  }

  private static void fillCell(Graphics g, Dimension dim, Punto puntoCentral,
                               int sizeHor, int sizeVer, int escala) {

    g.fillRect( (int) dim.getWidth() / 4 +
               (puntoCentral.x - sizeHor / 2) / escala,
               7 * (int) dim.getHeight() / 8 -
               (puntoCentral.y + sizeVer / 2) / escala,
               sizeHor / escala, sizeVer / escala);
  }

  private static void drawCell(Graphics g, Dimension dim, Punto puntoCentral,
                               int sizeHor, int sizeVer, int escala) {

    g.drawRect( dim.width / 4 +
               (puntoCentral.x - sizeHor / 2) / escala,
               7 * dim.height / 8 -
               (puntoCentral.y + sizeVer / 2) / escala,
               sizeHor / escala, sizeVer / escala);
  }

  private static void drawCadena(Graphics g, Dimension dim, String cadena, Punto puntoCentral,
                                int escala) {

    g.drawString(cadena, dim.width / 4 +
               (puntoCentral.x /*- sizeHor / 2*/) / escala,
               7 * dim.height / 8 -
               (puntoCentral.y /*+ sizeVer / 2*/) / escala
               );
  }


  // grafica la celda de acuerdo con el estado
  public static void graficaCelda(JPanel grafica, Celda c, int escala) {

    int width = grafica.getSize().width;
    int height = grafica.getSize().height;

    Graphics g = grafica.getGraphics();
    graficaCelda(g,new Dimension(width,height),c,escala);
  }


  // grafica la celda de acuerdo con el estado
  public static void graficaCelda(Graphics g, Dimension dim, Celda c, int escala) {

    Punto puntoCentral = c.puntoCentral;
    int size = c.size;
    byte estado = c.estado;
    Color color=Color.BLACK;

    if (estado == 0)
      color=Color.white;
    else if (estado == 1)
      color=Color.black;
    else if (estado == 2)
      color=Color.gray;
    else if (estado == 3)
      color=Color.green;
    else if (estado == 4)
      color=Color.blue;

     graficaCelda(g,dim,c,escala,color);

  }


  public static void graficaRobot( Graphics g, int x, int y){

    g.setColor(Color.BLUE);
    g.drawString("Posición (x,y,ang):",30,160);
    g.setColor(Color.RED);
    g.fillOval(40,40,80,80);
    g.setColor(Color.BLACK);
    g.fillRect(70,50,20,10);
  }

  public static void graficaParticiones(JPanel grafica, int ancho, int alto, int escala,
                                        Vector particiones) {
    int width = grafica.getSize().width;
    int height = grafica.getSize().height;

    Graphics g = grafica.getGraphics();
    graficaParticiones( g,  new Dimension(width,height), ancho,  alto,  escala,
                                         particiones);

  }
/*
  public static void graficaParticiones(Graphics g, Dimension dim, int ancho, int alto,int escala,
                                        Vector particiones) {

    for(int i=0; i<particiones.size(); i++){

    int xmin=0, ymin=0, xmax=ancho, ymax=alto;

    Variable[] var=(Variable[]) particiones.elementAt(i);
    Variable x=Variable.getVariable("x",var);
    Variable y=Variable.getVariable("y",var);

    if(!(x==null)&&!(y==null)){
      if(x.min!=Integer.MIN_VALUE) xmin=x.min;
        if(x.max!=Integer.MAX_VALUE) xmax=x.max;
          if(y.min!=Integer.MIN_VALUE) ymin=y.min;
            if(y.max!=Integer.MAX_VALUE) ymax=y.max;

    } else

    if(x==null&&!(y==null)){
      if(y.min!=Integer.MIN_VALUE) ymin=y.min;
        if(y.max!=Integer.MAX_VALUE) ymax=y.max;

    } else

    if(!(x==null)&&y==null){
      if(x.min!=Integer.MIN_VALUE) xmin=x.min;
        if(x.max!=Integer.MAX_VALUE) xmax=x.max;
    }
    int cx=xmin+(xmax-xmin)/2;
    int cy=ymin+(ymax-ymin)/2;
    Punto central=new Punto(cx,cy);

    g.setColor(Color.white);
    drawCell(g,  dim, central, (xmax-xmin),  (ymax-ymin),  escala);
    drawCadena(g,dim,"q"+i, central, escala);
    }
  }
*/

public static void graficaParticiones(Graphics g, Dimension dim, int ancho,
                                      int alto, int escala,
                                      Vector particiones) {

  for (int i = 0; i < particiones.size(); i++) {

    Variable[] var = (Variable[]) particiones.elementAt(i);
    Punto central=graficaParticion(g, dim, ancho, alto, escala,var);
    drawCadena(g,dim,"q"+i, central, escala);
  }
}

public static void graficaPolitica(JPanel grafica, int ancho, int alto,
                                   int escala,
                                   Vector particiones, int[] politica,
                                   String[] politicaSt) {
  int width = grafica.getSize().width;
  int height = grafica.getSize().height;

  Graphics g = grafica.getGraphics();
  graficaPolitica(g, new Dimension(width, height), ancho, alto, escala,
                  particiones, politica, politicaSt);

}


public static void graficaPolitica(Graphics g, Dimension dim, int ancho,
                                   int alto, int escala,
                                   Vector particiones, int[] politica,
                                   String[] politicaString) {

  for (int i = 0; i < particiones.size(); i++) {

    Variable[] var = (Variable[]) particiones.elementAt(i);
    Punto central=graficaParticion(g, dim, ancho, alto, escala,var);
    drawCadena(g,dim,politicaString[politica[i]], central, escala);
  }
}


  public static Punto graficaParticion(Graphics g, Dimension dim, int ancho, int alto,int escala,
                                        Variable[] particion) {

      int xmin=0, ymin=0, xmax=ancho, ymax=alto;

      Variable[] var=particion;
      Variable x=Variable.getVariable("x",var);
      Variable y=Variable.getVariable("y",var);

      if(!(x==null)&&!(y==null)){
        if(x.min!=Integer.MIN_VALUE) xmin=x.min;
          if(x.max!=Integer.MAX_VALUE) xmax=x.max;
            if(y.min!=Integer.MIN_VALUE) ymin=y.min;
              if(y.max!=Integer.MAX_VALUE) ymax=y.max;

      } else

      if(x==null&&!(y==null)){
        if(y.min!=Integer.MIN_VALUE) ymin=y.min;
          if(y.max!=Integer.MAX_VALUE) ymax=y.max;

      } else

      if(!(x==null)&&y==null){
        if(x.min!=Integer.MIN_VALUE) xmin=x.min;
          if(x.max!=Integer.MAX_VALUE) xmax=x.max;
      }
      int cx=xmin+(xmax-xmin)/2;
      int cy=ymin+(ymax-ymin)/2;
      Punto central=new Punto(cx,cy);

      g.setColor(Color.white);
      drawCell(g,  dim, central, (xmax-xmin),  (ymax-ymin),  escala);
      return central;

    }


  public static void graficaLimites(Graphics g, Dimension dim, int ancho, int alto, int escala){
    Punto central=new Punto(ancho/2,alto/2);
    g.setColor(Color.white);
    drawCell(g,dim,central,ancho,alto,escala);
  }


  public static void graficaRobot(JPanel grafica, int x, int y, int escala){
    int width = grafica.getSize().width;
    int height = grafica.getSize().height;

    Graphics g = grafica.getGraphics();
    graficaRobot(g,new Dimension(width,height),x,y,escala);

  }

  public static void graficaRobot(Graphics g, Dimension dim, int h, int v, int escala){

    int radio=5;
    int cx=dim.width/4+h/escala;
    int cy=7*dim.height/8-v/escala;

    g.setColor(Color.RED);
    g.fillOval( cx-radio , cy-radio , 2*radio, 2*radio);
  }


  public static void graficaRobot(Graphics g, int width, int height, int h, int v, int ang, int escala){
    int radio=5;
    int cx=width/4+h/escala;
    int cy=7*height/8-v/escala;

    int nh=cx-radio/2;
    int nv=cy+radio/2;

    double x=nh+(int)(((double)radio)*Math.cos(Math.toRadians(ang)));
    double y=nv-(int)(((double)radio)*Math.sin(Math.toRadians(ang)));

    Font f=new Font("Dialog",Font.BOLD,20);

    g.setColor(Color.RED);
    g.fillOval( cx-radio , cy-radio , 2*radio, 2*radio);

    g.setColor(Color.yellow);
    g.setFont(f);
    g.drawString(".", (int)x,(int)y);
  }

  public static void graficaRobot( Graphics g, int h, int v, int ang, int escala, Dimension dim){


    int width = dim.width;
    int height = dim.height;

    graficaRobot( g,  width,  height,  h,  v,  ang,  escala);

  }


  public static void graficaRobot( JPanel grafica, int h, int v, int ang, int escala){

    int width = grafica.getWidth();
    int height = grafica.getHeight();
    Graphics g=grafica.getGraphics();

    graficaRobot( g,  width,  height,  h,  v,  ang,  escala);

  }

  public static void graficaRobot(JPanel panel, Graphics g, int x, int y, int ang, int escala,
                           Dimension d, Image robot) {

    int deltax=robot.getWidth(panel)/2;
    int deltay=robot.getHeight(panel)/2;

    int cx = d.width / 4 + (x) / escala;
    int cy = 7 * d.height / 8 - (y) / escala;

    g.drawImage(robot, cx-deltax, cy-deltay, panel);
  }


}
