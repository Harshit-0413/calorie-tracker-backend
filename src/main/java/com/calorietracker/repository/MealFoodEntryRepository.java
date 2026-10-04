package com.calorietracker.repository;

import com.calorietracker.entity.MealFoodEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealFoodEntryRepository
        extends JpaRepository<MealFoodEntryEntity, Long> {
}