package com.example.treative.exception;

/**
 * General exception for simulation-related errors.
 * This exception extends RuntimeException.
 */
public class SimulationException extends RuntimeException {

    /**
     * Constructs a new SimulationException with the specified detail message.
     *
     * @param message the detail message
     */
    public SimulationException(String message) {
        super(message);
    }

}
