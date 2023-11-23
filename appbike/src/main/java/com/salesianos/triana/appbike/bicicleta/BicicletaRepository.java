package com.salesianos.triana.appbike.bicicleta;

import com.salesianos.triana.appbike.bicicleta.dto.GetBicicletaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BicicletaRepository extends JpaRepository<Bicicleta, UUID> {

    @Query(value = "SELECT  * FROM bicicleta b WHERE b.estacion_id = :uuid" ,nativeQuery = true)
    List<Bicicleta> findBicicletaByEstacionUuid(@Param("uuid") UUID uuid);
}
