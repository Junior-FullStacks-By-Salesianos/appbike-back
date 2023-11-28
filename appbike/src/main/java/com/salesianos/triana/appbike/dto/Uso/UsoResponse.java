package com.salesianos.triana.appbike.dto.Uso;

import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.model.Uso;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsoResponse(
        UUID id,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        double coste,
        String bicicleta,
        String modeloBicicleta,
        String marcaBicicleta,
        String estacionFin,
        String usuario
) {

    public static UsoResponse of(Uso u){
        return new UsoResponse(
                u.getUuid(),
                u.getFechaInicio(),
                u.getFechaFin(),
                u.getCoste(),
                u.getBicicleta().getNombre(),
                u.getBicicleta().getModelo(),
                u.getBicicleta().getMarca(),
                u.getEstacion() != null ? u.getEstacion().getNombre() : "Trip is still ongoing",
                u.getAuthor()
        );
    }

}
