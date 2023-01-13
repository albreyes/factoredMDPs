/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Lilith
 */
@Entity
@Table(name = "shutdown_valve")
@NamedQueries({
    @NamedQuery(name = "ShutdownValve.findAll", query = "SELECT s FROM ShutdownValve s"),
    @NamedQuery(name = "ShutdownValve.findByIdcomponente", query = "SELECT s FROM ShutdownValve s WHERE s.idcomponente = :idcomponente"),
    @NamedQuery(name = "ShutdownValve.findBySize", query = "SELECT s FROM ShutdownValve s WHERE s.size_ = :size"),
    @NamedQuery(name = "ShutdownValve.findByPressureRating", query = "SELECT s FROM ShutdownValve s WHERE s.pressureRating = :pressureRating"),
    @NamedQuery(name = "ShutdownValve.findByActuation", query = "SELECT s FROM ShutdownValve s WHERE s.actuation = :actuation")})

     @DiscriminatorValue("SHV")
public class ShutdownValve extends Componentes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "size_")
    private String size_;
    @Column(name = "pressure_rating")
    private String pressureRating;
    @Column(name = "actuation")
    private String actuation;

    public ShutdownValve() {
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
