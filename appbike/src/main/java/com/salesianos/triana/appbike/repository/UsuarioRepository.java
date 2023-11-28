package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findFirstByUsername(String username);
    boolean existsByUsernameIgnoreCase(String username);

}
