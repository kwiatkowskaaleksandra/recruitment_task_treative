package com.example.treative.state;

/**
 * State representing the recovery phase of the simulation.
 * Responsible for calculating recoveries and transitioning to the next state.
 */
public class RecoveryState implements SimulationState {

    /**
     * Handles the state transition by calculating recoveries and updating the context.
     *
     * @param context the simulation context
     */
    @Override
    public void handle(SimulationContext context) {
        int infected = context.getInfected();
        int recovered = context.getRecovered();

        if (context.getDay() >= context.getSimulation().getRecoveryDays()) {
            int newRecoveries = Math.min(infected, context.getSimulation().getPopulationSize());
            infected -= newRecoveries;
            recovered += newRecoveries;
        }

        context.setInfected(infected);
        context.setRecovered(recovered);

        context.setState(new DeathState());
    }

}
