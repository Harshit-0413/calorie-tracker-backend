package com.calorietracker.service;

import com.calorietracker.entity.MealFoodOccurrenceEntity;
import com.calorietracker.repository.MealFoodOccurrenceRepository;
import org.springframework.stereotype.Service;

@Service
public class MealFoodOccurrenceService {

    private final MealFoodOccurrenceRepository mealFoodOccurrenceRepository;

    public MealFoodOccurrenceService(
            MealFoodOccurrenceRepository mealFoodOccurrenceRepository
    ) {
        this.mealFoodOccurrenceRepository = mealFoodOccurrenceRepository;
    }

    public MealFoodOccurrenceEntity saveOccurrence(
            MealFoodOccurrenceEntity occurrence
    ) {
        return mealFoodOccurrenceRepository.save(occurrence);
    }

    public MealFoodOccurrenceEntity getOccurrence(Long occurrenceId) {
        return mealFoodOccurrenceRepository.findById(occurrenceId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Meal food occurrence not found: " + occurrenceId
                ));
    }
}