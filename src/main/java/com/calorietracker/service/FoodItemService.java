package com.calorietracker.service;

import com.calorietracker.entity.FoodItemEntity;
import com.calorietracker.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItemEntity saveFood(FoodItemEntity food) {
        return foodItemRepository.save(food);
    }

    public FoodItemEntity getFood(String foodId) {
        return foodItemRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Food not found: " + foodId
                ));
    }
}