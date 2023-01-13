package aprendizaje;

//import weka.classifiers.trees.j48.J48;
//import weka.classifiers.trees.m5.M5P;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.M5P;

import weka.classifiers.trees.Id3;
import weka.core.Instances;
import weka.core.Instance;
import weka.classifiers.Evaluation;
import weka.classifiers.Classifier;
import weka.gui.treevisualizer.*;
import weka.core.Utils;
import weka.core.Attribute;
import java.io.FileReader;
import java.io.StringReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import ia.Variable;
import utileria.Listas;
import utileria.Matrices;
import utileria.ESObjetos;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class miWeka {

  Node n; // arbol de weka
  public Nodo nodo; // arbol de estados
  private String grafo;
  J48 j48;
  Instances ejemplos;
  public Vector nodeList;

  public miWeka(String archivoEjemplos) {

    try {
          j48 = new J48();

          ejemplos = new Instances(new FileReader(
              archivoEjemplos));

       ejemplos.setClassIndex(ejemplos.numAttributes() - 1);
       j48.buildClassifier(ejemplos);

       TreeBuild tb = new TreeBuild();
       grafo = j48.graph();
       n = tb.create(new StringReader(grafo));
       nodo=getCopiaArbolDecision();
       reetiquetaHojas();
       nodeList=nodeList();

    }
    catch (FileNotFoundException fnf) {
      System.out.println(fnf.getMessage());
    }
    catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public miWeka(String archivoEjemplos, String archivoParticion) {

    try {
          j48 = new J48();

          ejemplos = new Instances(new FileReader(
              archivoEjemplos));

       ejemplos.setClassIndex(ejemplos.numAttributes() - 1);
       j48.buildClassifier(ejemplos);

       TreeBuild tb = new TreeBuild();
       grafo = j48.graph();
       n = tb.create(new StringReader(grafo));
       nodo=(Nodo)ESObjetos.leeObjeto(archivoParticion);
       nodeList=nodeList();

    }
    catch (FileNotFoundException fnf) {
      System.out.println(fnf.getMessage());
    }
    catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }



  private String valorClase(double valor){
    Attribute a=ejemplos.classAttribute();
    return a.value((int)valor);
  }

  public String[] getAtributos(){

    Enumeration e=ejemplos.enumerateAttributes();
    Vector v=new Vector();
    while(e.hasMoreElements()){
      Attribute a=(Attribute)e.nextElement();
      //System.out.println(a.name() );
      v.addElement(a.name());
    }
    return Listas.stringVectorToStringArray(v);
  }

  private Vector getAttributes(){

    Enumeration e=ejemplos.enumerateAttributes();
    Vector v=new Vector();

    while(e.hasMoreElements()){
      Attribute a=(Attribute)e.nextElement();
      v.addElement(a);
    }
    return v;
  }

/*
  public String[] int2StringState(int[] state){
    String[] stateS=new String[state.length];
    for(int i=0;i<state.length; i++){
      stateS[i]=ejemplos.attribute(i).value(state[i]);
          }
    return stateS;
  }
*/
  public String[] getValues(String atributo){
    Enumeration e=ejemplos.enumerateAttributes();
    Attribute at=null;
    Vector v=new Vector();

    while(e.hasMoreElements()){
      Attribute a = (Attribute) e.nextElement();
      if (a.name().equals(atributo))
        at = a;
    }
    System.out.println("atributo desde metodo "+at.name());
    Enumeration en=at.enumerateValues();

    int noValores=at.numValues();
    String[] valores=new String[noValores];
    for(int i=0; i<noValores; i++)
      valores[i]=at.value(i);

    return valores;
  }

  public int numOfLeaves(){
    return (int)j48.measureNumLeaves();
  }

  private String[][] tablaAtrib_Valor(Node n){
    Vector agenda =new Vector();
    agenda.addElement(n);
    String s;
    Vector paresAV=new Vector();

    while(!agenda.isEmpty()){
      Node currentNode=(Node) agenda.elementAt(0);
      agenda.remove(0);


     for(int noHijo=0;currentNode.getChild(noHijo)!=null;noHijo++){
       s= currentNode.getLabel()+"\t"+currentNode.getChild(noHijo).getLabel();

       paresAV.addElement(s);
       agenda.addElement(currentNode.getChild(noHijo).getTarget());
      }
    }

    // elimina repetidos
   String[] aux= Listas.conjunto(Listas.stringVectorToStringArray(paresAV));
   return Matrices.ordena(  lista2Tabla(aux),0);
  }

  public String[][] referencias() {
    return referencias(tablaAtrib_Valor(n));
  }

  public double valorClase(String[] valorAtributo){

    Vector atributos=getAttributes();
    Instance inst = new Instance(atributos.size());
    ejemplos.setClassIndex(ejemplos.numAttributes()-1);

    for(int i=0;i<atributos.size();i++){
      Attribute a=(Attribute) atributos.elementAt(i);
      inst.setValue( a, valorAtributo[i]);
    }
    inst.setDataset(ejemplos);

    return Double.parseDouble(valorClase(clasificaInstancia( inst)));

  }

  public double valorClase(double[] valorAtributo){

    Vector atributos=getAttributes();
    Instance inst = new Instance(atributos.size());
    ejemplos.setClassIndex(ejemplos.numAttributes()-1);

    for(int i=0;i<atributos.size();i++){
      Attribute a=(Attribute) atributos.elementAt(i);
      inst.setValue( a, valorAtributo[i]);
    }
    inst.setDataset(ejemplos);

    return Double.parseDouble(valorClase(clasificaInstancia( inst)));
  }


  public String getTree(){
   return grafo;
  }

  public String getFormattedTree() {
    String s = "";
    try {
      s = j48.prefix();
    }
    catch (FileNotFoundException fnf) {
      System.out.println(fnf.getMessage());
    }
    catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return s;
  }


  public double clasificaInstancia(Instance inst){
    double d=-1;
    try{
    //  inst.classIndex();
      d= j48.classifyInstance(inst);
    }
    catch(Exception e){System.out.println(e.getMessage());}

    return d;

  }

private String[][] referencias(String[][] tablaAt_valor){
  //Matrices.imprimeTabla(tablaAt_valor);
  Vector v=new Vector();
  String[][] tabla=Matrices.reduceTablaDif(tablaAt_valor,1,"=");

 for(int i=0; i<tabla.length;i++)
   if(!Matrices.miembro(tabla[i],v))
     v.addElement(tabla[i]);

  String[][] aux=new String[v.size()][];

  for(int i=0; i<aux.length;i++)
    aux[i]=(String[])v.elementAt(i);

  return aux;
}


  private String[][] lista2Tabla(String[] lista){
    String[][] tabla=new String[lista.length][3];
    StringTokenizer st;
    String valor;

    for(int i=0;i<lista.length;i++){
      st=new StringTokenizer(lista[i],"\t");
      tabla[i][0]=st.nextToken();

      valor=st.nextToken();

     if (valor.substring(0, 2).trim().equals("<=") ||
         valor.substring(0, 2).trim().equals(">=")) {
       tabla[i][1] = valor.substring(0, 2).trim();
       tabla[i][2] = valor.substring(2).trim();
     }
     else {
       tabla[i][1] = valor.substring(0, 1).trim();
       tabla[i][2] = valor.substring(1).trim();
     }
   }
    return tabla;
  }

  // metodo para consultas en un arbol de decision
  public String consultaArbol(String[] testValues) {
    return consultaArbol(nodo, testValues);
  }

  private String consultaArbol(Nodo nodo, String[] testValues) {

    String sol = "";
    if(!nodo.hasChildren()) {

      sol= nodo.label;
    } else

    for (int i = 0; i < nodo.linkLabels.size(); i++) {
      String atributo = nodo.label;
      String valor = nodo.linkLabels.elementAt(i).toString();
      boolean pasaPrueba = test(atributo, valor, testValues);

      if (pasaPrueba) {
        Nodo nuevo = (Nodo) nodo.children.elementAt(i);
        return consultaArbol(nuevo, testValues);
      }
    }
    return sol;
  }




  private boolean test(String atributo, String valor, String[] testValues) {

    String testValue = buscaTestValue(atributo, testValues);

    if (!nominal(valor)) {
      String[] token = miWeka.tokeniza(valor);
      String relacion = token[0];
      double value = Double.parseDouble(token[1]);

      if (relacion.equals("<="))
        if (Double.parseDouble(testValue) <= value)
          return true;
        else
          return false;
      else
      if ( (Double.parseDouble(testValue) > value))
        return true;
      else
        return false;
    }
    else
    if (testValue.equals(valor))
      return true;
    else
      return false;
  }

  private boolean nominal(String valor) {

    StringTokenizer st = new StringTokenizer(valor);
    int size = st.countTokens();

    if (size == 1)
      return true;
    else
      return false;
  }

  private static String[] tokeniza(String valor) {

    StringTokenizer st = new StringTokenizer(valor);
    int size = st.countTokens();
    String[] partes = new String[size];
    for (int i = 0; st.hasMoreTokens(); i++)
      partes[i] = st.nextToken().trim();
    return partes;
  }


  private String buscaTestValue(String atributo, String[] testValues){
   int i=Listas.indice(getAtributos(),atributo);

   return testValues[i];
  }

// reetiqueta las hojas recorriendo el arbol con depth-first search.
// usa notacion consecutiva q1, q2,..
public void reetiquetaHojas() {

  Vector agenda = new Vector();
  agenda.addElement(nodo);

  int i=0;
  while (!agenda.isEmpty()) {

    Nodo actual=(Nodo)agenda.elementAt(0);

    if(!actual.hasChildren()){
      actual.label="q"+i;
      i++;
    }
    // manejo de agenda
    agenda.removeElementAt(0);
    agregaSucesores(agenda, actual);
  }
}

public Nodo getNodo(String label){
  Nodo n=null;

  for(int i=0; i<nodeList.size(); i++){
    n=(Nodo)nodeList.elementAt(i);
    if(n.label.equals(label)) break;
  }
  return n;
}


public void bisectaNodo(Nodo estado, Variable constraint, String[][] atributos){
  Nodo parte1=new Nodo(estado.label);
  Nodo parte2=new Nodo("q"+numHojas());

  estado.setLabel(constraint.getName());

  int indice=Listas.indice(Matrices.dameColumna(atributos,0),constraint.getName());

  int min=Integer.parseInt( atributos[indice][2]);
  int max=Integer.parseInt( atributos[indice][3]);

  if(constraint.min>min) min=constraint.min;
  if(constraint.max<max) max=constraint.max;

  int biseccion=(max-min)/2+min;
  estado.addChild(parte1,"<= "+biseccion);
  estado.addChild(parte2,"> "+biseccion);

}


public Vector nodeList() {

  Vector nodos = new Vector();
  Vector agenda = new Vector();
  agenda.addElement(nodo);

  int i = 0;
  while (!agenda.isEmpty()) {

    Nodo actual = (Nodo) agenda.elementAt(0);
    nodos.addElement(actual);

    // manejo de agenda
    agenda.removeElementAt(0);
    agregaSucesores(agenda, actual);
  }
  return nodos;

}

// funcion para manejo de agenda-agrega sucesores al frente
private void agregaSucesores(Vector agenda, Nodo nodo) {

boolean masSucesores = true;

if (nodo.hasChildren())
  for(int i=0; i<nodo.children.size(); i++){
    Nodo hijo=(Nodo)nodo.children.elementAt(i);
   // System.out.println(hijo.label);
    agenda.insertElementAt(hijo, i);
  }
}

  // funcion que devuelve el arbol aprendido en formato Nodo
  public Nodo getCopiaArbolDecision(){
    return construyeArbol(n);
  }

  private Nodo construyeArbol(Node node){

    if(isLeaf(node)) return new Nodo(node.getLabel());

    Vector sucesores=getSucesores(node);
    Nodo arbol=new Nodo(node.getLabel());

    for(int i=0; i<sucesores.size(); i++){

      Node sucesor=(Node)sucesores.elementAt(i);
      Nodo subarbol=construyeArbol(sucesor);

      arbol.addChild(subarbol,sucesor.getParent(0).getLabel());
    }
    return arbol;
  }

  public Vector getSucesores(Nodo nodo){
  Vector sucesores=new Vector();

  for(int i=0; i<nodo.children.size(); i++)
    sucesores.addElement(nodo.children.elementAt(i));

  return sucesores;
}

// devuelve el arco que apunta al nodo sucesor
private String getEdge(Nodo sucesor) {

  Nodo padre = sucesor.parent;
  int indice = noSucesor(padre, sucesor);
  return padre.linkLabels.elementAt(indice).toString();

}

  public Variable[] getConstrains(String etiquetaHoja){
    return getLimites(getRestricciones( etiquetaHoja));
  }

  // devuelve un vector con las restricciones por rama
  private Vector getRestricciones(String etiquetaHoja){

    Vector constrains=new Vector();
    Nodo hoja = getNodo(etiquetaHoja);

    while(hoja.parent!=null){

      StringTokenizer st = new StringTokenizer(getEdge(hoja));
      Variable var = new Variable(hoja.parent.label, st.nextToken(),
                                  Integer.parseInt(st.nextToken()));
      constrains.insertElementAt(var, 0);
      hoja = hoja.parent;
    }

    return constrains;
  }

  // obtencion de limites en todo el espacio de variables
  private Variable[] getLimites(Vector restricciones){

    String[][] tabla=Variable.toTable(restricciones);
    String[] variable=Listas.conjunto(Matrices.dameColumna(tabla,0));

    Variable[] vars=new Variable[variable.length];

    for(int i=0; i<variable.length; i++)
      vars[i]=getMinMax(variable[i],restricciones);

    return vars;
  }

  // el indice es el mismo del indice de cada hoja
  public Vector getConstrains() {
    Vector hojas = getHojas();
    Vector restricciones = new Vector();

    for (int i = 0; i < hojas.size(); i++) {
      Nodo actual = (Nodo) hojas.elementAt(i);
      Variable[] rest = getConstrains(actual.label);

      restricciones.addElement(rest);
    }
    return restricciones;
  }

  // devuelve un vector de Nodos
  public Vector getHojas() {
    nodeList=nodeList();
    Vector hojas = new Vector();
    for (int i = 0; i < nodeList.size(); i++) {
      Nodo actual = (Nodo) nodeList.elementAt(i);
      if (!actual.hasChildren())
        hojas.addElement(actual);
    }
    return hojas;
  }

  public int numHojas(){

    return getHojas().size();
  }

  // obtiene los limites de una variable
  private Variable getMinMax(String varLabel, Vector rest){

    int min=Integer.MIN_VALUE;
    int max=Integer.MAX_VALUE;

    for(int i=0; i<rest.size(); i++){
      Variable var=(Variable)rest.elementAt(i);

      if(var.getName().equals(varLabel)){
        if(var.relacion.equals("<=")){
          // limite sup
          if(var.valor<max)
            max=var.valor;

        }
        // limite inferior
        else
          if(var.valor>min)
            min=var.valor;
      }
    }
    return new Variable(varLabel,min,max);
  }

// devuelve -1 si sucesor no es sucesor de padre
public int noSucesor(Nodo padre, Nodo sucesor) {
  int indice = -1;

  for (int i = 0; i < padre.children.size(); i++) {
    Nodo aux = (Nodo) padre.children.elementAt(i);

  if(sucesor.equals(aux))
    {
      indice = i;
      break;
    }
  }
  return indice;
}


  public Vector getSucesores(Node nodo){
    Vector sucesores=new Vector();

    for(int i=0; nodo.getChild(i) != null; i++)
      sucesores.addElement(nodo.getChild(i).getTarget());

    return sucesores;
  }

  public boolean isRoot(Node n){
    if(n.getRoot()) return true;
    else return false;
  }

  public boolean isLeaf(Node nodo) {
    Edge e = nodo.getChild(0);
    if (e == null)
      return true;
    else
      return false;
  }


  public static void main(String[] args) {

    String ejemplosCrudos = "../ejemplos/crudos/robot/robotFalsosEj.txt";
    String atributosCrudos = "../ejemplos/crudos/robot/robotFalsosAt.txt";
    String filename = "../ejemplos/weka/robot/robotFalsosEj.arff";

    Instancias ex = new Instancias(ejemplosCrudos, atributosCrudos, filename);
    ex.generate_arffFormat();
    miWeka arbol = new miWeka(filename);
  //  Matrices.imprimeTabla(arbol.referencias());
   // System.out.println(arbol.numOfLeaves());
    Nodo id3=arbol.nodo;

    Nodo.displayTreeConsole(id3,"");

    // prueba de consultas
    String[] estado={"6800","8260","50"};
    System.out.println(arbol.consultaArbol(estado));


    Nodo no=arbol.getNodo("q1");

    System.out.println("arco:  "+arbol.getEdge(no));

    // prueba de impresion de restricciones




    //System.out.println(padre.);

   // System.out.println(arbol.getTree());
   // System.out.println(arbol.getFormattedTree());

    /*
        String filename1 = "../ejemplos/weka/varios/weather.arff";
        miWeka arbol1 = new miWeka(filename1);
        Matrices.imprimeTabla(arbol1.referencias());
        Listas.imprimeLista(arbol.getAtributos());
     */
/*
    miWeka mw = new miWeka("../ejemplos/powerPlant/dTree.arff");
    Listas.imprimeLista(mw.getValues(mw.getAtributos()[1]));

    System.out.println(arbol.getClase());
    System.out.println(arbol.valorClase(3.0));

    System.out.println("prueba de instancias ..");
    double[] caso1 = {
        0, 0, 2};

    System.out.println(arbol.valorClase(caso1));

    Vector v = new Vector();
    v.addElement(new String("hola"));
    v.addElement(new Double(3.9));
*/

  }
}