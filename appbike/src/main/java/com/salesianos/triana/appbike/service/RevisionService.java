package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.Revision.NewRevisionDTO;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.model.EstadoRevision;
import com.salesianos.triana.appbike.model.Revision;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import com.salesianos.triana.appbike.repository.RevisionRepository;
import com.salesianos.triana.appbike.dto.Revision.RevisionDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RevisionService {
    private final RevisionRepository revisionRepository;
    private final TrabajadorService trabajadorService;
    private final EstacionRepository estacionRepository;

    public List<RevisionDTO> findAll() {
        if(revisionRepository.findAll().isEmpty())
            throw new EntityNotFoundException("There are no issues.");

        return revisionRepository.findAll().stream()
                .map(RevisionDTO::of)
                .toList();
    }

    public Page<RevisionDTO> searchPage(Pageable pageable){
        Page<RevisionDTO> pagedResult = revisionRepository.searchPage(pageable).map(RevisionDTO::of);

        if(pagedResult.isEmpty())
            throw new EntityNotFoundException("There are no issues.");

        return pagedResult;
    }

    public RevisionDTO edit(Long id, RevisionDTO r){



        return revisionRepository.findById(id).map(old -> {
            old.setFechaProgramada(r.fechaProgramada());
            old.setAnotaciones(r.anotaciones());
            old.setEstacion(estacionRepository.findById(r.estacion().id()).orElse(null));
            old.setTrabajador(trabajadorService.findById(r.trabajador().id()));
            old.setEstado(r.estado());
            if(r.estado() == EstadoRevision.FINISHED)
                old.setFechaRealizacion(LocalDate.now());

            return RevisionDTO.of(revisionRepository.save(old));
        }).orElseThrow(() -> new EntityNotFoundException("Unable to find issue" +
                " with id: " + id));
    }

    public Revision save(NewRevisionDTO revisionDTO){
        //Busco la estación porque no se puede hacer GetStationDTO toEntity

        return revisionRepository.save(NewRevisionDTO.toEntity(revisionDTO,
                estacionRepository.findById(revisionDTO.estacion()).orElse(null),
                trabajadorService.findById(revisionDTO.trabajador())));
    }

    public void delete(Long id){
        if(revisionRepository.existsById(id))
            revisionRepository.deleteById(id);
    }
}
