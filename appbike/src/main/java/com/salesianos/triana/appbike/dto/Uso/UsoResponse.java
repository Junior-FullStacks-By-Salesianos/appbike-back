package com.salesianos.triana.appbike.dto.Uso;

import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.model.Uso;

import java.time.LocalDateTime;

public record UsoResponse(
        Long id,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        double coste,
        String bicicleta,
        String estacionFin,
        String usuario
) {

    public static UsoResponse of(Uso u){
        return new UsoResponse(
                u.getId(),
                u.getFechaInicio(),
                u.getFechaFin(),
                u.getCoste(),
                u.getBicicleta().getNombre(),
                u.getEstacion() != null ? u.getEstacion().getNombre() : "El viaje aun no tiene estación de fin",
                u.getAuthor()
        );
    }

}