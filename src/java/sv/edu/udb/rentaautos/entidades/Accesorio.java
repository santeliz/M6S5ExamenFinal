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
@Table(name = "ACCESORIO")
@NamedQueries({
    @NamedQuery(name = "Accesorio.findAll", query = "SELECT a FROM Accesorio a"),
    @NamedQuery(name = "Accesorio.findByIdAccesorio", query = "SELECT a FROM Accesorio a WHERE a.idAccesorio = :idAccesorio"),
    @NamedQuery(name = "Accesorio.findByAccesorio", query = "SELECT a FROM Accesorio a WHERE a.accesorio = :accesorio"),
    @NamedQuery(name = "Accesorio.findByTarifa", query = "SELECT a FROM Accesorio a WHERE a.tarifa = :tarifa")})
public class Accesorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ACCESORIO")
    private Integer idAccesorio;
    @Size(max = 100)
    @Column(name = "ACCESORIO")
    private String accesorio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TARIFA")
    private BigDecimal tarifa;
    @OneToMany(mappedBy = "idAccesorio")
    private List<DetAccesorios> detAccesoriosList;

    public Accesorio() {
    }

    public Accesorio(Integer idAccesorio) {
        this.idAccesorio = idAccesorio;
    }

    public Integer getIdAccesorio() {
        return idAccesorio;
    }

    public void setIdAccesorio(Integer idAccesorio) {
        this.idAccesorio = idAccesorio;
    }

    public String getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(String accesorio) {
        this.accesorio = accesorio;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    public List<DetAccesorios> getDetAccesoriosList() {
        return detAccesoriosList;
    }

    public void setDetAccesoriosList(List<DetAccesorios> detAccesoriosList) {
        this.detAccesoriosList = detAccesoriosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccesorio != null ? idAccesorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accesorio)) {
            return false;
        }
        Accesorio other = (Accesorio) object;
        if ((this.idAccesorio == null && other.idAccesorio != null) || (this.idAccesorio != null && !this.idAccesorio.equals(other.idAccesorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.rentaautos.entidades.Accesorio[ idAccesorio=" + idAccesorio + " ]";
    }
    
}
