package com.example.treative.state;

import com.example.treative.model.DailyResult;

import java.time.LocalDate;

/**
 * Initial state of the simulation.
 * Responsible for setting up the initial daily result and transitioning to the next state.
 */
public class InitialState implements SimulationState {

    /**
     * Handles the state transition by creating the initial daily result and moving to the InfectionState.
     *
     * @param context the simulation context
     */
    @Override
    public void handle(SimulationContext context) {
        DailyResult result = new DailyResult();
        result.setSimulation(context.getSimulation());
        result.setDate(LocalDate.now());
        result.setNumberOfInfectedPeople(context.getInfected());
        result.setNumberOfHealthyPeople(context.getSusceptible());
        result.setNumberOfRecoveredPeople(context.getRecovered());
        result.setNumberOfPeopleWhoDied(context.getDeceased());
        context.addResult(result);

        context.setState(new InfectionState());
        context.setDay(context.getDay() + 1);
    }

}
