package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.UsuarioBici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioBiciRepository extends JpaRepository<UsuarioBici, UUID> {
}
