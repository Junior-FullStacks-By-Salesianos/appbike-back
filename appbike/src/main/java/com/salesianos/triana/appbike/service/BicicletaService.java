package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.Bike.PostBicicletaDTO;
import com.salesianos.triana.appbike.exception.*;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.repository.BicicletaRepository;
import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BicicletaService {

    private final BicicletaRepository repository;
    private final EstacionRepository repositoryStation;

    public List<GetBicicletaDTO> findAll() {

        if (repository.findAll().isEmpty())
            throw new EntityNotFoundException("There are no bikes");

        return repository.findAll().stream().map(GetBicicletaDTO::of).toList();

    }

    public Page<Bicicleta> searchPage(Pageable pageable) {
        Page<Bicicleta> pagedResult = repository.searchPage(pageable);

        if (pagedResult.isEmpty())
            throw new EntityNotFoundException("There are no bikes in that page.");

        return pagedResult;
    }

    public List<Bicicleta> findAllByStation(UUID uuidEstacion) {

        if (repository.findBicicletaByEstacionUuid(uuidEstacion).isEmpty())
            throw new NoBikesInThatStationException("Station not found");

        return repository.findBicicletaByEstacionUuid(uuidEstacion);
    }

    public Bicicleta saveDTO(PostBicicletaDTO nuevo) {

        List<Bicicleta> bicicletas = repository.findAll();
        Estacion estacion = repositoryStation.findByNumero(nuevo.estacion());

        boolean nameExists = bicicletas.stream()
                .anyMatch(b -> b.getNombre().equals(nuevo.nombre()));

        if (nameExists) {
            throw new BikeWithSameNameException("Enter another bike name, there is currently a bike with that name");
        }

        if (estacion!= null && estacion.getBicicletas().size() >= estacion.getCapacidad()) {
            throw new StationWithoutCapacityException("Station without more capacity of bikes");
        }
        Bicicleta bike = new Bicicleta();
        bike.setNombre(nuevo.nombre());
        bike.setMarca(nuevo.marca());
        bike.setModelo(nuevo.modelo());
        bike.setEstacion(nuevo.estacion() == null ? null : addBicicletaToStationByNumber(nuevo.estacion()));
        bike.setEstado(nuevo.estado());
        bike.setUsos(Collections.emptyList());

        return repository.save(bike);

    }

    public Estacion addBicicletaToStationByNumber(Long number) {
        return repositoryStation.findByNumero(number);
    }

    public Bicicleta findById(UUID uuid) {
        if (repository.findById(uuid).isPresent()) {
            return repository.findById(uuid).get();
        }
        throw new NotFoundException("Bicicleta");
    }

    public Bicicleta findByName(String nombre) {
        if (repository.findByNombre(nombre) != null) {
            return repository.findByNombre(nombre);
        }
        throw new NotFoundException("Bicicleta");
    }

    public boolean existsById(UUID uuid) {
        return repository.existsById(uuid);
    }

    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }
}
