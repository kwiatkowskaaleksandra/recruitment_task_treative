package com.example.treative.state;

import com.example.treative.model.DailyResult;

import java.time.LocalDate;

/**
 * State representing the final phase of a daily simulation cycle.
 * Responsible for recording the daily result and determining the next state.
 */
public class FinalState implements SimulationState {

    /**
     * Handles the state transition by recording the daily result and either looping back to infection state or finishing the simulation based on the number of simulation days.
     *
     * @param context the simulation context
     */
    @Override
    public void handle(SimulationContext context) {
        DailyResult result = new DailyResult();
        result.setSimulation(context.getSimulation());
        result.setDate(LocalDate.now().plusDays(context.getDay()));
        result.setNumberOfInfectedPeople(context.getInfected());
        result.setNumberOfHealthyPeople(context.getSusceptible());
        result.setNumberOfPeopleWhoDied(context.getDeceased());
        result.setNumberOfRecoveredPeople(context.getRecovered());

        context.addResult(result);

        if (context.getDay() < context.getSimulation().getSimulationDays()) {
            context.setState(new InfectionState());
            context.setDay(context.getDay() + 1);
        }
    }

}
