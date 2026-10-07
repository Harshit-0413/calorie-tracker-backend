
package com.calorietracker.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AiMealLogResponse {

    private String mealId;
    private String mealType;
    private String mealSource;
    private LocalDateTime loggedAt;
    private List<LoggedItem> items;

    public AiMealLogResponse() {
    }

    public AiMealLogResponse(
            String mealId,
            String mealType,
            String mealSource,
            LocalDateTime loggedAt,
            List<LoggedItem> items
    ) {
        this.mealId = mealId;
        this.mealType = mealType;
        this.mealSource = mealSource;
        this.loggedAt = loggedAt;
        this.items = items;
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

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public List<LoggedItem> getItems() {
        return items;
    }

    public double getTotalCalories() {
        return items.stream().mapToDouble(LoggedItem::getCalories).sum();
    }

    public double getTotalProtein() {
        return items.stream().mapToDouble(LoggedItem::getProtein).sum();
    }

    public double getTotalCarbs() {
        return items.stream().mapToDouble(LoggedItem::getCarbs).sum();
    }

    public double getTotalFat() {
        return items.stream().mapToDouble(LoggedItem::getFat).sum();
    }

    public double getTotalFiber() {
        return items.stream().mapToDouble(LoggedItem::getFiber).sum();
    }

    public double getTotalSugar() {
        return items.stream().mapToDouble(LoggedItem::getSugar).sum();
    }

    public static class LoggedItem {

        private Long entryId;
        private String foodId;
        private String foodName;
        private Double quantity;
        private String quantityUnit;
        private boolean estimated;
        private Double calories;
        private Double protein;
        private Double carbs;
        private Double fat;
        private Double fiber;
        private Double sugar;

        public LoggedItem() {
        }

        public LoggedItem(
                Long entryId,
                String foodId,
                String foodName,
                Double quantity,
                String quantityUnit,
                boolean estimated,
                Double calories,
                Double protein,
                Double carbs,
                Double fat,
                Double fiber,
                Double sugar
        ) {
            this.entryId = entryId;
            this.foodId = foodId;
            this.foodName = foodName;
            this.quantity = quantity;
            this.quantityUnit = quantityUnit;
            this.estimated = estimated;
            this.calories = calories;
            this.protein = protein;
            this.carbs = carbs;
            this.fat = fat;
            this.fiber = fiber;
            this.sugar = sugar;
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

        public boolean isEstimated() {
            return estimated;
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
    }
}
