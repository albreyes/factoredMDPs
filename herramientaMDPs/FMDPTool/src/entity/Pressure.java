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
@Table(name = "pressure")
@NamedQueries({
    @NamedQuery(name = "Pressure.findAll", query = "SELECT p FROM Pressure p"),
    @NamedQuery(name = "Pressure.findByIdvariable", query = "SELECT p FROM Pressure p WHERE p.idvariable = :idvariable"),
    @NamedQuery(name = "Pressure.findByPressure", query = "SELECT p FROM Pressure p WHERE p.pressure = :pressure")})

    @DiscriminatorValue("PRE")
public class Pressure extends Variables implements Serializable {
    private static final long serialVersionUID = 1L;
   /* @Id
    @Basic(optional = false)
    @Column(name = "idvariable")
    private String idvariable;*/
    @Column(name = "pressure")
    private String pressure;
    /*@JoinColumn(name = "idvariable", referencedColumnName = "idvariable", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Variables variables;*/

    public Pressure() {
    }

    /*public Pressure(String idvariable) {
        this.idvariable = idvariable;
    }

    public String getIdvariable() {
        return idvariable;
    }

    public void setIdvariable(String idvariable) {
        this.idvariable = idvariable;
    }*/

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
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
        if (!(object instanceof Pressure)) {
            return false;
        }
        Pressure other = (Pressure) object;
        if ((this.idvariable == null && other.idvariable != null) || (this.idvariable != null && !this.idvariable.equals(other.idvariable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Pressure[idvariable=" + idvariable + "]";
    }*/

}
