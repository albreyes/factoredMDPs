
package IIEClases;

import elvira.NodeList;
import elvira.potential.Potential;
import elvira.potential.PotentialTable;
import elvira.inference.elimination.VariableElimination;
import elvira.Network;
import elvira.Bnet;
import elvira.Evidence;
import elvira.IDiagram;
import elvira.Node;
import elvira.NodeList;
import elvira.Graph;
import elvira.Relation;
import elvira.FiniteStates;

import elvira.inference.clustering.HuginPropagation;
import elvira.InvalidEditException;
import java.lang.Exception;
import java.util.*;


public class DecisionHugin extends VariableElimination 
{
    Vector Decisiones;
    private  NodeList NodosPadresU;
    private  NodeList Padres;
    public   Potential PUtilidad;
    private  Vector VecR;
    private  Potential PU;
    public   boolean PUmodificada = false;

	public DecisionHugin(Bnet b, Evidence e) {

  		super(b,e);
  		PUtilidad=null;
  		NodosPadresU=null;
	}

	public void CambiarEvidencia(Evidence e) {
		observations = e;
	}	
	public	boolean Propagacion() throws InvalidEditException,Exception
	{
	  if( network.getClass()== Bnet.class) 
	  {
	    getPosteriorDistributions();
	  }
	  else if(network.getClass()==IDiagram.class)
	  {	   
	     PUtilidad = ObtenerUtilidadEsperada();
	  }
	  else{
	    System.out.print("Error in DecisionHugin.propagate(): ");
	    System.out.println("No esta implemetada para esta red :"+network.getClass());
	    return false;
	  }
	
	  if(network.getClass()==Bnet.class)
	  {
	    normalizeResults();	    
	  }
	  return true;
	}
	
	
	private Bnet ConstruirRedAislamiento() throws InvalidEditException,Exception
	{
		
		int i,  s;
		Enumeration e;
		Vector vars;
		NodeList varsInOriginal,LRemoved;
		Potential ptOriginal;
		Node node;
		Graph g = network.duplicate();
		LRemoved = new NodeList();
		varsInOriginal = new NodeList();  
		s = g.getNodeList().size();

		
		/*
		 *
		 * crea una red bayesiana a partir del diagrama de influencia 
		 *
		 *
		 */			
		for (i=0 ; i<s ; i++) 
		{
			node = network.getNodeList().elementAt(i);   
			if ( node.getKindOfNode() == Node.UTILITY )
			{    	 
				LRemoved.insertNode(node);   	       	    
				g.removeNode(node);
			    NodosPadresU = node.getParentNodes();
                Padres = node.getParentNodes();
			}
			else if (node.getKindOfNode() == Node.DECISION)
			{
				LRemoved.insertNode(node);   	       	    
				g.removeNode(node);    
			}		
		}  

		/*
		 *  Eliminar el nodo de Utilidad
		 */
		for (i = 0; i<NodosPadresU.size(); i++)
		{
				node=NodosPadresU.elementAt(i);
				if ( node.getKindOfNode() == Node.DECISION )
				{
					NodosPadresU.removeNode(i);
					break;
				}			
		}

		/*
		 * obtiene los potenciales es decir los valores de probabilidad 
		 * construye los potenciales de la red bayesiana 
		 *
		 */
			Vector rl = new Vector();  
			Potential pt;

			for (e = network.getRelationList().elements() ; e.hasMoreElements() ; ) 
			{   
				Relation r = (Relation) e.nextElement();    
				if ( r.getKind() == Relation.POTENTIAL )
				{        
				/*
				/* obtiene el numero de valores de la relaci�n */
					varsInOriginal=r.getVariables();
					vars=new Vector();	  	
					for(i=0; i < varsInOriginal.size(); i++) {	      
						node = network.getNodeList().getNode((varsInOriginal.elementAt(i)).getName());
						vars.addElement(node);
					}
						
						Relation rNew = r.copy();
						rNew.setVariables(vars);
					
						ptOriginal=r.getValues();	  	
					if (ptOriginal != null){
						pt = ptOriginal.copy();	    		    	
						pt.setVariables(vars);      	        
						rNew.setValues(pt);
					}		
					rl.add(rNew);
				}
				else if ( r.getKind() == Relation.UTILITY)    
				{    		 				   
				   if ( PUmodificada == false)				   
				   {	PUtilidad = r.getValues();
                   		PU = r.getValues();
                   }
                   else
                   {
                   	   PUtilidad=PU;
                   	   PUtilidad=PU;
                   }
                   	                   
				}
			}
			/* construye la red bayesiana */
			Bnet TempBnet = new Bnet();     
			TempBnet.setNodeList(g.getNodeList());
			TempBnet.setRelationList(rl);
			return TempBnet;			
	}
	
	
	public Potential ObtenerUtilidadEsperada() throws InvalidEditException,Exception
	{
			
			/* realiza la propagacion de la red bayesiana construida */
			Bnet RedAislamiento=null;
			Potential PG=null;
				
			RedAislamiento = ConstruirRedAislamiento();

			/*Vector*/ VecR=null;
			if ( observations.size() !=  RedAislamiento.getNodeList().size() ) 
			{
				HuginPropagation hp = new HuginPropagation(RedAislamiento,observations,"tables");	 
				hp.obtainInterest();
				hp.propagate(hp.getJoinTree().elementAt(0),"no");	 	
				VecR =hp.results;
				System.out.println ("Propagar la red temporal :");
				hp.showResults();			
			}

			/*
			 *
			 * Resultados de la propagacion
			 *
			 */

			//System.out.println("Nodos de Evidencia : " + observations.size());
			
			/*
			 *   agrega los padres de nodo utilidad 
			 *   con los valores posteriores 
			 * 
			 */
			
			Node NodeU=null;
			Potential NodoBuscado=null;
			int ContNodosPadresU = NodosPadresU.size();	 	     			
			Vector ListaNodos;
			Vector VecEvidencia = observations.getVariables();			
			Vector  VectorHijos = new Vector(); 
			
			for (int c=0;c<VecEvidencia.size();c++)
			{
				Node N = (Node) VecEvidencia.elementAt(c);	 	
				int ContPadres=0;
				while ( ContPadres  <NodosPadresU.size() )
				{
				  if ( NodosPadresU.elementAt(ContPadres).getName() ==  N.getName() )
				  {
				  		int ValorEvidencia = observations.getValue((FiniteStates) N);
				 		PotentialTable PTable = new PotentialTable( (FiniteStates)N);	 		
						PTable.setValue(ValorEvidencia,1);
						NodoBuscado =   PTable;
						VectorHijos.add(NodoBuscado);
						NodosPadresU.removeNode(N);													
				  		break;
				  }				  
				  ContPadres++;
				}
			}	
				
			ContNodosPadresU = NodosPadresU.size();	  	 
			int ContNsP;
			if ( VecR == null) ContNsP=0;
			else ContNsP=VecR.size();
			for (int c=0; c < ContNsP ;c++)
			{
				NodoBuscado = (Potential) VecR.elementAt(c);
				NodoBuscado.print();
				ListaNodos = NodoBuscado.getVariables();
				Node N = (Node) ListaNodos.firstElement();
				
				int ContPadres=0;	 	
				while ( ContPadres  < ContNodosPadresU ) 
				{
					  if ( NodosPadresU.elementAt(ContPadres).getName()== N.getName() )
					  {
			  			VectorHijos.add(NodoBuscado);
			  			NodosPadresU.removeNode(N);
			  			ContNodosPadresU=NodosPadresU.size();		  		
			  			break;
					  }				  
					  ContPadres++;
				}		 
			}
			if  (VectorHijos.size()>0)
				PG = (Potential) VectorHijos.firstElement();			
			
			/*  Inicia el calculo de probabilidad esperada */
			
			
			
			/*
			 * combinar un nodo aleatorio con el nodo utilidad			 
			 */
			PG = combine(PG,PUtilidad);
						
			if (PG != null)  { 
				ListaNodos = PG.getVariables();
				NodeU = (Node) ListaNodos.firstElement();	
				PG=addVariable(PG,(FiniteStates) NodeU);      				
			} 				
			
			ContNodosPadresU=0;	
			if  ( VectorHijos != null )			
				 ContNodosPadresU = VectorHijos.size();
			
			for (int i=1;i<ContNodosPadresU;i++)
			{
				PUtilidad=PG;
				PG = (Potential) VectorHijos.elementAt(i);	
				ListaNodos = PG.getVariables();
				Node Ntemp = (Node) ListaNodos.firstElement();			
				PG = combine(PG,PUtilidad);				
				if (PG != null) 				                
					PG=addVariable(PG,(FiniteStates) Ntemp);
			}
			return PG;
	}       // fin de la funcion Obtener Utilidad Esperada

    public NodeList ObtenerPadres()
    {
           return Padres;
    }
    public Vector ObtenerNodosPosteori()
    {
           return VecR;
    }
    public void ConfigurarUtilidad (Potential P)
    {
        PUtilidad = P;
    }
    public Potential ObtenerNodoUtilidad()
    {
        return PU;
    }
    
    public void ColocarNodoUtilidad( Potential Pot )
    {
        PU = Pot;
    }
    
    /*public void ModificarUtil( Potential PU )
    {
        PUtilidad=PU;
        PUtilidad=PU;        
    }*/
}