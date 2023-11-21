package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Uso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsoRepository extends JpaRepository<Uso, UUID> {
}
