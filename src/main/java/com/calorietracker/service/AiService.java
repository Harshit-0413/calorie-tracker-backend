package com.calorietracker.service;

import com.calorietracker.client.ClaudeClient;
import com.calorietracker.dto.MealAnalysisResponse;
import com.calorietracker.dto.MealItem;
import com.calorietracker.exception.AiResponseParseException;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class AiService {

    private final ClaudeClient claudeClient;
    private final ObjectMapper objectMapper;
    private final NutritionPromptBuilder promptBuilder;

    public AiService(
            ClaudeClient claudeClient,
            ObjectMapper objectMapper,
            NutritionPromptBuilder promptBuilder
    ) {
        this.claudeClient = claudeClient;
        this.objectMapper = objectMapper;
        this.promptBuilder = promptBuilder;
    }

    public MealAnalysisResponse parseMeal(String mealDescription) {

        String prompt = promptBuilder.build(mealDescription);

        String responseBody = claudeClient.testRequest(prompt);

        try {
            MealAnalysisResponse response = objectMapper.readValue(
                    responseBody,
                    MealAnalysisResponse.class
            );

            if (response.getItems() == null || response.getItems().isEmpty()) {
                throw new AiResponseParseException(
                        "AI response contains no meal items",
                        null
                );
            }

            for (MealItem item : response.getItems()) {
                if (!item.hasValidNutrition()) {
                    throw new AiResponseParseException(
                            "AI returned invalid nutrition values",
                            null
                    );
                }
            }

            return response;

        } catch (AiResponseParseException exception) {
            throw exception;

        } catch (Exception exception) {
            throw new AiResponseParseException(
                    "Failed to parse Claude response",
                    exception
            );
        }
    }
}