package ia;

import utileria.ServerTCP;
import utileria.Matrices;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ServidorPolitica extends ServerTCP{

  FMDP fmdp;

  public ServidorPolitica(int puerto, String policyFilename) {

    super(puerto);
    this.fmdp=FMDP.retrieveMDP(policyFilename);
  }

  public void enviaPolitica(){
    int[] estado=recibeArrayInt(fmdp.s[0].length);
    System.out.print("recibiendo: ");
    Matrices.imprimeLista(estado);
    int idEstado=Matrices.miembro(estado,fmdp.s);
    send(fmdp.politica[idEstado]);
  }

  public static void main(String[] args) {

    // args
    int puerto=5000;
    String policyFilename="../ejemplos/powerPlant/fmdp.obj";
    //String policyFilename="../ejemplos/russell/fmdp.obj";
    //String policyFilename="../ejemplos/robot/fmdp.obj";

    System.out.println("Bienvenidos al servidor de politica del Sistema SPI");
    System.out.println("Sistema de Planificacion con Incertidumbre");
    System.out.println("Version 0.0 \n");

    ServidorPolitica servidor=new ServidorPolitica(puerto,policyFilename);

    while(true)
      servidor.enviaPolitica();

  }

}