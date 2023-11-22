package com.salesianos.triana.appbike.estacion;

import com.salesianos.triana.appbike.estacion.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstacionRepository extends JpaRepository<Estacion, UUID> {
}
