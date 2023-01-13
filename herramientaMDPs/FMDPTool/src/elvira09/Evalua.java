
package elvira09;

/**
 * Title:        Monitor de Ejecuci�n de un Planificador Inteligente.
 * Description:  Definici�n de la clase evalua. Utiliza el criterio de la
 *               desviaci�n estandar o el criterio de p-value
 * Junio 2002.
 * Copyright:    Copyright (c) 2000
 * Company:      IIE
 * @author Pablo Ibarg�engoytia Gonz�lez
 * @version 1.0
 */

import java.lang.Math.*;

public class Evalua
{ 
	static final double PValue = 0.2; // valor P
	static final double DIST = 2.0;   // distancia de la media en desviaciones
	private boolean metodo;   // metodo true para PValue; metodo false para desv. std.

	public Evalua(boolean m)
	{ 
		metodo = m;
	}
  
	public boolean decide(double probPost[], double[] valores, int valorReal, String variable)
	{
		int i, j;
		double  ph=0.0, errd, errm, mean=0.0, dvstd, varianza=0.0, vr;
    
		if(metodo)
		{
			if(probPost[valorReal] < PValue)
			{
				System.out.println("falla Pvalue");
				return true;
			}
     
			else return false;
		}
		
		// Metodo de la desviacion estandar
		for( i=0; i<probPost.length; i++)
		{
			mean = mean + (valores[i] * probPost[i]);  // calculo de la media
			
			if (probPost[i] > ph)
			{ 
				ph = probPost[i];     // calcula la probabilidad mayor
				j = i;
			}
		}

		// prob_real = ph;  
    
		for (i=0; i<probPost.length; i++)
			varianza = varianza + (Math.pow((valores[i] - mean),2.0) * probPost[i]);

		dvstd = Math.sqrt(varianza);
		// errd = fabs((value[j] - vreal)/vreal);  //error de diferencia
		// errm = fabs((mean - vreal)/vreal);  //error de media

		//if(ph >= 0.80) dvstd = ssize/2;
    
		if((valores[valorReal] < (mean - (DIST*dvstd))) || (valores[valorReal] > (mean + (DIST*dvstd))))
		{
			//System.out.println("falla en la variable: "+variable+" " + mean + " " + dvstd + " " + valorReal);
			return true;
		}
    
    
		else 
			return false;
  }

}