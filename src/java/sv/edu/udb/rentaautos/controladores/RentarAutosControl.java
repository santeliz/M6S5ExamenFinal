
package sv.edu.udb.rentaautos.controladores;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sv.edu.udb.rentaautos.entidades.Accesorio;
import sv.edu.udb.rentaautos.entidades.AlquilerVehiculo;
import sv.edu.udb.rentaautos.entidades.DetAccesorios;
import sv.edu.udb.rentaautos.entidades.Marca;
import sv.edu.udb.rentaautos.entidades.Modelo;
import sv.edu.udb.rentaautos.entidades.Transmision;
import sv.edu.udb.rentaautos.modelo.AccesorioFacade;
import sv.edu.udb.rentaautos.modelo.AlquilerVehiculoFacade;
import sv.edu.udb.rentaautos.modelo.MarcaFacade;
import sv.edu.udb.rentaautos.modelo.ModeloFacade;
import sv.edu.udb.rentaautos.modelo.TransmisionFacade;


@ManagedBean
@SessionScoped
public class RentarAutosControl implements Serializable {

    @EJB
    AccesorioFacade accesorioFacade;  
    
    @EJB
    TransmisionFacade transmisionFacade;
    
    @EJB
    MarcaFacade marcaFacade;
    
    @EJB
    ModeloFacade modeloFacade;
    
    @EJB
    AlquilerVehiculoFacade alquilerVehiculoFacade;
    
    
    private AlquilerVehiculo alquilerVehiculo;
    private DetAccesorios detAccesorios;
    private List<Marca> lstMarca;
    private List<Modelo> lstModelo;
    private List<Transmision> lstTransmision;
    private List<Accesorio> lstAccesorio;
    
    
    public RentarAutosControl() {
    }
    
    @PostConstruct
    public void init() {
        alquilerVehiculo = new AlquilerVehiculo();
        alquilerVehiculo.setCostoAlquiler(new BigDecimal(0.0));
        alquilerVehiculo.setDias(0);
        alquilerVehiculo.setDetAccesoriosList(new ArrayList<DetAccesorios>());
        detAccesorios = new DetAccesorios();
        listarMarcas();
        listarModelos();
        listarAccesorios();
        listarTransmisiones();
    }
    
    public void calcularCosto() {
        
        // validando que esten indicados los datos necesarios para el calculo
        if (alquilerVehiculo.getIdMarca() == null) return;
        if (alquilerVehiculo.getIdModelo()== null) return;
        if (alquilerVehiculo.getIdTransmision()== null) return;
        if (alquilerVehiculo.getDias()== null) return;
        
        BigDecimal costo = alquilerVehiculo.getIdModelo().getTarifa().add(alquilerVehiculo.getIdTransmision().getTarifa());
        costo = costo.multiply(new BigDecimal(alquilerVehiculo.getDias())).setScale(2, RoundingMode.UP);
        alquilerVehiculo.setCostoAlquiler(costo);
        
    }    
    
    public String agregarDetalle() {
    
        try {
            detAccesorios.setIdAlquilerVehiculo(alquilerVehiculo);
            alquilerVehiculo.getDetAccesoriosList().add(detAccesorios);
            
            // recalculando el costo del alquiler del auto
            calcularCosto();    // costo alquiler vehiculo
            
            // mas el costo de los accesorios.
            BigDecimal sumaAccesorios = new BigDecimal(0.0);
            for (int i=0; i < alquilerVehiculo.getDetAccesoriosList().size(); i++) {
                DetAccesorios da = alquilerVehiculo.getDetAccesoriosList().get(i);
                System.out.println("Tarifa Accesorio: " + da.getIdAccesorio().getAccesorio() + " - " + da.getIdAccesorio().getTarifa());
                sumaAccesorios = sumaAccesorios.add(da.getIdAccesorio().getTarifa());
            }
            
//            for(DetAccesorios da: alquilerVehiculo.getDetAccesoriosList() ) {
//                sumaAccesorios.add(da.getIdAccesorio().getTarifa());
//            }

            System.out.println("suma acesorios: " + sumaAccesorios);
            BigDecimal costoAccesorios = new BigDecimal(0.0);
            costoAccesorios = sumaAccesorios.multiply(new BigDecimal(alquilerVehiculo.getDias())).setScale(2, RoundingMode.UP);
            System.out.println("costo acesorios: " + costoAccesorios);
            alquilerVehiculo.setCostoAlquiler(alquilerVehiculo.getCostoAlquiler().add(costoAccesorios));
            
            // guardando datos
            alquilerVehiculo = alquilerVehiculoFacade.edit(alquilerVehiculo);
            
            // reinicializando los accesorios.
            detAccesorios = new DetAccesorios();
            
            
//            detalleFactura.setPrecio(detalleFactura.getIdProducto().getPrecio());
//            detalleFactura.setIdFactura(factura);
//            factura.getDetalleFacturaList().add(detalleFactura);
//            factura = facturaFacade.edit(factura);
//            detalleFactura = new DetalleFactura();
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos Guardados", "Se agregó el accesorio correspondiente."));
        } catch (Exception e) {
            System.out.println("ERROR al agregar el accesorio " + e);
        }
        return "rentarauto.xhtml?faces-redirect=true";
    }    
    
        public String borrarDetalle() {
    
        try {
            
            alquilerVehiculo.getDetAccesoriosList().remove(detAccesorios);
            
            // recalcular el costo
            
            calcularCosto();    // costo alquiler vihiculo
            
            // mas el costo de los accesorios.
            BigDecimal sumaAccesorios = new BigDecimal(0.0);
            for(DetAccesorios da: alquilerVehiculo.getDetAccesoriosList() ) {
                sumaAccesorios = sumaAccesorios.add(da.getIdAccesorio().getTarifa());
            }
            BigDecimal costoAccesorios = new BigDecimal(0.0);
            costoAccesorios = sumaAccesorios.multiply(new BigDecimal(alquilerVehiculo.getDias())).setScale(2, RoundingMode.UP);
            alquilerVehiculo.setCostoAlquiler(alquilerVehiculo.getCostoAlquiler().add(costoAccesorios));
            
            alquilerVehiculo = alquilerVehiculoFacade.edit(alquilerVehiculo);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos Borrado", "Se borro el accesorio correspondiente."));
        } catch (Exception e) {
            System.out.println("ERROR al borrar el accesorio " + e);
        }
        return "rentarauto.xhtml?faces-redirect=true";
    }    
    
    public List<Marca> listarMarcas() {
        lstMarca = marcaFacade.findAll();
        return lstMarca;
    }
    
    public List<Modelo> listarModelos(){
        lstModelo = modeloFacade.findAll();
        return lstModelo;
    }
    
    public List<Accesorio> listarAccesorios() {
        lstAccesorio = accesorioFacade.findAll();
        return lstAccesorio;
    }
    
    public List<Transmision> listarTransmisiones() {
        lstTransmision = transmisionFacade.findAll();
        return lstTransmision;
    }

    public AlquilerVehiculo getAlquilerVehiculo() {
        return alquilerVehiculo;
    }

    public void setAlquilerVehiculo(AlquilerVehiculo alquilerVehiculo) {
        this.alquilerVehiculo = alquilerVehiculo;
    }

    public DetAccesorios getDetAccesorios() {
        return detAccesorios;
    }

    public void setDetAccesorios(DetAccesorios detAccesorios) {
        this.detAccesorios = detAccesorios;
    }

    public List<Marca> getLstMarca() {
        return lstMarca;
    }

    public void setLstMarca(List<Marca> lstMarca) {
        this.lstMarca = lstMarca;
    }

    public List<Modelo> getLstModelo() {
        return lstModelo;
    }

    public void setLstModelo(List<Modelo> lstModelo) {
        this.lstModelo = lstModelo;
    }

    public List<Transmision> getLstTransmision() {
        return lstTransmision;
    }

    public void setLstTransmision(List<Transmision> lstTransmision) {
        this.lstTransmision = lstTransmision;
    }

    public List<Accesorio> getLstAccesorio() {
        return lstAccesorio;
    }

    public void setLstAccesorio(List<Accesorio> lstAccesorio) {
        this.lstAccesorio = lstAccesorio;
    }
    
    
    
}
