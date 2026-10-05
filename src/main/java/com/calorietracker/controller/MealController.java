package com.calorietracker.controller;

import com.calorietracker.dto.CreateMealRequest;
import com.calorietracker.dto.MealResponse;
import com.calorietracker.entity.MealLogEntity;
import com.calorietracker.service.MealLogService;
import com.calorietracker.service.MealService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;
    private final MealLogService mealLogService;

    public MealController(
            MealService mealService,
            MealLogService mealLogService
    ) {
        this.mealService = mealService;
        this.mealLogService = mealLogService;
    }

    @PostMapping
    public MealLogEntity createMeal(
            @Valid @RequestBody CreateMealRequest request
    ) {
        return mealService.createMeal(request);
    }

    @GetMapping("/user/{uid}")
    public List<MealResponse> getMealsForUser(
            @PathVariable String uid
    ) {
        return mealLogService.getMealsForUser(uid)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private MealResponse toResponse(MealLogEntity meal) {
        return new MealResponse(
                meal.getId(),
                meal.getUser().getUid(),
                meal.getMealType(),
                meal.getMealSource(),
                meal.getOriginalPrompt(),
                meal.getAiInsight(),
                meal.getLoggedAt(),
                meal.getCreatedAt(),
                meal.getUpdatedAt()
        );
    }
}