/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Lilith
 */
@Entity
@Table(name = "flow")
@NamedQueries({
    @NamedQuery(name = "Flow.findAll", query = "SELECT f FROM Flow f"),
    @NamedQuery(name = "Flow.findByIdvariable", query = "SELECT f FROM Flow f WHERE f.idvariable = :idvariable"),
    @NamedQuery(name = "Flow.findByFlow", query = "SELECT f FROM Flow f WHERE f.flow = :flow")})

    @DiscriminatorValue("FLW")
public class Flow extends Variables implements Serializable {
    //private static final long serialVersionUID = 1L;
    //@Id
    //@Basic(optional = false)
    //@Column(name = "idvariable")
    //private String idvariable;
    @Column(name = "flow")
    private String flow;
    /*@JoinColumn(name = "idvariable", referencedColumnName = "idvariable", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Variables variables;*/

    public Flow() {
    }

   /* public Flow(String idvariable) {
        this.idvariable = idvariable;
    }

    public String getIdvariable() {
        return idvariable;
    }

    public void setIdvariable(String idvariable) {
        this.idvariable = idvariable;
    }*/

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    /*public Variables getVariables() {
        return variables;
    }

    public void setVariables(Variables variables) {
        this.variables = variables;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvariable != null ? idvariable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flow)) {
            return false;
        }
        Flow other = (Flow) object;
        if ((this.idvariable == null && other.idvariable != null) || (this.idvariable != null && !this.idvariable.equals(other.idvariable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Flow[idvariable=" + idvariable + "]";
    }*/

}
