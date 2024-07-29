package com.example.treative.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entity representing a simulation of an epidemic.
 * Contains parameters for the simulation and a list of daily results.
 */
@Table(name = "simulation")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Simulation {

    /**
     * Unique identifier for the simulation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSimulation;

    /**
     * Name of the simulation.
     */
    private String simulationName;

    /**
     * Total population size for the simulation.
     */
    private int populationSize;

    /**
     * Initial number of infected people at the start of the simulation.
     */
    private int initialNumberOfInfectedPeople;

    /**
     * Infective rate indicating how many people one infected person can infect.
     */
    private double infectiveRate;

    /**
     * Mortality rate indicating the percentage of infected people who die.
     */
    private double mortalityRate;

    /**
     * Number of days it takes for an infected person to recover.
     */
    private int recoveryDays;

    /**
     * Number of days it takes for an infected person to die.
     */
    private int deathDays;

    /**
     * Total number of days for which the simulation will run.
     */
    private int simulationDays;

    /**
     * List of daily results associated with this simulation.
     */
    @OneToMany(mappedBy = "simulation", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DailyResult> dailyResults;

}
