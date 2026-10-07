
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
    private boolean estimated;

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
        this(
                id, foodId, foodName, quantity, quantityUnit,
                calories, protein, carbs, fat, fiber, sugar, false
        );
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
            Double sugar,
            boolean estimated
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
        this.estimated = estimated;
    }

    public Long getId() {
        return id;
    }

    public String getFoodId() {
        return foodId;
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
        return estimated;
    }
}
