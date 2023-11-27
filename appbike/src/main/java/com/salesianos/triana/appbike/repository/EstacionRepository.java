package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, UUID> {

    Estacion findByNumero(Long number);

    @Query("SELECT COUNT(b) FROM Bicicleta b WHERE b.estacion.numero = :numeroEstacion")
    int countBikesInAStationForUuid(@Param("numeroEstacion") Long numeroEstacion);
}
