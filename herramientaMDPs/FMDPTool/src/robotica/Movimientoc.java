package robotica;

/**
 * @(#)Rotacion.java
 *
 * Sample Applet application
 *
 * @author
 * @version 1.00 04/11/22
 */

import javax.swing.JFrame;

public class Movimientoc{

   public int x=0, y=0, ang=0;
   JFrame ventana;
   int timeDelay;
   int pasoAngular=45;

   public Movimientoc(JFrame gf, int td){
     ventana   = gf;
     timeDelay = td;
   }

   public Movimientoc(){}

   public void actualizaCoordenadas(int x, int y, int ang){
     this.x=x; this.y=y; this.ang=ang;
   }

   public void rotarDer(int paso){
     ang=checaLimites(ang-paso);
   }

   public void rotarIzq(int paso){
     ang=checaLimites(ang+paso);
   }

   public void avanzar(int paso){
     x=x+(int)(((double)paso)*Math.cos(Math.toRadians(ang)));
     y=y+(int)(((double)paso)*Math.sin(Math.toRadians(ang)));
   }

   public void accion(int id, int paso) {
     if (id == 0)
       derecha(paso);
     if (id == 1)
       izquierda(paso);
     if (id == 2)
       arriba(paso);
     if (id == 3)
       abajo(paso);
   }

   public void derecha( int paso) {

     if (ang < 180)
       while (! (ang == 0) ) {
         rotarDer(pasoAngular);
         ventana.repaint();
         timeDelay(timeDelay);
       }
     else
       while (! (ang == 0) ) {
         rotarIzq(pasoAngular);
         ventana.repaint();
         timeDelay(timeDelay);
       }
     avanzar(paso);
   }

   public void izquierda( int paso) {

     if (ang < 180)
       while (! (ang == 180) ) {
         rotarIzq(pasoAngular);
         ventana.repaint();
         timeDelay(timeDelay);
       }
     else
       while (! (ang == 180) ) {
         rotarDer(pasoAngular);
         ventana.repaint();
         timeDelay(timeDelay);
       }

       avanzar(paso);
   }

   public void arriba( int paso) {

     if ( ang > 90 && ang < 270 )
       while (! (ang == 90) ) {
         rotarDer(pasoAngular);
         ventana.repaint();
         timeDelay(timeDelay);
       }
     else
       while (! (ang == 90) ) {
         rotarIzq(pasoAngular);
         ventana.repaint();
         timeDelay(timeDelay);
       }
       avanzar(paso);
   }

   public void abajo( int paso) {

     if ( ang > 90 && ang < 270 )
       while (! (ang == 270) ) {
         rotarIzq(pasoAngular);
         ventana.repaint();
         timeDelay(timeDelay);
       }
     else
       while (! (ang == 270) ) {
         rotarDer(pasoAngular);
         ventana.repaint();
         timeDelay(timeDelay);
       }
       avanzar(paso);
   }

   public void timeDelay(int velGiro) {
     try {
       Thread.sleep(velGiro);
     }
     catch (Exception ex) {}
   }

   int checaLimites(int angulo) {
     if (angulo >= 360)
       return angulo - 360;
     else if (angulo < 0)
       return angulo + 360;
     else
       return angulo;
   }
}