package com.salesianos.triana.appbike.revision;

import com.salesianos.triana.appbike.revision.Revision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RevisionRepository extends JpaRepository<Revision, UUID> {
}
