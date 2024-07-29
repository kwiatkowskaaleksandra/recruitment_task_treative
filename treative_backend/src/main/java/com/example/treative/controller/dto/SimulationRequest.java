package com.example.treative.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represents a request to create a new simulation.
 * It contains all the necessary parameters to define and run the simulation.
 */
@Getter
@AllArgsConstructor
public class SimulationRequest {

    /**
     * The name of the simulation.
     */
    private String name;

    /**
     * The total population size in the simulation.
     */
    private int populationSize;

    /**
     * The initial number of infected people at the start of the simulation.
     */
    private int initialNumberOfInfectedPeople;

    /**
     * The infective rate, representing how many healthy individuals one infected person can infect.
     */
    private double infectiveRate;

    /**
     * The mortality rate, representing the fraction of infected individuals who will die from the infection.
     */
    private double mortalityRate;

    /**
     * The number of days it takes for an infected individual to recover.
     */
    private int recoveryDays;

    /**
     * The number of days it takes for an infected individual to die from the infection.
     */
    private int deathDays;

    /**
     * The total number of days for which the simulation will run.
     */
    private int simulationDays;
}
