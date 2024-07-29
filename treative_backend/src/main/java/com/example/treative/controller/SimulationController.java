package com.example.treative.controller;

import com.example.treative.controller.dto.SimulationRequest;
import com.example.treative.model.Simulation;
import com.example.treative.service.SimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing simulations.
 * Provides endpoints to create, read, update, and delete simulations.
 */
@RequestMapping("/simulation")
@RestController
@RequiredArgsConstructor
public class SimulationController {

    private final SimulationService simulationService;

    /**
     * Creates a new simulation based on the provided request.
     *
     * @param simulationRequest the request containing the simulation parameters
     * @return ResponseEntity containing the created Simulation object and HTTP status CREATED
     */
    @PostMapping("/addNew")
    public ResponseEntity<Simulation> addNewSimulation(@RequestBody SimulationRequest simulationRequest) {
        Simulation createdSimulation = simulationService.addNewSimulation(simulationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSimulation);
    }

    /**
     * Retrieves a list of all simulations.
     *
     * @return ResponseEntity containing a list of all Simulation objects and HTTP status OK
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Simulation>> getAllSimulations() {
        return ResponseEntity.ok(simulationService.getAllSimulations());
    }

    /**
     * Retrieves all results for a specific simulation by its ID.
     *
     * @param idSimulation the ID of the simulation
     * @return ResponseEntity containing a map of simulation results and HTTP status OK
     */
    @GetMapping("/getAllSimulationResults/{idSimulation}")
    public ResponseEntity<Map<String, Object>> getAllSimulationResults(@PathVariable Long idSimulation) {
        Map<String, Object> simulationResult = simulationService.getSimulationResultByIdSimulation(idSimulation);
        return ResponseEntity.ok(simulationResult);
    }

    /**
     * Deletes a simulation by its ID.
     *
     * @param idSimulation the ID of the simulation to be deleted
     * @return ResponseEntity with HTTP status NO_CONTENT
     */
    @DeleteMapping("/deleteSimulationById/{idSimulation}")
    public ResponseEntity<HttpStatus> deleteSimulation(@PathVariable Long idSimulation) {
        simulationService.deleteSimulationByIdSimulation(idSimulation);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates an existing simulation by its ID with new parameters.
     *
     * @param idSimulation the ID of the simulation to be updated
     * @param simulationRequest the request containing the new simulation parameters
     * @return ResponseEntity containing the updated Simulation object and HTTP status OK
     */
    @PutMapping("/updateSimulation/{idSimulation}")
    public ResponseEntity<Simulation> updateSimulation(@PathVariable Long idSimulation, @RequestBody SimulationRequest simulationRequest) {
        return ResponseEntity.ok(simulationService.updateSimulationByIdSimulation(idSimulation, simulationRequest));
    }
}
