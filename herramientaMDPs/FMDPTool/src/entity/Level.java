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
@Table(name = "level")
@NamedQueries({
    @NamedQuery(name = "Level.findAll", query = "SELECT l FROM Level l"),
    @NamedQuery(name = "Level.findByIdvariable", query = "SELECT l FROM Level l WHERE l.idvariable = :idvariable"),
    @NamedQuery(name = "Level.findByLevel", query = "SELECT l FROM Level l WHERE l.level = :level")})

@DiscriminatorValue("LEV")
public class Level extends Variables implements Serializable {
    private static final long serialVersionUID = 1L;
   // @Id
   // @Basic(optional = false)
   // @Column(name = "idvariable")
    //private String idvariable;
    @Column(name = "level")
    private String level;
   /* @JoinColumn(name = "idvariable", referencedColumnName = "idvariable", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Variables variables;*/

    public Level() {
    }

   /* public Level(String idvariable) {
        this.idvariable = idvariable;
    }

    public String getIdvariable() {
        return idvariable;
    }

    public void setIdvariable(String idvariable) {
        this.idvariable = idvariable;
    }*/

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

  /*  public Variables getVariables() {
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
        if (!(object instanceof Level)) {
            return false;
        }
        Level other = (Level) object;
        if ((this.idvariable == null && other.idvariable != null) || (this.idvariable != null && !this.idvariable.equals(other.idvariable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Level[idvariable=" + idvariable + "]";
    }*/

}
