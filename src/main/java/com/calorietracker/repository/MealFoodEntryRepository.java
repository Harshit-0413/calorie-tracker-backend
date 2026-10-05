package com.calorietracker.repository;

import com.calorietracker.entity.MealFoodEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealFoodEntryRepository
        extends JpaRepository<MealFoodEntryEntity, Long> {
    List<MealFoodEntryEntity> findByMealId(String mealId);
}