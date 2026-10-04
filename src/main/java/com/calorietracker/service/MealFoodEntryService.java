package com.calorietracker.service;

import com.calorietracker.entity.MealFoodEntryEntity;
import com.calorietracker.repository.MealFoodEntryRepository;
import org.springframework.stereotype.Service;

@Service
public class MealFoodEntryService {

    private final MealFoodEntryRepository mealFoodEntryRepository;

    public MealFoodEntryService(MealFoodEntryRepository mealFoodEntryRepository) {
        this.mealFoodEntryRepository = mealFoodEntryRepository;
    }

    public MealFoodEntryEntity saveEntry(MealFoodEntryEntity entry) {
        return mealFoodEntryRepository.save(entry);
    }

    public MealFoodEntryEntity getEntry(Long entryId) {
        return mealFoodEntryRepository.findById(entryId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Meal food entry not found: " + entryId
                ));
    }
}