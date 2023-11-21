package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrabajadorRepository extends JpaRepository<Trabajador, UUID> {
}
