package com.salesianos.triana.appbike.repository;

import com.salesianos.triana.appbike.model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Long> {

    @Query("""
            select r from Revision r
            """)
    Page<Revision> searchPage(Pageable pageable);
}
