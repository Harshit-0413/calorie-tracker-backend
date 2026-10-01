package com.calorietracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "meal_food_occurrences")
public class MealFoodOccurrenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meal_food_entry_id", nullable = false)
    private MealFoodEntryEntity mealFoodEntry;

    @Column(nullable = false)
    private Double quantity;

    @Column(name = "quantity_unit", nullable = false)
    private String quantityUnit;

    @Column(name = "logged_at", nullable = false)
    private LocalDateTime loggedAt;

    protected MealFoodOccurrenceEntity() {
    }

    public MealFoodOccurrenceEntity(
            MealFoodEntryEntity mealFoodEntry,
            Double quantity,
            String quantityUnit,
            LocalDateTime loggedAt
    ) {
        this.mealFoodEntry = mealFoodEntry;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.loggedAt = loggedAt;
    }

    public Long getId() {
        return id;
    }

    public MealFoodEntryEntity getMealFoodEntry() {
        return mealFoodEntry;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }
}