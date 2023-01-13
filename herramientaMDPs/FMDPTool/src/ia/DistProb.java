package ia;


import java.util.Vector;
import java.util.ArrayList;
import java.io.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class DistProb implements Serializable{
  public double[][] probs;
  public int[] estado;
/*
  public DistProb(Vector probs,int[][] estados) {
    this.estados=new int[estados.length][];
    this.probs=new Vector();

  }
*/

public DistProb(int[] estado, double[][] probs) {

  this.estado = new int[estado.length];
  this.probs = new double[probs.length][];

//  System.out.println("checando consistencia");
//  System.out.println("long estado "+estado.length);
//  System.out.println("long probs "+probs.length);

  for (int i = 0; i < estado.length; i++)
    this.estado[i] = estado[i];

  for (int i = 0; i < probs.length; i++)
  this.probs[i] = probs[i];

 }


public boolean estadoValido(){
  boolean resp=false;

  for(int i=0; i<probs.length;i++)
    for(int j=0; j<probs[i].length;j++){

      Double NAN=new Double(Double.NaN);
      Double d=new Double(probs[i][j]);

      if(!d.equals(NAN)&&probs[i][j] !=0)
        resp = true;
    }
    return resp;
}
}