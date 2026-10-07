
package com.calorietracker.controller;

import com.calorietracker.dto.AiLogMealRequest;
import com.calorietracker.dto.AiMealLogResponse;
import com.calorietracker.dto.MealAnalysisResponse;
import com.calorietracker.dto.ParseMealRequest;
import com.calorietracker.service.AiService;
import com.calorietracker.service.MealService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;
    private final MealService mealService;

    public AiController(AiService aiService, MealService mealService) {
        this.aiService = aiService;
        this.mealService = mealService;
    }

    @PostMapping("/parse-meal")
    public MealAnalysisResponse parseMeal(
            @Valid @RequestBody ParseMealRequest request
    ) {
        return aiService.parseMeal(request.getPrompt());
    }

    @PostMapping("/log-meal")
    public AiMealLogResponse logMeal(
            @Valid @RequestBody AiLogMealRequest request
    ) {
        MealAnalysisResponse analysis =
                aiService.parseMeal(request.getPrompt());

        return mealService.logAiMeal(request, analysis);
    }
}
