package com.salesianos.triana.appbike.error;

import com.salesianos.triana.appbike.error.impl.ApiValidationSubError;
import com.salesianos.triana.appbike.exception.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        List<ApiValidationSubError> validationErrors = exception.getBindingResult().getAllErrors().stream()
                .map(ApiValidationSubError::fromObjectError)
                .toList();
        ErrorResponse er = ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("Invalid data error")
                .type(URI.create("https://api.bikeapp.com/errors/not-valid"))
                .property("Fields errors", validationErrors)
                .build();
        return ResponseEntity.status(status)
                .body(er);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ErrorResponse handleNotFoundException(EntityNotFoundException exception) {
        return ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage())
                .title("Entity not found")
                .type(URI.create("https://api.bikeapp.com/errors/not-found"))
                .property("timestamp", Instant.now())
                .build();
    }

    @ExceptionHandler({InUseException.class})
    public ResponseEntity<Object> handleAlreadyInUseException(InUseException exception) {
        ErrorResponse er = ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("Already in use")
                .type(URI.create("https://api.bikeapp.com/errors/user-bike-in-use"))
                .property("timestamp", Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(er);
    }

    @ExceptionHandler({NoBikesInThatStationException.class})
    private static ErrorResponse handleNoBikesInThatStationException(NoBikesInThatStationException exception){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage())
                .title("Station not found for that UUID")
                .type(URI.create("https://api.bikeapp.com/errors/not-found"))
                .property("date", LocalDateTime.now().format(formatter))
                .build();
    }

    @ExceptionHandler({BadRequestForBikeAdd.class})
    private static ErrorResponse handleBadRequestBikeAdd(BadRequestForBikeAdd exception){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("Bad request from user")
                .type(URI.create("https://api.bikeapp.com/errors/not-valid"))
                .property("date", LocalDateTime.now().format(formatter))
                .build();
    }

    @ExceptionHandler({BikeWithSameNameException.class})
    private static ErrorResponse handleBikeWithSameNameException(BikeWithSameNameException exception){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("Already exists a bike with that name")
                .type(URI.create("https://api.bikeapp.com/errors/not-valid"))
                .property("date", LocalDateTime.now().format(formatter))
                .build();
    }

    @ExceptionHandler({StationWithoutCapacityException.class})
    private static ErrorResponse handleStationWithoutCapacityException(StationWithoutCapacityException exception){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("That station is full of bikes")
                .type(URI.create("https://api.bikeapp.com/errors/not-valid"))
                .property("date", LocalDateTime.now().format(formatter))
                .build();
    }
}
