package robotica;

import java.awt.Dimension;
import java.util.Vector;
import utileria.FileOutput;
import java.io.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Celda implements Serializable{

  public Punto puntoCentral;

  public byte estado;  // libre=0 ocupada=1 indefinida=2 meta=3 inicio=4;
  public int size;
  public double costo;
  public double valor;
  public double recompensa;
  public Dimension dim;

  public Celda(Punto puntoCentral, byte estado, int size, double costo) {
    this.puntoCentral=puntoCentral;
    this.estado=estado;
    this.size=size;
    this.costo=costo;
  }

  public Celda(Punto puntoCentral, Dimension size, double recompensa) {
    this.puntoCentral=puntoCentral;
    this.estado=2;
    this.dim=size;
    this.recompensa=recompensa;
  }


  public Celda(Punto puntoCentral, byte estado, int size) {
    this.puntoCentral=puntoCentral;
    this.estado=estado;
    this.size=size;
  }

  public static boolean esIgual(Celda c1, Celda c2) {

    if(!(c1==null||c2==null)){
    if ( (c1.puntoCentral.x == c2.puntoCentral.x) &&
        (c1.puntoCentral.y == c2.puntoCentral.y))
      return true;
    else
      return false;
    }
    else if(c1==null && c2==null) return true;
    else return false;
  }

  public void setCost(double costo) {
    this.costo = costo;
  }

  public void setReward(double recompensa) {
    this.recompensa = recompensa;
  }


  private static Vector aplanaVectorCeldas(Vector paquete){
    Vector v=new Vector();
    Vector v1=new Vector();
    Celda c;
    for(int grupo=0; grupo<paquete.size();grupo++){
      v1=(Vector)paquete.elementAt(grupo);
      for(int celdas=0;celdas<v1.size();celdas++){
        c=(Celda)v1.elementAt(celdas);
        v.addElement(c);
      }
    }
    return v;
  }

  public static Vector construyeTodasCeldas(Vector estados, Vector celdas,
                                            int tamanoCelda, int radio) {

    Punto p;
    Celda c;
    Estado masCercano;
    Vector enRango;
    Vector listaDeCeldas = new Vector();
    Vector celdasVecinas;

    Celda.imprimeCeldas(celdas);
    Estado.imprimeEstados(estados);

    for (int i = 0; i < celdas.size(); i++) {
      c = (Celda) celdas.elementAt(i);
      p = c.puntoCentral;
      p.imprimeCoords();
      enRango=Estado.estadosRango(p, radio/2, estados);
      if (!enRango.isEmpty()) {
      masCercano = Estado.masCercano(p, enRango);

    //  masCercano.imprimeDatos();
        masCercano.p = p;
        celdasVecinas=construyeCeldasVecinas(masCercano, tamanoCelda);
     //   Celda.imprimeCeldas(celdasVecinas);
        listaDeCeldas.addElement(celdasVecinas);
      }
   //   else System.out.println("fuera de rango");
    }
    return aplanaVectorCeldas(listaDeCeldas);
  }

  public static void setGoals(Vector map, double[] reward, Vector goals) {

  //  Vector goals = new Vector();
    Celda c;
    int cellNo;
    int i = 0;

    while (i < reward.length) {

      cellNo = (int) (Math.random() * (map.size() - 1));
      c = (Celda) map.elementAt(cellNo);

      if (!miembro(c, goals)) {
        c.setReward(reward[i]);
        goals.addElement(c);
        i++;
      }
    }
   // return goals;
  }



  public static Vector construyeCeldasVecinas(Estado estado, int tamano) {

    int heading = estado.heading;
    if (heading < 0)
      heading += 360;
    int[] code = new int[2];

    if ( (estado.heading >= 0) && (estado.heading < 90)) {
      code[0] = 0;
      code[1] = 1;
    }
    if ( (estado.heading >= 90) && (estado.heading < 180)) {
      code[0] = 2;
      code[1] = 3;
    }
    if ( (estado.heading >= 180) && (estado.heading < 270)) {
      code[0] = 4;
      code[1] = 5;
    }
    if ( (estado.heading >= 270) && (estado.heading < 360)) {
      code[0] = 6;
      code[1] = 7;
    }

    Vector c = generaCeldas(estado, code, tamano);
    return c;
  }


  private static byte boolean2byte(boolean obstaculo){
    if(obstaculo) return 1;
    else return 0;
  }

  private static Vector generaCeldas(Estado e, int[] code, int tamano) {
    Punto p = e.p;
    int x = p.x;
    int y = p.y;
    Celda[] celda = new Celda[code.length + 1];
    Vector v = new Vector();
    for (int i = 0; i < code.length; i++) {
      switch (code[i]) {
        case 0: // construye celda al e
          celda[i] = new Celda(new Punto(x + tamano, y),
                               boolean2byte(e.obstaculos[0]), tamano);
          break;
        case 1: // construye celda al ne
          celda[i] = new Celda(new Punto(x + tamano, y + tamano),
                               boolean2byte(e.obstaculos[1]), tamano);
          break;
        case 2: // construye celda al n
          celda[i] = new Celda(new Punto(x, y + tamano),
                               boolean2byte(e.obstaculos[0]), tamano);
          break;
        case 3: // construye celda al nw
          celda[i] = new Celda(new Punto(x - tamano, y + tamano),
                               boolean2byte(e.obstaculos[1]), tamano);
          break;
        case 4: // construye celda al w
          celda[i] = new Celda(new Punto(x - tamano, y),
                               boolean2byte(e.obstaculos[0]), tamano);
          break;
        case 5: // construye celda al sw
          celda[i] = new Celda(new Punto(x - tamano, y - tamano),
                               boolean2byte(e.obstaculos[1]), tamano);
          break;
        case 6: // construye celda al s
          celda[i] = new Celda(new Punto(x, y - tamano),
                               boolean2byte(e.obstaculos[0]), tamano);
          break;
        case 7: // construye celda al se
          celda[i] = new Celda(new Punto(x + tamano, y - tamano),
                               boolean2byte(e.obstaculos[1]), tamano);
          break;
      }
      v.addElement(celda[i]);
    }
    v.insertElementAt(new Celda(new Punto(x, y),
                           (byte) 0, tamano),0);
    return v;
  }
/*
  private static void construyeCeldasVecinas(Punto p, int c, Vector celdas,
                                             Vector estados) {
    Estado e = Estado.con(p, estados);
    int x, y;
    Vector coords = Punto.generaVecinos(p, c);
    if (e != null) {
      for (int i = 0; i < e.obstaculos.length; i++) {
        // p debe corresponder cambiarlo
        x = ( (Punto) (coords.elementAt(i))).x;
        y = ( (Punto) (coords.elementAt(i))).y;
        celdas.addElement(new Celda(new Punto(x, y), e.obstaculos[i], c));
      }
      celdas.addElement(new Celda(p, false, c));
    }
  }
*/
/*
  public static void generaCeldas(int c, Vector ptosInteres, int radio,
                                   Vector celdas, Vector estados) {
    Punto ptoMasCercano;
    Punto pi;
    Vector pr;
    for (int i = 0; i < ptosInteres.size(); i++) {
      pi = (Punto) ptosInteres.elementAt(i);
      pr = Punto.puntosRango(pi, radio, estados);
      ptoMasCercano = Punto.masCercano(pi, pr);
      Celda.construyeCeldasVecinas(ptoMasCercano, c, estados, celdas);
    }
  }
*/


  public static Vector generaCeldas(int c, int ancho, int alto) {
    Vector v=new Vector();
    Celda celda;
    for(int y=0; y<alto;y+=c)
      for(int x=0;x<ancho;x+=c){
       celda=new Celda(new Punto(x+c/2, y+c/2),(byte)2,c);
        v.addElement(celda);
      }
    return v;
    }

    public static double valorMinimo(Vector celdas){
      double min=Double.MAX_VALUE;
      Celda c;
      for(int i=0;i<celdas.size();i++){
        c=(Celda)celdas.elementAt(i);
        if(c.valor<=min)
          min=c.valor;
      }
        return min;
    }

    public static double valorMaximo(Vector celdas){
      double max=Double.MIN_VALUE;
      Celda c;
      for(int i=0;i<celdas.size();i++){
        c=(Celda)celdas.elementAt(i);
        if(c.valor>=max)
          max=c.valor;
      }
        return max;
    }

    // funcion que genera aleatoriamentes
    public static Vector generaCeldas01(int c, int ancho, int alto) {
      Vector v=new Vector();
      Celda celda=null;

      for(int y=0; y<alto;y+=c)
        for(int x=0;x<ancho;x+=c){
          if((int)Math.round(Math.random())>0)
          celda=new Celda(new Punto(x, y),(byte)0,c,0);
          else
          if(y%3==0)
          celda=new Celda(new Punto(x, y),(byte)1,c,Double.MAX_VALUE);
          else celda=new Celda(new Punto(x, y),(byte)0,c,0);
          v.addElement(celda);
        }
      return v;
      }

      // checa si un punto es miembro de un conjunto de puntos
      public static boolean miembro(Celda c, Vector celdas) {
        boolean ocurre = false;
        Celda c0;
        for (int i = 0; i < celdas.size(); i++) {
          c0 = (Celda) celdas.elementAt(i);
          if (c.puntoCentral.x == c0.puntoCentral.x &&
              c.puntoCentral.y == c0.puntoCentral.y)
            ocurre = true;
        }
        return ocurre;
      }

      // checa si un punto es miembro de un conjunto de puntos
  //    public static boolean miembro(Punto p, Vector celdas) {

    //  }


    public static void registraCeldas(Vector celdas){
      FileOutput fo=new FileOutput("celdas.txt");
      Celda c;
      String s;

      fo.writeStringln("x\ty\testado\tsize");

      for(int i=0;i<celdas.size();i++){
        c=(Celda)celdas.elementAt(i);
        s = ""+c.puntoCentral.x+"\t"+c.puntoCentral.y+"\t"+c.estado+"\t"+c.size;
        fo.writeStringln(s);
      }
      fo.close();
    }

    public static void imprimeCeldas(Vector celdas){
         for(int i=0;i<celdas.size();i++)
       ((Celda)celdas.elementAt(i)).imprimeDatos();
    }

  public void imprimeDatos(){
    System.out.println(puntoCentral.x+" "+puntoCentral.y+" "+estado+" "+size+" "+costo+" "+recompensa+" "+valor);
  }



}