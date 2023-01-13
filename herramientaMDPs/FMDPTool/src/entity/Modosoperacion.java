/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Lilith
 */
@Entity
@Table(name = "modosoperacion")
@NamedQueries({
    @NamedQuery(name = "Modosoperacion.findAll", query = "SELECT m FROM Modosoperacion m"),
    @NamedQuery(name = "Modosoperacion.findByIdmodo", query = "SELECT m FROM Modosoperacion m WHERE m.idmodo = :idmodo"),
    @NamedQuery(name = "Modosoperacion.findByDescripcion", query = "SELECT m FROM Modosoperacion m WHERE m.descripcion = :descripcion")})
public class Modosoperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmodo")
    private Long idmodo;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modosoperacion")
    private Collection<Nivelusuario> nivelusuarioCollection;

    public Modosoperacion() {
    }

    public Modosoperacion(Long idmodo) {
        this.idmodo = idmodo;
    }

    public Modosoperacion(Long idmodo, String descripcion) {
        this.idmodo = idmodo;
        this.descripcion = descripcion;
    }

    public Long getIdmodo() {
        return idmodo;
    }

    public void setIdmodo(Long idmodo) {
        this.idmodo = idmodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Nivelusuario> getNivelusuarioCollection() {
        return nivelusuarioCollection;
    }

    public void setNivelusuarioCollection(Collection<Nivelusuario> nivelusuarioCollection) {
        this.nivelusuarioCollection = nivelusuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmodo != null ? idmodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modosoperacion)) {
            return false;
        }
        Modosoperacion other = (Modosoperacion) object;
        if ((this.idmodo == null && other.idmodo != null) || (this.idmodo != null && !this.idmodo.equals(other.idmodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Modosoperacion[idmodo=" + idmodo + "]";
    }

}
