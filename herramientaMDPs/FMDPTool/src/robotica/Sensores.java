package robotica;

import Javaclient.src.*;
import java.util.Vector;
import utileria.*;

/**
 * <p>Title: Sensores</p>
 * <p>Description: Programa para detectar objetos al N, S, E y O</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Tec de Monterrey Cuernavaca</p>
 * @author Alberto Reyes B.
 * @version 1.0
 */

public class Sensores{

  // devuelve promedios de lecturas a la derecha e izquierda
  public static int[] percep01(int[] reading, int umbral){

    int r = getPromedioBajoUmbral(10, 170, reading, umbral);
    int l = getPromedioBajoUmbral(191, 351, reading, umbral);

    int[] p={r,l};
    return p;
  }

  // devuelve promedios de lecturas a la derecha, centro e izquierda
  public static int[] percep02(int[] reading, int umbral){

    int r = getPromedioBajoUmbral(0, 134, reading, umbral);
    int c = getPromedioBajoUmbral(134, 267, reading, umbral);
    int l = getPromedioBajoUmbral(267, 401, reading, umbral);

    int[] p={r,c,l};
    return p;
  }


  // devuelve promedios de lecturas por partes
  // aun no lista
  public static int[] percep03(int[] reading, int umbral, int partes){
    int[] p=new int[partes];
    int incremento=(int)Math.round((double)reading.length/(double)partes);
    int inf,sup;

    if(reading.length%partes>0) partes=partes-1;
    for(int i=0;i<partes;i++){
      inf=i*incremento;
      sup=inf+incremento;
      if(sup>=reading.length) sup=reading.length-1;
      System.out.println("min "+ inf+"  max "+sup);
      p[i]=getPromedioBajoUmbral(inf,sup, reading, umbral);
    }
    return p;
  }
  // promedia las lecturas q estan bajo el umbral
  private static int getPromedioBajoUmbral(int inf, int sup, int[] reading,
                                           int umbral) {

    int promedio = Integer.MAX_VALUE;
    int tempCount = 0;
    for (int i = inf; i < sup; i++)
      if (reading[i] <= umbral) {
        promedio += reading[i];
        tempCount++;
      }
    if (tempCount != 0)
      promedio = promedio / tempCount;

    return promedio;
  }

  // discretiza valores promedio de lecturas de laser

  public static boolean[] obstDiscreto(int[] lectura, int min){
    boolean[] obstaculo=new boolean[lectura.length];

    for(int i=0; i<lectura.length; i++)
      if(lectura[i]<=min) obstaculo[i]=true;
        else obstaculo[i]=false;
    return obstaculo;
  }


// Devuelve vector con los promedios de lecturas al N, E, S y W
// El robot hace giros para cubrir todo su espacio circundante

  public static int[] sensado(PlayerClient robot, PositionPlayerDevice ppd, LaserPlayerDevice lpd){

    // variable q almacena los promedios de lecturas al N, E, S y W
  int lectura[] = new int[4];
  Vector v=new Vector();

  // establece 4 puntos de muestreo

  for (int pos = 0; pos < 4; pos++) {

    robot.readAll(); // lectura de sensores

    // solo lecturas frontales
    for(int i=100; i<200; i++)
      v.addElement(Integer.toString(lpd.getRange()[i]));

    // guarda promedio
    lectura[pos] = Listas.promedio(Listas.stringVectorToIntArray(v));
    // gira el robot 90 grados
    ppd.setSpeed(0, -90);

    try {
      Thread.sleep(1000);
    }
    catch (InterruptedException e) {
      System.out.println("Thread ppal interrumpido");
    }
    ppd.setSpeed(0,0);
  }
    return lectura;
  }

public static int sensorCentral(int noSensores,int heading){

  heading+=90;
  if(heading>=360)
    heading-=360;
  return noSensores*heading/360;
}

// indices de sensores para muestreo: radioSensado=no sensores de muestreo/2
// minId= indice del primer sensor maxId= indice del ultimo sensor
public static Vector indSensores(int sensorCentral, int radioSensado, int minId,
                         int maxId) {

  Vector v = new Vector();
  v.addElement(new Integer(sensorCentral));

  int nuevoSensorIncr=sensorCentral;
  int nuevoSensorDecr=sensorCentral;
  for(int i=0;i<radioSensado;i++){
    nuevoSensorIncr++;
    nuevoSensorDecr--;
    nuevoSensorIncr=checaRango(nuevoSensorIncr,minId,maxId);
    nuevoSensorDecr=checaRango(nuevoSensorDecr,minId,maxId);
    v.addElement(new Integer(nuevoSensorIncr));
    v.insertElementAt(new Integer(nuevoSensorDecr),0);
  }

  return v;
}
// metodo checa rango
public static int checaRango(int nuevoSensor,int minId,int maxId){

  if(nuevoSensor>maxId)
    nuevoSensor=nuevoSensor-maxId-1;
    else if(nuevoSensor<minId)
      nuevoSensor=maxId+nuevoSensor+1;
    return nuevoSensor;
}

  public static boolean obstaculo(Vector sensores, int[] lecturas, int umbral){
  int suma=0;

  int indice;
  for(int i=0; i<sensores.size(); i++){
    indice=((Integer)sensores.elementAt(i)).intValue();
    suma += lecturas[indice];
  }
  if(suma/sensores.size()<umbral)
    return true;
  else return false;

}

// devuelve lista de sensores centrales para 8 puntos cardinales [N,NW,W,SW,S,SE,E,NE]
public static int[] sensoresCardinales(int heading, int noSensores, int minId,
                                       int maxId) {
  int noPartes=8;
  int[] sensor = new int[noPartes];
  double variacion= (double)noSensores/360.0 * (double)heading;

  int norte = noSensores/4 - (int) variacion;
  sensor[0] = checaRango(norte, minId, maxId);
  int aux;
  for(int i=1; i<noPartes; i++)
    sensor[i] = checaRango(sensor[0] + i*noSensores / noPartes, minId, maxId);

  return sensor;
}

public static void main(String[] args) {

  int lecturas[]={22,23,24,19,20,19,40,40,40};
  Sensores.percep03(lecturas,20,5);
  int lecturas1[]=new int[401];
  Sensores.percep02(lecturas1,20);
}


}
