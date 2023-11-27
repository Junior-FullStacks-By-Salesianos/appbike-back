package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.dto.Station.AddStationDto;
import com.salesianos.triana.appbike.dto.Station.GetStationDto;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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
}
