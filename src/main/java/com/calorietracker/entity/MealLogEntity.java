package com.calorietracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "meal_logs")
public class MealLogEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfileEntity user;

    @Column(name = "meal_type", nullable = false)
    private String mealType;

    @Column(name = "meal_source", nullable = false)
    private String mealSource;

    @Column(name = "original_prompt")
    private String originalPrompt;

    @Column(name = "ai_insight")
    private String aiInsight;

    @Column(name = "logged_at", nullable = false)
    private LocalDateTime loggedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected MealLogEntity() {
    }

    public MealLogEntity(
            String id,
            UserProfileEntity user,
            String mealType,
            String mealSource,
            LocalDateTime loggedAt
    ) {
        this.id = id;
        this.user = user;
        this.mealType = mealType;
        this.mealSource = mealSource;
        this.loggedAt = loggedAt;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public UserProfileEntity getUser() {
        return user;
    }

    public String getMealType() {
        return mealType;
    }

    public String getMealSource() {
        return mealSource;
    }

    public String getOriginalPrompt() {
        return originalPrompt;
    }

    public String getAiInsight() {
        return aiInsight;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setOriginalPrompt(String originalPrompt) {
        this.originalPrompt = originalPrompt;
    }

    public void setAiInsight(String aiInsight) {
        this.aiInsight = aiInsight;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}