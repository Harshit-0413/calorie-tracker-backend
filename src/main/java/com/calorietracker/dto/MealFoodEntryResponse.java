package com.calorietracker.dto;

public class MealFoodEntryResponse {

    private Long id;
    private String foodId;
    private String foodName;
    private Double quantity;
    private String quantityUnit;
    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fat;
    private Double fiber;
    private Double sugar;

    public MealFoodEntryResponse() {
    }

    public MealFoodEntryResponse(
            Long id,
            String foodId,
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
        this.id = id;
        this.foodId = foodId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.fiber = fiber;
        this.sugar = sugar;
    }

    public Double getCalories() {
        return calories;
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

    public String getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public Long getId() {
        return id;
    }

    public Double getProtein() {
        return protein;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public Double getSugar() {
        return sugar;
    }
}