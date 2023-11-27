package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.station.AddStationDto;
import com.salesianos.triana.appbike.dto.station.GetStationDto;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EstacionService {

    private final EstacionRepository estacionRepository;

    public Estacion newStation(AddStationDto nuevo){
        Estacion e= new Estacion();
            e.setNombre(nuevo.nombre());
            e.setCapacidad(nuevo.capacidad());
            e.setNumero(nuevo.numero());
            e.setCoordenadas(nuevo.coordenadas());
        return estacionRepository.save(e);
    }

    public List<GetStationDto> findAll(){

        return estacionRepository.findAll()
                .stream()
                .map(GetStationDto::of)
                .toList();
    }



    public Optional<Estacion> findStationById(Long id) {
        return Optional.ofNullable(estacionRepository.findByNumero(id));
    }

    @Transactional
    public String delete(Long id) {
        estacionRepository.deleteByNumero(id);
        return "Eliminado con éxito";
    }
}
