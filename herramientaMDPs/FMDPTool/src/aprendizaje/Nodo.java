package aprendizaje;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.lang.Math;


/**
 * The <code>Nodo</code> class contains the label or name and the links for
 * a node in a  <code>DecisionTree</code>.
 *
 * @author Joseph P. Bigus
 * @author Jennifer Bigus
 *
 * @copyright
 * Constructing Intelligent Agents using Java
 * (C) Joseph P. Bigus and Jennifer Bigus 1997, 2001
 *
 */
public class Nodo extends Object implements Serializable {

  protected String label;       // name of the node
  protected Vector linkLabels;  // tests on links from parent to child
  protected Nodo parent;        // parent node
  protected Vector children;    // any children nodes


  /**
   * Creates a node.
   */
  public Nodo() {
    parent = null;
    children = new Vector();
    linkLabels = new Vector();
  }

public Nodo(String label,Vector children, Nodo parent, Vector linkLabels ) {
  this.label = label;
  this.children = children;
  this.parent = parent;
  this.linkLabels = linkLabels;
}

/**
* Copy constructor.
*/
public Nodo(Nodo aNodo) {
  this(aNodo.label,  aNodo.children, aNodo.parent, aNodo.linkLabels);
}


  /**
   * Creates a node with the given name.
   *
   * @param label the String that contains the name of the node
   */
  public Nodo(String label) {
    this.label = label;
    children = new Vector();
    parent = null;
    linkLabels = new Vector();
  }


  /**
   * Creates a node with the given name and parent.
   *
   * @param parent the Node that is the parent of the node being created
   * @param label the String that contains the name of the node
   */
  public Nodo(Nodo parent, String label) {
    this.parent = parent;
    children = new Vector();
    this.label = label;
    linkLabels = new Vector();
  }


  /**
   * Adds a child node and the link name for the link to that child.
   *
   * @param child the Node that is added as a child
   * @param linkLabel the String that contains the name of the link
   */
  public void addChild(Nodo child, String linkLabel) {
    children.addElement(child);
    child.parent=this;
    linkLabels.addElement(linkLabel);

  }


  /**
   * Checks if the node has children nodes linked to it.
   *
   * @return  <code>true</code> if the node has children. Otherwise, returns <code>
   *          false</code>.
   */
  public boolean hasChildren() {
    if (children.size() == 0) {
      return false;
    } else {
      return true;
    }
  }


  /**
   * Sets the name of the node.
   *
   * @param label the String that contains the name of the node
   */
  public void setLabel(String label) {
    this.label = label;
  }


  /**
   * Displays the tree, starting with the given root node.
   *
   * @param root the Node that is the root of the tree to be displayed
   * @param offset the String
   */
  public static void displayTree(Nodo root, String offset) {
    if (root.children.size() == 0) {
      DecisionTree.appendText("\n" + offset + "    THEN (" + root.label + ")  (Leaf node)");
      return;
    } else {
      Enumeration enume = root.children.elements();
      Enumeration enum2 = root.linkLabels.elements();

      DecisionTree.appendText("\n" + offset + "   " + root.label + " (Interior node)");
      while (enume.hasMoreElements()) {
        DecisionTree.appendText("\n" + offset + "   IF (" + (String) enum2.nextElement() + ")");
        displayTree((Nodo) enume.nextElement(), offset + "   ");
      }
    }
  }

  public static void displayTreeConsole(Nodo root, String offset) {
  if (root.children.size() == 0) {
    System.out.print("\n" + offset + "    THEN (" + root.label + ")  (Leaf node)");
    return;
  } else {
    Enumeration enume = root.children.elements();
    Enumeration enum2 = root.linkLabels.elements();

    System.out.print("\n" + offset + "   " + root.label + " (Interior node)");
    while (enume.hasMoreElements()) {
      System.out.print("\n" + offset + "   IF (" + (String) enum2.nextElement() + ")");
      displayTreeConsole((Nodo) enume.nextElement(), offset + "   ");
    }
  }
}

}
