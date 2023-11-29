package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.Bike.EditBicicletaDTO;
import com.salesianos.triana.appbike.dto.Bike.PostBicicletaDTO;
import com.salesianos.triana.appbike.exception.*;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.repository.BicicletaRepository;
import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import com.salesianos.triana.appbike.repository.UsoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BicicletaService {

    private final BicicletaRepository repository;
    private final EstacionRepository estacionRepository;
    private final UsoRepository usoRepository;

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
        List<Bicicleta> bikes = repository.findBicicletaByEstacionUuid(uuidEstacion);
        if (!bikes.isEmpty()) {
            return bikes;
        }
        if (estacionRepository.findById(uuidEstacion).isPresent()) {
            throw new NotFoundException("Bicicleta");
        }
        throw new NotFoundException("Estación");
    }
    public Bicicleta saveDTO(PostBicicletaDTO nuevo) {

        List<Bicicleta> bicicletas = repository.findAll();
        Estacion estacion = estacionRepository.findByNumero(nuevo.estacion());

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

    public Bicicleta edit(String nombre, EditBicicletaDTO editado) {

        List<Bicicleta> allBikes = repository.findAll();

        boolean existe = allBikes.stream()
                .anyMatch(bicicleta -> bicicleta.getNombre().equals(nombre));

        if (!existe) {
            throw new NameOfBikeNotFoundException("That name does not exist, change it please");
        }

        Optional<Bicicleta> bikeOptional = repository.findByNombre(nombre);

        if(bikeOptional.isPresent()){
            Bicicleta bike = bikeOptional.get();

            bike.setModelo(editado.modelo());
            bike.setMarca(editado.marca());
            bike.setEstado(editado.estado());
            bike.setEstacion(editado.estacion() == null ? null : addBicicletaToStationByNumber(editado.estacion()));

            return repository.save(bike);
        }
            throw new BadRequestForBikeAddException("Enter valid request please");

    }

    public Estacion addBicicletaToStationByNumber(Long number) {
        return estacionRepository.findByNumero(number);
    }

    public Bicicleta findById(UUID uuid) {
        if (repository.findById(uuid).isPresent()) {
            return repository.findById(uuid).get();
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

    public boolean existsById(UUID uuid) {
        return repository.existsById(uuid);
    }

    public void deleteByName(String name) {
        Bicicleta bike = findByName(name);
        bike.removeUsosFromBicicleta();
        repository.delete(bike);
    }

    public Optional<Bicicleta> isBikeAvailable(String nombre){
        return repository.isBikeNotAvailableByNameOfBike(nombre);
    }
}
