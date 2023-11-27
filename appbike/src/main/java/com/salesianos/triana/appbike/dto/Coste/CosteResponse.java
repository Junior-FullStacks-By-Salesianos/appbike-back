package com.salesianos.triana.appbike.dto.Coste;

import com.salesianos.triana.appbike.model.Coste;

import java.time.LocalDateTime;

public record CosteResponse(
        Long id,
        double precioMinuto,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin
) {

    public static CosteResponse of(Coste c){
        return new CosteResponse(
                c.getId(),
                c.getPrecioMinuto(),
                c.getFechaInicio(),
                c.getFechaFin()
        );
    }

}
