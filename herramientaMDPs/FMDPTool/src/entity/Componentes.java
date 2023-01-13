package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Liliana Sánchez R.
 */
@Entity
@Table(name = "componentes")
@NamedQueries({
    @NamedQuery(name = "Componentes.findAll", query = "SELECT c FROM Componentes c"),
    @NamedQuery(name = "Componentes.findByIdcomponente", query = "SELECT c FROM Componentes c WHERE c.idcomponente = :idcomponente"),
    @NamedQuery(name = "Componentes.findByType", query = "SELECT c FROM Componentes c WHERE c.type = :type"),
    @NamedQuery(name = "Componentes.findByResponse", query = "SELECT c FROM Componentes c WHERE c.response = :response"),
    @NamedQuery(name = "Componentes.findByDescription", query = "SELECT c FROM Componentes c WHERE c.description = :description"),
    @NamedQuery(name = "Componentes.findByTipo", query = "SELECT c FROM Componentes c WHERE c.tipo = :tipo")})

@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TYP2", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("COM")
public class Componentes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcomponente")
    private String idcomponente;
    @Column(name = "type_")
    private String type;
    @Column(name = "response")
    private String response;
    @Lob
    @Column(name = "figure")
    private byte[] figure;
    @Column(name = "description")
    private String description;
    @Column(name = "tipo")
    private String tipo;
    @JoinTable(name = "componentes_acciones", joinColumns = {
        @JoinColumn(name = "idcomponente", referencedColumnName = "idcomponente")}, inverseJoinColumns = {
        @JoinColumn(name = "idaccion", referencedColumnName = "idaccion")})
    @ManyToMany
    private Collection<Acciones> accionesCollection;
    @JoinTable(name = "componentes_variables", joinColumns = {
        @JoinColumn(name = "idcomponente", referencedColumnName = "idcomponente")}, inverseJoinColumns = {
        @JoinColumn(name = "idvariable", referencedColumnName = "idvariable")})
    @ManyToMany
    private Collection<Variables> variablesCollection;


    public Componentes() {
    }

    public Componentes(String idcomponente) {
        this.idcomponente = idcomponente;
    }

    public String getIdcomponente() {
        return idcomponente;
    }

    public void setIdcomponente(String idcomponente) {
        this.idcomponente = idcomponente;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public byte[] getFigure() {
        return figure;
    }

    public void setFigure(byte[] figure) {
        this.figure = figure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Collection<Acciones> getAccionesCollection() {
        return accionesCollection;
    }

    public void setAccionesCollection(Collection<Acciones> accionesCollection) {
        this.accionesCollection = accionesCollection;
    }

    public Collection<Variables> getVariablesCollection() {
        return variablesCollection;
    }

    public void setVariablesCollection(Collection<Variables> variablesCollection) {
        this.variablesCollection = variablesCollection;
    }

 /*   public Turbine getTurbine() {
        return turbine;
    }

    public void setTurbine(Turbine turbine) {
        this.turbine = turbine;
    }

    public ControlValve getControlValve() {
        return controlValve;
    }

    public void setControlValve(ControlValve controlValve) {
        this.controlValve = controlValve;
    }

    public ShutdownValve getShutdownValve() {
        return shutdownValve;
    }

    public void setShutdownValve(ShutdownValve shutdownValve) {
        this.shutdownValve = shutdownValve;
    }

    public Drum getDrum() {
        return drum;
    }

    public void setDrum(Drum drum) {
        this.drum = drum;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomponente != null ? idcomponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componentes)) {
            return false;
        }
        Componentes other = (Componentes) object;
        if ((this.idcomponente == null && other.idcomponente != null) || (this.idcomponente != null && !this.idcomponente.equals(other.idcomponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Componentes[idcomponente=" + idcomponente + "]";
    }

}
