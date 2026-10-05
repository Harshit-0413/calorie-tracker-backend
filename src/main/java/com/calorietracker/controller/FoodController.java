package com.calorietracker.controller;

import com.calorietracker.dto.CreateFoodRequest;
import com.calorietracker.entity.FoodItemEntity;
import com.calorietracker.service.FoodItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodItemService foodItemService;

    public FoodController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public FoodItemEntity createFood(@RequestBody CreateFoodRequest request) {
        return foodItemService.createFood(request);
    }
    @GetMapping("/{foodId}")
    public FoodItemEntity getFood(@PathVariable String foodId) {
        return foodItemService.getFood(foodId);
    }
}