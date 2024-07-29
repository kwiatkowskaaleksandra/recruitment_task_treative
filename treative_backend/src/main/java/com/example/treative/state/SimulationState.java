package com.example.treative.state;

/**
 * Interface representing the state of a simulation.
 * Defines a method to handle the state transition.
 */
public interface SimulationState {

    /**
     * Handles the state transition based on the given context.
     *
     * @param context the simulation context
     */
    void handle(SimulationContext context);

}
