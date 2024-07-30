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
        int day = context.getDay();
        int recoveryDays = context.getSimulation().getRecoveryDays();
        int deathDays = context.getSimulation().getDeathDays();

        if (day >= recoveryDays) {
            int actualRecoveries = context.getInfectionsByDay()[day - recoveryDays];

            if (day >= deathDays) {
                if (deathDays <= recoveryDays) {
                    int deathsBeforeRecovery = context.getDeathsByDay()[day - (recoveryDays - deathDays)];
                    actualRecoveries -= deathsBeforeRecovery;
                }
            }

            context.setInfected(context.getInfected() - actualRecoveries);
            context.setRecovered(context.getRecovered() + actualRecoveries);
            context.getRecoveriesByDay()[day] = actualRecoveries;
        }

        context.setState(new FinalState());
    }

}
