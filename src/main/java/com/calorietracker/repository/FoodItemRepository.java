package com.calorietracker.repository;

import com.calorietracker.entity.FoodItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository
        extends JpaRepository<FoodItemEntity, String> {
}