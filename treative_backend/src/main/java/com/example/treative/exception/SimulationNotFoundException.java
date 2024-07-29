package com.example.treative.exception;

/**
 * Exception thrown when a simulation cannot be found.
 * This exception extends RuntimeException.
 */
public class SimulationNotFoundException extends RuntimeException {

    /**
     * Constructs a new SimulationNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public SimulationNotFoundException(String message) {
        super(message);
    }

}
