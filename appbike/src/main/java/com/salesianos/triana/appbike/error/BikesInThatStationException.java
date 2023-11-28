package com.salesianos.triana.appbike.error;

public class BikesInThatStationException extends RuntimeException {
    public BikesInThatStationException() {
        super("The deletion was not satisfactory because there are bicycles associated with that station");
    }
}
