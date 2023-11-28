package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, UUID> {

    Optional<Trabajador> findByNombre(String nombre);
}
