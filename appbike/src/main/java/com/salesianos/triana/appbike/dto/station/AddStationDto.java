package com.salesianos.triana.appbike.dto.station;
import com.salesianos.triana.appbike.model.Estacion;
import java.util.UUID;

public record AddStationDto(
        UUID id,

        Long numero,

        String nombre,
        String coordenadas,

        int capacidad



){

    public static AddStationDto of(Estacion e){
        return new AddStationDto(
                e.getId(),
                e.getNumero(),
                e.getNombre(),
                e.getCoordenadas(),
                e.getCapacidad()
        );
    }

}
