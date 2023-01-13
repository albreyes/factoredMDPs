/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Lilith
 */
@Entity
@Table(name = "sesiones")
@NamedQueries({
    @NamedQuery(name = "Sesiones.findAll", query = "SELECT s FROM Sesiones s"),
    @NamedQuery(name = "Sesiones.findByIdsesion", query = "SELECT s FROM Sesiones s WHERE s.idsesion = :idsesion"),
    @NamedQuery(name = "Sesiones.findByFecha", query = "SELECT s FROM Sesiones s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Sesiones.findByNumIntentos", query = "SELECT s FROM Sesiones s WHERE s.numIntentos = :numIntentos")})
public class Sesiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsesion")
    private Long idsesion;
    @Basic(optional = false)
    @Column(name = "fecha")
     @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "numIntentos")
    private Integer numIntentos;
    @JoinColumn(name = "nombreUsuario", referencedColumnName = "nombreUsuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "idNivel", referencedColumnName = "idnivel")
    @ManyToOne
    private Nivelusuario nivelusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sesiones")
    private Collection<IntentosSesion> intentosSesionCollection;

    public Sesiones() {
    }

    public Sesiones(Long idsesion) {
        this.idsesion = idsesion;
    }

    public Sesiones(Long idsesion, Date fecha) {
        this.idsesion = idsesion;
        this.fecha = fecha;
    }

    public Long getIdsesion() {
        return idsesion;
    }

    public void setIdsesion(Long idsesion) {
        this.idsesion = idsesion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getNumIntentos() {
        return numIntentos;
    }

    public void setNumIntentos(Integer numIntentos) {
        this.numIntentos = numIntentos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Nivelusuario getNivelusuario() {
        return nivelusuario;
    }

    public void setNivelusuario(Nivelusuario nivelusuario) {
        this.nivelusuario = nivelusuario;
    }

    public Collection<IntentosSesion> getIntentosSesionCollection() {
        return intentosSesionCollection;
    }

    public void setIntentosSesionCollection(Collection<IntentosSesion> intentosSesionCollection) {
        this.intentosSesionCollection = intentosSesionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsesion != null ? idsesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sesiones)) {
            return false;
        }
        Sesiones other = (Sesiones) object;
        if ((this.idsesion == null && other.idsesion != null) || (this.idsesion != null && !this.idsesion.equals(other.idsesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Sesiones[idsesion=" + idsesion + "]";
    }

}
