package ia;


import java.util.Vector;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Estado {
  Vector variables;
  protected int id;
  int[] code;



  public Estado(int id, int[] code, Vector vars) {
    this.id=id;

    for(int i=0; i<code.length;i++)
      this.code[i]=code[i];

    variables=new Vector();

    for(int i=0; i<vars.size(); i++)
      variables.addElement(vars.elementAt(i));

  }

  public Estado(int id) {
    this.id=id;
    variables=new Vector();
 }

 public int[] getCode(){
   return code;
 }

  public static void main(String[] args) {
    Estado estado1 = new Estado(0);
  }

}