package com.example.treative.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity representing the daily results of a simulation.
 * Contains information about the number of infected, healthy, deceased, and recovered people for a specific day.
 */
@Table(name = "dailyResult")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyResult {

    /**
     * Unique identifier for the daily result.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResult;

    /**
     * The date for which the results are recorded.
     */
    private LocalDate date;

    /**
     * Number of people infected on the specified date.
     */
    private int numberOfInfectedPeople;

    /**
     * Number of healthy people on the specified date.
     */
    private int numberOfHealthyPeople;

    /**
     * Number of people who died on the specified date.
     */
    private int numberOfPeopleWhoDied;

    /**
     * Number of people who recovered on the specified date.
     */
    private int numberOfRecoveredPeople;

    /**
     * The simulation to which this daily result belongs.
     */
    @ManyToOne
    @JoinColumn(name = "idSimulation")
    private Simulation simulation;

}
