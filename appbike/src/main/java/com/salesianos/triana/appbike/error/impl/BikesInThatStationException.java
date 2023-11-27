package com.salesianos.triana.appbike.error.impl;

public class BikesInThatStationException extends RuntimeException {
    public BikesInThatStationException() {
        super("The delete were not fine cause of the station have bikes inside");
    }
}
