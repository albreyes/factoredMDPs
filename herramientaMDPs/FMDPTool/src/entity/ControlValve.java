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
 * @author Liliana Sánchez R.
 */
@Entity
@Table(name = "control_valve")
@NamedQueries({
    @NamedQuery(name = "ControlValve.findAll", query = "SELECT c FROM ControlValve c"),
    @NamedQuery(name = "ControlValve.findByIdcomponente", query = "SELECT c FROM ControlValve c WHERE c.idcomponente = :idcomponente"),
    @NamedQuery(name = "ControlValve.findBySize", query = "SELECT c FROM ControlValve c WHERE c.size_ = :size"),
    @NamedQuery(name = "ControlValve.findByPressureRating", query = "SELECT c FROM ControlValve c WHERE c.pressureRating = :pressureRating"),
    @NamedQuery(name = "ControlValve.findByActuation", query = "SELECT c FROM ControlValve c WHERE c.actuation = :actuation")})

     @DiscriminatorValue("CRV")
public class ControlValve extends Componentes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "size_")
    private String size_;
    @Column(name = "pressure_rating")
    private String pressureRating;
    @Column(name = "actuation")
    private String actuation;


    public ControlValve() {
    }

    public String getSize() {
        return size_;
    }

    public void setSize(String size) {
        this.size_ = size;
    }

    public String getPressureRating() {
        return pressureRating;
    }

    public void setPressureRating(String pressureRating) {
        this.pressureRating = pressureRating;
    }

    public String getActuation() {
        return actuation;
    }

    public void setActuation(String actuation) {
        this.actuation = actuation;
    }

}
