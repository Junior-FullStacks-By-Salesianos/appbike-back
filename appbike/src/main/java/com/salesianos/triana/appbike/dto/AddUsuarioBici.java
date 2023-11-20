package com.salesianos.triana.appbike.dto;

public record AddUsuarioBici(
        String username,
        String password,
        String verifyPassword,
        String email,
        String nombre
) {

}
