package entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "generation")
@NamedQueries({
    @NamedQuery(name = "Generation.findAll", query = "SELECT g FROM Generation g"),
    @NamedQuery(name = "Generation.findByIdvariable", query = "SELECT g FROM Generation g WHERE g.idvariable = :idvariable"),
    @NamedQuery(name = "Generation.findByGeneration", query = "SELECT g FROM Generation g WHERE g.generation = :generation")})

    @DiscriminatorValue("GEN")
public class Generation extends Variables implements Serializable {
    private static final long serialVersionUID = 1L;
    /*@Id
    @Basic(optional = false)
    @Column(name = "idvariable")
    private String idvariable;*/
    @Basic(optional = false)
    @Column(name = "generation")
    private String generation;
    /*@JoinColumn(name = "idvariable", referencedColumnName = "idvariable", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Variables variables;*/

    public Generation() {
    }

    /*public Generation(String idvariable) {
        this.idvariable = idvariable;
    }

    public Generation(String idvariable, String generation) {
        this.idvariable = idvariable;
        this.generation = generation;
    }

    public String getIdvariable() {
        return idvariable;
    }

    public void setIdvariable(String idvariable) {
        this.idvariable = idvariable;
    }*/

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

   /* public Variables getVariables() {
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
        if (!(object instanceof Generation)) {
            return false;
        }
        Generation other = (Generation) object;
        if ((this.idvariable == null && other.idvariable != null) || (this.idvariable != null && !this.idvariable.equals(other.idvariable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Generation[idvariable=" + idvariable + "]";
    }*/

}
