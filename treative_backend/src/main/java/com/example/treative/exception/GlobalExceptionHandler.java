package com.example.treative.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * Handles exceptions and provides appropriate responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles generic exceptions.
     *
     * @param ex the exception to handle
     * @return ResponseEntity containing the error details and HTTP status CONFLICT
     */
    private ResponseEntity<?> handleException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", HttpStatus.CONFLICT.getReasonPhrase());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    /**
     * Handles SimulationException and its subclasses.
     *
     * @param e the SimulationException to handle
     * @return ResponseEntity containing the error details and HTTP status CONFLICT
     */
    @ExceptionHandler({SimulationException.class})
    public ResponseEntity<?> handleSimulationException(SimulationException e) {
        return handleException(e);
    }
}
