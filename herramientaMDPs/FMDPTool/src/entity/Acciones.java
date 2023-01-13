package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Lilith
 */

@Entity
@Table(name = "acciones")
@NamedQueries({
    @NamedQuery(name = "Acciones.findAll", query = "SELECT a FROM Acciones a"),
    @NamedQuery(name = "Acciones.findByIdaccion", query = "SELECT a FROM Acciones a WHERE a.idaccion = :idaccion"),
    @NamedQuery(name = "Acciones.findByProtectionSystem", query = "SELECT a FROM Acciones a WHERE a.protectionSystem = :protectionSystem"),
    @NamedQuery(name = "Acciones.findByProcedure", query = "SELECT a FROM Acciones a WHERE a.procedure_ = :procedure"),
    @NamedQuery(name = "Acciones.findByResponse", query = "SELECT a FROM Acciones a WHERE a.response = :response"),
    @NamedQuery(name = "Acciones.findByDetection", query = "SELECT a FROM Acciones a WHERE a.detection = :detection"),
    @NamedQuery(name = "Acciones.findByAction", query = "SELECT a FROM Acciones a WHERE a.action_ = :action")})
public class Acciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idaccion")
    private String idaccion;
    @Column(name = "protection_system")
    private String protectionSystem;
    @Column(name = "procedure_")
    private String procedure_;
    @Column(name = "response")
    private String response;
    @Column(name = "detection")
    private String detection;
    @Column(name = "action_")
    private String action_;
    @ManyToMany(mappedBy = "accionesCollection")
    private Collection<Componentes> componentesCollection;
    @ManyToMany(mappedBy = "accionesCollection")
    private Collection<Procedimientos> procedimientosCollection;

    public Acciones() {
    }

    public Acciones(String idaccion) {
        this.idaccion = idaccion;
    }

    public String getIdaccion() {
        return idaccion;
    }

    public void setIdaccion(String idaccion) {
        this.idaccion = idaccion;
    }

    public String getProtectionSystem() {
        return protectionSystem;
    }

    public void setProtectionSystem(String protectionSystem) {
        this.protectionSystem = protectionSystem;
    }

    public String getProcedure() {
        return procedure_;
    }

    public void setProcedure(String procedure) {
        this.procedure_ = procedure;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDetection() {
        return detection;
    }

    public void setDetection(String detection) {
        this.detection = detection;
    }

    public String getAction() {
        return action_;
    }

    public void setAction(String action) {
        this.action_ = action;
    }

    public Collection<Componentes> getComponentesCollection() {
        return componentesCollection;
    }

    public void setComponentesCollection(Collection<Componentes> componentesCollection) {
        this.componentesCollection = componentesCollection;
    }

    public Collection<Procedimientos> getProcedimientosCollection() {
        return procedimientosCollection;
    }

    public void setProcedimientosCollection(Collection<Procedimientos> procedimientosCollection) {
        this.procedimientosCollection = procedimientosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaccion != null ? idaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acciones)) {
            return false;
        }
        Acciones other = (Acciones) object;
        if ((this.idaccion == null && other.idaccion != null) || (this.idaccion != null && !this.idaccion.equals(other.idaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Acciones[idaccion=" + idaccion + "]";
    }

}
