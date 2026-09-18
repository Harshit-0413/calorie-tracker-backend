package com.calorietracker.dto;

import jakarta.validation.constraints.NotBlank;

public class ParseMealRequest {

    @NotBlank(message = "Meal description cannot be empty")
    private String prompt;

    public ParseMealRequest(){
    }

    public String getPrompt(){
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
