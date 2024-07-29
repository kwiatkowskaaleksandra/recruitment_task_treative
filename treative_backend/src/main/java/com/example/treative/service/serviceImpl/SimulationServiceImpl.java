package com.example.treative.service.serviceImpl;

import com.example.treative.controller.dto.SimulationRequest;
import com.example.treative.exception.SimulationException;
import com.example.treative.exception.SimulationNotFoundException;
import com.example.treative.model.DailyResult;
import com.example.treative.model.Simulation;
import com.example.treative.service.DailyResultService;
import com.example.treative.state.SimulationContext;
import com.example.treative.repository.SimulationRepository;
import com.example.treative.service.SimulationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation of the SimulationService interface.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SimulationServiceImpl implements SimulationService {

    private final SimulationRepository simulationRepository;
    private final DailyResultService dailyResultService;

    @Override
    public Simulation addNewSimulation(SimulationRequest simulationRequest) {
        validateSimulationRequest(simulationRequest);

        Simulation savedSimulation = simulationRepository.save(mapSimulationRequestToSimulation(simulationRequest, new Simulation()));
        log.info("A new simulation has been added: {}", savedSimulation);

        generateDailyResult(savedSimulation);

        return savedSimulation;
    }

    @Override
    public List<Simulation> getAllSimulations() {
        return simulationRepository.findAll();
    }

    @Override
    public void deleteSimulationByIdSimulation(Long idSimulation) {
        List<DailyResult> dailyResultsList = dailyResultService.getAllDailyResultsByIdSimulation(idSimulation);
        dailyResultService.deleteAllDailyResultsBySimulation(dailyResultsList);
        log.info("Daily results deleted.");
        simulationRepository.delete(findSimulationByIdSimulation(idSimulation));
        log.info("Simulation deleted.");
    }

    @Override
    public Simulation findSimulationByIdSimulation(Long idSimulation) {
        return simulationRepository.findById(idSimulation).orElseThrow(() -> new SimulationNotFoundException(String.format("No simulation with id %s found", idSimulation)));
    }

    @Override
    public Map<String, Object> getSimulationResultByIdSimulation(Long idSimulation) {
        Optional<Simulation> simulation = simulationRepository.findById(idSimulation);
        List<DailyResult> dailyResultList = new ArrayList<>();
        if (simulation.isPresent()) {
            dailyResultList = dailyResultService.getAllDailyResultsByIdSimulation(idSimulation);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("simulation", simulation);
        resultMap.put("dailyResults", dailyResultList);

        return resultMap;
    }

    @Override
    public Simulation updateSimulationByIdSimulation(Long idSimulation, SimulationRequest simulationRequest) {
        validateSimulationRequest(simulationRequest);

        return simulationRepository.findById(idSimulation)
                .map(existingSimulation -> {
                    mapSimulationRequestToSimulation(simulationRequest, existingSimulation);

                    List<DailyResult> dailyResultsList = dailyResultService.getAllDailyResultsByIdSimulation(idSimulation);
                    dailyResultService.deleteAllDailyResultsBySimulation(dailyResultsList);
                    Simulation updatedSimulation = simulationRepository.save(existingSimulation);
                    generateDailyResult(updatedSimulation);
                    log.info("Simulation updated.");
                    return updatedSimulation;
                })
                .orElseThrow(() -> new SimulationException("Symulacja o ID " + idSimulation + " nie została znaleziona."));
    }

    /**
     * Generates daily results for a given simulation.
     *
     * @param simulation the simulation for which to generate daily results
     */
    private void generateDailyResult(Simulation simulation) {
        SimulationContext context = new SimulationContext(simulation);

        while (context.getDay() < simulation.getSimulationDays()) {
            context.nextState();
        }

        List<DailyResult> results = context.getResults();
        dailyResultService.saveAll(results);
        log.info("A new daily results has been added.");
    }

    /**
     * Maps the parameters from a SimulationRequest to a Simulation entity.
     *
     * @param simulationRequest the request containing the simulation parameters
     * @param simulation the Simulation entity to update
     * @return the updated Simulation entity
     */
    private Simulation mapSimulationRequestToSimulation(SimulationRequest simulationRequest, Simulation simulation) {
        simulation.setSimulationName(simulationRequest.getName());
        simulation.setPopulationSize(simulationRequest.getPopulationSize());
        simulation.setInitialNumberOfInfectedPeople(simulationRequest.getInitialNumberOfInfectedPeople());
        simulation.setInfectiveRate(simulationRequest.getInfectiveRate());
        simulation.setMortalityRate(simulationRequest.getMortalityRate());
        simulation.setRecoveryDays(simulationRequest.getRecoveryDays());
        simulation.setDeathDays(simulationRequest.getDeathDays());
        simulation.setSimulationDays(simulationRequest.getSimulationDays());

        return simulation;
    }

    /**
     * Validates the parameters of a SimulationRequest.
     *
     * @param simulationRequest the request containing the simulation parameters
     * @throws SimulationException if any validation criteria are not met
     */
    private void validateSimulationRequest(SimulationRequest simulationRequest) {
        if (simulationRequest.getName() == null || simulationRequest.getName().equals("")) {
            log.error("The simulation name is empty.");
            throw new SimulationException("Nazwa symulacji nie może być pusta.");
        }
        if (simulationRequest.getPopulationSize() <= 0 || simulationRequest.getInitialNumberOfInfectedPeople() <= 0) {
            log.error("The population size and the initial number of infected people must be greater than 0.");
            throw new SimulationException("Wielkość populacji oraz początkowa liczba osób zakażonych musi być większa od 0.");
        } else if (simulationRequest.getPopulationSize() < simulationRequest.getInitialNumberOfInfectedPeople()) {
            log.error("The population size cannot be less than the initial number of infected people.");
            throw new SimulationException("Wielkość populacji nie może być mniejsza niż początkowa liczba osób zakażonych.");
        }
        if (simulationRequest.getInfectiveRate() <= 0 || simulationRequest.getMortalityRate() <= 0) {
            log.error("An indicator determining how many people one infected person infects and the mortality rate must be greater than 0.");
            throw new SimulationException("Wskaźnik określający ile osób zaraża jedna zarażona osoba oraz wskaźnik śmiertelności musi być większy od 0.");
        }
        if (simulationRequest.getRecoveryDays() <= 0 || simulationRequest.getDeathDays() <= 0 || simulationRequest.getSimulationDays() <= 0) {
            log.error("The number of days that elapse from the time of infection to the recovery of the patient and the number of days that elapse from the time of " +
                    "infection to the death of the patient and the number of days for which the simulation is to be performed must be greater than 0.");
            throw new SimulationException("Ilość dni, która upływa od momentu zarażenia do wyzdrowienia chorego i ilość dni, która upływa od momentu zarażenia do śmierci " +
                    "chorego i Ilość dni, dla których ma być przeprowadzona symulacja muszą być większe od 0.");
        }
    }
}
