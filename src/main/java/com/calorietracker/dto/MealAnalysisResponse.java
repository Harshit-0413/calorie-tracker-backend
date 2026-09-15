package com.calorietracker.dto;

import java.util.List;

public class MealAnalysisResponse {
    private List<MealItem> items;

    public MealAnalysisResponse() {
    }

    public List<MealItem> getItems() {
        return items;
    }

    public void setItems(List<MealItem> items) {
        this.items = items;
    }
    public double getTotalCalories() {
        return items.stream()
                .mapToDouble(MealItem::getCalories)
                .sum();
    }
    public double getTotalProtein(){
        return items.stream()
                .mapToDouble(MealItem::getProtein)
                .sum();
    }
    public double getTotalCarbs() {
        return items.stream()
                .mapToDouble(MealItem::getCarbs)
                .sum();
    }

    public double getTotalFat() {
        return items.stream()
                .mapToDouble(MealItem::getFat)
                .sum();
    }

    public double getTotalFiber() {
        return items.stream()
                .mapToDouble(MealItem::getFiber)
                .sum();
    }
}
