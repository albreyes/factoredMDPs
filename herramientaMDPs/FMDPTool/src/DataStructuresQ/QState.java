package DataStructuresQ;

import ia.Estado;
import ia.Variable;
import ia.FMDP;
import utileria.ESObjetos;
import aprendizaje.miWeka;
import utileria.Listas;
import utileria.tablas;
import utileria.Matrices;
import java.util.Vector;
import java.util.ArrayList;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class QState extends Estado{

  public Variable[] restricciones;
  int politica;
  double reward;
  double utilidad;
  QState[] estados;

  public  QState(int id, Variable[] restricciones, double reward, double utilidad, int politica) {
    super(id);
    this.restricciones=(Variable[])restricciones.clone();
    this.reward=reward;
    this.utilidad=utilidad;
    this.politica=politica;
  }

  public Vector enFrontera(QState[] estados){

    Vector edosQ=new Vector();
    for(int i=0; i<estados.length; i++)
      if(enFrontera(estados[i])&&!estados[i].equals(this))
        edosQ.addElement(estados[i]);

    return edosQ;
  }

  public Vector region(QState[] estados){
    Vector edosQ=new Vector();
    for(int i=0; i<estados.length; i++)
      if(enFrontera(estados[i]))
        edosQ.addElement(estados[i]);

    return edosQ;
  }

  public static double media(Vector region){
    double suma=0;
    for(int i=0; i<region.size(); i++)
      suma+=((QState)region.elementAt(i)).utilidad;
    return suma/region.size();
  }

  public static double varianza(Vector region){

    double media=media(region);
    //System.out.println("media "+media);

    double suma=0;
    for(int i=0; i<region.size(); i++)
      suma+=Math.pow(((QState)region.elementAt(i)).utilidad-media, 2);

    return suma/region.size();
  }

  public QState mayorDiferencia(Vector enFrontera){

    double mayorDif=Double.MIN_VALUE;
    QState qstate=null;

    for(int i=0; i<enFrontera.size(); i++){

      double dif=Math.abs ( ((QState) enFrontera.elementAt(i)).utilidad-utilidad);

      if (dif>mayorDif){
        mayorDif=dif;
        qstate=(QState) enFrontera.elementAt(i);
      }
    }
    return qstate;
  }

  public  int dimParticion(QState qstate){

    int[] enIntervalo=enIntervalo(qstate);
    int[] comparte=comparte(qstate);

   // Listas.imprimeLista(enIntervalo);
   // Listas.imprimeLista(comparte);

    comparte=Listas.eliminaElementos(enIntervalo,comparte);


    int[] noCompartidas = new int[qstate.restricciones.length];
    for (int i = 0; i < qstate.restricciones.length; i++)
      noCompartidas[i] = i;

    noCompartidas=Listas.eliminaElementos(comparte,noCompartidas);


    // cuando no hay dimensiones compartidas devuelve cualquier dimension
    if (noCompartidas.length == 0) {
      int atributo = (int) (Math.random() * noCompartidas.length);
      return atributo;
    }
    // cuando hay varias dimensiones compartidas devuelve alguna de ellas
    else if (noCompartidas.length >  1) {
      int atributo = (int) (Math.random() * noCompartidas.length);
      return noCompartidas[atributo];
    }
    else
      // cuando hay solo un atributo compartido ese es el que devuelve
      return noCompartidas[0];
  }


/*
  public  int dimParticion(QState qstate){

    int[] enIntervalo=enIntervalo(qstate);
    int[] comparte=comparte(qstate);

    comparte=Listas.eliminaElementos(enIntervalo,comparte);


/*
             int[] resp=new int[qstate.restricciones.length];
        for(int i=0; i<qstate.restricciones.length; i++)
          resp[i]=i;

          comparte=Listas.eliminaElemento(comparte,resp);

*/
    // en caso de haber varias dimensiones compartidas se elige una
    // aleatoriamente
/*
    if (comparte.length >  1) {
      int atributo = (int) (Math.random() * comparte.length);

      return comparte[atributo];
    }
    else
      return comparte[0];
  }
*/
  public void imprimeDatos() {
    String s = "id " + id + " politica " + politica + " utilidad " + utilidad +
        " recompensa " + reward;
    System.out.println(s);
  }

  public static void imprimeDatos(QState qstate){
    qstate.imprimeDatos();
  }

  public boolean enFrontera(QState candidato){

    int[] enIntervalo=enIntervalo(candidato);
    int[] comparte=comparte(candidato);

    comparte=Listas.eliminaElementos(enIntervalo,comparte);

    if((enIntervalo.length+comparte.length)==restricciones.length){
      return true;
    }
    else return false;
  }

/*
  int[] comparte2(QState estadoQcandidato) {

    ArrayList al = new ArrayList();
    for (int d = 0; d < restricciones.length; d++)

           // caso 1. |<--->|
      if ( (estadoQcandidato.restricciones[d].min >= restricciones[d].min
            && estadoQcandidato.restricciones[d].max <= restricciones[d].max) ||
           // caso 2. <--->
          (estadoQcandidato.restricciones[d].min < restricciones[d].min
           && estadoQcandidato.restricciones[d].max > restricciones[d].max) ||
           // caso 3. |<--->
          (estadoQcandidato.restricciones[d].min >= restricciones[d].min
          && estadoQcandidato.restricciones[d].max > restricciones[d].max) ||
           // caso 4. <--->|
          (estadoQcandidato.restricciones[d].min < restricciones[d].min
          && estadoQcandidato.restricciones[d].max <= restricciones[d].max))

        al.add(new Integer(d));

    return Listas.toIntArray(al);
  }

*/

  int[] enIntervalo(QState estadoQcandidato) {

    ArrayList al = new ArrayList();
    for (int d = 0; d < restricciones.length; d++)

      // caso 1. |<--->| o <--->
      if ( (estadoQcandidato.restricciones[d].min >= restricciones[d].min
            && estadoQcandidato.restricciones[d].max <= restricciones[d].max) ||
          (estadoQcandidato.restricciones[d].min <= restricciones[d].min
           && estadoQcandidato.restricciones[d].max >= restricciones[d].max))

        al.add(new Integer(d));
      // caso 2. <--|(min)--> o <--|(max)-->
      else if((estadoQcandidato.restricciones[d].min < restricciones[d].min
              && estadoQcandidato.restricciones[d].max > restricciones[d].min) ||
            (estadoQcandidato.restricciones[d].min < restricciones[d].max
             && estadoQcandidato.restricciones[d].max > restricciones[d].max)
)
        al.add(new Integer(d));
    return Listas.toIntArray(al);
  }


  int[] comparte(QState estadoQcandidato) {

    ArrayList al = new ArrayList();
      for (int d = 0; d < restricciones.length; d++)
        if (estadoQcandidato.restricciones[d].min == restricciones[d].min
            || estadoQcandidato.restricciones[d].max == restricciones[d].max
            || estadoQcandidato.restricciones[d].max == restricciones[d].min
            || estadoQcandidato.restricciones[d].min == restricciones[d].max
            )
          al.add(new Integer(d));
      return Listas.toIntArray( al);
  }

  public static long menorHiperVolumen(QState[] qstates, String[][] atributos){
    long menor=Long.MAX_VALUE;

    for(int i=0; i<qstates.length; i++){
      long hv=hiperVolumen( qstates[i],  atributos);
      if(hv<menor)
        menor=hv;
    }
    return menor;

  }

  public static QState menorQState(QState[] qstates, String[][] atributos){
    long menor=Long.MAX_VALUE;
    QState menorHV=null;

    for(int i=0; i<qstates.length; i++){
      long hv=hiperVolumen( qstates[i],  atributos);
      if(hv<menor){
        menor = hv;
        menorHV=qstates[i];
      }
    }
    return menorHV;


  }


  public static long hiperVolumen(QState qstate, String[][] atributos){

    long producto=1;
    for(int i=0; i<qstate.restricciones.length; i++){
      int indice = Listas.indice(Matrices.dameColumna(atributos, 0),
                                 qstate.restricciones[i].getName());

      int min = Integer.parseInt(atributos[indice][2]);
      int max = Integer.parseInt(atributos[indice][3]);

      if (qstate.restricciones[i].min > min)
        min = qstate.restricciones[i].min;
      if (qstate.restricciones[i].max < max)
        max = qstate.restricciones[i].max;

      producto *= (max - min);

    }
  return producto;
  }

/* igual y luego sirve
  public static int menorMagnitud(QState qstate, String[][] atributos){

    int menor=Integer.MAX_VALUE;
    for(int i=0; i<qstate.restricciones.length; i++){
      int indice = Listas.indice(Matrices.dameColumna(atributos, 0),
                                 qstate.restricciones[i].getName());

      int min = Integer.parseInt(atributos[indice][2]);
      int max = Integer.parseInt(atributos[indice][3]);

      if (qstate.restricciones[i].min > min)
        min = qstate.restricciones[i].min;
      if (qstate.restricciones[i].max < max)
        max = qstate.restricciones[i].max;

      if((max - min) < menor)
      menor= (max - min);

    }
  return menor;
  }
*/

  public static QState[] getQStates(String folder){

    FMDP fmdp = (FMDP) ESObjetos.leeObjeto(folder + "/fmdp.obj");
    miWeka arbol = new miWeka(folder + "/dTreeCont.arff");

    return getQStates( fmdp,  arbol);
  }


// solo aplica en casos donde la funcion de recompensa cubra
// todo el espacio de variables. ejemplo: robot de russell

public static QState[] getQStates(FMDP fmdp, miWeka arbol) {

  Vector constraint = arbol.getConstrains();
  QState[] resp = new QState[constraint.size()];
  for (int i = 0; i < resp.length; i++)
    resp[i] = new QState(i, (Variable[]) constraint.elementAt(i),
                         fmdp.reward[i], fmdp.utilidad[i], fmdp.politica[i]);
  return resp;
}

// probemos con este mejor
public static QState[] getQStates(FMDP fmdp, Vector constraint, String[][] atributos) {

  // primero checa que las restricciones contengan todas las variables
  checkConstraints( fmdp,  constraint,
                                     atributos);
  QState[] resp = new QState[constraint.size()];
  for (int i = 0; i < resp.length; i++)
    resp[i] = new QState(i, (Variable[]) constraint.elementAt(i),
                         fmdp.reward[i], fmdp.utilidad[i], fmdp.politica[i]);
  return resp;
}


// modificado para inlcuir variables sin restricciones,
// actializa vector constraints

private static void checkConstraints(FMDP fmdp, Vector constraints,
                                     String[][] atributos) {

  for (int i = 0; i < constraints.size(); i++) {
    Variable[] actual = (Variable[]) constraints.elementAt(i);
    if (actual.length != atributos.length - 1) {
      // enviar actual y restriccion para ajustar variable
      constraints.setElementAt(complementConstraint(actual, atributos), i);
      // el nuevo objeto colocarlo en la posicion anterior con setElement
    }

  }
}

// regresa null si no es miembro o el elemento si si
private static Variable miembro(String atributo, Variable[] restricciones){

  Variable res=null;
  for(int i=0; i<restricciones.length; i++)
    if(atributo.equals(restricciones[i].getName())){
      res = restricciones[i];
      break;
    }
    return res;
}

  private static Variable[] complementConstraint(Variable[] restricciones,
                                                 String[][] atributos) {
    Variable[] res = new Variable[atributos.length - 1];
    for (int i = 0; i < res.length; i++) {
      Variable var = miembro(atributos[i][0], restricciones);
      // si atributo i es miembro de restricciones
      if (var != null) {
        // entonces copia elemento restricciones i en res i
        res[i] = var;
      }
      // de lo contrario crea un nuevo objeto Variable con atributos[i][0] (nombre)
      // atributos[i][2] (min) atributos[i][3] (max) y asignarlo en res i
      else
        res[i] = new Variable(atributos[i][0], Integer.parseInt(atributos[i][2]),
                              Integer.parseInt(atributos[i][3]));

    }
    return res;
  }

  public static void main(String[] args) {

    QState[] estados = QState.getQStates("../ejemplos/russell/20metasN");
    String[][] atributos=tablas.fileToMatrix("../ejemplos/russell/20metasN/atributos.txt",":,/t ");

    for(int j=0; j<estados.length; j++){

      Vector enFrontera = estados[j].enFrontera(estados);
      Vector region = estados[j].region(estados);
      System.out.println("Frontera de Q"+estados[j].id);


      for (int i = 0; i < enFrontera.size(); i++){
        QState.imprimeDatos( (QState) enFrontera.elementAt(i));

      }

      System.out.println("varianza "+QState.varianza(region));
      System.out.println("utilidad "+estados[j].utilidad);
      System.out.println("estado con mayor diiferencia de utilidad ");
      estados[j].mayorDiferencia(enFrontera).imprimeDatos();
      System.out.println("partir sobre dim ");
      System.out.println(estados[j].dimParticion(estados[j].mayorDiferencia(enFrontera)));
    }

      }


}