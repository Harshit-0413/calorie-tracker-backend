package com.calorietracker.controller;

import com.calorietracker.dto.CreateMealRequest;
import com.calorietracker.entity.MealLogEntity;
import com.calorietracker.service.MealService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public MealLogEntity createMeal(
            @Valid @RequestBody CreateMealRequest request
    ) {
        return mealService.createMeal(request);
    }
}