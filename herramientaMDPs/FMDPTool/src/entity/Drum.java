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
@Table(name = "drum")
@NamedQueries({
    @NamedQuery(name = "Drum.findAll", query = "SELECT d FROM Drum d"),
    @NamedQuery(name = "Drum.findByIdcomponente", query = "SELECT d FROM Drum d WHERE d.idcomponente = :idcomponente"),
    @NamedQuery(name = "Drum.findBySensitivity", query = "SELECT d FROM Drum d WHERE d.sensitivity = :sensitivity"),
    @NamedQuery(name = "Drum.findByCaptaincy", query = "SELECT d FROM Drum d WHERE d.captaincy = :captaincy")})

    @DiscriminatorValue("DRU")
public class Drum extends Componentes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "sensitivity")
    private String sensitivity;
    @Column(name = "captaincy")
    private String captaincy;

    public Drum() {
    }

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
    }

    public String getCaptaincy() {
        return captaincy;
    }

    public void setCaptaincy(String captaincy) {
        this.captaincy = captaincy;
    }

}
