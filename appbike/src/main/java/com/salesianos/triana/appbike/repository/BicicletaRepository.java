package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Bicicleta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, UUID> {

    @Query("""
            select b from Bicicleta b
            """)
    Page<Bicicleta> searchPage(Pageable pageable);

    @Query(value = "SELECT  * FROM bicicleta b WHERE b.estacion_id = :uuid", nativeQuery = true)
    List<Bicicleta> findBicicletaByEstacionUuid(@Param("uuid") UUID uuid);

    Optional<Bicicleta> findByNombre(String nombre);

    @Query("SELECT b FROM Bicicleta b LEFT JOIN FETCH b.usos u WHERE b.nombre = :nombre AND u.fechaFin IS NULL")
    Optional<Bicicleta> isBikeNotAvailableByNameOfBike(@Param("nombre") String nombre);

}
