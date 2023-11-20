package com.salesianos.triana.appbike.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianos.triana.appbike.model.UsuarioBici;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioBiciResponse(
        String id,
        String username,
        String password,
        String email,
        String nombre,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt
) {

    public static UsuarioBiciResponse of(UsuarioBici u){
        return new UsuarioBiciResponse(
                u.getId().toString(),
                u.getUsername(),
                u.getPassword(),
                u.getEmail(),
                u.getNombre(),
                u.getCreatedAt()
        );
    }

}
