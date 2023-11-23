package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.repository.BicicletaRepository;
import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.model.Bicicleta;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BicicletaService {

    private final BicicletaRepository repository;

    public List<GetBicicletaDTO> findAll(){

        if(repository.findAll().isEmpty())
            throw new EntityNotFoundException("There are no bikes");

        return repository.findAll().stream().map(GetBicicletaDTO::of).toList();

    }

    public List<Bicicleta> findAllByStation(UUID uuidEstacion){
        return repository.findBicicletaByEstacionUuid(uuidEstacion);
    }

    public Optional<Bicicleta> findById(UUID uuid){
        return repository.findById(uuid);
    }

    public boolean existsById(UUID uuid){
        return repository.existsById(uuid);
    }

    public void deleteById(UUID uuid){
        repository.deleteById(uuid);
    }
}
