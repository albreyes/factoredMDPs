/*
 * Created on 26/03/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package DataStructuresQ;

/**
 * @author areyes
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SimpleNode {

	public String label;
	public double value;

	/**
	 *
	 */

	public SimpleNode(double value) {
		this.value=value;
		label="undefined";
		// TODO Auto-generated constructor stub
	}

	public SimpleNode(String label, double value) {
		this.label=label;
		this.value=value;
		// TODO Auto-generated constructor stub
	}

	public SimpleNode(String label) {
		this.label=label;
		// TODO Auto-generated constructor stub
	}

	public void updateValue(double val){
		value=val;
	}
}
