package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, UUID> {
}
