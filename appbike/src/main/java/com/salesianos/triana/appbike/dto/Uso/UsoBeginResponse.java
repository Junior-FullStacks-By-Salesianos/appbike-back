package com.salesianos.triana.appbike.dto.Uso;

import com.salesianos.triana.appbike.model.Uso;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsoBeginResponse(
        UUID id,
        LocalDateTime fechaInicio,
        String bicicleta,
        String usuario
) {

    public static UsoBeginResponse of(Uso u){
        return new UsoBeginResponse(
                u.getUuid(),
                u.getFechaInicio(),
                u.getBicicleta().getNombre(),
                u.getAuthor()
        );
    }

}
