package com.salesianos.triana.appbike.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

     /*
    @ExceptionHandler({NoteNotFoundException.class, EmptyNoteListException.class})
    public ProblemDetail handleNotFoundException(EntityNotFoundException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setTitle("Entity not found");
        problemDetail.setType(URI.create("https://api.midominio.com/errors/not-found"));
        problemDetail.setProperty("entityType", "Note");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
*/

    @ExceptionHandler({EntityNotFoundException.class})
    public ErrorResponse handleNotFoundException(EntityNotFoundException exception) {
        return ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage())
                .title("Entity not found")
                .type(URI.create("https://api.midominio.com/errors/not-found"))
                .property("entityType", "Note")
                .property("timestamp", Instant.now())
                .build();
    }

}
