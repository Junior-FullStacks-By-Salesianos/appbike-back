package com.salesianos.triana.appbike.dto.station;

import com.salesianos.triana.appbike.model.Estacion;
import java.util.UUID;

public record GetStationDto (
        Long number,

        String name,
        String coordinates,

        int capacity,

        Long bikes
){
    public static  GetStationDto of (Estacion e){
        return new GetStationDto(
                e.getNumero(),
                e.getNombre(),
                e.getCoordenadas(),
                e.getCapacidad(),
                e.getBicicletas().stream().count()
        );
    }
}
