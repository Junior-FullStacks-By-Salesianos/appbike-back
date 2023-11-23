package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RevisionRepository extends JpaRepository<Revision, UUID> {
}
