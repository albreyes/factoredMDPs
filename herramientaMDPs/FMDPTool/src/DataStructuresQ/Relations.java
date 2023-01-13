/*
 * Created on 26/03/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package DataStructuresQ;

import java.util.Vector;

/**
 * @author areyes
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Relations {

	public Vector relations=new Vector();


	/**
	 *  initializes a Vector of Links with one relation
	 */
	public Relations(Link r) {

		relations.addElement(r);
		// TODO Auto-generated constructor stub
	}
	// With no Link
	public Relations() {}

	public void addRel(Link r){
		relations.addElement(r);
	}
/*	public void addNode(SimpleNode sn){
		n.addElement(sn);
	}*/

	//public String[] qualitativeVector(){}

	public void updateRelations(Vector nodes){

		SimpleNode sn;
		Link l;

		for(int i=0; i<nodes.size(); i++)
		 for(int j=0; j<relations.size(); j++){
			sn=(SimpleNode)nodes.elementAt(i);
			l=(Link)relations.elementAt(j);
			if(sn.label.equals(l.var.label))
			l.updateLink(sn.value);
		}
	}

	public void printRelations(){
		Link l;
		for(int i=0; i<relations.size(); i++){
			l=(Link) relations.elementAt(i);
			System.out.println("rel("+l.var.label+","+l.reference.label+","+l.qValue+").");
		}

	}
        private int mapQ(String q) {
          if (q.equals("zero"))
            return 0;
          else if (q.equals("pos"))
            return 1;
          else if (q.equals("neg"))
            return 2;
          else
            return -1;
        }

        public int[] arrayRelations(){
                Link l;
                int[] values=new int[relations.size()];

                for(int i=0; i<relations.size(); i++){
                        l=(Link) relations.elementAt(i);
                        values[i]=mapQ(l.qValue);
                }
                return values;
        }

}
