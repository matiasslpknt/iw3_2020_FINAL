package ar.edu.iua.model.persistence;

import ar.edu.iua.model.OrdenDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO orden_detalle (id_orden, caudal, densidad, temperatura, masa_acumulada) VALUES (?, ?, ?, ?, ?);", nativeQuery = true)
    void guardarOrdenDetale(long idOrden, double caudal , double densidad, double temperatura, double masaAcumulada);
}
