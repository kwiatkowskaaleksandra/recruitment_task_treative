package com.example.treative.state;

/**
 * State representing the infection phase of the simulation.
 * Responsible for calculating new infections and transitioning to the next state.
 */
public class InfectionState implements SimulationState {

    /**
     * Handles the state transition by calculating new infections and updating the context.
     *
     * @param context the simulation context
     */
    @Override
    public void handle(SimulationContext context) {
        int infected = context.getInfected();
        int susceptible = context.getSusceptible();
        int newInfections = (int) (infected * context.getSimulation().getInfectiveRate());

        newInfections = Math.min(newInfections, susceptible);

        infected += newInfections;
        susceptible -= newInfections;

        context.setInfected(infected);
        context.setSusceptible(susceptible);

        context.setState(new RecoveryState());
    }

}
