package com.example.treative.service;

import com.example.treative.controller.dto.SimulationRequest;
import com.example.treative.model.Simulation;

import java.util.List;
import java.util.Map;

/**
 * Service interface for managing Simulation entities.
 * Provides methods to create, retrieve, update, and delete simulations, as well as to retrieve simulation results.
 */
public interface SimulationService {

    /**
     * Adds a new simulation based on the provided request.
     *
     * @param simulationRequest the request containing the simulation parameters
     * @return the created Simulation entity
     */
    Simulation addNewSimulation(SimulationRequest simulationRequest);

    /**
     * Retrieves a list of all simulations.
     *
     * @return a list of all Simulation entities
     */
    List<Simulation> getAllSimulations();

    /**
     * Deletes a simulation by its ID.
     *
     * @param idSimulation the ID of the simulation to be deleted
     */
    void deleteSimulationByIdSimulation(Long idSimulation);

    /**
     * Finds a simulation by its ID.
     *
     * @param idSimulation the ID of the simulation to find
     * @return the found Simulation entity
     */
    Simulation findSimulationByIdSimulation(Long idSimulation);

    /**
     * Retrieves all results for a specific simulation by its ID.
     *
     * @param idSimulation the ID of the simulation
     * @return a map containing the simulation results
     */
    Map<String, Object> getSimulationResultByIdSimulation(Long idSimulation);

    /**
     * Updates an existing simulation by its ID with new parameters.
     *
     * @param idSimulation the ID of the simulation to be updated
     * @param simulationRequest the request containing the new simulation parameters
     * @return the updated Simulation entity
     */
    Simulation updateSimulationByIdSimulation(Long idSimulation, SimulationRequest simulationRequest);
}
