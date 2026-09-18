package com.calorietracker.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealItemTest {

    @Test
    void shouldReturnTrueForValidNutrition() {

        MealItem item = new MealItem();

        item.setFood("Poha");
        item.setQuantity("2 plates");
        item.setCalories(450);
        item.setProtein(10);
        item.setCarbs(70);
        item.setFat(15);
        item.setFiber(6);

        assertTrue(item.hasValidNutrition());
    }

    @Test
    void shouldReturnFalseForNegativeNutrition() {

        MealItem item = new MealItem();

        item.setFood("Poha");
        item.setQuantity("2 plates");
        item.setCalories(-450);
        item.setProtein(10);
        item.setCarbs(70);
        item.setFat(15);
        item.setFiber(6);

        assertFalse(item.hasValidNutrition());
    }
}