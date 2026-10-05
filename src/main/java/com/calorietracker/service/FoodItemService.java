package com.calorietracker.service;

import com.calorietracker.dto.CreateFoodRequest;
import com.calorietracker.entity.FoodItemEntity;
import com.calorietracker.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItemEntity createFood(CreateFoodRequest request) {

        System.out.println("Food ID received: " + request.getId());
        FoodItemEntity food = new FoodItemEntity(
                request.getId(),
                request.getName(),
                request.getCalories(),
                request.getProtein(),
                request.getCarbs(),
                request.getFat(),
                request.getFiber(),
                request.getSugar(),
                request.getServingSize(),
                request.getServingUnit()
        );

        food.setNameHindi(request.getNameHindi());
        food.setCategory(request.getCategory());
        food.setAliases(request.getAliases());
        food.setBrand(request.getBrand());
        food.setVerified(request.getVerified());
        food.setSource(request.getSource());

        return foodItemRepository.save(food);
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