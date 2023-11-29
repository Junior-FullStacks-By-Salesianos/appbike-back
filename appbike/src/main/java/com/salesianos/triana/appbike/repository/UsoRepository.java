package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Uso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsoRepository extends JpaRepository<Uso, UUID> {

    @Query("SELECT u from Uso u WHERE u.author = :author")
    Page<Uso> findByAuthor(@Param("author") String author, Pageable pageable);

    @Query("SELECT u FROM Uso u WHERE u.author = :author AND u.fechaFin IS NULL")
    Optional<Uso> findCurrentUsoByUser(@Param("author") String author);

    @Query("SELECT u FROM Uso u WHERE u.bicicleta.uuid = :bicicletaId AND u.fechaFin IS NULL")
    Optional<Uso> findCurrentUsoByBicicleta(@Param("bicicletaId") UUID bicicletaId);

    @Query("SELECT u FROM Uso u WHERE u.fechaFin IS NOT NULL AND u.author = :author ORDER BY u.fechaFin DESC LIMIT 1")
    Optional<Uso> findLastUsoFinished(@Param("author") String author);

    @Query("SELECT u FROM Uso u")
    Page<Uso> findAllPageable(Pageable pageable);


}
