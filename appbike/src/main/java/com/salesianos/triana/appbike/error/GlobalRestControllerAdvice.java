package com.salesianos.triana.appbike.error;

import com.salesianos.triana.appbike.error.impl.ApiValidationSubError;
import com.salesianos.triana.appbike.exception.*;
import com.salesianos.triana.appbike.exception.InUseException;
import com.salesianos.triana.appbike.exception.InvalidCredentialsException;
import com.salesianos.triana.appbike.exception.InvalidPinExcepcion;
import com.salesianos.triana.appbike.exception.NotEnoughBalanceException;
import com.salesianos.triana.appbike.security.errorhandling.JwtTokenException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponse;
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
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
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

    @ExceptionHandler({ EntityNotFoundException.class })
    public ErrorResponse handleNotFoundException(EntityNotFoundException exception) {
        return ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage())
                .title("Entity not found")
                .type(URI.create("https://api.bikeapp.com/errors/not-found"))
                .property("timestamp", Instant.now())
                .build();
    }

    @ExceptionHandler({ InUseException.class })
    public ErrorResponse handleAlreadyInUseException(InUseException exception) {
        return ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("Already in use")
                .type(URI.create("https://api.bikeapp.com/errors/user-bike-in-use"))
                .property("timestamp", Instant.now())
                .build();
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ErrorResponse handleInvalidCredentialsException(
            InvalidCredentialsException exception) {
        return ErrorResponse.builder(exception, HttpStatus.UNAUTHORIZED, exception.getMessage())
                .title("Username or password incorrect")
                .type(URI.create("https://api.bikeapp.com/errors/invalid-credentials"))
                .property("timestamp", Instant.now())
                .build();
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ErrorResponse handleAuthenticationException(AuthenticationException exception) {
        return ErrorResponse.builder(exception, HttpStatus.UNAUTHORIZED, exception.getMessage())
                .title("AUTHENTICATION")
                .type(URI.create("https://api.bikeapp.com/errors/unauthorized-user"))
                .property("timestamp", Instant.now())
                .build();

    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ErrorResponse handleAccessDeniedException(AccessDeniedException exception) {
        return ErrorResponse.builder(exception, HttpStatus.FORBIDDEN, exception.getMessage())
                .title("ACCESS DENIED")
                .type(URI.create("https://api.bikeapp.com/errors/access-denied"))
                .property("timestamp", Instant.now())
                .build();

    }


    @ExceptionHandler({JwtTokenException.class})
    public ErrorResponse handleTokenException(JwtTokenException exception) {
        return ErrorResponse.builder(exception, HttpStatus.FORBIDDEN, exception.getMessage())
                .title("TOKEN INVALID")
                .type(URI.create("https://api.bikeapp.com/errors/invalid-token"))
                .property("timestamp", Instant.now())
                .build();
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ErrorResponse handleUserNotExistsException(UsernameNotFoundException exception) {
        return ErrorResponse.builder(exception, HttpStatus.FORBIDDEN, exception.getMessage())
                .title("FORBIDDEN")
                .type(URI.create("https://api.bikeapp.com/errors/forbidden"))
                .property("timestamp", Instant.now())
                .build();
    }

    @ExceptionHandler({InvalidPinExcepcion.class})
    public ErrorResponse handleInvalidPin(InvalidPinExcepcion exception) {
        return ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("Invalid Pin")
                .type(URI.create("https://api.bikeapp.com/errors/invalid-pin"))
                .property("timestamp", Instant.now())
                .build();
    }

    @ExceptionHandler({NotEnoughBalanceException.class})
    public ErrorResponse handleInvalidPin(NotEnoughBalanceException exception) {
        return ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("Not enough balance")
                .type(URI.create("https://api.bikeapp.com/errors/not-enough-balance"))
                .property("timestamp", Instant.now())
                .build();
    }

    @ExceptionHandler(BikesInThatStationException.class)
    public ErrorResponse handleBikesInThatStationException(BikesInThatStationException exception) {
        return ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("The station can't be deleted")
                .type(URI.create("https://api.bikeapp.com/errors/bikes-in-station"))
                .property("timestamp", Instant.now())
                .build();
    }

    @ExceptionHandler({ BadRequestForBikeAddException.class })
    private static ErrorResponse handleBadRequestBikeAdd(BadRequestForBikeAddException exception) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("Bad request from user")
                .type(URI.create("https://api.bikeapp.com/errors/not-valid"))
                .property("date", LocalDateTime.now().format(formatter))
                .build();
    }

    @ExceptionHandler({ BikeWithSameNameException.class })
    private static ErrorResponse handleBikeWithSameNameException(BikeWithSameNameException exception) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("Already exists a bike with that name")
                .type(URI.create("https://api.bikeapp.com/errors/not-valid"))
                .property("date", LocalDateTime.now().format(formatter))
                .build();
    }

    @ExceptionHandler({ StationWithoutCapacityException.class })
    private static ErrorResponse handleStationWithoutCapacityException(StationWithoutCapacityException exception) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return ErrorResponse.builder(exception, HttpStatus.BAD_REQUEST, exception.getMessage())
                .title("That station is full of bikes")
                .type(URI.create("https://api.bikeapp.com/errors/not-valid"))
                .property("date", LocalDateTime.now().format(formatter))
                .build();
    }

    @ExceptionHandler({ NameOfBikeNotFoundException.class })
    private static ErrorResponse handleNameOfBikeNotFoundException(NameOfBikeNotFoundException exception) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage())
                .title("That bicycle name does not exist")
                .type(URI.create("https://api.bikeapp.com/errors/not-found"))
                .property("date", LocalDateTime.now().format(formatter))
                .build();
    }

}
