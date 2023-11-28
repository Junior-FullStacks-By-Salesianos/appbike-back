package com.salesianos.triana.appbike.dto.UsuarioBici;

import com.salesianos.triana.appbike.model.UsuarioBici;

import java.util.UUID;

public record UsuarioBiciResponse(
        UUID id,
        String username,
        String nombre,
        double saldo
) {

    public static UsuarioBiciResponse of (UsuarioBici u){
        return new UsuarioBiciResponse(
                u.getId(),
                u.getUsername(),
                u.getNombre(),
                u.getSaldo()
        );
    }

}
