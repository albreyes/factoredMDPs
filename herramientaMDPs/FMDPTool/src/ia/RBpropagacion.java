package ia;

import java.io.*;
import java.util.*;
import elvira.FiniteStates;
import elvira.Bnet;
import elvira.NodeList;
import elvira.Configuration;
import elvira.Evidence;
import elvira.parser.ParseException;
import elvira.potential.PotentialTable;
import elvira.potential.Potential;
import elvira.inference.clustering.HuginPropagation;
import elvira.Relation;

import utileria.Listas;

/**
 * <p>Title: Prototipo de Diagnostico</p>
 * <p>Description: Diagnostico de Fallas de una TG</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: MCC ITESM</p>
 * @author Zenon Flores Loredo
 * @version 1.0
 */

public class RBpropagacion {
  private Bnet RB;
  private Vector rPropagacion;
  public RBpropagacion(String filename) throws IOException, ParseException{
    FileInputStream filenet = new FileInputStream(filename);
    RB = new Bnet(filenet);
    filenet.close();
    filenet = null;
    rPropagacion = null;
  }

  //Retorna el nombre de los nodos que conforman a la RB
  public Vector nombreDeNodos(){
    FiniteStates nodo;
    NodeList listaNodos = RB.getNodeList();
    Vector NodosPNombre = new Vector(1,1);
    for (int i = 0; i < listaNodos.size(); i++){
      nodo = (FiniteStates) listaNodos.elementAt(i);
      NodosPNombre.addElement(nodo.getName());
    }
    return NodosPNombre;
  }

  //Retorna la tabla de probabilidad condicional del nodo
  public double[][] probCondicional(String NombreNodo){
    FiniteStates nodo,nodo2;
    Relation x;
    NodeList z;
    double[]    ProbCond;
    double[][]  Prob_Condicional = null;
    int NumeroRenglones = 0,
        NumeroColumnas = 1,
        Consecutivo = 0;
    NodeList listaNodos = RB.getNodeList();
    for (int i = 0; i < listaNodos.size(); i++){
      nodo = (FiniteStates) listaNodos.elementAt(i);
      if(NombreNodo.equals(nodo.getName())){
        NumeroRenglones = nodo.getNumStates();
        x = RB.getRelation(nodo);
        z = x.getVariables();
        for (int j=1;j<z.size();j++){
          nodo2 = (FiniteStates) z.elementAt(j);
          NumeroColumnas *= nodo2.getNumStates();
        }
        Prob_Condicional = new double[NumeroRenglones][NumeroColumnas];
        Potential y = x.getValues();
        ProbCond =((PotentialTable) y).getValues();
        for(int j=0; j<NumeroRenglones; j++){
          for(int k=0; k<NumeroColumnas;k++){
            Prob_Condicional[j][k] = ProbCond[Consecutivo++];
          }
        }
        break;
      }
    }
    return Prob_Condicional;
  }

  private boolean seEncuentra(int[] Indice, int IndiceObj){
    for(int i=0; i<Indice.length;i++){
      if ( Indice[i] == IndiceObj ){
        return true;
      }
    }
    return false;
  }

  // devuelve vector de FiniteStates con variables evidencia por diferencia
  private Vector variablesEvidencia(int[] indiceVarDesc){
    FiniteStates nodo;
    NodeList listaNodos = RB.getNodeList();
    Vector VarP = new Vector(1,1);
    for (int j = 0; j < listaNodos.size(); j++){
      nodo = (FiniteStates) listaNodos.elementAt(j);
      if(!seEncuentra(indiceVarDesc, j)){
        VarP.addElement(nodo);
      }
    }
    return VarP;
  }

  // devuelve vector de FiniteStates con variables evidencia por conversion
  private Vector variablesEvidencia(String[] VarDesc) {
    FiniteStates nodo;
    NodeList listaNodos = RB.getNodeList();
    Vector VarP = new Vector(1, 1);
    // busca cada nombre de variable en la lista de nodos
    for(int i=0; i<VarDesc.length; i++)
    for (int j = 0; j < listaNodos.size(); j++) {
      nodo = (FiniteStates) listaNodos.elementAt(j);
      if (nodo.getName().equals(VarDesc[i]))
        VarP.addElement(nodo);
    }
    return VarP;
  }


  public double[] dameResultados(int posNodo, PotentialTable pt){
    FiniteStates nodo;
    NodeList listaNodos = RB.getNodeList();
    Vector VarP = new Vector();
    nodo = (FiniteStates) listaNodos.elementAt(posNodo);
    double r[]=new double[nodo.getNumStates()];
    for(int i=0; i<nodo.getNumStates(); i++){
      r[i]= pt.getValue(i);
    }
    return r;
  }

  private Vector convierteAValoresEvidencia(Vector listaNodos, int[] vals)
  throws IOException{
    Vector VarPVal = new Vector(); // vector de valores de evidencia
    Integer IntegerVal;
   // Node TNode;
    for (int i=0;i<listaNodos.size();i++){
      IntegerVal = new Integer(vals[i]);
      VarPVal.addElement(IntegerVal);
    }
    return VarPVal;
  }

 //Verifica si hay algun error en la propagacion
 private boolean PropaOK (HuginPropagation Hp ){
  FiniteStates fs;
  boolean OK= true;
  Vector VecProb = Hp.results;

  for (int I=0;I<RB.getNodeList().size();I++){
    int ip=0;
    boolean found = false;
    fs = (FiniteStates) RB.getNodeList().elementAt(I);
    double[] PastProb = new double [fs.getNumStates()];
    while (!found && ip<VecProb.size()){
      Potential p =( Potential) VecProb.elementAt(ip);
      if (fs.equals(((FiniteStates)p.getVariables().elementAt(0)))){
        found= true;
        PastProb = ((PotentialTable) p).getValues();
      }
      else ip++;
    }
    ip=0;
    Double NAN = new Double(Double.NaN);
    while ( OK && ip < PastProb.length ){
      Double d = new Double(PastProb[ip]);
      if (d.equals(NAN)){
        OK=false;
        return OK;
      }
      else ip++;
    }
  }
  return OK;
 }

  public Vector propagacionHugin(Vector VarP, Vector VarPVal)
  throws IOException {
   Configuration Conf = new Configuration(VarP,VarPVal);
   Evidence E = new Evidence(Conf);
   HuginPropagation hp = new HuginPropagation(RB,E,"tables");
   hp.obtainInterest();
   hp.propagate(hp.getJoinTree().elementAt(0),"no");
   if ( !PropaOK(hp) )
      System.out.println("Evidencia No Valida");
   return hp.getResults();
  }

  //Realiza la propapagacion para mas de una variable desconocida
  public void propagaPorNombresVarsDesc(String[] varsE, int[] valsE)
  throws IOException{
    rPropagacion = null;
    Vector valoresE; // vector de valores evidencia
    Vector varE;    // vector de variables evidencia

    //Obtiene un vector con las variables evidencia conocidas por conversion
    varE    = variablesEvidencia(varsE);

    //Relaciona los valores evidencias de acuerdo con la vars conocidas
    valoresE = convierteAValoresEvidencia(varE,valsE);

    //Propaga con las variables evidencias conocidas
    rPropagacion = propagacionHugin(varE, valoresE);
  }


  //Realiza la propapagacion para mas de una variable desconocida
  public void propagaPorNombresVarsDesc(Vector VarDesc, int[] valores)
  throws IOException{
    rPropagacion = null;
    Vector valoresE; // vector de valores evidencia
    Vector varE;    // vector de variables evidencia
    String aux = null;
    int[] pos = new int[VarDesc.size()];

    //obtiene la posicion de las variables desconocidas en la RB
    for(int i=0; i<VarDesc.size(); i++){
      aux     = VarDesc.get(i).toString();
      pos[i]  = RB.getNodePosition(aux);
    }
    //Obtiene un vector con las variables evidencia conocidas
    varE    = variablesEvidencia(pos);

    //Relaciona los valores evidencias de acuerdo con la vars conocidas
    valoresE = convierteAValoresEvidencia(varE,valores);

    //Propaga con las variables evidencias conocidas
    rPropagacion = propagacionHugin(varE, valoresE);
  }

  //Entrega la probabilidad posterior de un nodo de interes no conocido
  //despues de una propagacion
  public double[] probPosterior(String NodoInterez){
   double pp[] = null;
   Vector vars;
   PotentialTable pTable=null;
   FiniteStates nodo;
   double ProbPost[] = null;
   for (int i=0; i<rPropagacion.size(); i++){
     pTable = (PotentialTable) rPropagacion.elementAt(i);
     vars = pTable.getVariables();
     nodo = (FiniteStates) vars.get(0);
     if(NodoInterez.equals(nodo.getName())){
      pp = pTable.getValues();
      ProbPost = new double[pp.length];
      for (int ii=0;ii<pp.length;ii++)
        ProbPost[ii] = pp[ii];
      break;
     }
     pTable = null; vars = null; nodo = null;
   }
   return ProbPost;
  }

  public static void main(String[] args) throws IOException,ParseException{


    RBpropagacion red = new RBpropagacion(
        "../redes/robot/caso1Cualclase0Elv-learnt-0.elv");
    PropagaRB redAux = new PropagaRB(
        "../redes/robot/caso1Cualclase0Elv-learnt-0.elv");

    RBD redD = new RBD(
        "../redes/robot/caso1Cualclase0Elv-learnt-0.elv");

    // obtiene variables en t
    String[] varsT=Listas.stringVectorToStringArray(redD.dameNodosTiempoT());
    Listas.imprimeLista(varsT);
    // obtiene variables en t+1
    String[] varsTmas1=Listas.stringVectorToStringArray(redD.dameNodosTiempoTmas1());
    Listas.imprimeLista(varsTmas1);

    int[] valsT={0,0,0,0,0,0,0,0,0,0,0};

     red.propagaPorNombresVarsDesc(varsT, valsT);
     for(int i=0; i<varsTmas1.length;i++)
     Listas.imprimeLista ( red.probPosterior(varsTmas1[i]));

  }

}