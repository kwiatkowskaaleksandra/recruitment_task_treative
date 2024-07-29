package com.example.treative.repository;

import com.example.treative.model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Simulation entities.
 */
@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Long> {

}
