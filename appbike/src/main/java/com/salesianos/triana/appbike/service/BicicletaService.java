package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.error.NoBikesInThatStationException;
import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.repository.BicicletaRepository;
import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.model.Bicicleta;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Bicicleta> searchPage(Pageable pageable){
        Page<Bicicleta> pagedResult = repository.searchPage(pageable);

        if(pagedResult.isEmpty())
            throw new EntityNotFoundException("There are no bikes in that page.");

        return pagedResult;
    }

    public List<Bicicleta> findAllByStation(UUID uuidEstacion){

        if(repository.findBicicletaByEstacionUuid(uuidEstacion).isEmpty())
            throw new NoBikesInThatStationException(uuidEstacion);

        return repository.findBicicletaByEstacionUuid(uuidEstacion);
    }

    public Bicicleta findById(UUID uuid){
        if(repository.findById(uuid).isPresent()) {
            return repository.findById(uuid).get();
        }
        throw new NotFoundException("Bicicleta");
    }

    public Bicicleta findByName(String nombre){
        if(repository.findByNombre(nombre) !=null){
            return repository.findByNombre(nombre);
        }
        throw new NotFoundException("Bicicleta");
    }

    public boolean existsById(UUID uuid){
        return repository.existsById(uuid);
    }

    public void deleteById(UUID uuid){
        repository.deleteById(uuid);
    }
}