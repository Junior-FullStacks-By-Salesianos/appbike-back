package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.Revision.NewRevisionDTO;
import com.salesianos.triana.appbike.exception.DateEarlierThanTodayException;
import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.model.EstadoRevision;
import com.salesianos.triana.appbike.model.Revision;
import com.salesianos.triana.appbike.model.Trabajador;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import com.salesianos.triana.appbike.repository.RevisionRepository;
import com.salesianos.triana.appbike.dto.Revision.RevisionDTO;
import com.salesianos.triana.appbike.repository.TrabajadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
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
    private final EstacionRepository estacionRepository;
    private final TrabajadorRepository trabajadorRepository;

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

        Optional<Estacion> e = estacionRepository.findByNombre(r.nombreEstacion());
        Optional<Trabajador> t = trabajadorRepository.findByNombre(r.nombreTrabajador());

        return revisionRepository.findById(id).map(old -> {
            old.setFechaRealizacion(r.fechaRealizacion());
            old.setFechaProgramada(r.fechaProgramada());
            old.setAnotaciones(r.anotaciones());
            old.setEstacion(e.orElseThrow(() -> new NotFoundException("Estacion")));
            old.setTrabajador(t.orElseThrow(() -> new NotFoundException("Trabajador")));
            old.setEstado(r.estado());
            return RevisionDTO.of(revisionRepository.save(old));
        }).orElseThrow(() -> new EntityNotFoundException("Unable to find issue" +
                " with id: " + id));
    }

    public Revision save(NewRevisionDTO newDTO){
        Estacion e = estacionRepository.findByNombre(newDTO.nombreEstacion()).orElseThrow(() -> new NotFoundException("Estacion"));
        Trabajador t = trabajadorRepository.findByNombre(newDTO.nombreTrabajador()).orElseThrow(() -> new NotFoundException("Trabajador"));

        if(newDTO.fechaProgramada().isBefore(LocalDate.now()))
            throw new DateEarlierThanTodayException("Cannot create an issue with a deadline date which is earlier than the current date.");

        return revisionRepository.save(NewRevisionDTO.toEntity(newDTO,e,t));
    }

    public void delete(Long id){
        if(revisionRepository.existsById(id))
            revisionRepository.deleteById(id);
    }
}
