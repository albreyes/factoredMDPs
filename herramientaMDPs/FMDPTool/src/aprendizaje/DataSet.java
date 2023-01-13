package aprendizaje;

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;


/**
 * The <code>DataSet</code> class is used to load data from text files into
 * memory for use in training or testing.
 *
 * @author Joseph P. Bigus
 * @author Jennifer Bigus
 *
 * @copyright
 * Constructing Intelligent Agents using Java
 * (C) Joseph P. Bigus and Jennifer Bigus 1997, 2001
 *
 */
public class DataSet extends Object implements Serializable {
  protected String name;
  protected String fileName;
  protected boolean allNumericData;  // if true use double[] else String[]
  protected Vector data;             // raw data from file
  protected Vector normalizedData;   // scaled and translated data
  protected Hashtable variableList;  // variable definitions
  protected Vector fieldList;        // field definitions where index = column
  protected int fieldsPerRec = 0;
  protected int normFieldsPerRec = 0;
  protected int numRecords = 0;
  transient public JTextArea textArea1;


  /**
   * Creates a dataset with the given name that will be populated from the
   * specified file.
   *
   * @param name the String that contains the name of the dataset
   * @param fileName the text file from which the dataset is populated
   */
  public DataSet(String name, String fileName) {
    this.name = name;  // object name
    if (fileName.endsWith(".dfn") || fileName.endsWith(".dat")) {
      int inx = fileName.indexOf('.');

      this.fileName = fileName.substring(0, inx);  // strip off the extension
    } else {
      this.fileName = fileName;  // text file name only 
    }
    fieldsPerRec = 0;                // start with no variables defined
    allNumericData = true;           // assume all numeric data
    data = new Vector();             // holds string data
    variableList = new Hashtable();  // for named lookup
    fieldList = new Vector();        // for ordered lookup
  }


  /**
   * Adds text to the text area for display.
   *
   * @param text the String to be displayed
   */
  public void trace(String text) {
    if (textArea1 != null) {
      textArea1.append(text);
    } else {
      System.out.println(text);
    }
  }


  /**
   * Sets the text area to be displayed for the dataset information.
   *
   * @param textArea the JTextArea text area to be displayed
   */
  public void setDisplay(JTextArea textArea) {
    textArea1 = textArea;
  }


  /**
   * Displays all variables and their values.
   */
  public void displayVariables() {
    Enumeration enume = variableList.elements();

    trace("\n\nVariables:");
    while (enume.hasMoreElements()) {
      String values;
      Var temp = (Var) enume.nextElement();

      if (temp.labels != null) {
        values = temp.getLabels();
      } else {
        values = "< real>";
      }
      trace("\n " + temp.name + "( " + values + ") ");
    }
  }


  /**
   * Method getNumRecords
   *
   * @return the int
   *
   */
  public int getNumRecords() {
    return numRecords;
  }


  /**
   * Method getFieldsPerRec
   *
   * @return the int
   *
   */
  public int getFieldsPerRec() {
    return fieldsPerRec;
  }


  /**
   * Method getNormFieldsPerRec
   *
   * @return the int
   *
   */
  public int getNormFieldsPerRec() {
    return normFieldsPerRec;
  }


  /**
   * Method getNormalizedData
   *
   * @return the Vector
   *
   */
  public Vector getNormalizedData() {
    return normalizedData;
  }


  /**
   * Method getFileName
   *
   * @return the String
   *
   */
  public String getFileName() {
    return fileName;
  }


  /**
   * Reads the data file definition which is a simple text files that contains a
   * list of the field data types and their names.
   */
  public void loadDataFileDefinition() {
    String tempRec[] = null;  // used when data is symbolic
    String line = null;

    trace("\nReading file definition " + fileName + ".dfn\n ");
    BufferedReader in = null;

    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName + ".dfn")));
    } catch (FileNotFoundException exc) {
      trace("Error: Can't find definition file " + fileName + ".dfn");
    }
    int recInx = 0;
    int token = 0;
    StringTokenizer input = null;

    do {
      try {
        line = in.readLine();
        if (line != null) {
          input = new StringTokenizer(line);
        } else {
          break;
        }
      } catch (IOException exc) {
        trace("Error reading file: " + fileName + ".dfn");
      }
      trace("\n Record " + recInx + ": ");
      String varType = input.nextToken();
      String varName = input.nextToken();

      if (varType.equals("continuous")) {
        addVariable(new ContinuousVariable(varName));
      } else if (varType.equals("discrete")) {
        addVariable(new DiscreteVariable(varName));
      } else if (varType.equals("categorical")) {
        addVariable(new DiscreteVariable(varName));
      }
      trace(varType + " " + varName);
      recInx++;
    } while (token != StreamTokenizer.TT_EOF);
    fieldsPerRec = fieldList.size();
    trace("\nCreated " + fieldsPerRec + " variables.\n");
  }


  /**
   * Retrieves the size of the class field.
   *
   * @return the class field size
   */
  public int getClassFieldSize() {
    if (variableList.get("ClassField") == null) {
      trace("DataSet " + name + "does not have a ClassField");
      return 0;
    } else {
      return ((Var) variableList.get("ClassField")).getNormalizedSize();
    }
  }


  /**
   * Reads the data from the file defined by the data file definition.
   */
  public void loadDataFile() {
    String tempRec[] = null;  // used when data is symbolic

    loadDataFileDefinition();  // first read the file def and create the vars
    fieldsPerRec = fieldList.size();
    String line = null;

    trace("\nReading file " + fileName + ".dat with " + fieldsPerRec + " fields per record\n ");
    BufferedReader in = null;

    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName + ".dat")));
    } catch (FileNotFoundException exc) {
      trace("Error: Can't find file " + fileName + ".dat");
    }
    int recInx = 0;
    int token = 0;
    StringTokenizer input = null;

    do {
      try {
        line = in.readLine();
        if (line != null) {
          input = new StringTokenizer(line);
          tempRec = new String[fieldsPerRec];
          data.addElement(tempRec);  // add record
        } else {
          break;
        }
      } catch (IOException exc) {
        trace("Error reading file: " + fileName + ".dat");
      }
      trace("\n Record " + recInx + ": ");
      for (int i = 0; i < fieldsPerRec; i++) {
        tempRec[i] = input.nextToken();
        ((Var) fieldList.elementAt(i)).computeStatistics(tempRec[i]);
        trace(tempRec[i] + " ");
      }
      recInx++;
    } while (token != StreamTokenizer.TT_EOF);
    numRecords = recInx;
    trace("\nLoaded " + numRecords + " records into memory.\n");
    normalizeData();  // now convert to numeric form
    displayVariables();
    displayNormalizedData();
  }


  /**
   * Adds a variable to the list of fields in the record.
   *
   * @param var the Variable object to be added to the list
   */
  public void addVariable(Var var) {
    variableList.put(var.name, var);
    fieldList.addElement(var);  // add in order of arrival
    var.setColumn(fieldsPerRec);
    fieldsPerRec++;
  }


  /**
   * Computes the record size after each variablel in the record is normalized.
   *
   * @return the normalized record size
   */
  public int getNormalizedRecordSize() {
    int sum = 0;
    Enumeration vars = variableList.elements();

    while (vars.hasMoreElements()) {
      Var thisVar = (Var) vars.nextElement();

      sum += thisVar.getNormalizedSize();
    }
    return sum;
  }


  /**
   * Retrieves the class field value for the given record index.
   *
   * @param recIndex the int record index
   *
   * @return the class field value
   */
  public String getClassFieldValue(int recIndex) {
    Var classField = (Var) variableList.get("ClassField");

    return ((String[]) data.elementAt(recIndex))[classField.column];
  }


  /**
   * Retrieves the class field value for a given activation.
   *
   * @param activations the double array of activations from which the class field
   *                    value is retrieved
   * @param index       the starting index of the output unit
   *
   * @return the class field value
   */
  public String getClassFieldValue(double[] activations, int index) {
    String value;
    Var classField = (Var) variableList.get("ClassField");

    if (classField.isCategorical()) {
      value = classField.getDecodedValue(activations, index);
    } else {
      value = String.valueOf(activations[index]);
    }
    return value;
  }


  /**
   * Normalizes a record by translating discrete data to a one-of-N vector and by
   * scaling all continuous data to be in the 0.0 to 1.0 range.
   */
  public void normalizeData() {
    String tempRec[] = null;

    normalizedData = new Vector();
    normFieldsPerRec = getNormalizedRecordSize();
    Enumeration rawData = data.elements();

    while (rawData.hasMoreElements()) {
      int inx = 0;
      double normNumRec[] = new double[normFieldsPerRec];
      Enumeration fields = fieldList.elements();

      tempRec = (String[]) rawData.nextElement();
      for (int i = 0; i < fieldsPerRec; i++) {
        Var thisVar = (Var) fields.nextElement();

        inx = thisVar.normalize(tempRec[i], normNumRec, inx);
      }
      normalizedData.addElement(normNumRec);
    }
  }


  /**
   * Displays the normalized data.
   */
  public void displayNormalizedData() {
    double tempNumRec[];
    int recInx = 0;

    trace("\n\nNormalized data:");
    Enumeration rawData = normalizedData.elements();

    while (rawData.hasMoreElements()) {
      trace("\n Record " + recInx++ + ": ");
      tempNumRec = (double[]) rawData.nextElement();
      int numFields = tempNumRec.length;

      for (int i = 0; i < numFields; i++) {
        trace(String.valueOf(tempNumRec[i]) + " ");
      }
    }
    trace("\n");
  }
}
