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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Lilith
 */

@Entity
@Table(name = "variables")
@NamedQueries({
    @NamedQuery(name = "Variables.findAll", query = "SELECT v FROM Variables v"),
    @NamedQuery(name = "Variables.findByIdvariable", query = "SELECT v FROM Variables v WHERE v.idvariable = :idvariable"),
    @NamedQuery(name = "Variables.findByCycle", query = "SELECT v FROM Variables v WHERE v.cycle_ = :cycle_"),
    @NamedQuery(name = "Variables.findByPrincipal", query = "SELECT v FROM Variables v WHERE v.principal = :principal"),
    @NamedQuery(name = "Variables.findByOutput", query = "SELECT v FROM Variables v WHERE v.output_ = :output_"),
    @NamedQuery(name = "Variables.findByDescripcion", query = "SELECT v FROM Variables v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Variables.findByTipo", query = "SELECT v FROM Variables v WHERE v.tipo = :tipo"),
    @NamedQuery(name = "Variables.findByIdNum", query = "SELECT v FROM Variables v WHERE v.idNum = :idNum")})

@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("VAR")
public class Variables implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idvariable")
    private String idvariable;
    @Column(name = "cycle_")
    private String cycle_;
    @Column(name = "principal")
    private String principal;
    @Column(name = "output_")
    private String output_;
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Column(name = "figura")
    private byte[] figura;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "idNum")
    private String idNum;
    @ManyToMany(mappedBy = "variablesCollection")
    private Collection<Componentes> componentesCollection;
   /* @OneToOne(cascade = CascadeType.ALL, mappedBy = "variables")
    private Pressure pressure;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "variables")
    private Flow flow;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variables")
    private Collection<IntentosSesion> intentosSesionCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "variables")
    private Level level;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "variables")
    private Generation generation;*/

    public Variables() {
    }

    public Variables(String idvariable) {
        this.idvariable = idvariable;
    }

    public Variables(String idvariable, String tipo, String idNum) {
        this.idvariable = idvariable;
        this.tipo = tipo;
        this.idNum = idNum;
    }

    public String getIdvariable() {
        return idvariable;
    }

    public void setIdvariable(String idvariable) {
        this.idvariable = idvariable;
    }

    public String getCycle() {
        return cycle_;
    }

    public void setCycle(String cycle) {
        this.cycle_ = cycle;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getOutput() {
        return output_;
    }

    public void setOutput(String output) {
        this.output_ = output;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFigura() {
        return figura;
    }

    public void setFigura(byte[] figura) {
        this.figura = figura;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Collection<Componentes> getComponentesCollection() {
        return componentesCollection;
    }

    public void setComponentesCollection(Collection<Componentes> componentesCollection) {
        this.componentesCollection = componentesCollection;
    }

   /* public Pressure getPressure() {
        return pressure;
    }

    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public Collection<IntentosSesion> getIntentosSesionCollection() {
        return intentosSesionCollection;
    }

    public void setIntentosSesionCollection(Collection<IntentosSesion> intentosSesionCollection) {
        this.intentosSesionCollection = intentosSesionCollection;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvariable != null ? idvariable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Variables)) {
            return false;
        }
        Variables other = (Variables) object;
        if ((this.idvariable == null && other.idvariable != null) || (this.idvariable != null && !this.idvariable.equals(other.idvariable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Variables[idvariable=" + idvariable + "]";
    }

}
