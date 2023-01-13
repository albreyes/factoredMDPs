/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Lilith
 */
@Entity
@Table(name = "turbine")
@NamedQueries({
    @NamedQuery(name = "Turbine.findAll", query = "SELECT t FROM Turbine t"),
    @NamedQuery(name = "Turbine.findByIdcomponente", query = "SELECT t FROM Turbine t WHERE t.idcomponente = :idcomponente"),
    @NamedQuery(name = "Turbine.findBySize", query = "SELECT t FROM Turbine t WHERE t.size_ = :size"),
    @NamedQuery(name = "Turbine.findByResponse", query = "SELECT t FROM Turbine t WHERE t.response_ = :response"),
    @NamedQuery(name = "Turbine.findByVanesNumber", query = "SELECT t FROM Turbine t WHERE t.vanesNumber = :vanesNumber")})

     @DiscriminatorValue("TUR")
public class Turbine extends Componentes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "size_")
    private String size_;
    @Column(name = "response_")
    private String response_;
    @Column(name = "vanes_number")
    private String vanesNumber;

    public Turbine() {
    }

    public String getSize() {
        return size_;
    }

    public void setSize(String size) {
        this.size_ = size;
    }

    public String getResponse() {
        return response_;
    }

    public void setResponse(String response) {
        this.response_ = response;
    }

    public String getVanesNumber() {
        return vanesNumber;
    }

    public void setVanesNumber(String vanesNumber) {
        this.vanesNumber = vanesNumber;
    }


}
