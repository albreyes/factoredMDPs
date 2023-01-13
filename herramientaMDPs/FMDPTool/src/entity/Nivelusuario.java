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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Lilith
 */
@Entity
@Table(name = "nivelusuario")
@NamedQueries({
    @NamedQuery(name = "Nivelusuario.findAll", query = "SELECT n FROM Nivelusuario n"),
    @NamedQuery(name = "Nivelusuario.findByIdnivel", query = "SELECT n FROM Nivelusuario n WHERE n.idnivel = :idnivel"),
    @NamedQuery(name = "Nivelusuario.findByDescripcion", query = "SELECT n FROM Nivelusuario n WHERE n.descripcion = :descripcion")})
public class Nivelusuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnivel")
    private Long idnivel;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "nivelusuario")
    private Collection<Usuario> usuarioCollection;
    @OneToMany(mappedBy = "nivelusuario")
    private Collection<Sesiones> sesionesCollection;
    @JoinColumn(name = "idmodo", referencedColumnName = "idmodo")
    @ManyToOne(optional = false)
    private Modosoperacion modosoperacion;

    public Nivelusuario() {
    }

    public Nivelusuario(Long idnivel) {
        this.idnivel = idnivel;
    }

    public Nivelusuario(Long idnivel, String descripcion) {
        this.idnivel = idnivel;
        this.descripcion = descripcion;
    }

    public Long getIdnivel() {
        return idnivel;
    }

    public void setIdnivel(Long idnivel) {
        this.idnivel = idnivel;
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

    public Collection<Sesiones> getSesionesCollection() {
        return sesionesCollection;
    }

    public void setSesionesCollection(Collection<Sesiones> sesionesCollection) {
        this.sesionesCollection = sesionesCollection;
    }

    public Modosoperacion getModosoperacion() {
        return modosoperacion;
    }

    public void setModosoperacion(Modosoperacion modosoperacion) {
        this.modosoperacion = modosoperacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnivel != null ? idnivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nivelusuario)) {
            return false;
        }
        Nivelusuario other = (Nivelusuario) object;
        if ((this.idnivel == null && other.idnivel != null) || (this.idnivel != null && !this.idnivel.equals(other.idnivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Nivelusuario[idnivel=" + idnivel + "]";
    }

}
