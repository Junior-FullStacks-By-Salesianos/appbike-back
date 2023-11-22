package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BicicletaRepository extends JpaRepository<Bicicleta, UUID> {

    Bicicleta findByUuid(UUID id);

}
