/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.rentaautos.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JAVA
 */
@Entity
@Table(name = "TRANSMISION")
@NamedQueries({
    @NamedQuery(name = "Transmision.findAll", query = "SELECT t FROM Transmision t"),
    @NamedQuery(name = "Transmision.findByIdTransmision", query = "SELECT t FROM Transmision t WHERE t.idTransmision = :idTransmision"),
    @NamedQuery(name = "Transmision.findByTransmision", query = "SELECT t FROM Transmision t WHERE t.transmision = :transmision"),
    @NamedQuery(name = "Transmision.findByTarifa", query = "SELECT t FROM Transmision t WHERE t.tarifa = :tarifa")})
public class Transmision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRANSMISION")
    private Integer idTransmision;
    @Size(max = 100)
    @Column(name = "TRANSMISION")
    private String transmision;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TARIFA")
    private BigDecimal tarifa;
    @OneToMany(mappedBy = "idTransmision")
    private List<AlquilerVehiculo> alquilerVehiculoList;

    public Transmision() {
    }

    public Transmision(Integer idTransmision) {
        this.idTransmision = idTransmision;
    }

    public Integer getIdTransmision() {
        return idTransmision;
    }

    public void setIdTransmision(Integer idTransmision) {
        this.idTransmision = idTransmision;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    public List<AlquilerVehiculo> getAlquilerVehiculoList() {
        return alquilerVehiculoList;
    }

    public void setAlquilerVehiculoList(List<AlquilerVehiculo> alquilerVehiculoList) {
        this.alquilerVehiculoList = alquilerVehiculoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransmision != null ? idTransmision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transmision)) {
            return false;
        }
        Transmision other = (Transmision) object;
        if ((this.idTransmision == null && other.idTransmision != null) || (this.idTransmision != null && !this.idTransmision.equals(other.idTransmision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.rentaautos.entidades.Transmision[ idTransmision=" + idTransmision + " ]";
    }
    
}
