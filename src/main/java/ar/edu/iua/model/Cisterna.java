package ar.edu.iua.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="cisterna")
public class Cisterna implements Serializable {

    private static final long serialVersionUID = -1761751975510718871L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    private Double capacidad;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "camion_id")
    @JsonBackReference
    private Camion camion;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Double capacidad) {
        this.capacidad = capacidad;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }
}
