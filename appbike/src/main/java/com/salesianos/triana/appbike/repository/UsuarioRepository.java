package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findFirstByUsername(String username);
}
