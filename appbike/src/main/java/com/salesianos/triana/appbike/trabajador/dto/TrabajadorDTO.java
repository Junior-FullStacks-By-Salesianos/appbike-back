package com.salesianos.triana.appbike.trabajador.dto;

import com.salesianos.triana.appbike.trabajador.Trabajador;

import java.util.UUID;

public record TrabajadorDTO(UUID id, String email, String nombre) {

    public static TrabajadorDTO of(Trabajador t){
        return new TrabajadorDTO(t.getId(),
                t.getEmail(),
                t.getNombre());
    }
}
