
package com.calorietracker.dto;

public class MealItem {

    private String food;
    private Double quantity;
    private String quantityUnit;
    private boolean estimated;

    private double calories;
    private double protein;
    private double carbs;
    private double fat;
    private double fiber;
    private Double sugar;

    public MealItem() {
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public boolean isEstimated() {
        return estimated;
    }

    public void setEstimated(boolean estimated) {
        this.estimated = estimated;
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

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public boolean hasValidNutrition() {
        return food != null
                && !food.isBlank()
                && quantity != null
                && Double.isFinite(quantity)
                && quantity > 0
                && quantityUnit != null
                && !quantityUnit.isBlank()
                && Double.isFinite(calories)
                && Double.isFinite(protein)
                && Double.isFinite(carbs)
                && Double.isFinite(fat)
                && Double.isFinite(fiber)
                && Double.isFinite(sugar)
                && calories >= 0
                && protein >= 0
                && carbs >= 0
                && fat >= 0
                && fiber >= 0
                && sugar >= 0;
    }
}
