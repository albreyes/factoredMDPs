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
@Table(name = "tipousuarios")
@NamedQueries({
    @NamedQuery(name = "Tipousuarios.findAll", query = "SELECT t FROM Tipousuarios t"),
    @NamedQuery(name = "Tipousuarios.findByIdTipoUsuario", query = "SELECT t FROM Tipousuarios t WHERE t.idTipoUsuario = :idTipoUsuario"),
    @NamedQuery(name = "Tipousuarios.findByDescripcion", query = "SELECT t FROM Tipousuarios t WHERE t.descripcion = :descripcion")})
public class Tipousuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoUsuario")
    private Long idTipoUsuario;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipousuarios")
    private Collection<Usuario> usuarioCollection;

    public Tipousuarios() {
    }

    public Tipousuarios(Long idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Tipousuarios(Long idTipoUsuario, String descripcion) {
        this.idTipoUsuario = idTipoUsuario;
        this.descripcion = descripcion;
    }

    public Long getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Long idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUsuario != null ? idTipoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipousuarios)) {
            return false;
        }
        Tipousuarios other = (Tipousuarios) object;
        if ((this.idTipoUsuario == null && other.idTipoUsuario != null) || (this.idTipoUsuario != null && !this.idTipoUsuario.equals(other.idTipoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Tipousuarios[idTipoUsuario=" + idTipoUsuario + "]";
    }

}
