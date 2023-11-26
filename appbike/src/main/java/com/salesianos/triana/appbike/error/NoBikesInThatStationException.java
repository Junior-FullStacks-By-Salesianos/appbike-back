package com.salesianos.triana.appbike.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

public class NoBikesInThatStationException extends ErrorResponseException {

    public NoBikesInThatStationException(UUID uuidEstacion){
        super(HttpStatus.NOT_FOUND, asProblemDetail("En la estación con el UUID "+uuidEstacion+" no hay bicicletas"),null);
    }

    private static ProblemDetail asProblemDetail(String message){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,message);
        problem.setType(URI.create("http://bikeapp.com/errors/not-found"));
        problem.setTitle("Alumno no encontrado");
        problem.setProperty("local-datetime", LocalDateTime.now());
        return problem;
    }
}
