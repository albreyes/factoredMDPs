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

public class Ref extends SimpleNode{
	double threshold;
	/**
	 *
	 */
	public Ref(double value, double threshold) {
		super(value);
		this.threshold=threshold;
		// TODO Auto-generated constructor stub
	}

	public Ref(String label, double value, double threshold) {
		super(label,value);
		this.threshold=threshold;
		// TODO Auto-generated constructor stub
	}

}
