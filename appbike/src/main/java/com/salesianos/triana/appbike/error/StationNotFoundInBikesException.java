package com.salesianos.triana.appbike.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

public class StationNotFoundInBikesException extends ErrorResponseException {

    public StationNotFoundInBikesException(UUID uuidEstacion){
        super(HttpStatus.NOT_FOUND, asProblemDetail("La estación con el UUID "+uuidEstacion+" no existe, introduzca otro UUID"),null);
    }

    private static ProblemDetail asProblemDetail(String message){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,message);
        problem.setType(URI.create("http://bikeapp.com/errors/not-found"));
        problem.setTitle("Alumno no encontrado");
        problem.setProperty("local-datetime", LocalDateTime.now());
        return problem;
    }
}
