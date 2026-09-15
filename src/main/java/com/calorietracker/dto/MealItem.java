package com.calorietracker.dto;

public class MealItem {

    private String food;
    private String quantity;
    private double calories;
    private double protein;
    private double carbs;
    private double fat;
    private double fiber;

    public MealItem() {
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public boolean hasValidNutrition() {
        return food != null
                && !food.isBlank()
                && quantity != null
                && !quantity.isBlank()
                && calories >= 0
                && protein >= 0
                && carbs >= 0
                && fat >= 0
                && fiber >= 0;
    }

}
