
package com.calorietracker.service;

import org.springframework.stereotype.Component;

@Component
public class NutritionPromptBuilder {

    public String build(String mealDescription) {

        return """
                You are a nutrition analysis assistant.

                Analyze the user's meal description and identify
                every food item separately.

                Return ONLY valid JSON.
                Do not use markdown code fences.
                Do not include explanations outside the JSON.

                Use exactly this structure:

                {
                  "items": [
                    {
                      "food": "Roti",
                      "quantity": 2,
                      "quantityUnit": "piece",
                      "estimated": true,
                      "calories": 208,
                      "protein": 6.2,
                      "carbs": 37,
                      "fat": 5,
                      "fiber": 4,
                      "sugar": 1
                    }
                  ]
                }

                Rules:
                - quantity must be a positive number, never a string.
                - quantityUnit must be a non-empty string.
                - Use standard units: g, ml, piece, serving, cup, or bowl.
                - Use g for explicitly provided weights in grams.
                - Use ml for explicitly provided volumes in millilitres.
                - Use piece for countable items.
                - estimated must be true if quantity or nutrition is estimated.
                - estimated must be false only when both quantity and
                  nutrition are explicitly provided by the user.
                - Never pretend an estimated quantity or nutrition value
                  was explicitly provided by the user.
                - If quantity is unclear, estimate a reasonable amount.
                - Calories are in kcal.
                - Protein, carbs, fat, fiber, and sugar are in grams.
                - Return numeric values for all nutrition fields.
                - All nutrition values must be zero or positive.
                - Do not invent precision beyond a reasonable estimate.
                - Return one object per distinct food item.
                - Do not include explanations outside the JSON.

                User meal:
                """ + mealDescription;
    }
}
