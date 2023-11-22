package com.salesianos.triana.appbike.revision;

import com.salesianos.triana.appbike.revision.dto.EditRevisionDTO;
import com.salesianos.triana.appbike.trabajador.TrabajadorRepository;
import com.salesianos.triana.appbike.trabajador.TrabajadorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RevisionService {
    private final RevisionRepository revisionRepository;
    private final TrabajadorService trabajadorService;

    public List<Revision> findAll() {
        if(revisionRepository.findAll().isEmpty())
            throw new EntityNotFoundException("There are no issues.");

        return revisionRepository.findAll();
    }

    public EditRevisionDTO edit(Long id, EditRevisionDTO r){

        return revisionRepository.findById(id).map(old -> {
            old.setFechaProgramada(r.fechaProgramada());
            old.setAnotaciones(r.anotaciones());
            old.setEstacion(r.estacion());
            old.setTrabajador(trabajadorService.findById(r.trabajador().id()));
            old.setEstado(r.estado());
            return EditRevisionDTO.of(revisionRepository.save(old));
        }).orElseThrow(() -> new EntityNotFoundException("Unable to find issue" +
                " with id: " + id));
    }
}
