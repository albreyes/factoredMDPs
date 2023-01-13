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
public class Link {

	public SimpleNode var;	// variables
	public Ref reference;
	public String qValue;
	/**
	 *
	 */
	public Link(SimpleNode var, Ref reference) {
		this.var=var;
		this.reference=reference;
		this.qValue=continuousToQualitative();
		// TODO Auto-generated constructor stub
	}
	private String continuousToQualitative(){

		if(var.value>reference.value+reference.threshold) return "pos";
		  else
  		    if(var.value<reference.value-reference.threshold) return "neg";
		  	else return "zero";
	}

	// actualiza valor del nodo
	public void updateLink(double val){
		var.value=val;
		this.qValue=continuousToQualitative();
	}
}
