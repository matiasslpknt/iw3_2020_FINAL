package ar.edu.iua.model;

import javax.persistence.*;
import java.io.Serializable;

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
}
