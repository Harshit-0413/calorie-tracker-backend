package com.calorietracker.controller;

import com.calorietracker.dto.MealAnalysisResponse;
import com.calorietracker.dto.ParseMealRequest;
import com.calorietracker.service.AiService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/parse-meal")
    public MealAnalysisResponse parseMeal(@Valid @RequestBody ParseMealRequest request) {
        return aiService.parseMeal(request.getPrompt());
    }
}
