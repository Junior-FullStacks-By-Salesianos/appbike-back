package com.salesianos.triana.appbike.uso;

import com.salesianos.triana.appbike.uso.Uso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsoRepository extends JpaRepository<Uso, UUID> {
}
