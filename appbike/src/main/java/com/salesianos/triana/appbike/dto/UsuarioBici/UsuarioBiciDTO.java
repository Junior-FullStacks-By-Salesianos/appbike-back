package com.salesianos.triana.appbike.dto.UsuarioBici;

import com.salesianos.triana.appbike.model.UsuarioBici;

import java.time.LocalDate;
import java.util.UUID;

public record UsuarioBiciDTO(UUID id, LocalDate createdAt, String email,
                             String nombre, String username, String numTarjeta,
                             String pin, double saldo) {

    public static UsuarioBiciDTO of(UsuarioBici u){
        return new UsuarioBiciDTO(u.getId(),
                u.getCreatedAt().toLocalDate(),
                u.getEmail(),
                u.getNombre(),
                u.getUsername(),
                u.getNumTarjeta(),
                u.getPin(),
                u.getSaldo());
    }
}
