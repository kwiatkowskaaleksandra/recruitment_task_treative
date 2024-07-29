package com.example.treative.state;

/**
 * State representing the death phase of the simulation.
 * Responsible for calculating deaths and transitioning to the next state.
 */
public class DeathState implements SimulationState {

    /**
     * Handles the state transition by calculating deaths and updating the context.
     *
     * @param context the simulation context
     */
    @Override
    public void handle(SimulationContext context) {
        int infected = context.getInfected();
        int deceased = context.getDeceased();

        if (context.getDay() >= context.getSimulation().getDeathDays()) {
            int newDeaths = (int) (infected * context.getSimulation().getMortalityRate());
            newDeaths = Math.min(newDeaths, infected);
            infected -= newDeaths;
            deceased += newDeaths;
        }

        context.setInfected(infected);
        context.setDeceased(deceased);

        context.setState(new FinalState());
    }
}
