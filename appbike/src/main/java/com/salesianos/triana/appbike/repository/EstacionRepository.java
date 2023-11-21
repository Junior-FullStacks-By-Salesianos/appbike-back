package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstacionRepository extends JpaRepository<Estacion, UUID> {
}
