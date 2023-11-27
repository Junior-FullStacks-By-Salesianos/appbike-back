package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.repository.BicicletaRepository;
import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.repository.EstacionRepository;
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
    private final EstacionRepository estacionRepository;

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
        List<Bicicleta> bikes = repository.findBicicletaByEstacionUuid(uuidEstacion);
        if(!bikes.isEmpty()){
            return bikes;
        }
        if(estacionRepository.findById(uuidEstacion).isPresent()){
            throw new NotFoundException("Bicicleta");
        }
        throw new NotFoundException("Estación");
    }

    public Bicicleta findById(UUID uuid){
        Optional<Bicicleta> bike = repository.findById(uuid);
        if(bike.isPresent()) {
            return bike.get();
        }
        throw new NotFoundException("Bicicleta");
    }

    public Bicicleta findByName(String nombre){
        Optional<Bicicleta> bike = repository.findByNombre(nombre);
        if(bike.isPresent()){
            return bike.get();
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
