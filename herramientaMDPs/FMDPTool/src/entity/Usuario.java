/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuario.findByContra", query = "SELECT u FROM Usuario u WHERE u.contra = :contra"),
    @NamedQuery(name = "Usuario.findByContraPass", query = "SELECT u FROM Usuario u WHERE u.contra = :contra and u.nombreUsuario =:nombreUsuario"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApaterno", query = "SELECT u FROM Usuario u WHERE u.apaterno = :apaterno"),
    @NamedQuery(name = "Usuario.findByAmaterno", query = "SELECT u FROM Usuario u WHERE u.amaterno = :amaterno")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "contra")
    private String contra;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apaterno")
    private String apaterno;
    @Basic(optional = false)
    @Column(name = "amaterno")
    private String amaterno;
    @JoinColumn(name = "idnivel_inicial", referencedColumnName = "idnivel")
    @ManyToOne
    private Nivelusuario nivelusuario;
    @JoinColumn(name = "idTipoUsuario", referencedColumnName = "idTipoUsuario")
    @ManyToOne(optional = false)
    private Tipousuarios tipousuarios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Sesiones> sesionesCollection;

    public Usuario() {
    }

    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Usuario(String nombreUsuario, String contra, String nombre, String apaterno, String amaterno) {
        this.nombreUsuario = nombreUsuario;
        this.contra = contra;
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public Nivelusuario getNivelusuario() {
        return nivelusuario;
    }

    public void setNivelusuario(Nivelusuario nivelusuario) {
        this.nivelusuario = nivelusuario;
    }

    public Tipousuarios getTipousuarios() {
        return tipousuarios;
    }

    public void setTipousuarios(Tipousuarios tipousuarios) {
        this.tipousuarios = tipousuarios;
    }

    public Collection<Sesiones> getSesionesCollection() {
        return sesionesCollection;
    }

    public void setSesionesCollection(Collection<Sesiones> sesionesCollection) {
        this.sesionesCollection = sesionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.nombreUsuario == null && other.nombreUsuario != null) || (this.nombreUsuario != null && !this.nombreUsuario.equals(other.nombreUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Usuario[nombreUsuario=" + nombreUsuario + "]";
    }

}
