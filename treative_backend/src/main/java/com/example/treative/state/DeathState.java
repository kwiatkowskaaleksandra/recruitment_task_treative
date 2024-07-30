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
        int day = context.getDay();
        int deathDays = context.getSimulation().getDeathDays();
        int recoveryDays = context.getSimulation().getRecoveryDays();

        if (day >= deathDays && deathDays <= recoveryDays) {
            int deathsToday = (int) (context.getInfectionsByDay()[day - deathDays] * context.getSimulation().getMortalityRate());
            context.setInfected(context.getInfected() - deathsToday);
            context.setDeceased(context.getDeceased() + deathsToday);
            context.getDeathsByDay()[day] = deathsToday;
        }

        context.setState(new RecoveryState());
    }
}
