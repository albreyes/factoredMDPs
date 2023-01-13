/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
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
import javax.persistence.Table;

/**
 *
 * @author Lilith
 */
@Entity
@Table(name = "intentos_sesion")
@NamedQueries({
    @NamedQuery(name = "IntentosSesion.findAll", query = "SELECT i FROM IntentosSesion i"),
    @NamedQuery(name = "IntentosSesion.findByIdintento", query = "SELECT i FROM IntentosSesion i WHERE i.idintento = :idintento"),
    @NamedQuery(name = "IntentosSesion.findByRecomendacion", query = "SELECT i FROM IntentosSesion i WHERE i.recomendacion = :recomendacion"),
    @NamedQuery(name = "IntentosSesion.findByAccion", query = "SELECT i FROM IntentosSesion i WHERE i.accion = :accion"),
    @NamedQuery(name = "IntentosSesion.findByApertura", query = "SELECT i FROM IntentosSesion i WHERE i.apertura = :apertura")})
public class IntentosSesion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idintento")
    private Long idintento;
    @Basic(optional = false)
    @Column(name = "recomendacion")
    private int recomendacion;
    @Basic(optional = false)
    @Column(name = "accion")
    private int accion;
    @Column(name = "apertura")
    private Integer apertura;
    @JoinColumn(name = "idvariable", referencedColumnName = "idvariable")
    @ManyToOne(optional = false)
    private Variables variables;
    @JoinColumn(name = "idsesion", referencedColumnName = "idsesion")
    @ManyToOne(optional = false)
    private Sesiones sesiones;

    public IntentosSesion() {
    }

    public IntentosSesion(Long idintento) {
        this.idintento = idintento;
    }

    public IntentosSesion(Long idintento, int recomendacion, int accion) {
        this.idintento = idintento;
        this.recomendacion = recomendacion;
        this.accion = accion;
    }

    public Long getIdintento() {
        return idintento;
    }

    public void setIdintento(Long idintento) {
        this.idintento = idintento;
    }

    public int getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(int recomendacion) {
        this.recomendacion = recomendacion;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public Integer getApertura() {
        return apertura;
    }

    public void setApertura(Integer apertura) {
        this.apertura = apertura;
    }

    public Variables getVariables() {
        return variables;
    }

    public void setVariables(Variables variables) {
        this.variables = variables;
    }

    public Sesiones getSesiones() {
        return sesiones;
    }

    public void setSesiones(Sesiones sesiones) {
        this.sesiones = sesiones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idintento != null ? idintento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IntentosSesion)) {
            return false;
        }
        IntentosSesion other = (IntentosSesion) object;
        if ((this.idintento == null && other.idintento != null) || (this.idintento != null && !this.idintento.equals(other.idintento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.IntentosSesion[idintento=" + idintento + "]";
    }

}
