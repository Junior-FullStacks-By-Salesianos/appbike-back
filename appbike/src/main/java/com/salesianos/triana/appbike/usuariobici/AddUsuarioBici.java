package com.salesianos.triana.appbike.usuariobici;

public record AddUsuarioBici(
        String username,
        String password,
        String verifyPassword,
        String email,
        String nombre
) {

}
