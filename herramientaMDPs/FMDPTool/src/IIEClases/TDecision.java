
package IIEClases;

import java.lang.Integer;
import java.io.*;
import java.util.*;

import elvira.NodeList;
import elvira.potential.Potential;
import elvira.potential.PotentialTable;
import elvira.Network;
import elvira.Bnet;
import elvira.Evidence;
import elvira.IDiagram;
import elvira.Node;
import elvira.NodeList;
import elvira.FiniteStates;
import elvira.Configuration;
import elvira.parser.ParseException;

public class TDecision 
{	

	private Network NET;
	
	private IDiagram ID;
	
	private Bnet    Bnet;
	
	double Values[];
	
	private int TypeofNet;
	
	private Evidence E;
	
	private Vector   VectorResultados;
	public  String   MejorDecision; //  Toma de Decision
	public  double   MaxUtilidad;  //   Maxima Utilidad Esperada
    private NodeList Padres;
    private DecisionHugin ve;



/*
 *
 *	 Constructor de la Clase
 *   F : nombre de la red (bayesiana, decision ) en formato .elv
 *	 Ejemplo : TDecision ("ejemplo.elv");	 
 *
 */
	
	public TDecision(String F) throws ParseException, IOException, Exception 
	{	  	 
	  try {
	  	NET = new Network(); 
	  	NET = NET.read(F);	  		  	
	  }
	  catch (ParseException e) {
	  	System.out.println("Archivo no valido");
	  }
	  catch (IOException e) {
	  	System.out.println("Error de lectura en TD");
	  };
	  DIValido();  	  	  
	} 
			




/*
 *	 M�todo para evaluar el tipo de red;
 *	 Valor devuelto:	
 *	 1 :  Diagrama de Influencia	
 *	 2 :  Red Bayesiana 
 *	 0 :  Otro Red General (objeto Network);	
 */	
	
	public int DIValido()  
	{
	  if (!NET.isEmpty()) 
	  {
	  	   if ( NET.getClass() == IDiagram.class )        
		   {
		   	   ID = (IDiagram) NET;
		   	   TypeofNet=1;
		   	   return 1;	
		   }
		   else if ( NET.getClass() == Bnet.class )        
		   {
		   	   Bnet = (Bnet) NET;
		   	   TypeofNet=2;
		   	   return 2;	
		   }
		   else return 0;
	  }
	  else return 0;     
	}
	
/*
 *  M�todo para imprimir en pantalla (MS-DOS)
 *	los valores  de probabilidad
 *  posteores de la red
 *
 */
 
	public void Imprimir()
	{
		FiniteStates FS;
				
		if ( TypeofNet==2 )
		{
		//  La  Red es Bayesiana	
			int ip,ipt;
			ip=0;						
						
			//  hacer mientras  existe Variables
			//  VectorResultados guarda los Objetos Potencial devueltos de la propagaci�n
			while ( ip <VectorResultados.size() )
 	   		{
 	   			Potential p =( Potential) VectorResultados.elementAt(ip);
 	   			//   metodo para obtener el nombre de la variables
 	   			FS = (FiniteStates)p.getVariables().elementAt(0); 	   			
 	   			//   metodo para obtener los valores dobles { 0.1 ,0.56, .. etc } 
 	   			double[] PastProb = ((PotentialTable) p).getValues();
 	   			
 	   			ipt=0;
 	   			System.out.println(FS.getName()); 	   		 		
 	   			System.out.println("["); 	   		 		
 	   			while (ipt < PastProb.length ) 	   			{ 	   		
 	   		 		
 	   		 		Double d = new Double(PastProb[ipt]);
 	   		 	 	System.out.println("\t" + "[" + FS.getState(ipt) + "] = " + d); 	   		 		
 	   		 	    ipt++; 	   		 		
 	   			}	   			
 	   			ip++;
 	   			System.out.println("]"); 	   		 		 	   		 		
 	   		}	   		
 	   	}	
 	   	else if ( TypeofNet==1 ) {
 	   	//  la red es diagrama de influencia
 	   		UtilidadEsperada();
 	   	}	
 	    else System.out.println ("Red No Implementada");
	}

/*
 *
 *		 M�todo para propagar la red
 *		 Salida =  VectorResultados :  vector de objetos potencial	
 *		 tama�o : de 1 a N elementos	
 *
 */		
public void Propagar() throws Exception 
  { 
   Network bVE; 
   IDiagram id; 
     switch (TypeofNet) { 
     case  1: 
    bVE=ID.copy(); 
    if (ve == null) {ve = new DecisionHugin(( Bnet) bVE,E);} 
         else 
         { 
          ve.PUmodificada = true; 
          ve.CambiarEvidencia(E); 
         } 
      break; 
     case 2: 
      bVE=Bnet; 
      ve = new DecisionHugin(( Bnet) bVE,E); 
      ve.PUmodificada = false; 
      ve.CambiarEvidencia(E); 
      break; 
     default: 
      return; 
     } 
        /*DecisionHugin*/ 
 
      //   Si no hay variables de interes especificada, coloca todas las variables no observadas como interes 
        ve.obtainInterest(); 
 
           ve.Propagacion(); 
           if ( TypeofNet == 1 ) { 
            VectorResultados = new Vector(); 
            VectorResultados.add(ve.PUtilidad); 
           } 
           else 
            VectorResultados = ve.results; 
            Padres = ve.ObtenerPadres(); 
    } 
 
 /*
(version anterior)        
        public void Propagar() throws Exception
	{
		Network bVE;
		IDiagram id;			
		  switch (TypeofNet) {
		  case  1:
			bVE=ID.copy();
			
		  	break;
		  case 2:
		  	bVE=Bnet;
		  	break;
		  default:
		  	return;		  	
		  }
	     //*DecisionHugin 
	      if (ve == null) {ve = new DecisionHugin(( Bnet) bVE,E);}
	      else 
	      {
	      	ve.PUmodificada = true;
	      	ve.CambiarEvidencia(E);
	      }	
     //   Si no hay variables de interes especificada, coloca todas las variables no observadas como interes	      
	      ve.obtainInterest();	           
          
          ve.Propagacion();
          if ( TypeofNet == 1 ) {
          	VectorResultados = new Vector();
          	VectorResultados.add(ve.PUtilidad);	  
          }	
          else	
          	VectorResultados = ve.results;
           Padres = ve.ObtenerPadres();
   }
*/
/*
 *  M�todo para crea el objeto evidencia
 */ 
	private void CrearEvidencia(Vector Variables, Vector Valores)
	{	
	    Configuration Conf = new Configuration(Variables,Valores);	
		E = new Evidence(Conf);
	}
/*
 *
 *	 Metodo para colocar  Evidencia a la Red Evaluada
 *	 Entrada:  Cadena de nodos 
 *	 ejemplo :  	ColocarEvidencia_Consola ( "A,B,C"),ColocarEvidencia_Consola ( "");
 *					ColocarEvidencia_Consola ( "1,4,2");
 *	 Salida : true si no huvo un error para construir la evidencia 
 *			  false   hay error	
 *
 */	
	public boolean ColocarEvidencia_Consola(String CadenaNodosEvidencia) throws IOException
	{
	  
	  Node tNode;
	  FiniteStates FS;	  
	  String NameofNode, No_or_NameofNode, Valor;	  
	  int Data;
	  
	  Vector vecEvidences=new Vector();
	  Vector vecEviValor=new Vector();      
      StringTokenizer Token = new StringTokenizer(CadenaNodosEvidencia,",");
      
	  FS = new FiniteStates();
      
      while ( Token.hasMoreTokens() )
      {             
        
        No_or_NameofNode=Token.nextToken().trim();       
      
        if ( EsNumerico(No_or_NameofNode) )
        {        
            // si los datos introducidos son numericos
            Data = Integer.parseInt(No_or_NameofNode);                	
        	if ( TypeofNet ==1 ) {
        	// si es diagrama de influencia 	
        		tNode = (Node ) ID.getNodeList().elementAt(Data);
        		FS = (FiniteStates) tNode;
        	}	
        	else if (TypeofNet ==2 )
        	// si es red bayesiana
        		FS = (FiniteStates) Bnet.getNodeList().elementAt(Data);        	
        }	
        else         
        { 
            // si los datos de la cadena son del tipo "aaa,bbb,etc"
            if ( TypeofNet ==1 ) {         	
        		tNode = (Node ) ID.getNode(No_or_NameofNode);        
        		FS = (FiniteStates) tNode;
        	}	
        	else if (TypeofNet ==2 )      		
                FS = (FiniteStates) Bnet.getNode(No_or_NameofNode);        
        }                
        // agrega la evidencia
        vecEvidences.add(FS);            
              
 	    // pide la evidencia en consola MS-dos
 	    System.out.print(" Valor de "+FS.getName() + " : ");
 	    No_or_NameofNode = PedirCadena();
 	    
 	    if (EsNumerico(No_or_NameofNode)) 
 	    {
 	    	Data = Integer.parseInt(No_or_NameofNode);
 	    	Integer IntegerVal = new Integer(Data);
 	    	vecEviValor.addElement(IntegerVal); 	    	
 	    }
 	    else
 	    	return false; 	    
        
	  }
	  // crea la evidencia total
 	  CrearEvidencia(vecEvidences, vecEviValor);
 	  return true;           
    }
    
/*
 *   M�todo para saber si un String  se puede
 *   convertir a numerico
 *	 Salida : true � false 
 */
	private boolean EsNumerico(String S) 	
	{
		char car[];
		car = S.toCharArray();
		for (int i=0; i<S.length(); i++) 			
			if (!Character.isDigit(car[i])) 
				return false;		
		return true;
	}


/*	 Metodo para colocar  Evidencia a la Red Evaluada
 *	 Entrada:  Cadena de nodos , cadena de valores de evidencia
 *	 ejemplo :  	ColocarEvidencia( "A,B,C","0,3,1")
 *					ColocarEvidencia ( "","");
 *					ColocarEvidencia( "2,4,1", "0,0,7");
 *	 Salida : true si no huvo un error para construir la evidencia
 *			  false   hay error,	
 */
	public boolean ColocarEvidencia(String CadenaNodosEvidencia, String Valores) throws IOException
	{
	  
	
	  Node tNode;
	  FiniteStates FS;	  
	  String NameofNode, No_or_NameofNode, Valore;	  
	  int Data;
	  
	  Vector vecEvidences=new Vector();
	  Vector vecEviValor=new Vector();      
      StringTokenizer Token = new StringTokenizer(CadenaNodosEvidencia,",");
      StringTokenizer TokenValor = new StringTokenizer(Valores,",");
      
      FS = new FiniteStates();

     /* for(int i=0;i<FS.getStates().size();i++)
          System.out.println(FS.getStates().get(i));*/
      // realizar mientras exista token de evidencia
      while ( Token.hasMoreTokens() )
      {             
        
        No_or_NameofNode=Token.nextToken().trim();
        Valore=TokenValor.nextToken().trim();
        
        // evidencia con nombre de nodos
        if ( EsNumerico(No_or_NameofNode) )
        {  
            Data = Integer.parseInt(No_or_NameofNode);                	
        	// Diagrama de influencia
        	if ( TypeofNet ==1 ) {
        		tNode = (Node ) ID.getNodeList().elementAt(Data);
        		FS = (FiniteStates) tNode;
        	}	
        	//  Red Bayesiana
        	else if (TypeofNet ==2 )
        		FS = (FiniteStates) Bnet.getNodeList().elementAt(Data);        	
        }	
        //  evidencia con numero de nodos
        else         
        { 
            // Diagrama de influencia
            if ( TypeofNet ==1 ) { 
        		tNode = (Node ) ID.getNode(No_or_NameofNode);        
        		FS = (FiniteStates) tNode;
        	}	
        	// Red Bayesiana
        	else if (TypeofNet ==2 )   {
        		try{
                FS = (FiniteStates) Bnet.getNode(No_or_NameofNode); 
        		}catch(Exception e){System.out.println(e.getLocalizedMessage());}
        	}       
        }                
        
        //  Evaluar la cadena de valores
        //  si no es un formato n�mero, retorna false y sale de la funcion
        if (EsNumerico(Valore)) 
 	    {
 	    	Data = Integer.parseInt(Valore);
 	    	Integer IntegerVal = new Integer(Data);
 	    	// coloca la evidencia en el vector de valores
 	    	vecEviValor.addElement(IntegerVal); 	    	
 	    }
 	    else return false; 	    
        // coloca la evidencia en el vector de variables
        vecEvidences.add(FS);        
	  }
	  
	  // crea el objeto evidence
	  CrearEvidencia(vecEvidences, vecEviValor);          
      return true;      
	}  

	private String PedirCadena() throws IOException
	{
	
 	   InputStreamReader inStream = new InputStreamReader( System.in ) ;
       BufferedReader    stdin    = new BufferedReader( inStream );
 	   String inData = stdin.readLine();
       return inData;  
	}
	
	
/*
 *		M�todo para Obtener los Valores de propagacion de la red
 *
 *		SALIDA: Un Vector de objetos Resultados 
 *		Resultado ( Es una clase con  dos variables
 *			public String NombreNodo;  // nombre del nodo
			public double Valores[]	   // valores de probabilidad posteori;	
 *		
 */

	public Vector ObtenerValores()
	{
		Vector V = new Vector();
		String NombreActual;
		Iterator IT;
		double PastProb[];
		Resultado R=null;	
			
		if ( TypeofNet==2 )
		{
			//  La  Red es Bayesiana
			//  hacer mientras  existe Variables
			 for (int i = 0; i<VectorResultados.size(); i++)
 	   		 {
 	   			R = new Resultado();
 	   			Potential p =( Potential) VectorResultados.elementAt(i);
 	   			FiniteStates FS = (FiniteStates)p.getVariables().elementAt(0);
 	   			PastProb = ((PotentialTable) p).getValues();
 	   			R.NombreNodo = FS.getName();
 	   			R.Valores = PastProb;
 	   			V.add(R);
 	   		 }
        }
        else return ObtenerUtilidad();
 	   	return V;
	}
	
/*
 *
 *   M�todo para obtener las decisiones y utilidades 
 *	 Devuelve un vector de objetos Resultados
 *            String , double[] 	
 *	 Ejemplo : nada,{15} , ejecutar,{26} ,etc
 *
 */
 
	 public Vector ObtenerUtilidad() 	
	 {
	 	Vector  V = new Vector();	 	
	 	Resultado R = null;
	 	double P[];	 	
		
		Potential Pt = (Potential )VectorResultados.firstElement();		
		Vector variables =  Pt.getVariables();
		long  tam = Pt.getSize();
		
		Configuration conf = new Configuration(variables);		
		
		for (int i=0 ; i< tam; i++) 
		{	
				String name = new String((String) 
		      		((FiniteStates)variables.elementAt(0)).getState(i));
		      R = new Resultado();
		      R.NombreNodo = name;
		      P = new double[1];
		      P[0] = Pt.getValue(conf);
		      R.Valores = P;
		      V.add(R);
		      conf.nextConfiguration();		    	
		}
	 	return V;	 
	 }

/*
 *
 *    M�todo para obtener la utilidad Mayor;
 *	  Salida : double ( Utilidad Mayor )
 *		
 *
 */	
	
	
	public double ObtenerMayorUtilidad() 
	{
		double Max=0.0,RMax;
		Potential Pt = (Potential )VectorResultados.firstElement();		
		Vector variables =  Pt.getVariables();
		long  tam = Pt.getSize();
		Configuration conf = new Configuration(variables);		
		
		for (int i=0 ; i< tam; i++) 
		{	//conf.
			RMax= Pt.getValue(conf);
			if (Max < RMax) 
			{
				Max = RMax;
				String name = new String((String) 
		      		((FiniteStates)variables.elementAt(0)).getState(i));
				this.MejorDecision= name;
				this.MaxUtilidad=Max;	
			}		
			conf.nextConfiguration();			
		}
		return Max;					
	}

 
	/*
	 *	  Metodo  utilizado para imprimir los valores de decision de un diagrama de influencia
	 *	  es llamado por Imprimir
	 *
	 *
	 */
	private void UtilidadEsperada() 
	{
		Potential Pt = (Potential )VectorResultados.firstElement();		
		Vector variables =  Pt.getVariables();
		long  tam = Pt.getSize();
		Configuration conf = new Configuration(variables);		
		
		for (int i=0 ; i< tam; i++) 
		{
			conf.pPrint();
			System.out.println (" = " + Pt.getValue(conf));			
			conf.nextConfiguration();			
		}	
	}

/*
 *   M�todo para checar si la propagacion tuvo exito,
 *	 Unicamente en Red Bayesiana
 */	
	public boolean Estado_de_Propagacion( )
    {
 	   	FiniteStates fs;
 	   	boolean OK= true; 	   	
 	    	   	
 	   	for (int I=0;I<Bnet.getNodeList().size();I++)
 	   	{
 	   		int ip=0;
 	   		boolean found = false;
 	   		fs = (FiniteStates) Bnet.getNodeList().elementAt(I);
 	   		double[] PastProb = new double [fs.getNumStates()];
 	   		while (!found && ip<VectorResultados.size())
 	   		{
 	   			Potential p =( Potential) VectorResultados.elementAt(ip); 	   			
 	   			if (fs.equals(((FiniteStates)p.getVariables().elementAt(0))))
 	   			{
 	   				found= true;
 	   				PastProb = ((PotentialTable) p).getValues(); 	   				
 	   			}
 	   			else ip++;
 	   		}	   		
 	   		   		
 	   		ip=0;
 	   		Double NAN = new Double(Double.NaN);
 	   		while ( OK && ip < PastProb.length )
 	   		{ 	   		
 	   		 	Double d = new Double(PastProb[ip]);
 	   		 	if (d.equals(NAN)) 
 	   		 	{
 	   		 		OK=false;
 	   		 		return OK; 	   		 		
 	   		 	}
 	   		 	else ip++; 	   		 		
 	   		}
 	   	}
  		return OK;
	}   
	
	public Object Obtener_Red()
	{
		if (TypeofNet == 1)
			return this.ID;
		else if  (TypeofNet == 2)
			return this.Bnet;
		else return null;	
	}
    public NodeList ObtenerPadres()
    {
        return Padres;
    }
    public Vector NodosPosteori()
    {
        /*Vector  V   = new Vector();
	 	Resultado R = null;
	 	double P[];

		//Vector variables =  Pt.getVariables();
		long  tam = ve.ObtenerNodosPosteori().size(); //Pt.getSize();

		for (int i=0 ; i< tam; i++)
		{
			  Potential Pt = (Potential ) ve.ObtenerNodosPosteori().elementAt(i);
              String name = ((FiniteStates) Pt.getVariables().firstElement()).getName();
		      R = new Resultado();
		      R.NombreNodo = name;
		      P = new double[((PotentialTable) Pt).getValues().length];
		      P = ((PotentialTable) Pt).getValues();
		      R.Valores = P;
		      V.add(R);
		}
	 	return V;*/
        return   ve.ObtenerNodosPosteori();
    }

    public Vector NodosEvidencia()
    {
        Vector  NodoEvi=new Vector();
        Vector  Var   = new Vector();
        Vector  Val   = new Vector();
	 	Resultado R = null;
	 	double P[];
        String Estados[];
	
		long  tam = E.getVariables().size();
        Var= E.getVariables();
        Val = E.getValues();
		for (int i=0 ; i< tam; i++)
		{
			  FiniteStates FS = ( FiniteStates ) Var.elementAt(i);
		      R = new Resultado();
		      R.NombreNodo = FS.getName();
		      P = new double[FS.getNumStates()];
              Estados = new String[FS.getNumStates()];
              for (int j=0; j<FS.getNumStates(); j++)
              {    Estados[j] = FS.getState(j);
                   P[j] = 0.0;
              }
              int Seleccionado = ((Integer) Val.elementAt(i)).intValue();
              P[Seleccionado] = 1.0;
		      R.Valores = P;
              R.Estados = Estados;
		      NodoEvi.add(R);
		}
	 	return  NodoEvi;
    }
    
    
       

    
    public Potential ObtenerValoresdeUtilidad()
    {
         return ve.ObtenerNodoUtilidad();
    }
    
    public void ColocarValoresdeUtilidad(Potential P)
    {
         //ve.ModificarUtil(P);
         ve.ColocarNodoUtilidad(P);
    }

}

// fin clase


