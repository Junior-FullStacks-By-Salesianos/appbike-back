package com.salesianos.triana.appbike.security.errorhandling;

public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String msg) {
        super(msg);
    }

}
