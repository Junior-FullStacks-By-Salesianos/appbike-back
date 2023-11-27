package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface EstacionRepository extends JpaRepository<Estacion, UUID> {
    @Query("SELECT e FROM Estacion e WHERE e.numero = :numero")
    Estacion findByNumero(Long numero);

    @Modifying
    @Query("DELETE FROM Estacion e WHERE e.numero = :numero")
    void deleteByNumero(Long numero);
}
