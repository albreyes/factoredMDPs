package ia;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;
import elvira.parser.ParseException;
import elvira.potential.PotentialTable;
import elvira.Node;
import elvira.FiniteStates;
import ia.RBD;
import ia.PropagaRB;
import ia.ModeloTransicion;
import ia.Estados;
import aprendizaje.Instancias;
import aprendizaje.miWeka;
import aprendizaje.ValorDiscreto;
import utileria.*;
import ia.MarkovDecisionProcess;
import DataStructuresQ.Cualitativos;
import DataStructuresQ.QState;
import aprendizaje.Nodo;


/**
 * <p>Title: Clase FMDP</p>
 * <p>Description: Clase que conjunta metodos para la especificacion,
 * aprendizaje y solucion de un MDP factorizado</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Tec-IIE</p>
 * @author Alberto Reyes Ballesteros
 * @version 1.0
 */

public class FMDP implements Serializable{

  // modelo (esto se puede aprender)
    public int[][] s;
    public double[][][] modelo;
    public double[] reward;
    public String folder;


  // atributos de solucion
    public int[] politica;
    public double[] utilidad;

    public FMDP(){}

    // esto instancia un MDP regular.
    public FMDP(Variable[] vars, double[] recompensa, double[][][] modeloTranx){

      s=Estados.generaEstados(vars);
      reward=Listas.copy(recompensa);
      modelo=Matrices.copy(modeloTranx);
    }

    // esto instancia un mdp regular en base a una rbd, un arbol de decision
    // y el conjunto de variables de estado discretas

    public FMDP(String attributeFile, String[] netFiles) {

      s = Estados.generaEstados(Variable.getVars(attributeFile));

      File f = new File(attributeFile);
      folder=f.getParent();
      //String dTreeFile = f.getParent() + "/dTree.arff";
      String dTreeFile = folder + "/dTree.arff";

      reward = FMDP.generaFuncionRecompensa(dTreeFile, s);

      try {
        modelo = FMDP.modelo(netFiles);
      }
      catch (ParseException pe) {}
      catch (IOException ioe) {}
    }


    // esta funcion permite recuperar un arreglo con los valores de utilidad.
    // el indice es el numero de estado.

    public double[] getUtilidad(){
      return utilidad;
    }

    // esta version permite escibir el arbol de decision aprendido en un archivo
    // especificado como parametro. Es solo para uso interno.

    // esta es la version buena para el aprendizaje de MDPs
    // este metodo aprende un mdp factorizado discreto
    // recibe dos archivos, uno de ejemplos y otro de atributos, y devuelve
    // una lista con la ruta a las redes dinamicas de cada accion. Igualmente
    // escribe en un archivo los CPTs de las variables en el tiempo t+1 para
    // su posterior uso en SPUDD. (Nota se debe parsear este archivo para ser
    // completamente compatible con el SPUDD problem file).

public static String[] aprendeFMDP(String ejemplosCrudos,
                                   String atributosCrudos,
                                   int noAcciones,
                                   int SNumMaxParents
                                   ) {
  long time=System.currentTimeMillis();

  File f = new File(ejemplosCrudos);
  String arffFile = f.getParent() + "/dTree.arff";
  String OutputFolder = f.getParent();

  Instancias ex = new Instancias(ejemplosCrudos, atributosCrudos,
                                 arffFile);
  ex.generate_arffFormat();

  miWeka arbol = new miWeka(arffFile);

  // el conjunto de datos se separa para tener n conjunto de datos
  // donde n= noAcciones con el fin de aprender una RBD por accion

  Vector v = ex.separaDatosElvFormat( noAcciones);

  String[] red = RBD.aprendeRBD(v, OutputFolder, atributosCrudos,
                                SNumMaxParents);

  time=System.currentTimeMillis()-time;
  System.out.println("modelo aprendido en "+time+" ms");

  writeMDPspec(red,arbol,OutputFolder);

  return red;
}


public static void writeMDPspec(String[] red, miWeka arbol, String OutputFolder){
  formateaRedes(red);
    FileOutput fo = new FileOutput(OutputFolder + "/mdp.txt");
  try {
    for (int i = 0; i < red.length; i++) {
      System.out.println("accion " + i);
      fo.writeStringln("accion " + i);

      RBD rbd = new RBD(red[i]);

      rbd.cpts(fo);
    }
  }
  catch (ParseException pe) {}
  catch (IOException ioe) {}

  // tambien coloca la funcion de recompensa al final

  fo.writeStringln("reward function");
  fo.writeStringln(arbol.getFormattedTree());

  fo.close();

}

    // recibe etiquetas de las variables de estado y caracteristicas,
    // los valores del estado y caracteristicas vienen en forma de lista
    // de enteros y lista de listas de enteros respectivamente

    private static int factor(String[] varsEdo, int[] valoresEdo,
                             String[] varsCaract,
                             int[][] valoresCaract) {
      // obtener indices de caracteristicas en
      int[] aux = Listas.getIndexes(varsEdo, varsCaract);
      int[] vals = Listas.dameElementos(valoresEdo, aux);

      return Matrices.miembro(vals, valoresCaract);
    }

    private static int[] factor(String[] varsEdo, int[] valoresEdo,
                               String[] varsCaract) {
      // obtener indices de caracteristicas en
      int[] aux = Listas.getIndexes(varsEdo, varsCaract);
      int[] vals = Listas.dameElementos(valoresEdo, aux);

      return vals;
    }

    // funcion que compila una red bayesiana dinamica
    // recibe el archivo donde leer la red y devuelve un objeto ModeloTransicion
    // el indice en el vector de distribuciones en el objeto es el numero de estado.

    public static ModeloTransicion compilaModeloTranx(String netFilename) throws
        ParseException, IOException {

      RBD rbd = new RBD(netFilename);
      rbd.generaEstadosEvidencia();
      rbd.generaEstadosInteres();
      // el formateo lo puse por separado
      /*
            // formatea CPTs antes de propagar
            String[] vars = Listas.concatena(rbd.varsT, rbd.varsTmas1);
            for (int i = 0; i < vars.length; i++)
              rbd.red.formateaCPT(vars[i]);
              // guarda la red formateada
            rbd.red.guardaRed();
       */
      Vector v = rbd.compilaRedBayesiana();

      return new ModeloTransicion(rbd, v);
    }

    // genera una tabla normalizada de SxS mostrando las transiciones del estado
    // i (filas) al estado j (columnas). La tabla se construye en base a
    // caracteristicas. Aplica para una accion, recibe un objeto Modelo
    // Transicion.

    public static double[][] modeloXaccion(ModeloTransicion mt) {

      // lista de vars de estado
      String[] varsEstado = mt.rbd.dameNodos(0);
      /*
            System.out.println("vars de estado");
            Listas.imprimeLista(varsEstado);
       */
      // obtiene vector con numero de valores x variable de estado
      int[] valsEdo = new int[varsEstado.length];
      for (int i = 0; i < varsEstado.length; i++)
        valsEdo[i] = mt.rbd.red.getStatesNum(varsEstado[i]);

        // obtiene estados de instancia
      int[][] estados = Estados.generaEstados(valsEdo);

      // lista de variables de efectos
      String[] varsEfecto = mt.rbd.dameNodos(1);
      /*
            System.out.println("vars de efectos");
            Listas.imprimeLista(varsEfecto);
       */
      // obtiene vector con numero de valores x variable de efectos
      int[] valsEfecto = new int[varsEfecto.length];
      for (int i = 0; i < varsEfecto.length; i++)
        valsEfecto[i] = mt.rbd.red.getStatesNum(varsEfecto[i]);

        // se generan posibles estados resultantes
      int[][] estadosEfecto = Estados.generaEstados(valsEfecto);

      // se dimensiona el arreglo de retorno
      double[][] modeloXaccion = new double[estados.length][estadosEfecto.length];
      /*
            System.out.println("vars tiempo t ");
                Listas.imprimeLista(mt.rbd.varsT);
                System.out.println("vars tiempo t+1 ");
                Listas.imprimeLista(mt.rbd.varsTmas1);
       */
      // todos los estados de instancia vs todos los efectos
      for (int i = 0; i < estados.length; i++) {
        /*
                 System.out.println("estado "+i);
          Listas.imprimeLista(estados[i]);
         */
        // se obtiene el indice de la caracteristica del estado i
        int caract = factor(varsEstado, estados[i], mt.rbd.varsT,
                            mt.rbd.estadosEv);

        // con el indice se busca la distribucion de probabilidades del estado
        DistProb dp = (DistProb) (mt.distribuciones.elementAt(caract));
        /*
                  System.out.println("vector caract para edo "+i);
                  Listas.imprimeLista(dp.estado);
         */
        for (int j = 0; j < estadosEfecto.length; j++) {
          /*
                    System.out.println("estado sig ");
                    Listas.imprimeLista(estadosEfecto[j]);
           */
          // se obtiene la caracteristica del efecto
          int[] caractEfecto = factor(varsEfecto, estadosEfecto[j],
                                      mt.rbd.varsTmas1);
          /*
                    System.out.println("vector caract efectos ");
                    Listas.imprimeLista(caractEfecto);
           */
          // se guarda la probabilidad de transicion del estado
          modeloXaccion[i][j] = mt.getProbability(dp.estado, caractEfecto);

        }
      }

      //   System.out.println("no. estados i "+modeloXaccion.length);
      //   System.out.println("no. estados j "+modeloXaccion[0].length);
      //   Matrices.imprimeTabla(modeloXaccion);
      //   System.out.println("normalizada");

      modeloXaccion = Matrices.normalizaMatriz(modeloXaccion);
      //   Matrices.imprimeTabla(modeloXaccion);
      return modeloXaccion;

    }

    // esta funcion creo que hay que eliminarla
    public static void formateaRedes(String[] redes, Variable[] vars)throws ParseException,
        IOException{

    //  vars=Variable.getVarsRBD(vars);

      for(int i=0; i<redes.length; i++){
        PropagaRB red=new PropagaRB(redes[i]);
        red.formateaRed(vars);
        red.guardaRed();
      }
      System.out.println("Redes Formateadas !");
    }

    // modelo de transicion en formato tabular
    // recibe vector de objetos ModeloTransicion
    // devuelve matriz de transiciones en tabla 3D

    public static double[][][] modelo(String[] redes) throws ParseException,
        IOException {

      ModeloTransicion[] mts = new ModeloTransicion[redes.length];
      double[][][] modelo = new double[redes.length][][];

      for (int i = 0; i < redes.length; i++) {

        mts[i] = compilaModeloTranx(redes[i]);
        System.out.println(redes[i]);
          modelo[i]=modeloXaccion(mts[i]);
      }

      return modelo;
    }

    // transforma un arbol de decision en un arreglo conteniendo la funcion
    // de recompensa. El indice del arreglo corresponde al numero de estado
    // mientras que el elemento i es el valor de reward.

    public static double[] generaFuncionRecompensa(String dtreeFile,
                                                   int[][] estados) {

      double[] r = new double[estados.length];
      double[] stateDouble;
      miWeka arbol = new miWeka(dtreeFile);
    //  Listas.imprimeLista(arbol.getAtributos());

      for (int i = 0; i < estados.length; i++) {
        stateDouble = Listas.intToDouble(estados[i]);
     //   Listas.imprimeLista(arbol.int2StringState(estados[i]));
        r[i] = arbol.valorClase(stateDouble);
     //   System.out.println(r[i]);
      }

      return r;
    }

    // funcion para guardar un MDP en disco
    public static void saveMDP(FMDP fmdp){
      ESObjetos.escribeObjeto(fmdp,fmdp.folder + "/fmdp.obj");
    }

    // funcion para leer un de disco
    public static FMDP retrieveMDP(String path){
      FMDP fmdp=(FMDP) ESObjetos.leeObjeto(path);
      return fmdp;
    }

    // funcion que resulve el MDP con value iteration. devuelve la politica.
    public int[] solveMDP(double factorDesc, int noIt, double epsilon) {

      MarkovDecisionProcess mdp = new MarkovDecisionProcess(modelo, reward,
          factorDesc, noIt, epsilon);

      // instancia estos dos atributos
      politica = mdp.valueIteration();
      utilidad = mdp.utilidad;

      // con fines de dubbugeo

  //  Utileria.imprimeTabla(utilidad);
  //  Utileria.imprimeTabla(politica);

      return politica;
    }

    public static void formateaRedes(String[] redes){

      try{

        for(int r=0; r<redes.length; r++){
          RBD rbd = new RBD(redes[r]);
          String[] ListaVars = Listas.stringVectorToStringArray(rbd.red.
              dameNombreDeNodos());

          for (int i = 0; i < ListaVars.length; i++)
            rbd.red.formateaCPT(ListaVars[i]);

          rbd.red.guardaRed();
        }

      }
      catch(ParseException pe){}
      catch(IOException ioe){}
    }

    // Funcion de aprendizaje y solucion de MDPs factorizados
    public static FMDP resuelveFMDP(String[] redes,
                                    String atributosCrudos,
                                    String arbolFilename,
                                           double discountFactor,
                                           int maxIterationNum,
                                           double epsilon) {

        FMDP fmdp=new FMDP(atributosCrudos,redes);
        fmdp.solveMDP(discountFactor,maxIterationNum,epsilon);
         
        FMDP.saveMDP(fmdp);

        return fmdp;
    }


    // Funcion de aprendizaje y solucion de MDPs factorizados
    public static FMDP aprendeResuelveFMDP(String ejemplosCrudos,
                                           String atributosCrudos,
                                           int SNumMaxParents,
                                           int noAcciones,
                                           double discountFactor,
                                           int maxIterationNum,
                                           double epsilon) {

      String[] redes=aprendeFMDP( ejemplosCrudos,
                                        atributosCrudos,
                                        noAcciones,
                                        SNumMaxParents
                                       );

        FMDP fmdp=new FMDP(atributosCrudos,redes);

        fmdp.solveMDP(discountFactor,maxIterationNum,epsilon);
        Matrices.imprimeLista(fmdp.politica);
        FMDP.saveMDP(fmdp);
        return fmdp;
    }


    // Funcion de aprendizaje y solucion de MDPs factorizados.
    // Recibe datos continuos y genera discretizacion de acuerdo
    // con archivo atributos discretos. Puede haber atributos
    // discretos y continuos
    public static FMDP aprendeResuelveFMDPCont_Dis(String ejemplosContinuos,
                                           String atributosContinuos,
                                           String atributosDiscretos,
                                           int SNumMaxParents,
                                           int noAcciones,
                                           double discountFactor,
                                           int maxIterationNum,
                                           double epsilon) {

    String ejemplosDiscretos=ValorDiscreto.discretAuto(ejemplosContinuos,
                  atributosContinuos,
                  atributosDiscretos);

    return aprendeResuelveFMDP(ejemplosDiscretos,
                                       atributosDiscretos,
                                       SNumMaxParents,
                                       noAcciones,
                                       discountFactor,
                                       maxIterationNum,
                                       epsilon);
    }

    // Funcion de aprendizaje y solucion de MDPs factorizados.
    // Recibe datos continuos y genera cualificacion de acuerdo
    // con umbral. Puede haber atributos discretos y continuos

    public static void aprendeResuelveFMDPCont_Cualitativos(String ejemplos,
                                           String atributos,
                                           double umbral,
                                           int SNumMaxParents,
                                           int noAcciones,
                                           double discountFactor,
                                           int maxIterationNum,
                                           double epsilon) {

   String[] files=Cualitativos.cualificaAuto(ejemplos,
                 atributos,umbral);

   String[] red =FMDP.aprendeFMDP(files[0], // ejemplos
                                  files[1], // atributos
                                  noAcciones,
                                  SNumMaxParents);

/* Esta linea se usara en lugar de la anterior en cuanto el modelo este refinado

    return aprendeResuelveFMDP(files[0],
                                       files[1],
                                       SNumMaxParents,
                                       noAcciones,
                                       discountFactor,
                                       maxIterationNum,
                                       epsilon);
 */
    }


    // Funcion de aprendizaje y solucion de MDPs factorizados.
    // Recibe datos continuos y genera cualificacion de acuerdo
    // . Puede haber atributos discretos y continuos

    public static FMDP aprendeResuelveFMDPCont_Cualitativos(String ejemplos,
                                           String atributos,
                                           String atributosDisc,
                                           int SNumMaxParents,
                                           int noAcciones,
                                           double discountFactor,
                                           int maxIterationNum,
                                           double epsilon) {

        String ejemplosDiscretos=ValorDiscreto.discretAuto(ejemplos,
                                                       atributos,
                                                       atributosDisc);


   String[] files=Cualitativos.cualificaAuto(ejemplosDiscretos,
                 atributosDisc);

    return aprendeResuelveFMDP(files[0],
                                       files[1],
                                       SNumMaxParents,
                                       noAcciones,
                                       discountFactor,
                                       maxIterationNum,
                                       epsilon);

    }


    // esta funcion es para resolver MDPs ya particionados
    public static FMDP aprendeResuelveFMDPCont_Cualitativos(String ejemplos,
                                           String atributos,
                                           String atributosDisc,
                                           miWeka arbol,
                                           int SNumMaxParents,
                                           int noAcciones,
                                           double discountFactor,
                                           int maxIterationNum,
                                           double epsilon) {

        String ejemplosDiscretos=ValorDiscreto.discretAuto(ejemplos,
                                                       atributos,
                                                       atributosDisc);


   String[] files=Cualitativos.cualificaAuto(ejemplosDiscretos,
                 atributosDisc,arbol);


    return aprendeResuelveFMDP(files[0],
                                       files[1],
                                       SNumMaxParents,
                                       noAcciones,
                                       discountFactor,
                                       maxIterationNum,
                                       epsilon);

    }


    public int consultaPolitica(int[] edo){
      int noEstado=Matrices.indiceSublista(s,edo);
   //   System.out.println("estado num: "+noEstado);
      return politica[noEstado];
    }

    // Imprime datos de un fmdp. Recomendable usar en espacion pequenos.
    public static void imprimeResultados(FMDP fmdp){

      for(int a=0; a<fmdp.modelo.length; a++){
        System.out.println("modelo accion "+a);
        Matrices.imprimeTabla(fmdp.modelo[a]);
        tablas.matrixToFile("modeloAccion"+a, fmdp.modelo[a]);
      }

      System.out.println("politica optima");
      for(int i=0; i<fmdp.s.length; i++){
        System.out.println("recompensa "+fmdp.reward[i]);
        System.out.println("utilidad "+fmdp.getUtilidad()[i]);
        System.out.print("estado ");
        Listas.imprimeLista(fmdp.s[i]);
        System.out.println("accion " + fmdp.politica[i]);
      }

      
    }
    public static void imprimeResultados(FMDP fmdp, String path){

      double[][] recompensaSXA=new double[fmdp.s.length][fmdp.modelo.length];
      
      for(int a=0; a<fmdp.modelo.length; a++){
        System.out.println("modelo accion "+a);
        Matrices.imprimeTabla(fmdp.modelo[a]);
        tablas.matrixToFile(path+"modeloAccion"+a+".csv", fmdp.modelo[a]);
        
        for(int s=0;s<fmdp.s.length;s++)
        recompensaSXA[s][a]= fmdp.reward[s];
      }

  //    Matrices.imprimeTabla(recompensaSXA);
      tablas.matrixToFile(path+"recompensaSXA.csv", recompensaSXA);
       tablas.matrixToFile(path+"catalogoEstados.csv", fmdp.s);
    //  System.out.println("politica optima");
    /*
       for(int i=0; i<fmdp.s.length; i++){
        System.out.println("recompensa "+fmdp.reward[i]);
        System.out.println("utilidad "+fmdp.getUtilidad()[i]);
        System.out.print("estado ");
        Listas.imprimeLista(fmdp.s[i]);
        System.out.println("accion " + fmdp.politica[i]);
      }
*/
            // forma funciones de politica y utilidad esperada
      double[][] resultados=new double[fmdp.s.length][2];
      for(int i=0; i<fmdp.s.length; i++){
          resultados[i][0]=fmdp.politica[i];
          resultados[i][1]=fmdp.utilidad[i];
      }
      tablas.matrixToFile(path+"resultados.csv", resultados);
      
      
    }
      // esta funcion actualiza el arbol de estados

      private static void parteEstado(QState[] estados, QState aPartir,
                                      miWeka arbol, String[][] atributos) {

        Nodo nodo = arbol.getNodo("q" + aPartir.id);
        Vector enFrontera = aPartir.enFrontera(estados);
        QState mayorDif = aPartir.mayorDiferencia(enFrontera);
        int dim = aPartir.dimParticion(aPartir.mayorDiferencia(enFrontera));
        Variable var = aPartir.restricciones[dim];
        arbol.bisectaNodo(nodo, var, atributos);
        arbol.reetiquetaHojas();
      }


      public static FMDP refinaMDP(String ejemplos,
                                   String atributosF,
                                   String atributosDisc,
                                   int SNumMaxParents,
                                   int noAcciones,
                                   double discountFactor,
                                   int maxIterationNum,
                                   double epsilon,
                                   int ITERACIONES
                                   ) {

        long time=System.currentTimeMillis();

        File f = new File(ejemplos);
        String folder = f.getParent();

        miWeka arbol = new miWeka(folder + "/dTreeCont.arff");
     //   System.out.println("La funcion de recompensa inicial a refinar es : ");
     //   System.out.println(arbol.getTree());
     //   System.out.println("La particion inicial a refinar es : ");
     //   Nodo.displayTreeConsole(arbol.nodo, "  ");
        String[][] atributos = tablas.fileToMatrix(atributosF, ":,\t ");

        FMDP fmdp = (FMDP) ESObjetos.leeObjeto(folder + "/fmdp.obj");
        Vector agenda=new Vector();

        for (int i = 0; i < ITERACIONES; i++) {

         QState[] estados = QState.getQStates(fmdp, arbol.getConstrains(),atributos);

         // solo para impresion de informacion
      //   System.out.println("Los estados q del sistema en la iteracion "+i+" son:");
      //   for(int x=0; x<estados.length; x++)
      //     estados[x].imprimeDatos();

         QState aPartir = aPartir(estados, atributos, agenda);

         System.out.println("\nde estos, el estado a partir es :");
         aPartir.imprimeDatos();

          if (aPartir == null)
            break;

          parteEstado(estados, aPartir, arbol, atributos);


     //     System.out.println("La funcion de recompensa despues del corte "+(i+1)+" es:");
     //     System.out.println(arbol.getTree());
     //     System.out.println("La particion despues del corte "+(i+1)+" es:");
     //     Nodo.displayTreeConsole(arbol.nodo, "  ");



          fmdp = aprendeResuelveFMDPCont_Cualitativos(ejemplos,
                                                      atributosF,
                                                      atributosDisc,
                                                      arbol,
                                                      SNumMaxParents,
                                                      noAcciones,
                                                      discountFactor,
                                                      maxIterationNum,
                                                      epsilon);

         // fmdp=aux;

          System.out.println("ITERACION "+i);

        }
        time=System.currentTimeMillis()-time;
        System.out.println("refinamiento en "+time+" ms con "+ITERACIONES+" cortes");

        ESObjetos.escribeObjeto(arbol.nodo, folder + "/particion.obj");
        FMDP.saveMDP(fmdp);
        return fmdp;
      }


      public static FMDP refinaMDPGreedy(String ejemplos,
                                   String atributosF,
                                   String atributosDisc,
                                   int SNumMaxParents,
                                   int noAcciones,
                                   double discountFactor,
                                   int maxIterationNum,
                                   double epsilon, int ITERACIONES
                                   ) {

        long time=System.currentTimeMillis();

        File f = new File(ejemplos);
        String folder = f.getParent();

        miWeka arbol = new miWeka(folder + "/dTreeCont.arff");
        String[][] atributos = tablas.fileToMatrix(atributosF, ":,\t ");

        FMDP fmdp = (FMDP) ESObjetos.leeObjeto(folder + "/fmdp.obj");
        Vector agenda=new Vector();

        int noCortes=0;

        for (int i = 0; i < ITERACIONES; i++) {

         // QState[] estados = QState.getQStates(fmdp, arbol);
         QState[] estados = QState.getQStates(fmdp, arbol.getConstrains(),atributos);
          // enviar agenda de ids de Qstates para no seleccionarlo
          QState aPartir = aPartir(estados, atributos, agenda);

         ESObjetos.escribeObjeto(arbol.nodo, folder + "/particion.obj");
         ESObjetos.escribeObjeto(fmdp, folder + "/fmdpparcial.obj");

          if (aPartir == null)
            break;

          //System.out.println("partiendo estado q"+aPartir.id);
          parteEstado(estados, aPartir, arbol, atributos);


          FMDP aux = aprendeResuelveFMDPCont_Cualitativos(ejemplos,
                                                      atributosF,
                                                      atributosDisc,
                                                      arbol,
                                                      SNumMaxParents,
                                                      noAcciones,
                                                      discountFactor,
                                                      maxIterationNum,
                                                      epsilon);


          // si mejora la cosa
          if(aux.politica[aPartir.id]!=aux.politica[aPartir.id+1]){
          System.out.println("cambia la politica al partir "+aPartir.id);
          actualizaAgenda( aPartir.id,  agenda);
          System.out.println("contenido de la agenda :");
          Listas.imprimeLista(agenda);
          fmdp=aux;
                    noCortes++;
          }
          else{
            // hay q componer esto
            System.out.println("no mejora la politica al partir "+aPartir.id);
            arbol.nodo = (Nodo)ESObjetos.leeObjeto(folder + "/particion.obj");
            arbol.nodeList=arbol.nodeList();
            // agregar identificador de QState aPartir a agenda .. checar consistencia del id
            System.out.println("agregando qstate a agenda de revisados"+aPartir.id);
            agenda.addElement(new Integer(aPartir.id));
            fmdp=(FMDP)ESObjetos.leeObjeto(folder + "/fmdpparcial.obj");
          }

          System.out.println("ITERACION "+i);

        }
        time=System.currentTimeMillis()-time;
        System.out.println("refinamiento en "+time+" ms con "+noCortes+" cortes");

        ESObjetos.escribeObjeto(arbol.nodo, folder + "/particion.obj");
        FMDP.saveMDP(fmdp);
        return fmdp;
      }


      // considera los estados revisados
      private static QState aPartir(QState[] estados, String[][] atributos, Vector agenda) {

        QState aPartir = null;
        long ref = QState.menorHiperVolumen(estados, atributos);
        QState menorHipervolumen=QState.menorQState(estados, atributos);

        double maxVarianza = Double.MIN_VALUE;

        for (int j = 0; j < estados.length; j++) {

          Vector region = estados[j].region(estados);
          double varianza = QState.varianza(region);
          long hipervol = QState.hiperVolumen(estados[j], atributos);


        Vector enFrontera = estados[j].enFrontera(estados);
        int dim = estados[j].dimParticion(estados[j].mayorDiferencia(enFrontera));
       // System.out.println("num de restricciones "+estados[j].restricciones.length);
        Variable var = estados[j].restricciones[dim];
        int dimActual=(var.max-var.min);
        Variable varMenor=menorHipervolumen.restricciones[dim];
        int dimMenor=varMenor.max-varMenor.min;

          // checar que j no este en la agenda
          if(!Listas.miembro(new Integer(j),agenda))
          if (varianza > maxVarianza && hipervol >= 2 * ref && dimActual>=2*dimMenor) { //
            maxVarianza = varianza;
            aPartir = estados[j];
          }
        }
        return aPartir;
      }

      public static void actualizaAgenda(int aPartir, Vector agenda){
        int entero;
        for(int i=0; i<agenda.size(); i++){
          entero=((Integer)agenda.elementAt(i)).intValue();
          entero++;
          if(entero>aPartir)
            agenda.setElementAt(new Integer(entero),i);
        }
      }

  public static void main(String[] args) throws ParseException, IOException {


    // Prueba de refinamiento de funcion de valor

 //   String ejemplosCrudos="../ejemplos/russell/20metasN/exploracion.dat";
 //   String atributosCrudos="../ejemplos/russell/20metasN/atributos.txt";

/*
    String ejemplosCrudos="../ejemplos/Carpeta nueva/exploration.dat";
    String atributosCrudos="../ejemplos/Carpeta nueva/atributos.txt";

    int SNumMaxParents=10;
    int noAcciones=5;
    double discountFactor=0.9;
    int maxIterationNum=500;
    double epsilon=1e-5;
    int etapas=20;

    FMDP.aprendeResuelveFMDPCont_Cualitativos(ejemplosCrudos,
                                               atributosCrudos,
                                               atributosCrudos,
                                                SNumMaxParents,
                                                noAcciones,
                                                discountFactor,
                                                maxIterationNum,
                                                epsilon);


    FMDP.refinaMDP(  ejemplosCrudos,
                                            atributosCrudos,
                                            atributosCrudos,
                                            SNumMaxParents,
                                            noAcciones,
                                            discountFactor,
                                            maxIterationNum,
                                            epsilon, etapas);


*/

    // Ejemplo del robot de stage con cualificacion de datos usando vars
    // cualitativas

    double umbral=0.05;
    String ejemRobotStage0="../ejemplos/playerStage/cualitativos-ver0/ejemplosDis.txt";
    String atribRobotStage0="../ejemplos/playerStage/cualitativos-ver0/atributos.txt";

    FMDP.aprendeResuelveFMDPCont_Cualitativos(ejemRobotStage0, atribRobotStage0,
                                              umbral,
                                              10, 3, 0.9, 500, 1e-5);


    // Ejemplo del robot de stage con cualificacion de datos usando las ramas
    // como estados


    String ejemRobotStage="../ejemplos/robot/ramasQ/ejemplosCont.txt";
    String atribRobotStage="../ejemplos/robot/ramasQ/atribCont.txt";
    String atribDisRobotStage="../ejemplos/robot/ramasQ/atribContDisc.txt";

    FMDP robotStage = FMDP.aprendeResuelveFMDPCont_Cualitativos(ejemRobotStage,
        atribRobotStage, atribDisRobotStage,
        10, 3, 0.9, 500, 1e-5);

    //FMDP.imprimeResultados(robotStage);


    // un ejemplo simple de russell con la red hecha a mano

    String[] redes0 = {
        "../ejemplos/russell/manualmente/a0.elv",
        "../ejemplos/russell/manualmente/a1.elv",
        "../ejemplos/russell/manualmente/a2.elv",
        "../ejemplos/russell/manualmente/a3.elv"};

    String atbts0 = "../ejemplos/russell/manualmente/atributos.txt";
    String arbol = "../ejemplos/russell/manualmente/dTree.arff";

    FMDP fmdp0 = resuelveFMDP(redes0,
                              atbts0,
                              arbol,
                              0.9,
                              500,
                              1e-5);

   // FMDP.imprimeResultados(fmdp0);


    // un ejemplo simple de russell con aprendizaje

    String ejemplos = "../ejemplos/russell/ejemplos.txt";
    String atbts = "../ejemplos/russell/atributos.txt";

    FMDP fmdp=FMDP.aprendeResuelveFMDP(ejemplos,atbts,10,4,0.9,500,1e-5);
    FMDP.imprimeResultados(fmdp);

    // un ejemplo de russell con datos continuos

    String ejemRussellCont="../ejemplos/russell/20Metas/exploracion.dat";
    String atribRussellCont="../ejemplos/russell/20Metas/atributos.txt";

    FMDP russellCont = FMDP.aprendeResuelveFMDPCont_Cualitativos(ejemRussellCont,
        atribRussellCont, atribRussellCont,
        10, 4, 0.9, 500, 1e-5);

    FMDP.imprimeResultados(russellCont);


    // un ejemplo de aprendizaje y solucion de MDP en el dominio de plantas electricas

      String ejemplosCrudos="../ejemplos/powerPlant/version0/ejemplos.txt";
      String atributosCrudos="../ejemplos/powerPlant/version0/atributos.txt";
      int SNumMaxParents=10;
      int noAcciones=9;
      double discountFactor=0.9;
      int maxIterationNum=100;
      double epsilon=1e-5;

      FMDP.aprendeResuelveFMDP(ejemplosCrudos, atributosCrudos, SNumMaxParents,
                               noAcciones, discountFactor, maxIterationNum,
                               epsilon);

        // un ejemplo con discretizacion automatica para el caso del robot
        // de player stage

        String ejemplosContinuos="../ejemplos/robot/discreto/ejemplosCont.txt";
        String atributosContinuos="../ejemplos/robot/discreto/atribCont.txt";
        String ats="../ejemplos/robot/discreto/atributos.txt";

        FMDP.aprendeResuelveFMDPCont_Dis(ejemplosContinuos, atributosContinuos,
                                         ats,
                                         10,
                                         3,
                                         0.9,
                                         500,
                                         1e-5);



  }


}