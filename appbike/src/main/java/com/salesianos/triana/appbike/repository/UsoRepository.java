package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Uso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsoRepository extends JpaRepository<Uso, UUID> {

    List<Uso> findByAuthor(String author);

    @Query("SELECT u FROM Uso u WHERE u.author = :author AND u.fechaFin IS NULL")
    Optional<Uso> findCurrentUsoByUser(@Param("author") String author);

    @Query("SELECT u FROM Uso u WHERE u.bicicleta.uuid = :bicicletaId AND u.fechaFin IS NULL")
    Optional<Uso> findCurrentUsoByBicicleta(@Param("bicicletaId") UUID bicicletaId);

    Optional<Uso> findById(Long id);
}
