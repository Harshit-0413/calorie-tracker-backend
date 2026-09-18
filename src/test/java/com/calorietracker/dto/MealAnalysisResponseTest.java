package com.calorietracker.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MealAnalysisResponseTest {

    @Test
    void shouldReturnCorrectTotalsForOneMealItem() {
        MealAnalysisResponse response = new MealAnalysisResponse();
        response.setItems(List.of(createMealItem("Poha", "2 plates", 450, 10, 70, 15, 6)));

        assertEquals(450, response.getTotalCalories());
        assertEquals(10, response.getTotalProtein());
        assertEquals(70, response.getTotalCarbs());
        assertEquals(15, response.getTotalFat());
        assertEquals(6, response.getTotalFiber());
    }

    @Test
    void shouldReturnSummedTotalsForMultipleMealItems() {
        MealAnalysisResponse response = new MealAnalysisResponse();
        response.setItems(List.of(
                createMealItem("Rice", "1 cup", 200, 4, 45, 1, 1),
                createMealItem("Dal", "1 bowl", 150, 9, 20, 4, 5)
        ));

        assertEquals(350, response.getTotalCalories());
        assertEquals(13, response.getTotalProtein());
        assertEquals(65, response.getTotalCarbs());
        assertEquals(5, response.getTotalFat());
        assertEquals(6, response.getTotalFiber());
    }

    @Test
    void shouldReturnZeroTotalsForEmptyItems() {
        MealAnalysisResponse response = new MealAnalysisResponse();
        response.setItems(List.of());

        assertEquals(0.0, response.getTotalCalories());
        assertEquals(0.0, response.getTotalProtein());
        assertEquals(0.0, response.getTotalCarbs());
        assertEquals(0.0, response.getTotalFat());
        assertEquals(0.0, response.getTotalFiber());
    }

    private MealItem createMealItem(
            String food,
            String quantity,
            double calories,
            double protein,
            double carbs,
            double fat,
            double fiber
    ) {
        MealItem item = new MealItem();
        item.setFood(food);
        item.setQuantity(quantity);
        item.setCalories(calories);
        item.setProtein(protein);
        item.setCarbs(carbs);
        item.setFat(fat);
        item.setFiber(fiber);
        return item;
    }
}
