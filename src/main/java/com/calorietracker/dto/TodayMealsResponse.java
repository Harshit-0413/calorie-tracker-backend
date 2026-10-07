package com.calorietracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TodayMealsResponse {

    private LocalDate date;

    private List<TodayMeal> meals;

    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;
    private double totalFiber;
    private double totalSugar;

    public TodayMealsResponse(
            LocalDate date,
            List<TodayMeal> meals,
            double totalCalories,
            double totalProtein,
            double totalCarbs,
            double totalFat,
            double totalFiber,
            double totalSugar
    ) {
        this.date = date;
        this.meals = meals;
        this.totalCalories = totalCalories;
        this.totalProtein = totalProtein;
        this.totalCarbs = totalCarbs;
        this.totalFat = totalFat;
        this.totalFiber = totalFiber;
        this.totalSugar = totalSugar;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<TodayMeal> getMeals() {
        return meals;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public double getTotalFiber() {
        return totalFiber;
    }

    public double getTotalSugar() {
        return totalSugar;
    }

    public static class TodayMeal {

        private String mealId;
        private String mealType;
        private String mealSource;
        private String originalPrompt;
        private LocalDateTime loggedAt;

        private List<TodayMealEntry> entries;

        private double totalCalories;
        private double totalProtein;
        private double totalCarbs;
        private double totalFat;
        private double totalFiber;
        private double totalSugar;

        public TodayMeal(
                String mealId,
                String mealType,
                String mealSource,
                String originalPrompt,
                LocalDateTime loggedAt,
                List<TodayMealEntry> entries,
                double totalCalories,
                double totalProtein,
                double totalCarbs,
                double totalFat,
                double totalFiber,
                double totalSugar
        ) {
            this.mealId = mealId;
            this.mealType = mealType;
            this.mealSource = mealSource;
            this.originalPrompt = originalPrompt;
            this.loggedAt = loggedAt;
            this.entries = entries;
            this.totalCalories = totalCalories;
            this.totalProtein = totalProtein;
            this.totalCarbs = totalCarbs;
            this.totalFat = totalFat;
            this.totalFiber = totalFiber;
            this.totalSugar = totalSugar;
        }

        public String getMealId() {
            return mealId;
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

        public LocalDateTime getLoggedAt() {
            return loggedAt;
        }

        public List<TodayMealEntry> getEntries() {
            return entries;
        }

        public double getTotalCalories() {
            return totalCalories;
        }

        public double getTotalProtein() {
            return totalProtein;
        }

        public double getTotalCarbs() {
            return totalCarbs;
        }

        public double getTotalFat() {
            return totalFat;
        }

        public double getTotalFiber() {
            return totalFiber;
        }

        public double getTotalSugar() {
            return totalSugar;
        }
    }

    public static class TodayMealEntry {

        private Long entryId;
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

        public TodayMealEntry(
                Long entryId,
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
            this.entryId = entryId;
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

        public Long getEntryId() {
            return entryId;
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
}