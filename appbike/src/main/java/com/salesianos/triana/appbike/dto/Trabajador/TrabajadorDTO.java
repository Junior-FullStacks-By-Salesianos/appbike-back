package com.salesianos.triana.appbike.dto.Trabajador;

import com.salesianos.triana.appbike.model.Trabajador;

import java.util.UUID;

public record TrabajadorDTO(UUID id, String email, String nombre) {

    public static TrabajadorDTO of(Trabajador t){
        return new TrabajadorDTO(t.getId(),
                t.getEmail(),
                t.getNombre());
    }

    public static Trabajador toEntity(TrabajadorDTO t){
        return Trabajador.builder()
                .email(t.email)
                .nombre(t.nombre)
                .build();
    }
}
