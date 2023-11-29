package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.Station.AddStationDto;
import com.salesianos.triana.appbike.dto.Station.EditStationDto;
import com.salesianos.triana.appbike.dto.Station.GetStationDto;
import com.salesianos.triana.appbike.dto.Station.StationResponse;
import com.salesianos.triana.appbike.exception.NotFoundException;
import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstacionService {

    private final EstacionRepository estacionRepository;

    public Estacion newStation(AddStationDto nuevo) {
        Estacion e = new Estacion();
        e.setNombre(nuevo.nombre());
        e.setCapacidad(nuevo.capacidad());
        e.setNumero((long) (estacionRepository.findAll().size() + 1));
        e.setCoordenadas(nuevo.coordenadas());
        return estacionRepository.save(e);
    }

    public Page<Estacion> searchPage(Pageable pageable) {
        Page<Estacion> pagedResult = estacionRepository.searchPage(pageable);

        if (pagedResult.isEmpty())
            throw new EntityNotFoundException("There are no stations in that page.");

        return pagedResult;
    }
    public List<GetStationDto> findAll() {

        return estacionRepository.findAll()
                .stream()
                .map(GetStationDto::of)
                .toList();
    }

    public Optional<Estacion> findStationById(Long id) {
        return Optional.ofNullable(estacionRepository.findByNumero(id));
    }

    public Estacion findById(UUID id) {
        return estacionRepository.findById(id).orElseThrow(()->new NotFoundException("Estación"));
    }

    public Estacion editStation(Long id, EditStationDto nuevo) {
        Optional<Estacion> exist = Optional.ofNullable(estacionRepository.findByNumero(id));
        if (exist.isPresent()) {
            Estacion e = exist.get();
            e.setNombre(nuevo.nombre());
            e.setCoordenadas(nuevo.coordenadas());
            e.setCapacidad(nuevo.capacidad());
            return estacionRepository.save(e);
        } else {
            throw new NotFoundException("La estación no existe");
        }
    }

    @Transactional
    public String delete(Long id) {
        estacionRepository.deleteByNumero(id);
        return "Eliminado con éxito";
    }
}
