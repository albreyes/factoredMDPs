package robotica;

import utileria.Listas;
import java.util.Vector;
import utileria.FileOutput;
import utileria.Matrices;
import utileria.tablas;
import aprendizaje.ValorDiscreto;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Estado {
  Punto p;
  int heading;
  // obstaculos [der, centro, izq] respecto a frente robot
  boolean[] obstaculos=new boolean[3];

  public Estado() {
    p=new Punto(0,0);
    heading=0;
  }
  public Estado(Punto p, int heading, boolean[] obstaculos) {
    this.p=p;
    this.heading=heading;
    this.obstaculos=obstaculos;
  }

  public Estado(Punto p, int heading) {
    this.p=p;
    this.heading=heading;
  }

  // por derogarse
  public static Estado colecta(Punto p, int heading,
                        int[] lecturas, int umbral) {
    int min = 0;
    int max = lecturas.length - 1;
    int[] central = Sensores.sensoresCardinales(heading, lecturas.length, min,
                                                max);
    System.out.println("sensores centrales");
    Listas.imprimeLista(central);
    boolean[] obstaculo = new boolean[central.length];
    Vector v;
    for (int i = 0; i < obstaculo.length; i++) {
      v = Sensores.indSensores(central[i], max/8, min, max);
      obstaculo[i] = Sensores.obstaculo(v, lecturas, umbral);
    }
    return new Estado(p, heading, obstaculo);
  }

  // primera version de colecta
  public static Estado colecta1(Punto p, int heading,
                                int[] lecturas, int umbral) {

    boolean[] obstaculo = Sensores.obstDiscreto(Sensores.percep02(lecturas,
        umbral), umbral);

    return new Estado(p, heading, obstaculo);
  }

  // por derogarse
  public static void eliminaEdosRepetidos(Vector estados) {

    Vector v = new Vector();
    for (int i = 0; i < estados.size(); i++)
      if (!miembro( ( (Estado) estados.elementAt(i)).p, v))
        v.add(estados.elementAt(i));
    estados = v;
  }

// checa si el punto s esta contenido en el vector de estados v
  private static boolean miembro(Punto s, Vector v) {
    boolean ocurre = false;

    for (int i = 0; i < v.size(); i++)
      if ( (s.x == ( (Estado) v.elementAt(i)).p.x) &&
          (s.y == ( (Estado) v.elementAt(i)).p.y))
        ocurre = true;
    return ocurre;
  }

  // Genera lista de estados dentro de radio de busqueda dado un conjunto
  // de estados
  public static Vector estadosRango(Punto p, int radio, Vector estados){

    Vector v=new Vector();
    Estado es;

    for(int i=0;i<estados.size();i++)
      if(Punto.dEuclideana(p, (Estado)estados.elementAt(i))<=(double)radio){
        es=(Estado)estados.elementAt(i);
        v.addElement(es);
      }

    return v; // regresa vector de estados lleno o vacio
  }


  // determina el vecino mas cercano a ref
  public static Estado masCercano(Punto ref, Vector estados){

   Estado e=null;
   if(!estados.isEmpty()){

    double distMin = Double.MAX_VALUE;
    double d=0;
    for (int i = 0; i < estados.size(); i++){
      e=(Estado)estados.elementAt(i);
      d=Punto.dEuclideana(ref, e.p);
      if (d <= distMin){
        distMin=d;
        e=(Estado)estados.elementAt(i);
      }
    }
  }
    return e;
  }

// devuelve el estado de un conjunto de estados donde este el punto p
  public static Estado con(Punto p, Vector estados) {
    Estado e;
    int estado = -1;
    Punto p0;
    System.out.println("estados registrados: "+estados.size());
    if(p!=null)
    for (int i = 0; i < estados.size(); i++) {
      e = (Estado) estados.elementAt(i);
      p0 = e.p;
      if (p.x == p0.x && p.y == p0.y)
        estado = i;
    }
    if (estado < 0)
      return null;
    else
      return (Estado) estados.elementAt(estado);
  }

  // escribe en un archivo los estados visitados
  public static void registraEstados(Vector estados){

    FileOutput fo=new FileOutput("estados.txt");
    Estado c;
    String s,aux;

    fo.writeStringln("x\ty\theading\tizq\tcentro\tder");

    for(int i=0;i<estados.size();i++){
      c=(Estado)estados.elementAt(i);
      aux="";
      for(int j=0;j<c.obstaculos.length;j++)
        aux=aux+c.obstaculos[j]+"\t";

      s = ""+c.p.x+"\t"+c.p.y+"\t"+c.heading+"\t"+ aux;
      fo.writeStringln(s);
    }
    fo.close();
  }

  // escribe en un archivo los estados visitados
  public static void registraEstadosAcciones(Vector estados, Vector acciones){

    FileOutput fo=new FileOutput("estadosAcciones.txt");
    Estado e;
    String s;

    fo.writeStringln("x\ty\theading\ta");

    for(int i=0;i<estados.size();i++){
      e=(Estado)estados.elementAt(i);

      s = ""+e.p.x+"\t"+e.p.y+"\t"+e.heading+"\t"+ acciones.elementAt(i);
      fo.writeStringln(s);
    }
    fo.close();
  }

  // escribe en un archivo  [x,y,ang,reward]
  public static void registraEstadoReward(Vector estados, Vector metas){

    FileOutput fo=new FileOutput("estadoReward.txt");
    Estado e;
    String s;
    double r;

   // fo.writeStringln("x\ty\theading\t\tobst\tr");
   fo.writeStringln("x\ty\theading\tr");

    for(int i=0;i<estados.size();i++){
      e=(Estado)estados.elementAt(i);
      //e.imprimeDatos();
      r=recompensa(e.p,metas);
      s = ""+e.p.x+"\t"+e.p.y+"\t"+e.heading+"\t"+ (int)r;
     // s = ""+e.p.x+"\t"+e.p.y+"\t"+e.heading+"\t"+ e.obstaculos[1]+"\t"+ (int)r;
      fo.writeStringln(s);
    }
    fo.close();
  }

  // devuelve estados discretos correspondientes a las celdas
  public static int[] getEstadosDiscretos(Vector celdas, int[][] s,
                                          String atContFilename,
                                          String atDisFilename) {

    String[][] atCont = tablas.fileToMatrix(atContFilename, "\t,:");
    String[][] atDis = tablas.fileToMatrix(atDisFilename, "\t,:");
    Vector atributos = new Vector();

    int[] estadoDis = new int[celdas.size()];

    ValorDiscreto vdx = new ValorDiscreto(Integer.parseInt(atCont[0][2]),
                                          Integer.parseInt(atCont[0][3]),
                                          atDis[0].length - 1);
    ValorDiscreto vdy = new ValorDiscreto(Integer.parseInt(atCont[1][2]),
                                          Integer.parseInt(atCont[1][3]),
                                          atDis[1].length - 1);

    for (int i = 0; i < estadoDis.length; i++) {
      Punto p = ( (Celda) (celdas.elementAt(i))).puntoCentral;
      int xd = Integer.parseInt(vdx.getValorDiscreto1(p.x).substring(1));
      int yd = Integer.parseInt(vdy.getValorDiscreto1(p.y).substring(1));

      int[] estado = {
          xd, yd};
      estadoDis[i] = Matrices.indiceSublista(s, estado);
    }

    return estadoDis;
  }


  public static double recompensa(Punto p, Vector metas){

    double r = 0;
    Celda c0;
    for (int i = 0; i < metas.size(); i++) {
      c0 = (Celda) metas.elementAt(i);

     // c0.imprimeDatos();
      if (Punto.miembro(p,c0))
        r = c0.recompensa;
    }
    return r;

  }

  public static void imprimeEstados(Vector estados){
       for(int i=0;i<estados.size();i++)
     ((Estado)estados.elementAt(i)).imprimeDatos();
  }


  public void imprimeDatos(){
    System.out.print(p.x+" "+p.y+" "+heading+" ");
    Listas.imprimeLista(obstaculos);
  }
}