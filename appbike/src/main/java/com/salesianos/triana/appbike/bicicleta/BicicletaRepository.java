package com.salesianos.triana.appbike.bicicleta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BicicletaRepository extends JpaRepository<Bicicleta, UUID> {
}
