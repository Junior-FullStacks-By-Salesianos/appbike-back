package com.salesianos.triana.appbike.usuariobici;

import com.salesianos.triana.appbike.usuariobici.UsuarioBici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioBiciRepository extends JpaRepository<UsuarioBici, UUID> {
}
