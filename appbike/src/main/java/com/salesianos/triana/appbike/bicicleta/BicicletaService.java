package com.salesianos.triana.appbike.bicicleta;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BicicletaService {

    private final BicicletaRepository repository;

    public List<Bicicleta> findAll(){
        return repository.findAll();
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
