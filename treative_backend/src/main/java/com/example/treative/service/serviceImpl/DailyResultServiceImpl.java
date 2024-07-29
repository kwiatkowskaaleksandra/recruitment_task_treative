package com.example.treative.service.serviceImpl;

import com.example.treative.model.DailyResult;
import com.example.treative.repository.DailyResultRepository;
import com.example.treative.service.DailyResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the DailyResultService interface.
 */
@Service
@RequiredArgsConstructor
public class DailyResultServiceImpl implements DailyResultService {

    private final DailyResultRepository dailyResultRepository;

    @Override
    public void saveAll(List<DailyResult> results) {
        dailyResultRepository.saveAll(results);
    }

    @Override
    public List<DailyResult> getAllDailyResultsByIdSimulation(Long idSimulation) {
        return dailyResultRepository.findAllBySimulation_IdSimulation(idSimulation);
    }

    @Override
    public void deleteAllDailyResultsBySimulation(List<DailyResult> results) {
        dailyResultRepository.deleteAll(results);
    }
}
