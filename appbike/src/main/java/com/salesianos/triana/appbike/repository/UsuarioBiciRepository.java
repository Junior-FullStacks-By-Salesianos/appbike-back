package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.UsuarioBici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioBiciRepository extends JpaRepository<UsuarioBici, UUID> {

    @Query("SELECT u FROM Uso u WHERE u.author = :userId AND u.fechaFin IS NULL")
    Optional<Uso> findActiveUsoByUsuario(@Param("userId") String userId);

}
