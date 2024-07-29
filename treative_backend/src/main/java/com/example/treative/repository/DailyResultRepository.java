package com.example.treative.repository;

import com.example.treative.model.DailyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing DailyResult entities.
 */
@Repository
public interface DailyResultRepository extends JpaRepository<DailyResult, Long> {

    /**
     * Finds all DailyResult entities associated with a specific simulation.
     *
     * @param idSimulation the ID of the simulation
     * @return a list of DailyResult entities associated with the given simulation ID
     */
    List<DailyResult> findAllBySimulation_IdSimulation(Long idSimulation);
}
