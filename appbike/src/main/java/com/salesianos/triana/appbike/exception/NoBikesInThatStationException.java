package com.salesianos.triana.appbike.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

public class NoBikesInThatStationException extends RuntimeException {

    public NoBikesInThatStationException(String message){super(message);}

    }

