
package IIEClases;

import java.util.Vector;

//import IIEClases.*;
import java.util.*;

public class MyVector
{
    public static Vector V;
    
    public static void main(String arg[])
    {
    
		construir();
	
	}
	
	
	static public void construir()
	{
		
		
		double  a[] = new double[10];
		String  nombres = new String ("ABCDEFGHI");
		Vector V = new Vector();
		String NombreActual;
		Iterator IT;
		for (int i = 0; i < a.length; i++)
		{
			a[i]= i*i;
		}
		Resultado R=null;	
		Resultado[] RE= new Resultado[nombres.length()];
		for (int i = 0; i<nombres.length(); i++)		
		{	
					
			R = new Resultado();
			NombreActual=String.valueOf(nombres.charAt(i));
			R.NombreNodo=NombreActual;
			
			
			RE[i]= R;
			V.add(R);
			//R.NombreNodo = NombreActual
			//R.Valores = a;
			System.out.println ("Valor Agregado :" + R.NombreNodo);
			
			
			//V.add(aux);
			//IT = V.iterator();
			
		}	
		 IT = V.iterator();
		 while (IT.hasNext() )
		 {
		 	System.out.println (((Resultado) IT.next()).NombreNodo);
		 }
		
		for (int i = 0; i<RE.length; i++)
		System.out.println (RE[i].NombreNodo);
		/*
	    for (int i = 0; i<RE.length; i++)
		{
			 //System.out.println (((Resultado) V.elementAt(i)).NombreNodo);
			 //System.out.println (((Resultado) Lista.e.elementAt(i)).NombreNodo);
			 System.out.println (RE[i].NombreNodo);
			 
		}*/		
	
	}
	
	public void imprimir ()
	{
	
	}    	
    
}