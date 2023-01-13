package aprendizaje;

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.lang.Math;


/**
 * The <code>DecisionTree</code> class implements a decision tree.
 *
 * @author Joseph P. Bigus
 * @author Jennifer Bigus
 *
 * @copyright
 * Constructing Intelligent Agents using Java
 * (C) Joseph P. Bigus and Jennifer Bigus 1997, 2001
 *
 */
public class DecisionTree implements Serializable {
  protected String name;
  protected DataSet ds;
  protected Var classVar;         // the class Variable
  protected Hashtable variableList;
  protected Vector examples;
  protected static JTextArea textArea1;
  protected String record[];  // one record of train/test data in string format




  /**
   * Creates an instance of a decision tree with the given name.
   *
   * @param name the String object that contains the name of this decision tree
   *
   * @param Name the String object
   */
  public DecisionTree(String Name) {
    name = Name;
  }

   /**
   * Appends text to the text area.
   *
   * @param text the String object that contains the text to be displayed
   *
   */
  public static void appendText(String text) {
    if (textArea1 != null) textArea1.append(text);
  }



  /**
   * Determines whether each record in the <code>examples</code> vector
   * matches the given variable value.
   *
   * @param examples the Vector object that contains the records
   * @param variable the Variable object that contains the value to be matched
   *
   * @return the boolean that contains <code>true</code> if the records
   *         match and <code>false</code> if they do not
   */
  public boolean identical(Vector examples, Var variable) {
    int index = variable.column;  // see which column to check
    Enumeration enume = examples.elements();
    boolean same = true;
    String value = ((String[]) examples.firstElement())[index];

    while (enume.hasMoreElements()) {
      if (value.equals(((String[]) enume.nextElement())[index])) {
        continue;
      } else {
        same = false;
        break;
      }
    }
    return same;
  }


  /**
   * Returns the value which occurs most often in the given vector.
   *
   * @param examples the Vector object which is examined
   *
   * @return the String object that occurs most often
   */
  public String majority(Vector examples) {
    int index = classVar.column;
    Enumeration enume = examples.elements();
    int counts[] = new int[classVar.labels.size()];

    while (enume.hasMoreElements()) {
      String value = ((String[]) enume.nextElement())[index];
      int inx = ((Var) classVar).getIndex(value);

      counts[inx]++;
    }  /* enbwhile */
    int maxVal = 0;
    int maxIndex = 0;

    for (int i = 0; i < classVar.labels.size(); i++) {
      if (counts[i] > maxVal) {
        maxVal = counts[i];
        maxIndex = i;
      }  /* endif */
    }    /* endfor */
    return classVar.getLabel(maxIndex);
  }


  /**
   * Returns an integer array containing the number of occurrences of each
   * discrete value in the given vector.
   *
   * @param examples the Vector object that contains the discrete values
   *
   * @return the int[] that contains the number of occurrences of each
   *         discrete value
   */
  public int[] getCounts(Vector examples) {
    int index = classVar.column;  // look at class column only
    Enumeration enume = examples.elements();
    int counts[] = new int[classVar.labels.size()];

    while (enume.hasMoreElements()) {
      String value = ((String[]) enume.nextElement())[index];
      int inx = ((Var) classVar).getIndex(value);

      counts[inx]++;
    }  /* enbwhile */
    return counts;
  }


  /**
   * Computes the information content, given the number of positive
   * and negative examples.
   *
   * @param p the number of positve values
   * @param n the number of negative values
   *
   * @return the double value that represents the information content
   */
  double computeInfo(int p, int n) {
    double total = p + n;
    double pos = p / total;
    double neg = n / total;
    double temp;

    if ((p == 0) || (n == 0)) {
      temp = 0.0;
    } else {
      temp = (-1.0 * (pos * Math.log(pos) / Math.log(2))) - (neg * Math.log(neg) / Math.log(2));
    }

    //  textArea1.appendText("Info( " + pos + ", " + neg + ") = " + temp) ;
    return temp;
  }


  /**
   * Computes the remainder value for the given variable and vector.
   *
   * @param variable the Var object for which the remainder is computed
   * @param examples the Vector object that contains the records
   *
   * @return the double value that represents the computed remainder
   */
  double computeRemainder(Var variable, Vector examples) {
    int positive[] = new int[variable.labels.size()];
    int negative[] = new int[variable.labels.size()];
    int index = variable.column;
    int classIndex = classVar.column;
    double sum = 0;
    double numValues = variable.labels.size();
    double numRecs = examples.size();

    for (int i = 0; i < numValues; i++) {
      String value = variable.getLabel(i);  // get discrete value
      Enumeration enume = examples.elements();

      while (enume.hasMoreElements()) {
        String record[] = (String[]) enume.nextElement();  // get next record

        if (record[index].equals(value)) {
          if (record[classIndex].equals("yes")) {
            positive[i]++;
          } else {
            negative[i]++;
          }
        }
      }                                     /* endwhile */
      double weight = (positive[i] + negative[i]) / numRecs;
      double myrem = weight * computeInfo(positive[i], negative[i]);

      sum = sum + myrem;

      //   textArea1.appendText("Computing rem for value " + value + " = " + myrem + " weight = " + weight);
    }                                       /* endfor */

    //   textArea1.appendText("Remainder for " + variable.name + " = " + sum) ;
    return sum;
  }


  /**
   * Returns a subset of a vector where the variable name equals the
   * given value.
   *
   * @param examples the Vector object that contains the records
   * @param variable the Variable object
   * @param value the String value to be matched
   *
   * @return the Vector object that contains the matching records
   */
  Vector subset(Vector examples, Var variable, String value) {
    int index = variable.column;
    Enumeration enume = examples.elements();
    Vector matchingExamples = new Vector();

    while (enume.hasMoreElements()) {
      String[] record = (String[]) enume.nextElement();

      if (value.equals(record[index])) {
        matchingExamples.addElement(record);
      }
    }
    textArea1.append("\n Subset - there are " + matchingExamples.size() + " records with " + variable.name + " = " + value);
    return matchingExamples;
  }


  /**
   * Chooses the variable with the greatest gain.
   *
   * @param variables the Hashtable object that contains the variables
   * @param examples the Vector object that contains the records
   *
   * @return the Var object with the greatest gain
   */
  Var chooseVariable(Hashtable variables, Vector examples) {
    Enumeration enume = variables.elements();
    double gain = 0.0, bestGain = -1.0;
    Var best = null;
    int counts[];

    counts = getCounts(examples);
    int pos = counts[0];
    int neg = counts[1];
    double info = computeInfo(pos, neg);

    textArea1.append("\nInfo = " + info);
    while (enume.hasMoreElements()) {
      Var tempVar = (Var) enume.nextElement();

      gain = info - computeRemainder(tempVar, examples);
      textArea1.append("\n" + tempVar.name + " gain = " + gain);
      if (gain > bestGain) {
        bestGain = gain;
        best = tempVar;
      }
    }
    textArea1.append("\nChoosing best variable: " + best.name);
    return best;  //
  }


  /**
   * Constructs a decision tree with the given a vector of example
   * data records, splitting on variables and values with the most
   * information content.
   *
   * @param examples the Vector object that contains the example records
   * @param variables the Hashtable object that contains the variables
   * @param defaultValue the Node object that contains the default value
   *                     if the tree cannot be built from the examples
   *
   * @return the Node object that is the root of the tree
   */
  public Nodo buildDecisionTree(Vector examples, Hashtable variables, Nodo defaultValue) {
    Nodo tree = new Nodo() ;

    if (examples.size() == 0) {
      return defaultValue;
    } else if (identical(examples, classVar)) {
      return new Nodo(((String[]) examples.firstElement())[classVar.column]);
    } else if (variables.size() == 0) {
      return new Nodo(majority(examples));
    } else {
      Var best = chooseVariable(variables, examples);

      tree = new Nodo(best.name);           // should be variable with most Gain
      Enumeration enume = best.labels.elements();
      int numValues = best.labels.size();

      for (int i = 0; i < numValues; i++) {
        Vector examples1 = subset(examples, best, best.getLabel(i));
        Hashtable variables1 = (Hashtable) variables.clone();

        variables1.remove(best.getName());
        Nodo subTree = buildDecisionTree(examples1, variables1, new Nodo(majority(examples1)));

        tree.addChild(subTree, best.name + "=" + best.getLabel(i));
      }                                     /* endfor */
    }
    return tree;
  }
}
