package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Coste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CosteRepository extends JpaRepository<Coste, Long> {

    @Query("SELECT c FROM Coste c WHERE c.fechaFin IS NULL")
    Coste getCurrentCost();

}
