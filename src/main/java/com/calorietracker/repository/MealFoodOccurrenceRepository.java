package com.calorietracker.repository;

import com.calorietracker.entity.MealFoodOccurrenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealFoodOccurrenceRepository
        extends JpaRepository<MealFoodOccurrenceEntity, Long> {
}