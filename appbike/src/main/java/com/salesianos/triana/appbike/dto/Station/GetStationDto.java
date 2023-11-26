package com.salesianos.triana.appbike.dto.Station;

import com.salesianos.triana.appbike.model.Estacion;
import java.util.UUID;

public record GetStationDto (
        UUID id,
        Long number,

        String name,
        String coordinates,

        int capacity,

        Long bikes
){
    public static  GetStationDto of (Estacion e){
        return new GetStationDto(
                e.getId(),
                e.getNumero(),
                e.getNombre(),
                e.getCoordenadas(),
                e.getCapacidad(),
                e.getBicicletas().stream().count()
        );
    }
}