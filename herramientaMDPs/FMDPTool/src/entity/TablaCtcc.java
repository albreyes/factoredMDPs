/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Lilith
 */
@Entity
@Table(name = "tabla_ctcc")
@NamedQueries({
    @NamedQuery(name = "TablaCtcc.findAll", query = "SELECT t FROM TablaCtcc t"),
    @NamedQuery(name = "TablaCtcc.findByVariable", query = "SELECT t FROM TablaCtcc t WHERE t.variable = :variable"),
    @NamedQuery(name = "TablaCtcc.findByValor", query = "SELECT t FROM TablaCtcc t WHERE t.valor = :valor"),
    @NamedQuery(name = "TablaCtcc.findByDescripcion", query = "SELECT t FROM TablaCtcc t WHERE t.descripcion = :descripcion")})
public class TablaCtcc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "variable")
    private String variable;
    @Column(name = "valor")
    private Double valor;
    @Column(name = "descripcion")
    private String descripcion;

    public TablaCtcc() {
    }

    public TablaCtcc(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (variable != null ? variable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablaCtcc)) {
            return false;
        }
        TablaCtcc other = (TablaCtcc) object;
        if ((this.variable == null && other.variable != null) || (this.variable != null && !this.variable.equals(other.variable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TablaCtcc[variable=" + variable + "]";
    }

}
