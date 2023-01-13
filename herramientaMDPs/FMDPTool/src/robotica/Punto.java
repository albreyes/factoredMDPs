package robotica;

import java.util.Vector;
import java.io.*;

/**
 * <p>Title: Clase Punto</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Tec de Monterrey Cuernavaca</p>
 * @author Alberto Reyes Ballesteros
 * @version 1.0
 */

public class Punto implements Serializable{

   int x,y;

  public Punto(int x, int y){
    this.x=x;
    this.y=y;
  }

  public void imprimeCoords(){
    System.out.println("("+x+","+y+")");
  }
/*
  public int getX() {
    return x;
  }
  public int getY(){
  return y;}
*/
  //imprime el contenido de un vector de puntos
   public static void imprimePuntos(Vector lista){
     Punto p;
    for(int i=0; i<lista.size(); i++){
      p=(Punto)lista.elementAt(i);
      System.out.print("("+p.x+","+p.y+")"+"  ");
      System.out.println("");
    }
   }

   // distancia euclideana entre dos puntos
   public static double dEuclideana(Punto p1, Punto p2) {
     return Math.sqrt(Math.pow( (p2.x - p1.x), 2) + Math.pow( (p2.y - p1.y), 2));
   }

  // distancia euclideana entre un punto y un estado
  public static double dEuclideana(Punto p1, Estado p2){

    return dEuclideana(p1, p2.p);
  }

  // Genera lista de puntos dentro de radio de busqueda dado un conjunto
  // de puntos
  public static Vector puntosRango(Punto p, int radio, Vector estados){
    Vector v=new Vector();
    Estado es;

    for(int i=0;i<estados.size();i++)
      if(dEuclideana(p, (Estado)estados.elementAt(i))<=radio){
        es=(Estado)estados.elementAt(i);
        v.addElement(es.p);
      }

    return v;
  }

  // determina el vecino mas cercano a ref
  public static Punto masCercano(Punto ref, Vector puntos){
    Punto p=null;
    Vector v = new Vector();
    double distMin = Double.MAX_VALUE;
    double d=0;
    for (int i = 0; i < puntos.size(); i++){
      d=dEuclideana(ref, (Punto) puntos.elementAt(i));
      if (d <= distMin){
        distMin=d;
        p=(Punto)puntos.elementAt(i);
      }
    }
    return p;
  }


  // genera vecinos
   public static Vector generaVecinos(Punto p, int dist){
     Vector v=new Vector();
     v.addElement(new Punto(p.x,p.y+dist));
     v.addElement(new Punto(p.x-dist,p.y+dist));
     v.addElement(new Punto(p.x-dist,p.y));
     v.addElement(new Punto(p.x-dist,p.y-dist));
     v.addElement(new Punto(p.x,p.y-dist));
     v.addElement(new Punto(p.x+dist,p.y-dist));
     v.addElement(new Punto(p.x+dist, p.y));
     v.addElement(new Punto(p.x+dist, p.y+dist));
     return v;
   }

  // elimina puntos del vector grande contenidos en el chico
  public static Vector eliminaPuntos(Vector grande, Vector chico){
    Vector v=new Vector();

    for(int i=0;i<grande.size();i++)
      if(!miembro((Punto)grande.elementAt(i),chico))
        v.addElement((Punto)grande.elementAt(i));

    return v;
  }
  // checa si un punto es miembro de un conjunto de puntos
  public static boolean miembro(Punto p, Vector puntos){
    boolean ocurre = false;
    Punto p0;
    for (int i = 0; i < puntos.size(); i++){
      p0=(Punto)puntos.elementAt(i);
      if (p.x==p0.x&&p.y==p0.y)
        ocurre = true;
    }
    return ocurre;
  }

  // checa si un punto es miembro de una celda
  public static boolean miembro(Punto p, Celda c){
    boolean ocurre = false;
    int xmin,xmax, ymin,ymax;
    if(c.dim==null){
       xmin = c.puntoCentral.x - c.size / 2;
       xmax = c.puntoCentral.x + c.size / 2;
       ymin = c.puntoCentral.y - c.size / 2;
       ymax = c.puntoCentral.y + c.size / 2;
    } else{
      xmin = c.puntoCentral.x - c.dim.width / 2;
      xmax = c.puntoCentral.x + c.dim.width / 2;
      ymin = c.puntoCentral.y - c.dim.height / 2;
      ymax = c.puntoCentral.y + c.dim.height / 2;
    }

      if ((xmin<=p.x&& p.x<=xmax) && (ymin<=p.y&& p.y<=ymax))
        ocurre = true;

    return ocurre;
  }

  // genera puntos de interes
  public static Vector generaPtosInteres(int c, int ancho, int alto){
    Vector v=new Vector();

    for(int y=0; y<alto-c;y+=3*c)
      for(int x=0;x<ancho-c;x+=3*c)
        v.addElement(new Punto(x,y));

    return v;
  }

}