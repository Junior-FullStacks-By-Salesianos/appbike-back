package com.salesianos.triana.appbike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.salesianos.triana.appbike.model.Trabajador;
import com.salesianos.triana.appbike.repository.TrabajadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrabajadorService {
    private final TrabajadorRepository trabajadorRepository;

    public List<Trabajador> findAll() {
        return trabajadorRepository.findAll();
    }

    public Trabajador findById(UUID id) {
        return trabajadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find a worker with id: " + id));
    }
}
