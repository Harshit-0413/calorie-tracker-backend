package com.calorietracker.dto;

import jakarta.validation.constraints.NotBlank;

public class ParseMealRequest {

    @NotBlank
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
