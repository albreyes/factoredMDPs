package ia;

import java.io.*;
import java.util.*;
import elvira.Node;
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
import utileria.Matrices;
import aprendizaje.ValorDiscreto;

/*
import elvira.Network;
import elvira.Relation;
import elvira.parser.ParseException;
import elvira.potential.PotentialTable;
import elvira.ContinuousConfiguration;
import elvira.inference.clustering.*;
import elvira.potential.Potential;
*/


/**
 * <p>Title: Prototipo de Diagnostico</p>
 * <p>Description: Diagnostico de Fallas de una TG</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: MCC ITESM</p>
 * @author not attributable
 * @version 1.0
 */

public class PropagaRB {
  private  Bnet b;
  private String netFile;


  public PropagaRB(String filename) throws IOException, ParseException{
    FileInputStream filenet=new FileInputStream(filename);
    netFile=filename;
    b = new Bnet(filenet);
    filenet.close();
    filenet = null;


  }

  public void imprimeVariablesEvidencia(String varDesconocida){
    imprimeVariablesEvidencia(b.getNodePosition(varDesconocida));
  }

  public void imprimeVariablesEvidencia(int varDesconocida){
    Vector listaNodosEvidencia=new Vector();
    listaNodosEvidencia=dameVariablesEvidencia(varDesconocida);
    Node TNode;
    for (int i=0;i<b.getNodeList().size()-1;i++){
       TNode = (Node) listaNodosEvidencia.elementAt(i);
       //System.out.println(TNode.getName()+" en pos: "+i);
    }
  }

  private Vector asignaValoresEvidencia(Vector VarP) throws IOException{
    Vector VarPVal = new Vector(); // vector de valores de evidencia
    //	System.out.print("\n");
    VarPVal = GetValue(VarP); // Pide los Valores
    return VarPVal;
  }

  // este es para cuando los valores llegan como parametro en un array
  private Vector asignaValoresEvidencia(Vector VarP, int[] vals) throws IOException{
    Vector VarPVal = new Vector(); // vector de valores de evidencia
    Integer IntegerVal;
    Node TNode;
    for (int i=0;i<b.getNodeList().size()-1;i++){
      TNode = (Node) VarP.elementAt(i);
      IntegerVal = new Integer(vals[i]);
        System.out.println("Valor de " + TNode.getName() + ": "+vals[i]);
      VarPVal.addElement(IntegerVal);
    }
    return VarPVal;
  }


  public double[] dameResultados(int posNodo, PotentialTable pt){
    FiniteStates nodo;
    NodeList listaNodos = b.getNodeList();
    Vector VarP = new Vector();
    nodo = (FiniteStates) listaNodos.elementAt(posNodo);
    double r[]=new double[nodo.getNumStates()];
    for(int i=0; i<nodo.getNumStates(); i++){

      r[i]=pt.getValue(i);
      //System.out.println(nodo.getState(i)+":"+r[i]);
    }
    return r;
  }

  // devuelve distribucion de probabilidades de una variable
  public double[] dameResultados(String nodo, Vector nodosPropagados){
    double[] res=null;
    for(int i=0; i<nodosPropagados.size(); i++){
      NodoPropagado np=(NodoPropagado) nodosPropagados.elementAt(i);
      if(np.nombre.equals(nodo))
        res=np.dProbs;
    }
    return res;
  }
  // devuelve probabilidad de un valor de una variable
  public double dameResultados(String nodo, int indiceEstado,
                               Vector nodosPropagados) {
    double[] res = null;
    for (int i = 0; i < nodosPropagados.size(); i++) {
      NodoPropagado np = (NodoPropagado) nodosPropagados.elementAt(i);
      if (np.nombre.equals(nodo))
        res = np.dProbs;
    }
    return res[indiceEstado];
  }


  // devuelve vector con vars de evidencia
  private Vector dameVariablesEvidencia(int indexUnknownVar){
    FiniteStates nodo;
    NodeList listaNodos = b.getNodeList();
    Vector VarP = new Vector();
    for (int j = 0; j < listaNodos.size(); j++){
      nodo = (FiniteStates) listaNodos.elementAt(j);
      if ( indexUnknownVar != j ){
        VarP.addElement(nodo);
        //System.out.print(nodo.getName()+ " ");
      }
    }
    return VarP;
  }

  // checa si un objeto s esta en una lista de objetos l
      private boolean member(Object s, Object[] l){
        if(Arrays.binarySearch(l,s)<0) return false;
        else return true;
      }

      // convierte la lista de vars en un vector de nodos
    private Vector dameNodos(String[] vars){
      FiniteStates nodo;
      NodeList listaNodos = b.getNodeList();
      Vector VarP = new Vector();
     // for (int i = 0; i < vars.length; i++){
      for (int i = 0; i < listaNodos.size(); i++){
        nodo = (FiniteStates) listaNodos.elementAt(i);
        if(member(nodo.getName(),vars)){
          VarP.addElement(nodo);
          //System.out.print(nodo.getName()+ " ");
        }
      }
      return VarP;
    }

  public Vector dameNombreDeNodos(){
    FiniteStates nodo;
    NodeList listaNodos = b.getNodeList();
    Vector NodosPNombre = new Vector(1,1);
    for (int i = 0; i < listaNodos.size(); i++){
      nodo = (FiniteStates) listaNodos.elementAt(i);
      NodosPNombre.addElement(nodo.getName());
      //System.out.print(nodo.getName()+ " ");
    }
    return NodosPNombre;
  }

  public Vector dameNodosRelacionados(String NombreNodo){
    FiniteStates nodo, nodo2;
    Relation x;
    NodeList y;
    Vector NodosRel = new Vector(1,1);

    NodeList listaNodos = b.getNodeList();
    for (int i = 0; i < listaNodos.size(); i++){
      nodo = (FiniteStates) listaNodos.elementAt(i);
      if (NombreNodo.equals(nodo.getName())) {
        x = b.getRelation(nodo);
        y = x.getVariables();
        for (int j=1;j<y.size();j++){
          nodo2 = (FiniteStates) y.elementAt(j);
          NodosRel.addElement(nodo2.getName());
        }
        break;
      }
    }
    return NodosRel;
  }


  // no creo que sirva este metodo
  public double valor(String nodo, String estado, String nodoPadre,
                      String estadoPadre) {

    FiniteStates node=null, parentNode, aux;
    NodeList listaNodos = b.getNodeList();
    Relation r;

    //

    for (int i = 0; i < listaNodos.size(); i++) {
      aux = (FiniteStates) listaNodos.elementAt(i);
      if (nodo.equals(aux.getName()))
        node = aux;
      else if (nodoPadre.equals(aux.getName()))
        parentNode = aux;
    }

    r=b.getRelation(node);
    Potential p = r.getValues();
    Potential ptable=((PotentialTable) p);

    return 0;
  }

  public FiniteStates dameNodo(String etiqueta){

    NodeList listaNodos = b.getNodeList();
    FiniteStates nodo;
    FiniteStates nodoAux = null;

    for (int i = 0; i < listaNodos.size(); i++){
  nodo = (FiniteStates) listaNodos.elementAt(i);
  if(etiqueta.equals(nodo.getName())) nodoAux=nodo;
  }
  return nodoAux;
}

public String[] dameEstados(String etiquetaNodo){
  FiniteStates nodo=dameNodo(etiquetaNodo);


  int size=nodo.getNumStates();
  String[] s=new String[size];

  for(int i=0; i<size; i++)
    s[i]=nodo.getState(i);

    return s;
}

  public int getStatesNum(String etiquetaNodo){

    FiniteStates nodo=dameNodo(etiquetaNodo);
    return nodo.getNumStates();
  }

/*
  public String[] damePadres(String nodo){

    Node node=null, nodeChild;
    NodeList nodeList = b.getNodeList();

    for (int i = 0; i < b.getNodeList().size(); i++)
                        {
                          Node naux=nodeList.elementAt(i);
                          if(naux.getName().equals(nodo))
                          node = naux;
                        }


    NodeList nodeListChild = new NodeList();
    nodeListChild = node.getParentNodes();

    String[] padres=new String[nodeListChild.size()];

    for (int j = 0; j < nodeListChild.size(); j++)
    {
            nodeChild = nodeListChild.elementAt(j);
        //    System.out.println(nodeChild.getName() + ", ");
            padres[j]=nodeChild.getName();

    }
    return padres;

  }
*/

  public String[] damePadres(String etiquetaNodo){

    FiniteStates nodo=dameNodo(etiquetaNodo);
    NodeList nl=nodo.getParentNodes();

    int size=nl.size();

    String[] s=new String[size];

    for(int i=0; i<size; i++)
      s[i]=((FiniteStates)nl.elementAt(i)).getName();

      return s;
  }



  public String[] dameHijos(String etiquetaNodo){

    FiniteStates nodo=dameNodo(etiquetaNodo);
    NodeList nl=nodo.getChildrenNodes();

    int size=nl.size();
    String[] s=new String[size];

    for(int i=0; i<size; i++)
      s[i]=((FiniteStates)nl.elementAt(i)).getName();
      return s;
  }


  public double[][] dameProbCondicionalDeNodoOrdenada(String nodo, String[] ordenDeseado){

    double[][] tabla=dameProbCondicionalDeNodo(nodo);
    String[] padres=Listas.reverse(damePadres(nodo));
    String[] ordenReal=dameEstados(nodo);
    double[][] tablaN=new double[tabla.length][tabla[0].length];
    tabla=Matrices.ordenaFilas(tabla,ordenReal,ordenDeseado);
    double[][] subtabla,subtablaOrdenada=null;

    for(int i=0;i<padres.length;i++){
      String[] ordenPadre=dameEstados(padres[i]);
      int incr=(int)Math.pow(ordenDeseado.length,i+1);
      for(int col=0;col<tabla[0].length;col+=incr){
        if(i==0)
        subtabla = Matrices.obtenSubtabla(tabla, col, col + incr-1);
        else subtabla = Matrices.obtenSubtabla(tablaN, col, col + incr-1);

        subtablaOrdenada=Matrices.ordenaColumnas(subtabla,ordenPadre,ordenDeseado,incr/ordenDeseado.length);
        Matrices.inserta(tablaN,subtablaOrdenada,col);
      }
    }
    return tablaN;
  }

  // esta funcion se debe revisar. no se requiere en el sistema
  // pero puede ser util.
  public double[][] dameProbCondicionalDeNodoOrdenada(String nodo){

    double[][] tabla=dameProbCondicionalDeNodo(nodo);
    String[] padres=Listas.reverse(damePadres(nodo));
    String[] ordenReal=dameEstados(nodo);
    String[] ordenDeseado=ValorDiscreto.ordenaValores(ordenReal);
   // System.out.println("antes de ordenar filas");
   // Matrices.imprimeTabla(tabla);

   // System.out.println("nodo: "+nodo);

//System.out.println("valores: ");
//Matrices.imprimeLista(dameEstados(nodo));
//Matrices.imprimeLista(ordenReal);

    double[][] tablaN=new double[tabla.length][tabla[0].length];
    tabla=Matrices.ordenaFilas(tabla,ordenReal,ordenDeseado);
    double[][] subtabla,subtablaOrdenada=null;

   //     System.out.println("despues de ordenar filas");
   //  Matrices.imprimeTabla(tabla);

    for(int i=0;i<padres.length;i++){
      String[] ordenPadre=dameEstados(padres[i]);
      String[] ordenDeseadoP=ValorDiscreto.ordenaValores(ordenPadre);

      int incr=(int)Math.pow(ordenDeseadoP.length,i+1);

      for(int col=0;col<tabla[0].length;col+=incr){
        if(i==0)
        subtabla = Matrices.obtenSubtabla(tabla, col, col + incr-1);
        else subtabla = Matrices.obtenSubtabla(tablaN, col, col + incr-1);

        subtablaOrdenada=Matrices.ordenaColumnas(subtabla,ordenPadre,ordenDeseadoP,incr/ordenDeseadoP.length);
        Matrices.inserta(tablaN,subtablaOrdenada,col);
      }
    }
    return tablaN;

  }

  public void guardaRed() {
    try {
      b.saveBnet(new FileWriter(netFile));
    }
    catch (IOException ioe) {}
  }

  public void guardaRed(String filename) {
    try {
      b.saveBnet(new FileWriter(filename));
    }
    catch (IOException ioe) {}
  }


  public void formateaCPT(String var){

    asignaProbCondicionalDeNodo(var, Matrices.intercambiaCeros(
            dameProbCondicionalDeNodo(var)));
      //  guardaRed();

  }

  // checa si una variable esta en la red
  public boolean member(String var){
    boolean resp=false;
    Vector v=dameNombreDeNodos();

    for(int i=0; i<v.size(); i++){
      String currentVar=(String) v.elementAt(i);
      if (currentVar.equals(var)){
        resp = true;
        break;
      }
    }

    return resp;
  }

  public void formateaRed(Variable[] vars) {
    // int i=10;
    for (int i = 0; i < vars.length; i++) {
      //System.out.println("var "+vars[i].getName());
      String var = vars[i].getName();

      if (member(var)) { // esto es para evitar que formatee una variable
                         //q no esta en la red
        String[] faltantes = missingStates(vars[i]);
        for (int s = 0; s < faltantes.length; s++)
          agregaEstado(var, faltantes[s]);

        ordenaEstados(var);
        formateaCPT(var);
        Listas.imprimeLista(dameEstados(var));
        Matrices.imprimeTabla(dameProbCondicionalDeNodo(var));
      }
    }
  }

  public void asignaProbCondicionalDeNodo(String NombreNodo, double[][] probs){

    FiniteStates nodo,nodo2;
    Relation x;

    // transforma tabla en lista
    double[] prob = probs[0];
    for (int i = 1; i < probs.length; i++)
      prob = Listas.concatena(prob, probs[i]);

    NodeList listaNodos = b.getNodeList();

    // busca en la lista de nodos la variable deseada
    for (int i = 0; i < listaNodos.size(); i++){
      nodo = (FiniteStates) listaNodos.elementAt(i);
      if(NombreNodo.equals(nodo.getName())){

        x = b.getRelation(nodo);

        Potential y = x.getValues();
        // cambia la tabla original por la nueva
        ((PotentialTable) y).setValues(prob);

        break;
      }
    }
  }
/*
  public double[][] dameProbCondicionalDeNodo(String NombreNodo){
    FiniteStates nodo=dameNodo(NombreNodo);
    //nodo.
    Node n;
    n.
  }
*/

  public double[][] dameProbCondicionalDeNodo(String NombreNodo){
    FiniteStates nodo,nodo2;
    Relation x;
    NodeList z;
    double[]    ProbCond;
    double[][]  Prob_Condicional = null;
    int NumeroRenglones = 0,
        NumeroColumnas = 1,
        Consecutivo = 0;
    NodeList listaNodos = b.getNodeList();
    for (int i = 0; i < listaNodos.size(); i++){
      nodo = (FiniteStates) listaNodos.elementAt(i);
      if(NombreNodo.equals(nodo.getName())){

        NumeroRenglones = nodo.getNumStates();
        x = b.getRelation(nodo);
        z = x.getVariables();
        for (int j=1;j<z.size();j++){
          nodo2 = (FiniteStates) z.elementAt(j);
          NumeroColumnas *= nodo2.getNumStates();
        }

        Prob_Condicional = new double[NumeroRenglones][NumeroColumnas];
        Potential y = x.getValues();


        ProbCond =((PotentialTable) y).getValues();
        // solo para debugear
      //  Listas.imprimeLista(ProbCond);

        for(int j=0; j<NumeroRenglones; j++){
          for(int k=0; k<NumeroColumnas;k++){
            if(Consecutivo<ProbCond.length) // esto no es la sol,o si?
            Prob_Condicional[j][k] = ProbCond[Consecutivo++];
          }
        }
        break;
      }
    }
    return Prob_Condicional;
  }


    // un ejemplo de uso de propagacion
    public void propagaTodosCasos() throws IOException{
      for (int i = 0; i < b.getNodeList().size(); i++)
        propaga(i);
    }

    public double[] propagaPorNombreVarDesc(String UnknownVar) throws IOException{
      return propaga(b.getNodePosition(UnknownVar));
    }


    public int getNodePosition(String var){
      return b.getNodePosition(var);
    }

    public double[] propagaPorNombreVarDesc(String UnknownVar, int[] valores) throws IOException{
      Vector VarPVal = new Vector(); // vector de valores de evidencia
      Vector VarP = new Vector(); // vector de variables de evidencia
      int index=b.getNodePosition(UnknownVar);

      VarP=dameVariablesEvidencia(index);
      VarPVal=asignaValoresEvidencia(VarP,valores);

      PotentialTable potentialTable=propagacionHugin( VarP,  VarPVal);
      return dameResultados(index,potentialTable);
    }

    public double[] propaga(int indexUnknownVar) throws IOException {

      // vector de variables de evidencia
      Vector VarP = dameVariablesEvidencia(indexUnknownVar);
      // vector de valores de evidencia
      Vector VarPVal = asignaValoresEvidencia(VarP);

      PotentialTable potentialTable = propagacionHugin(VarP, VarPVal);

      return dameResultados(indexUnknownVar, potentialTable);
    }

    public void propaga(String[] evidenceNodes) throws IOException{
      Vector VarPVal = new Vector(); // vector de valores de evidencia
      Vector VarP = new Vector(); // vector de variables de evidencia
      VarP=dameNodos(evidenceNodes);
      VarPVal=asignaValoresEvidencia(VarP);
      PotentialTable potentialTable=propagacionHugin(VarP,VarPVal);
     }

     public PotentialTable propaga(String[] evidenceNodes, int[] evidenceValues) throws
         IOException {

       Vector VarP = dameNodos(evidenceNodes); // vector de variables de evidencia
       Vector VarPVal = new Vector();

       Listas.imprimeLista(evidenceNodes);
       Listas.imprimeLista(evidenceValues);

       for (int i = 0; i < evidenceValues.length; i++)
         VarPVal.addElement(new Integer(evidenceValues[i]));

       return propagacionHugin(VarP, VarPVal);
     }

     // esto debe quedar en el futuro. mientras usese otra version de propagaMultiple
     public Vector propagaMultiple(String[] evidenceNodes, int[] evidenceValues) throws
         IOException {

       Vector VarP = dameNodos(evidenceNodes); // vector de variables de evidencia
       Vector VarPVal = new Vector();

       Listas.imprimeLista(evidenceNodes);
       Listas.imprimeLista(evidenceValues);

       for (int i = 0; i < evidenceValues.length; i++)
         VarPVal.addElement(new Integer(evidenceValues[i]));

       return propagacionHuginMultiple(VarP, VarPVal);
     }


     // permite la propagacion para determinados nodos desconocidos
     public double[][] propagaMultiple(String[] unknownNodes,
                                       String[] evidenceNodes,
                                       int[] evidenceValues) throws
         IOException, ParseException {

       // aqui se usa una clase de zenon
       RBpropagacion red = new RBpropagacion(netFile);

       red.propagaPorNombresVarsDesc(evidenceNodes, evidenceValues);

       double[][] aux = new double[unknownNodes.length][];
       for (int i = 0; i < unknownNodes.length; i++)
         aux[i] = red.probPosterior(unknownNodes[i]);

       return aux;
     }


     public PotentialTable propagacionHugin(Vector VarP, Vector VarPVal) throws
         IOException {

       Configuration Conf = new Configuration(VarP, VarPVal);
       Evidence E = new Evidence(Conf);

       HuginPropagation hp = new HuginPropagation(b, E, "tables");

       hp.obtainInterest();
       hp.propagate(hp.getJoinTree().elementAt(0), "no");

       if (PropaOK(hp)) {
         //   System.out.println("Evidencia Valida");
         //   hp.showResults();
       }
       else
         System.out.println("Evidencia No Valida");

       Vector R = hp.getResults();
       PotentialTable potentialTable = (PotentialTable) R.elementAt(0);

       return potentialTable;
     }

     public Vector propagacionHuginMultiple(Vector VarP, Vector VarPVal) throws
         IOException {

       Vector v=new Vector();
       Configuration Conf = new Configuration(VarP, VarPVal);
       Evidence E = new Evidence(Conf);

       HuginPropagation hp = new HuginPropagation(b, E, "tables");

       hp.obtainInterest();
       hp.propagate(hp.getJoinTree().elementAt(0), "no");

       if (PropaOK(hp)) {
         System.out.println("Evidencia Valida");
         hp.showResults();
       }
       else
         System.out.println("Evidencia No Valida");

       Vector R = hp.getResults();
       PotentialTable potentialTable = new PotentialTable();

       for (int i = 0; i < R.size(); i++) {
         PotentialTable pt = ( (PotentialTable) R.elementAt(i));
         FiniteStates fs = (FiniteStates) (pt.getVariables().elementAt(0));
        // System.out.println(fs.getName());
        // Listas.imprimeLista(fs.getStringStates());
        // Listas.imprimeLista(pt.getValues());
         v.addElement(new NodoPropagado(fs.getName(),fs.getStringStates(),pt.getValues()));
       }

       return v;

     }


      // para verificar si hay algun error en la propagacion
     private boolean PropaOK (HuginPropagation Hp )
        {
                        FiniteStates fs;
                        boolean OK= true;
                        Vector VecProb = Hp.results;


                        for (int I=0;I<b.getNodeList().size();I++)
                        {
                                int ip=0;
                                boolean found = false;
                                fs = (FiniteStates) b.getNodeList().elementAt(I);
                                double[] PastProb = new double [fs.getNumStates()];
                                while (!found && ip<VecProb.size())
                                {
                                        Potential p =( Potential) VecProb.elementAt(ip);
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

    // recibe la lista de nodos por evidenciar y devuelve un vector
    // de valores. El indice del valor en el vector Variables corresponde al
    // indice del nodo en el el vector NombreVar
    private Vector GetValue( Vector listaNodosEvidencia) throws IOException{
      Integer IntegerVal;
      Node TNode;
      Vector valores = new Vector();
      for (int i=0;i< b.getNodeList().size()-1;i++){
        TNode = (Node) listaNodosEvidencia.elementAt(i);
        System.out.print("Valor de " + TNode.getName() + ":");
        IntegerVal = new Integer(Integer.parseInt(leeDato()));
        valores.addElement(IntegerVal);
      }
      return valores;
    }

    // devuelve una lista de estados faltantes para la variable var
    public String[] missingStates(Variable var){
      String[] reales=dameEstados(var.getName());
      String[] deseados=var.values;

      return Listas.diferencia(reales,deseados);
    }

    // agrega un estado a una variable y ademas le pone un cero en probs cond.
    public void agregaEstado(String variable, String estado) {

      double[][] cpt = dameProbCondicionalDeNodo(variable);
      FiniteStates nodo = dameNodo(variable);

      // se obtiene lista de estados y se agrega el nuevo
      Vector edos = nodo.getStates();
      edos.addElement(estado);

      // se inserta fila de ceros al final de la cpt
      double[] fila = new double[cpt[0].length];
      cpt = Matrices.insertaFila(cpt, fila, cpt.length);

      updateEstados(variable, Listas.stringVectorToStringArray(edos), cpt);
      // Matrices.imprimeTabla(dameProbCondicionalDeNodo(variable));
    }

    public void ordenaEstados(String var){
      double cpt[][]= dameProbCondicionalDeNodo(var);
      Matrices.imprimeTabla(cpt);

      String[] estados=dameEstados(var);
 //     Listas.imprimeLista(estados);

      String[] estadosOrdenados=ValorDiscreto.ordenaValores(estados);
 //     Listas.imprimeLista(estadosOrdenado);

      int indices[]=Listas.getIndexes(estados,estadosOrdenados);
 //     Listas.imprimeLista(indices);

      double[][] cptOrdenada=new double[cpt.length][];

      for(int i=0; i<cptOrdenada.length; i++)
        cptOrdenada[i]=cpt[indices[i]];

      updateEstados(var, estadosOrdenados, cptOrdenada);
    }

    // actualiza estados de una variable y su cpt
    private void updateEstados(String variable, String[] estados, double[][] cpt){

      FiniteStates nodo=dameNodo(variable);
      nodo.setStates(estados);
      asignaProbCondicionalDeNodo(variable,cpt);
    }

    public String leeDato() throws IOException {
      InputStreamReader inStream = new InputStreamReader( System.in );
      BufferedReader stdin = new BufferedReader( inStream );
      String inData = stdin.readLine();
      return inData;
    }

    public int getNumNodes(){
      return b.getNodeList().size();
    }

    public static void main(String[] args) throws IOException,ParseException{


      PropagaRB red = new PropagaRB(
                "C:/Documents and Settings/xperto/Escritorio/powerPlant/a0Elv.elv");

      Variable vars[];
          vars=Variable.getVars("C:/Documents and Settings/xperto/Escritorio/powerPlant/atributos.txt");

          vars=Variable.getVarsRBD(vars);
/*
          for(int i=0; i<vars.length; i++){
            System.out.println(vars[i].getName());
            Listas.imprimeLista(vars[i].values);
          }
*/
 //         red.formateaRed(vars);

 //System.out.println(red.member("ffw_prime"));

         // red.guardaRed("C:/Documents and Settings/xperto/Escritorio/powerPlant/a5FormateadaElv.elv");


      /*
      PropagaRB red = new PropagaRB(
          "C:/Documents and Settings/xperto/Escritorio/powerPlant/a8Elv.elv");
        RBD redD = new RBD(
          "C:/Documents and Settings/xperto/Escritorio/powerPlant/a8Elv.elv");

        // obtiene variables en t
       String[] varsT=Listas.stringVectorToStringArray(redD.dameNodosTiempoTmas1());
      Listas.imprimeLista(varsT);
      for(int i=0; i<varsT.length;i++){
        System.out.println(varsT[i]);

        Matrices.imprimeTabla(red.
            dameProbCondicionalDeNodo(varsT[i]));
      System.out.println("version modificada");

      double[][] nueva=Matrices.intercambiaCeros(red.
            dameProbCondicionalDeNodo(varsT[i]));
        Matrices.imprimeTabla(nueva);


      }

      red.asignaProbCondicionalDeNodo(varsT[1],Matrices.intercambiaCeros(red.
            dameProbCondicionalDeNodo(varsT[1])));

        Matrices.imprimeTabla(red.dameProbCondicionalDeNodo(varsT[1]));
*/

/*
      PropagaRB redAux = new PropagaRB(
          "../redes/robot/caso1Cualclase0Elv-learnt-0.elv");

        // PropagaRB redAux = new PropagaRB(netFile);

            // obtiene variables en t
             String[] varsT=Listas.stringVectorToStringArray(redAux.dameNodosTiempoT());
            Listas.imprimeLista(varsT);
            // obtiene variables en t+1
            String[] varsTmas1=Listas.stringVectorToStringArray(redAux.dameNodosTiempoTmas1());
            Listas.imprimeLista(varsTmas1);

        int[] evidenceValues={0,0,0,0,0,0,0,0,0,0,0};
        Matrices.imprimeTabla(redAux.propagaMultiple(varsTmas1,varsT,evidenceValues));
        int[] evidenceValues1={1,0,0,0,0,0,0,0,0,0,1};
        Matrices.imprimeTabla(redAux.propagaMultiple(varsTmas1,varsT,evidenceValues1));

      // Listas.imprimeLista( red.propagaPorNombreVarDesc("qang3_prime"));
      for(int i=0; i<varsT.length;i++){
        System.out.println(varsT[i]);
        Matrices.imprimeTabla(redAux.
            dameProbCondicionalDeNodo(varsT[i]));
      System.out.println("version modificada");
        Matrices.imprimeTabla(Matrices.intercambiaCeros(redAux.
            dameProbCondicionalDeNodo(varsT[i])));
      }
*/
/*
      Vector nodo = red.dameNodosTiempoTmas1();
      String var;

      String[] ordenDeseado = {
          "cero", "pos", "neg"};

      for (int i = 0; i < nodo.size(); i++) {
     //for (int i = 0; i < 1; i++) {

        var = nodo.elementAt(i).toString();

        String[] padres = red.damePadres(nodo.elementAt(i).toString());
        String[] padresInv = Listas.reverse(padres);
        System.out.println("tabla original");
        Matrices.imprimeTabla(red.dameProbCondicionalDeNodo(nodo.elementAt(i).
            toString()));

        System.out.println("tabla en proceso");
        Matrices.imprimeTabla(red.dameProbCondicionalDeNodoOrdenada(nodo.
            elementAt(i).toString()));

       System.out.println("tabla ordenada");
        Matrices.imprimeTabla(red.dameProbCondicionalDeNodoOrdenada(nodo.
            elementAt(i).toString(), ordenDeseado));

      }

      // Matrices.imprimeLista(Matrices.stringVectorToStringArray(red.
      //    dameNodosTiempoTmas1()));
*/


    }
}

  class NodoPropagado{

    String nombre;
    double[] dProbs;
    String[] estados;

    NodoPropagado(String nombre, String[] estados, double[] dProbs){
      this.nombre=nombre;
      this.dProbs=dProbs;
      this.estados=estados;
    }

    double getValor(String estado){
      int pos=-1;

      for(int i=0; i<estados.length; i++)
        if(estado.equals(estados[i]))
          pos=i;

      return dProbs[pos];
    }

    double getValor(int estado){
      return dProbs[estado];
    }
  }