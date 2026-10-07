package com.calorietracker.dto;

import java.time.LocalDate;

public class DashboardSummaryResponse {

    private LocalDate date;

    private NutritionSummary calories;
    private NutritionSummary protein;
    private NutritionSummary carbs;
    private NutritionSummary fat;

    private Double fiberConsumed;
    private Double sugarConsumed;

    private TodayMealsResponse meals;

    public DashboardSummaryResponse(
            LocalDate date,
            NutritionSummary calories,
            NutritionSummary protein,
            NutritionSummary carbs,
            NutritionSummary fat,
            Double fiberConsumed,
            Double sugarConsumed,
            TodayMealsResponse meals
    ) {
        this.date = date;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.fiberConsumed = fiberConsumed;
        this.sugarConsumed = sugarConsumed;
        this.meals = meals;
    }

    public LocalDate getDate() {
        return date;
    }

    public NutritionSummary getCalories() {
        return calories;
    }

    public NutritionSummary getProtein() {
        return protein;
    }

    public NutritionSummary getCarbs() {
        return carbs;
    }

    public NutritionSummary getFat() {
        return fat;
    }

    public Double getFiberConsumed() {
        return fiberConsumed;
    }

    public Double getSugarConsumed() {
        return sugarConsumed;
    }

    public TodayMealsResponse getMeals() {
        return meals;
    }

    public static class NutritionSummary {

        private Double goal;
        private Double consumed;
        private Double remaining;

        public NutritionSummary(
                Double goal,
                Double consumed
        ) {
            this.goal = goal;
            this.consumed = consumed;
            this.remaining = Math.max(0.0, goal - consumed);
        }

        public Double getGoal() {
            return goal;
        }

        public Double getConsumed() {
            return consumed;
        }

        public Double getRemaining() {
            return remaining;
        }
    }
}