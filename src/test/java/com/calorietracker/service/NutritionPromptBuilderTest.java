
package com.calorietracker.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NutritionPromptBuilderTest {

    private final NutritionPromptBuilder promptBuilder =
            new NutritionPromptBuilder();

    @Test
    void shouldIncludeMealDescriptionInPrompt() {
        String mealDescription = "2 plates poha and 3 eggs";

        String prompt = promptBuilder.build(mealDescription);

        assertTrue(prompt.contains(mealDescription));
    }

    @Test
    void shouldIncludeRequiredMealItemJsonFields() {
        String prompt = promptBuilder.build("Poha");

        assertTrue(prompt.contains("\"items\""));
        assertTrue(prompt.contains("\"food\""));
        assertTrue(prompt.contains("\"quantity\""));
        assertTrue(prompt.contains("\"quantityUnit\""));
        assertTrue(prompt.contains("\"estimated\""));
        assertTrue(prompt.contains("\"calories\""));
        assertTrue(prompt.contains("\"protein\""));
        assertTrue(prompt.contains("\"carbs\""));
        assertTrue(prompt.contains("\"fat\""));
        assertTrue(prompt.contains("\"fiber\""));
        assertTrue(prompt.contains("\"sugar\""));
    }

    @Test
    void shouldRequestOnlyValidJsonWithoutMarkdown() {
        String prompt = promptBuilder.build("Poha");

        assertTrue(prompt.contains("Return ONLY valid JSON."));
        assertTrue(prompt.contains("Do not use markdown code fences."));
        assertTrue(prompt.contains(
                "Do not include explanations outside the JSON."));
    }
}
