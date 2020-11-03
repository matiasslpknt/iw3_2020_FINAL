package ar.edu.iua.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orden")
public class Orden implements Serializable {

    private static final long serialVersionUID = -6842932053341232133L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String numeroOrden;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "camion_id")
    private Camion camion;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @OneToOne(cascade =  CascadeType.ALL)
    private OrdenDetalle ordenDetalle;

    @OneToOne(cascade =  CascadeType.ALL)
    private Conciliacion conciliacion;

    @OneToMany(targetEntity = OrdenDetalle.class, mappedBy = "orden", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<OrdenDetalle> ordenDetalleList;

    private double masaAcumulada;

    private double densidad;

    private double temperatura;

    private double caudal;

    private Date fechaUltimoAlmacenamiento;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public OrdenDetalle getOrdenDetalle() {
        return ordenDetalle;
    }

    public void setOrdenDetalle(OrdenDetalle ordenDetalle) {
        this.ordenDetalle = ordenDetalle;
    }

    public Conciliacion getConciliacion() {
        return conciliacion;
    }

    public void setConciliacion(Conciliacion conciliacion) {
        this.conciliacion = conciliacion;
    }

    public List<OrdenDetalle> getOrdenDetalleList() {
        return ordenDetalleList;
    }

    public void setOrdenDetalleList(List<OrdenDetalle> ordenDetalleList) {
        this.ordenDetalleList = ordenDetalleList;
    }

    public double getMasaAcumulada() {
        return masaAcumulada;
    }

    public void setMasaAcumulada(double masaAcumulada) {
        this.masaAcumulada = masaAcumulada;
    }

    public double getDensidad() {
        return densidad;
    }

    public void setDensidad(double densidad) {
        this.densidad = densidad;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getCaudal() {
        return caudal;
    }

    public void setCaudal(double caudal) {
        this.caudal = caudal;
    }

    public Date getFechaUltimoAlmacenamiento() {
        return fechaUltimoAlmacenamiento;
    }

    public void setFechaUltimoAlmacenamiento(Date fechaUltimoAlmacenamiento) {
        this.fechaUltimoAlmacenamiento = fechaUltimoAlmacenamiento;
    }
}
