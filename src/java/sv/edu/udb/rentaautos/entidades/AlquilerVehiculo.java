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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JAVA
 */
@Entity
@Table(name = "ALQUILER_VEHICULO")
@NamedQueries({
    @NamedQuery(name = "AlquilerVehiculo.findAll", query = "SELECT a FROM AlquilerVehiculo a"),
    @NamedQuery(name = "AlquilerVehiculo.findByIdAlquilerVehiculo", query = "SELECT a FROM AlquilerVehiculo a WHERE a.idAlquilerVehiculo = :idAlquilerVehiculo"),
    @NamedQuery(name = "AlquilerVehiculo.findByDias", query = "SELECT a FROM AlquilerVehiculo a WHERE a.dias = :dias"),
    @NamedQuery(name = "AlquilerVehiculo.findByCostoAlquiler", query = "SELECT a FROM AlquilerVehiculo a WHERE a.costoAlquiler = :costoAlquiler")})
public class AlquilerVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALQUILER_VEHICULO")
    private Integer idAlquilerVehiculo;
    @Column(name = "DIAS")
    private Integer dias;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ALQUILER")
    private BigDecimal costoAlquiler;
//    @OneToMany(mappedBy = "idAlquilerVehiculo")
    @OneToMany(mappedBy = "idAlquilerVehiculo", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)  // para que cuando grave Factura tambien guarde DetalleFactura
    private List<DetAccesorios> detAccesoriosList;
    @JoinColumn(name = "ID_MARCA", referencedColumnName = "ID_MARCA")
    @ManyToOne
    private Marca idMarca;
    @JoinColumn(name = "ID_MODELO", referencedColumnName = "ID_MODELO")
    @ManyToOne
    private Modelo idModelo;
    @JoinColumn(name = "ID_TRANSMISION", referencedColumnName = "ID_TRANSMISION")
    @ManyToOne
    private Transmision idTransmision;

    public AlquilerVehiculo() {
    }

    public AlquilerVehiculo(Integer idAlquilerVehiculo) {
        this.idAlquilerVehiculo = idAlquilerVehiculo;
    }

    public AlquilerVehiculo(Integer idAlquilerVehiculo, BigDecimal costoAlquiler) {
        this.idAlquilerVehiculo = idAlquilerVehiculo;
        this.costoAlquiler = costoAlquiler;
    }

    public Integer getIdAlquilerVehiculo() {
        return idAlquilerVehiculo;
    }

    public void setIdAlquilerVehiculo(Integer idAlquilerVehiculo) {
        this.idAlquilerVehiculo = idAlquilerVehiculo;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public BigDecimal getCostoAlquiler() {
        return costoAlquiler;
    }

    public void setCostoAlquiler(BigDecimal costoAlquiler) {
        this.costoAlquiler = costoAlquiler;
    }

    public List<DetAccesorios> getDetAccesoriosList() {
        return detAccesoriosList;
    }

    public void setDetAccesoriosList(List<DetAccesorios> detAccesoriosList) {
        this.detAccesoriosList = detAccesoriosList;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    public Transmision getIdTransmision() {
        return idTransmision;
    }

    public void setIdTransmision(Transmision idTransmision) {
        this.idTransmision = idTransmision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlquilerVehiculo != null ? idAlquilerVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlquilerVehiculo)) {
            return false;
        }
        AlquilerVehiculo other = (AlquilerVehiculo) object;
        if ((this.idAlquilerVehiculo == null && other.idAlquilerVehiculo != null) || (this.idAlquilerVehiculo != null && !this.idAlquilerVehiculo.equals(other.idAlquilerVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.rentaautos.entidades.AlquilerVehiculo[ idAlquilerVehiculo=" + idAlquilerVehiculo + " ]";
    }
    
}
