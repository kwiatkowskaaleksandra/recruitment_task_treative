package com.example.treative.service;

import com.example.treative.model.DailyResult;

import java.util.List;

/**
 * Service interface for managing DailyResult entities.
 * Provides methods to save, retrieve, and delete daily results.
 */
public interface DailyResultService {

    /**
     * Saves a list of DailyResult entities.
     *
     * @param results the list of DailyResult entities to save
     */
    void saveAll(List<DailyResult> results);

    /**
     * Retrieves all DailyResult entities associated with a specific simulation ID.
     *
     * @param idSimulation the ID of the simulation
     * @return a list of DailyResult entities associated with the given simulation ID
     */
    List<DailyResult> getAllDailyResultsByIdSimulation(Long idSimulation);

    /**
     * Deletes all DailyResult entities associated with a specific simulation.
     *
     * @param results the list of DailyResult entities to delete
     */
    void deleteAllDailyResultsBySimulation(List<DailyResult> results);

}

