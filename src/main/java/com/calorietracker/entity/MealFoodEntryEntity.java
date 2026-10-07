
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

@Entity
@Table(name = "meal_food_entries")
public class MealFoodEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meal_id", nullable = false)
    private MealLogEntity meal;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "food_id", nullable = true)
    private FoodItemEntity food;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(nullable = false)
    private Double quantity;

    @Column(name = "quantity_unit", nullable = false)
    private String quantityUnit;

    @Column(nullable = false)
    private Double calories;

    @Column(nullable = false)
    private Double protein;

    @Column(nullable = false)
    private Double carbs;

    @Column(nullable = false)
    private Double fat;

    @Column(nullable = false)
    private Double fiber;

    @Column(nullable = false)
    private Double sugar;

    @Column(name = "nutrition_estimated")
    private Boolean estimated = false;

    protected MealFoodEntryEntity() {
    }

    public MealFoodEntryEntity(
            MealLogEntity meal,
            FoodItemEntity food,
            String foodName,
            Double quantity,
            String quantityUnit,
            Double calories,
            Double protein,
            Double carbs,
            Double fat,
            Double fiber,
            Double sugar
    ) {
        this(
                meal, food, foodName, quantity, quantityUnit,
                calories, protein, carbs, fat, fiber, sugar, false
        );
    }

    public MealFoodEntryEntity(
            MealLogEntity meal,
            FoodItemEntity food,
            String foodName,
            Double quantity,
            String quantityUnit,
            Double calories,
            Double protein,
            Double carbs,
            Double fat,
            Double fiber,
            Double sugar,
            boolean estimated
    ) {
        this.meal = meal;
        this.food = food;
        this.foodName = foodName;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.fiber = fiber;
        this.sugar = sugar;
        this.estimated = estimated;
    }

    public Long getId() {
        return id;
    }

    public MealLogEntity getMeal() {
        return meal;
    }

    public FoodItemEntity getFood() {
        return food;
    }

    public String getFoodName() {
        return foodName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public Double getCalories() {
        return calories;
    }

    public Double getProtein() {
        return protein;
    }

    public Double getCarbs() {
        return carbs;
    }

    public Double getFat() {
        return fat;
    }

    public Double getFiber() {
        return fiber;
    }

    public Double getSugar() {
        return sugar;
    }

    public boolean isEstimated() {
        return Boolean.TRUE.equals(estimated);
    }
}
