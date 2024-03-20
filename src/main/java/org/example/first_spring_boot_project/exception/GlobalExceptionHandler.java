package org.example.first_spring_boot_project.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(EntityNotFoundException e) {
        log.severe("ToDo not found!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    }
}
