/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Lilith
 */
@Entity
@Table(name = "procedimientos")
@NamedQueries({
    @NamedQuery(name = "Procedimientos.findAll", query = "SELECT p FROM Procedimientos p"),
    @NamedQuery(name = "Procedimientos.findByIdprocedimiento", query = "SELECT p FROM Procedimientos p WHERE p.idprocedimiento = :idprocedimiento"),
    @NamedQuery(name = "Procedimientos.findByDescripcion", query = "SELECT p FROM Procedimientos p WHERE p.descripcion = :descripcion")})
public class Procedimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idprocedimiento")
    private String idprocedimiento;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinTable(name = "procedimientos_acciones", joinColumns = {
        @JoinColumn(name = "idprocedimiento", referencedColumnName = "idprocedimiento")}, inverseJoinColumns = {
        @JoinColumn(name = "idaccion", referencedColumnName = "idaccion")})
    @ManyToMany
    private Collection<Acciones> accionesCollection;

    public Procedimientos() {
    }

    public Procedimientos(String idprocedimiento) {
        this.idprocedimiento = idprocedimiento;
    }

    public String getIdprocedimiento() {
        return idprocedimiento;
    }

    public void setIdprocedimiento(String idprocedimiento) {
        this.idprocedimiento = idprocedimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Acciones> getAccionesCollection() {
        return accionesCollection;
    }

    public void setAccionesCollection(Collection<Acciones> accionesCollection) {
        this.accionesCollection = accionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocedimiento != null ? idprocedimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procedimientos)) {
            return false;
        }
        Procedimientos other = (Procedimientos) object;
        if ((this.idprocedimiento == null && other.idprocedimiento != null) || (this.idprocedimiento != null && !this.idprocedimiento.equals(other.idprocedimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Procedimientos[idprocedimiento=" + idprocedimiento + "]";
    }

}
