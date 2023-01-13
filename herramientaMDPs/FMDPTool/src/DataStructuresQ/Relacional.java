package DataStructuresQ;

import java.util.Vector;
import utileria.Listas;

//import DataStructuresQ.*;
/**
 * <p>Title: Sistema Inteligente de Planificacion</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Relacional {

          Vector nodes;
          Relations r;
          Vector actions;
          Relations r_actions;

          public Relacional(Vector vars, Relations varsQ, Vector actions,
                            Relations r_actions) {
            nodes = vars;
            r = varsQ;
            this.actions = actions;
            this.r_actions = r_actions;
          }

          public Relacional(Vector vars, Relations varsQ) {
            nodes = vars;
            r = varsQ;
          }

          public int[] updateRelations(double[] array) {
            // se actualizan las variables
            SimpleNode nodo;
            for (int i = 0; i < nodes.size(); i++) {
              nodo = (SimpleNode) nodes.elementAt(i);
              nodo.updateValue(array[i]);
            }

            // se actualizan las relaciones
            r.updateRelations(nodes);
            /*
                    System.out.println("Despues de actualizar");
                    r.printRelations();
             */

            return r.arrayRelations();

          }

          private int[] updateR_Actions(double[] array) {
            // se actualizan las variables
            SimpleNode nodo;
            for (int i = 0; i < actions.size(); i++) {
              nodo = (SimpleNode) actions.elementAt(i);
              nodo.updateValue(array[i]);
            }

            // se actualizan las relaciones
            r_actions.updateRelations(actions);
            /*
                  System.out.println("Despues de actualizar");
                  r.printRelations();
             */

            return r_actions.arrayRelations();

          }


}