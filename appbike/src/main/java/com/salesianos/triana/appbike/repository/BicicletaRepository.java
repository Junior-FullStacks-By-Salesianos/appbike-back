package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Bicicleta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BicicletaRepository extends JpaRepository<Bicicleta, UUID> {

    @Query("""
            select b from Bicicleta b
            """)
    Page<Bicicleta> searchPage(Pageable pageable);

    @Query(value = "SELECT  * FROM bicicleta b WHERE b.estacion_id = :uuid" ,nativeQuery = true)
    List<Bicicleta> findBicicletaByEstacionUuid(@Param("uuid") UUID uuid);
}
