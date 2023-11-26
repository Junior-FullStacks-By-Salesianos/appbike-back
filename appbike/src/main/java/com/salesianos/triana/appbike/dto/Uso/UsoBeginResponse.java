package com.salesianos.triana.appbike.dto.Uso;

import com.salesianos.triana.appbike.model.Uso;

import java.time.LocalDateTime;

public record UsoBeginResponse(
        Long id,
        LocalDateTime fechaInicio,
        String bicicleta,
        String usuario
) {

    public static UsoBeginResponse of(Uso u){
        return new UsoBeginResponse(
                u.getId(),
                u.getFechaInicio(),
                u.getBicicleta().getNombre(),
                u.getAuthor()
        );
    }

}
