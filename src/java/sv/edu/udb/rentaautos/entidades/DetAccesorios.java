/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.rentaautos.entidades;

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
import javax.validation.constraints.NotNull;

/**
 *
 * @author JAVA
 */
@Entity
@Table(name = "DET_ACCESORIOS")
@NamedQueries({
    @NamedQuery(name = "DetAccesorios.findAll", query = "SELECT d FROM DetAccesorios d"),
    @NamedQuery(name = "DetAccesorios.findByIdDetAccesorios", query = "SELECT d FROM DetAccesorios d WHERE d.idDetAccesorios = :idDetAccesorios")})
public class DetAccesorios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DET_ACCESORIOS")
    private Integer idDetAccesorios;
    @JoinColumn(name = "ID_ACCESORIO", referencedColumnName = "ID_ACCESORIO")
    @ManyToOne
    private Accesorio idAccesorio;
    @JoinColumn(name = "ID_ALQUILER_VEHICULO", referencedColumnName = "ID_ALQUILER_VEHICULO")
    @ManyToOne
    private AlquilerVehiculo idAlquilerVehiculo;

    public DetAccesorios() {
    }

    public DetAccesorios(Integer idDetAccesorios) {
        this.idDetAccesorios = idDetAccesorios;
    }

    public Integer getIdDetAccesorios() {
        return idDetAccesorios;
    }

    public void setIdDetAccesorios(Integer idDetAccesorios) {
        this.idDetAccesorios = idDetAccesorios;
    }

    public Accesorio getIdAccesorio() {
        return idAccesorio;
    }

    public void setIdAccesorio(Accesorio idAccesorio) {
        this.idAccesorio = idAccesorio;
    }

    public AlquilerVehiculo getIdAlquilerVehiculo() {
        return idAlquilerVehiculo;
    }

    public void setIdAlquilerVehiculo(AlquilerVehiculo idAlquilerVehiculo) {
        this.idAlquilerVehiculo = idAlquilerVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetAccesorios != null ? idDetAccesorios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetAccesorios)) {
            return false;
        }
        DetAccesorios other = (DetAccesorios) object;
        if ((this.idDetAccesorios == null && other.idDetAccesorios != null) || (this.idDetAccesorios != null && !this.idDetAccesorios.equals(other.idDetAccesorios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.rentaautos.entidades.DetAccesorios[ idDetAccesorios=" + idDetAccesorios + " ]";
    }
    
}
