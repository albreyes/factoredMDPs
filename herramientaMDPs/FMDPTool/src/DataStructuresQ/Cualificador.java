package DataStructuresQ;

import DataStructuresQ.*;
import aprendizaje.miWeka;
import java.util.Vector;
import utileria.Listas;
import utileria.Matrices;
import utileria.tablas;
import java.util.Arrays;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Cualificador {


  //Vector actions=new Vector();
  Relations r=new Relations();
  //Relations r_actions=new Relations();
  Relacional rel;
  Vector vars;
  Vector referencias;
  Vector ligas;
  miWeka arbol;

  private Vector generaNodos(String[] variables){
    Vector v=new Vector();
    for(int i=0; i<variables.length; i++)
      v.addElement(new SimpleNode(variables[i]));
    return v;
  }

  private Vector creaReferencias(String[][] datos) {
    Vector v = new Vector();
    for (int i = 0; i < datos.length; i++)
      v.addElement(new Ref(datos[i][0], Double.parseDouble(datos[i][1]),
                           Double.parseDouble(datos[i][2])));
    return v;
  }

  private void creaBaseDeRelaciones() {
    Link l;
    for (int i = 0; i < ligas.size(); i++){
      l=(Link)ligas.elementAt(i);
      r.addRel(l);
    }
  }


  private SimpleNode dameVariable(Ref referencia){
    SimpleNode aux, aux1=null;
    for(int i=0;i<vars.size();i++){
      aux=(SimpleNode)vars.elementAt(i);
      if(referencia.label.equals(aux.label))
        aux1=aux;
    }
    return aux1;
  }

  private Vector creaLigas() {
    Vector v = new Vector();
    Ref referencia;
    SimpleNode sn;
    int indice;
    for (int i = 0; i < referencias.size(); i++){

      referencia=(Ref)referencias.elementAt(i);
      sn=dameVariable( referencia);
      v.addElement(new Link(sn, referencia));
    }
    return v;
  }

  public Cualificador(String[][] datos) {

    instanciaVars(datos);
  }

  public Cualificador(String filename) {

    instanciaVars(tablas.fileToMatrix(filename,"\t") );
  }


  public void instanciaVars(String[][] datos){
    vars = generaNodos(Listas.conjunto(Matrices.dameColumna(datos, 0)));
    referencias = creaReferencias(datos);
    ligas = creaLigas();
    creaBaseDeRelaciones();
    rel = new Relacional(vars, r);

  }

  public Cualificador(Relations r, Vector vars) {
    this.vars=vars;
    this.r=r;
    rel = new Relacional(vars, r);
  }

  public Cualificador(miWeka arbol){
    this.arbol=arbol;
  }

  public Cualificador() {

   vars=new Vector();
// se establecen los nodos sin valor
    SimpleNode x = new SimpleNode("x");
    SimpleNode y = new SimpleNode("y");
    SimpleNode ang = new SimpleNode("ang");

    vars.addElement(x);
    vars.addElement(y);
    vars.addElement(ang);

// se crean las referencias
    Ref x1 = new Ref("x1", 748, 453.5);
    Ref x2 = new Ref("x2", 5242, 453.5);
    Ref x3 = new Ref("x3", 5831, 453.5);
    Ref x4 = new Ref("x4", 6751, 453.5);
    Ref y1 = new Ref("y1", 746, 458.55);
    Ref y2 = new Ref("y2", 2244, 458.55);
    Ref y3 = new Ref("qy3", 6758, 458.55);
    Ref y4 = new Ref("qy4", 8255, 458.55);
    Ref ang1 = new Ref("ang1", 90, 18.45);
    Ref ang2 = new Ref("ang2", 180, 18.45);
    Ref ang3 = new Ref("ang3", 270, 18.45);

// se crean las ligas
    Link qx1 = new Link(x, x1);
    Link qx2 = new Link(x, x2);
    Link qx3 = new Link(x, x3);
    Link qx4 = new Link(x, x4);
    Link qy1 = new Link(y, y1);
    Link qy2 = new Link(y, y2);
    Link qy3 = new Link(y, y3);
    Link qy4 = new Link(y, y4);
    Link qang1 = new Link(ang, ang1);
    Link qang2 = new Link(ang, ang2);
    Link qang3 = new Link(ang, ang3);

// se crea la base de relaciones cualitativas

    r.addRel(qang3);
    r.addRel(qang2);
    r.addRel(qang1);
    r.addRel(qy4);
    r.addRel(qy3);
    r.addRel(qy2);
    r.addRel(qy1);
    r.addRel(qx4);
    r.addRel(qx3);
    r.addRel(qx2);
    r.addRel(qx1);

    rel = new Relacional(vars, r);
  }

  public int[] cualifica(double[] estado){

    return rel.updateRelations(estado);
  }

  public int cualifica(String[] estado){

    String edo=arbol.consultaArbol(estado);
    return Integer.parseInt(edo.substring(1));
  }

  public static void main(String[] args){
/*
    String[][] datos=
   {{"ang3", "270", "18.45"},
    {"ang2", "180", "18.45"},
    {"ang1", "90", "18.45"},
    {"qy4", "8255", "458.55"},
    {"qy3", "6758", "458.55"},
    {"y2", "2244", "458.55"},
    {"y1", "746", "458.55"},
    {"x4", "6751", "453.5"},
    {"x3", "5831", "453.5"},
    {"x2", "5242", "453.5"},
    {"x1", "748", "453.5"}};
*/
String[][] datos=
{{"ang", "270", "18.45"},
{"ang", "180", "18.45"},
{"ang", "90", "18.45"},
{"y", "8255", "458.55"},
{"y", "6758", "458.55"},
{"y", "2244", "458.55"},
{"y", "746", "458.55"},
{"x", "6751", "453.5"},
{"x", "5831", "453.5"},
{"x", "5242", "453.5"},
{"x", "748", "453.5"}};


Cualificador c1=new Cualificador();
Cualificador c2=new Cualificador(datos);
Cualificador c3=new Cualificador("../ejemplos/crudos/varsCualitativas.txt");
Cualificador c4=new Cualificador("../ejemplos/robotNoObst/angDiscreto/referencias.txt");

String[][] states=tablas.fileToMatrix("../ejemplos/crudos/testStates.txt","\t");
Matrices.imprimeTabla(states);
/*
for(int i=0; i<states.length;i++)
  Listas.imprimeLista(c1.cualifica(Listas.stringToDouble(states[i])));


    for(int i=0; i<states.length;i++)
  Listas.imprimeLista(c3.cualifica(Listas.stringToDouble(states[i])));

*/


for(int i=0; i<states.length;i++)
  Listas.imprimeLista(c4.cualifica(Listas.stringToDouble(states[i])));

  }

}