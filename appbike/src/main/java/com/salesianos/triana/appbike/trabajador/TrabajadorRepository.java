package com.salesianos.triana.appbike.trabajador;

import com.salesianos.triana.appbike.trabajador.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrabajadorRepository extends JpaRepository<Trabajador, UUID> {
}
