package com.example.treative.state;

import com.example.treative.model.DailyResult;
import com.example.treative.model.Simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Context class for managing the state of a simulation.
 * Holds the current state, simulation data, and daily results.
 */
public class SimulationContext {

    /**
     * The simulation associated with this context.
     */
    private Simulation simulation;

    /**
     * The current state of the simulation.
     */
    private SimulationState state;

    /**
     * The list of daily results for the simulation.
     */
    private List<DailyResult> results;

    /**
     * The number of infected individuals in the current state.
     */
    private int infected;

    /**
     * The number of susceptible (healthy) individuals in the current state.
     */
    private int susceptible;

    /**
     * The number of deceased individuals in the current state.
     */
    private int deceased;

    /**
     * The number of recovered individuals in the current state.
     */
    private int recovered;

    /**
     * The current day of the simulation.
     */
    private int day;

    /**
     * Constructs a SimulationContext with the given simulation.
     * Initializes the initial state and population data.
     *
     * @param simulation the simulation to manage
     */
    public SimulationContext(Simulation simulation) {
        this.simulation = simulation;
        this.results = new ArrayList<>();
        this.infected = simulation.getInitialNumberOfInfectedPeople();
        this.susceptible = simulation.getPopulationSize() - infected;
        this.deceased = 0;
        this.recovered = 0;
        this.day = 0;
        this.state = new InitialState();
    }

    /**
     * Sets the current state of the simulation.
     *
     * @param state the new state to set
     */
    public void setState(SimulationState state) {
        this.state = state;
    }

    /**
     * Advances the simulation to the next state.
     */
    public void nextState() {
        state.handle(this);
    }

    /**
     * Adds a daily result to the simulation's results list.
     *
     * @param result the daily result to add
     */
    public void addResult(DailyResult result) {
        results.add(result);
    }

    /**
     * Returns the list of daily results.
     *
     * @return the list of daily results
     */
    public List<DailyResult> getResults() {
        return results;
    }

    /**
     * Returns the number of infected individuals.
     *
     * @return the number of infected individuals
     */
    public int getInfected() {
        return infected;
    }

    /**
     * Sets the number of infected individuals.
     *
     * @param infected the number of infected individuals to set
     */
    public void setInfected(int infected) {
        this.infected = Math.max(infected, 0);
    }

    /**
     * Returns the number of susceptible individuals.
     *
     * @return the number of susceptible individuals
     */
    public int getSusceptible() {
        return susceptible;
    }

    /**
     * Sets the number of susceptible individuals.
     *
     * @param susceptible the number of susceptible individuals to set
     */
    public void setSusceptible(int susceptible) {
        this.susceptible = Math.max(susceptible, 0);
    }

    /**
     * Returns the number of deceased individuals.
     *
     * @return the number of deceased individuals
     */
    public int getDeceased() {
        return deceased;
    }

    /**
     * Sets the number of deceased individuals.
     *
     * @param deceased the number of deceased individuals to set
     */
    public void setDeceased(int deceased) {
        this.deceased = Math.max(deceased, 0);
    }

    /**
     * Returns the number of recovered individuals.
     *
     * @return the number of recovered individuals
     */
    public int getRecovered() {
        return recovered;
    }

    /**
     * Sets the number of recovered individuals.
     *
     * @param recovered the number of recovered individuals to set
     */
    public void setRecovered(int recovered) {
        this.recovered = Math.max(recovered, 0);
    }

    /**
     * Returns the current day of the simulation.
     *
     * @return the current day of the simulation
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the current day of the simulation.
     *
     * @param day the current day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Returns the simulation associated with this context.
     *
     * @return the simulation associated with this context
     */
    public Simulation getSimulation() {
        return simulation;
    }
}
