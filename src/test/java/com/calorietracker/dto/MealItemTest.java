package com.calorietracker.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealItemTest {

    @Test
    void shouldReturnTrueForValidNutrition() {
        MealItem item = new MealItem();

        item.setFood("Poha");
        item.setQuantity(2.0);
        item.setQuantityUnit("plates");
        item.setEstimated(false);
        item.setCalories(450);
        item.setProtein(10);
        item.setCarbs(70);
        item.setFat(15);
        item.setFiber(6);
        item.setSugar(1.0);

        assertTrue(item.hasValidNutrition());
    }

    @Test
    void shouldReturnFalseForNegativeNutrition() {
        MealItem item = new MealItem();

        item.setFood("Poha");
        item.setQuantity(2.0);
        item.setQuantityUnit("plates");
        item.setCalories(-450);
        item.setProtein(10);
        item.setCarbs(70);
        item.setFat(15);
        item.setFiber(6);
        item.setSugar(1.0);
        assertFalse(item.hasValidNutrition());
    }

    @Test
    void shouldReturnFalseWhenQuantityIsMissing() {
        MealItem item = new MealItem();

        item.setFood("Poha");
        item.setQuantityUnit("plates");
        item.setCalories(450);
        item.setProtein(10);
        item.setCarbs(70);
        item.setFat(15);
        item.setFiber(6);
        item.setSugar(1.0);
        assertFalse(item.hasValidNutrition());
    }

    @Test
    void shouldReturnFalseWhenQuantityIsZero() {
        MealItem item = new MealItem();

        item.setFood("Poha");
        item.setQuantity(0.0);
        item.setQuantityUnit("plates");
        item.setCalories(450);
        item.setProtein(10);
        item.setCarbs(70);
        item.setFat(15);
        item.setFiber(6);
        item.setSugar(1.0);
        assertFalse(item.hasValidNutrition());
    }

    @Test
    void shouldReturnFalseWhenQuantityUnitIsMissing() {
        MealItem item = new MealItem();

        item.setFood("Poha");
        item.setQuantity(2.0);
        item.setCalories(450);
        item.setProtein(10);
        item.setCarbs(70);
        item.setFat(15);
        item.setFiber(6);
        item.setSugar(1.0);
        assertFalse(item.hasValidNutrition());
    }
}