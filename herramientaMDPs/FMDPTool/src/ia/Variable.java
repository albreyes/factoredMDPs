package ia;

import java.util.Vector;
import utileria.Matrices;
import utileria.Listas;
import utileria.tablas;
import aprendizaje.miWeka;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Variable {

  String nombre;
  Vector valores;
  String[] values;
  public int valor;
  public int min, max;
  public String relacion;

  public Variable(String nombre, int valor){
  this.nombre=nombre;
  valores=new Vector();
}

  public Variable(String nombre, int min, int max){
    this.nombre=nombre;
    this.min=min;
    this.max=max;
  }

  public Variable(String nombre, String relacion, int valor){
    this.nombre=nombre;
    this.relacion=relacion;
    this.valor=valor;
  }

// genera una lista de variables aumentada para tener vars en tiempo t y t+1
public static Variable[] getVarsRBD(Variable[] vars){

  Variable[] varsN=new Variable[vars.length*2];
  for(int i=0; i<varsN.length; i++)
    if(i<vars.length)
      varsN[i]=vars[i];
      else {
        String[] valores=Listas.copy(vars[i - vars.length].values);
        varsN[i] = new Variable(vars[i - vars.length].getName() + "_prime",
                                valores);
      }
    return varsN;
}

  public static Variable getVariable(String nombre, Variable[] lista){
    Variable var=null;

    for(int i=0; i<lista.length; i++)
      if(nombre.equals(lista[i].getName())){
        var=lista[i];
        break;
      }
    return var;
  }

  public static String[][] toTable(Vector v){

    String[][] arreglo=new String[v.size()][3];

    for (int i = 0; i < v.size(); i++) {
      Variable var = (Variable) (v.elementAt(i));
      arreglo[i][0]=var.getName();
      arreglo[i][1]=var.relacion;
      arreglo[i][2]=""+var.valor;
    }
    return arreglo;
  }

public static Variable[] getVars(String atributteFile) {

  String atributos[][] = tablas.fileToMatrix(atributteFile, "\t,:");
  atributos = Matrices.eliminaFila(atributos, atributos.length - 1);

  Variable[] vars = new Variable[atributos.length];

  for (int i = 0; i < atributos.length; i++) {

    String name = atributos[i][0];
    String[] valores = new String[atributos[i].length - 1];

    for (int j = 1; j < atributos[i].length; j++)
      valores[j - 1] = atributos[i][j];

    vars[i] = new Variable(name, valores);

  }
  return vars;
}

  public Variable(String nombre, Vector vals) {
    this.nombre=nombre;

    valores=new Vector();
    for(int i=0; i<vals.size();i++)
      valores.addElement(vals.elementAt(i));
  }

  public Variable(String nombre, String[] vals) {
    this.nombre=nombre;

    values=new String[vals.length];
    for(int i=0; i<vals.length;i++)
      values[i]=vals[i];
  }


  public String getName(){
    return nombre;
  }

  public Vector getValores(){
    return valores;
  }

  public static void main(String[] args) {
    Variable vars[];
    vars=Variable.getVars("C:/Documents and Settings/xperto/Escritorio/powerPlant/atributos.txt");

    for(int i=0; i<vars.length; i++){
      System.out.println(vars[i].getName());
      Matrices.imprimeLista(vars[i].values);
    }
  }

}