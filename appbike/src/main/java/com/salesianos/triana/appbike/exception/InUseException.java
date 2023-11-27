package com.salesianos.triana.appbike.exception;

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}
