package com.salesianos.triana.appbike.dto;

import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;

import java.time.LocalDateTime;

public record UsoBeginResponse(
        LocalDateTime fechaInicio,
        String bicicleta,
        String usuario
) {

    public static UsoBeginResponse of(Uso u){
        return new UsoBeginResponse(
                u.getFechaInicio(),
                u.getBicicleta().getNombre(),
                u.getUsuario()
        );
    }

}
