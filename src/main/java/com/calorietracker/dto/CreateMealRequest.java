package com.calorietracker.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateMealRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String mealType;

    @NotBlank
    private String mealSource;

    private String originalPrompt;

    @NotEmpty
    @Valid
    private List<CreateMealFoodEntryRequest> foodEntries;

    public CreateMealRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealSource() {
        return mealSource;
    }

    public void setMealSource(String mealSource) {
        this.mealSource = mealSource;
    }

    public String getOriginalPrompt() {
        return originalPrompt;
    }

    public void setOriginalPrompt(String originalPrompt) {
        this.originalPrompt = originalPrompt;
    }

    public List<CreateMealFoodEntryRequest> getFoodEntries() {
        return foodEntries;
    }

    public void setFoodEntries(List<CreateMealFoodEntryRequest> foodEntries) {
        this.foodEntries = foodEntries;
    }
}