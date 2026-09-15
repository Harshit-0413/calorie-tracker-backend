package com.calorietracker.service;

import org.springframework.stereotype.Component;

@Component
public class NutritionPromptBuilder {

    public String build(String mealDescription) {

        return """
                You are a nutrition analysis assistant.

                Analyze the user's meal description.

                Identify every food item and estimate its quantity,
                calories, protein, carbohydrates, fat, and fiber.

                Return ONLY valid JSON.
                Do not use markdown code fences.
                Do not include any text before or after the JSON.

                The JSON must follow exactly this structure:

                {
                  "items": [
                    {
                      "food": "string",
                      "quantity": "string",
                      "calories": 0,
                      "protein": 0,
                      "carbs": 0,
                      "fat": 0,
                      "fiber": 0
                    }
                  ]
                }

                Rules:
                - calories are in kcal.
                - protein, carbs, fat, and fiber are in grams.
                - Identify every food item separately.
                - If quantity is unclear, make a reasonable estimate.
                - Return numbers for all nutrition values.
                - Do not return markdown.
                - Do not return explanations outside the JSON.
                - If the user does not provide a quantity, estimate a standard serving size and mark the quantity as "(estimated)".
                - Never pretend an estimated quantity was explicitly provided by the user.

                User meal:
                """ + mealDescription;
    }
}