package com.calorietracker.dto;

import java.time.LocalDateTime;

public class MealResponse {

    private String id;
    private String userId;
    private String mealType;
    private String mealSource;
    private String originalPrompt;
    private String aiInsight;
    private LocalDateTime loggedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MealResponse() {
    }

    public String getAiInsight() {
        return aiInsight;
    }

    public void setAiInsight(String aiInsight) {
        this.aiInsight = aiInsight;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }

    public String getMealSource() {
        return mealSource;
    }

    public void setMealSource(String mealSource) {
        this.mealSource = mealSource;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getOriginalPrompt() {
        return originalPrompt;
    }

    public void setOriginalPrompt(String originalPrompt) {
        this.originalPrompt = originalPrompt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MealResponse(
            String id,
            String userId,
            String mealType,
            String mealSource,
            String originalPrompt,
            String aiInsight,
            LocalDateTime loggedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.mealType = mealType;
        this.mealSource = mealSource;
        this.originalPrompt = originalPrompt;
        this.aiInsight = aiInsight;
        this.loggedAt = loggedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Generate getters
}