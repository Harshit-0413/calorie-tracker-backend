package com.calorietracker.service;

import com.calorietracker.entity.WeightEntryEntity;
import com.calorietracker.repository.WeightEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightEntryService {

    private final WeightEntryRepository weightEntryRepository;

    public WeightEntryService(WeightEntryRepository weightEntryRepository) {
        this.weightEntryRepository = weightEntryRepository;
    }

    public WeightEntryEntity saveWeight(WeightEntryEntity weightEntry) {
        return weightEntryRepository.save(weightEntry);
    }

    public List<WeightEntryEntity> getWeightsForUser(String uid) {
        return weightEntryRepository.findByUserUid(uid);
    }
}