package com.calorietracker.controller;

import com.calorietracker.dto.CreateMealRequest;
import com.calorietracker.dto.MealFoodEntryResponse;
import com.calorietracker.dto.MealResponse;
import com.calorietracker.entity.MealFoodEntryEntity;
import com.calorietracker.entity.MealLogEntity;
import com.calorietracker.service.MealFoodEntryService;
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
    private final MealFoodEntryService mealFoodEntryService;

    public MealController(
            MealService mealService,
            MealLogService mealLogService,
            MealFoodEntryService mealFoodEntryService
    ) {
        this.mealService = mealService;
        this.mealLogService = mealLogService;
        this.mealFoodEntryService = mealFoodEntryService;
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
    @GetMapping("/{mealId}/entries")
    public List<MealFoodEntryResponse> getMealEntries(
            @PathVariable String mealId
    ) {
        return mealFoodEntryService.getEntriesForMeal(mealId)
                .stream()
                .map(this::toFoodEntryResponse)
                .toList();
    }

    private MealFoodEntryResponse toFoodEntryResponse(MealFoodEntryEntity entry) {
        return new MealFoodEntryResponse(
                entry.getId(),
                entry.getFood().getId(),
                entry.getFoodName(),
                entry.getQuantity(),
                entry.getQuantityUnit(),
                entry.getCalories(),
                entry.getProtein(),
                entry.getCarbs(),
                entry.getFat(),
                entry.getFiber(),
                entry.getSugar()
        );
    }
}