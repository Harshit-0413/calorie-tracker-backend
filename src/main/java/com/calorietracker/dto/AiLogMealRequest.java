
package com.calorietracker.dto;

import jakarta.validation.constraints.NotBlank;

public class AiLogMealRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String mealType;

    @NotBlank
    private String prompt;

    public AiLogMealRequest() {
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

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
