package elvira09;

public class PTabla 
{
	public double[] PuntoMedio(double min, double max, int partes)
    {
    	double[] Arep=new double[partes];
	    double incremento;
	    double acumulador;
	    
	    incremento = (max-min)/(double)partes;
	    	    	        
	    for(int i=0;i<partes;i++)
	    {
	    	acumulador = min ;
	    	max = acumulador + incremento;
	    	
	    	double div = (acumulador + max) /2;
	    	
	    	Arep[i]=div;
	    	
	    	min = max;
	   }
	    
	    
	   return(Arep);
    	
    }
}
