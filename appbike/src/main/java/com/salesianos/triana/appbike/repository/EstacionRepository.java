package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Estacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, UUID> {


    @Query("""
            select e from Estacion e
            """)
    Page<Estacion> searchPage(Pageable pageable);

    @Query("SELECT COUNT(b) FROM Bicicleta b WHERE b.estacion.numero = :numeroEstacion")
    int countBikesInAStationForUuid(@Param("numeroEstacion") Long numeroEstacion);

    @Query("SELECT e FROM Estacion e WHERE e.numero = :numero")
    Estacion findByNumero(Long numero);

    /*
    @Query("SELECT COUNT(b.estacion_id) AS cantidad_bicicletas\n" +
           "FROM estacion e\n" +
           "LEFT JOIN bicicleta b ON e.id = b.estacion_id\n" +
           "WHERE e.id = :uuid\n" +
           "GROUP BY e.id, e.nombre;")
    int bikeInStation(UUID uuid);
*/

    @Modifying
    @Query("DELETE FROM Estacion e WHERE e.numero = :numero")
    void deleteByNumero(Long numero);

    Optional<Estacion> findByNombre(String s);
}
