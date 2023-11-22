package com.salesianos.triana.appbike.dto.station;


import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.model.Uso;

import java.util.List;
import java.util.Set;

public record AddStationDto(
        Long id,

        Long numero,

        String nombre,
        String coordenadas,

        int capacidad,

        Set<Bicicleta> bicicletas,

        List<Uso> usos
){

    public static AddStationDto of(Estacion e){
        return new AddStationDto(
            e.getId(),
            e.getNumero(),
            e.getNombre(),
            e.getCoordenadas(),
            e.getCapacidad(),
            e.getBicicletas(),
            e.getUsos()
        );
    }

}
