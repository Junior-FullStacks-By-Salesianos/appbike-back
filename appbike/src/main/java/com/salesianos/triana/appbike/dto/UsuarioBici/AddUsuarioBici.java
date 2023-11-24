package com.salesianos.triana.appbike.dto.UsuarioBici;

public record AddUsuarioBici(
        String username,
        String password,
        String verifyPassword,
        String email,
        String nombre
) {

}
