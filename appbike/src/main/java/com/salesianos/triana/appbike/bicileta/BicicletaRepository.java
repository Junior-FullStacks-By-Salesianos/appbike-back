package com.salesianos.triana.appbike.bicileta;

import com.salesianos.triana.appbike.bicileta.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BicicletaRepository extends JpaRepository<Bicicleta, UUID> {
}
