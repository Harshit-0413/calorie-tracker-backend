package com.calorietracker.service;

import com.calorietracker.client.ClaudeClient;
import com.calorietracker.dto.MealAnalysisResponse;
import com.calorietracker.exception.AiResponseParseException;
import com.calorietracker.exception.ClaudeApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AiServiceTest {

    @Mock
    private ClaudeClient claudeClient;

    @Mock
    private NutritionPromptBuilder promptBuilder;

    private AiService aiService;

    @BeforeEach
    void setUp() {
        aiService = new AiService(
                claudeClient,
                new ObjectMapper(),
                promptBuilder
        );
    }

    @Test
    void shouldReturnParsedMealAnalysisForValidClaudeResponse() {
        String mealDescription = "2 plates poha";
        String prompt = "test prompt";

        String claudeResponse = """
                {
                  "items": [
                    {
                      "food": "Poha",
                      "quantity": 2,
                      "quantityUnit": "plate",
                      "estimated": false,
                      "calories": 450,
                      "protein": 10,
                      "carbs": 70,
                      "fat": 15,
                      "fiber": 6,
                        "sugar":1
                    }
                  ]
                }
                """;

        when(promptBuilder.build(mealDescription)).thenReturn(prompt);
        when(claudeClient.testRequest(prompt)).thenReturn(claudeResponse);

        MealAnalysisResponse response = aiService.parseMeal(mealDescription);

        assertEquals(1, response.getItems().size());
        assertEquals("Poha", response.getItems().get(0).getFood());
        assertEquals(2.0, response.getItems().get(0).getQuantity());
        assertEquals("plate", response.getItems().get(0).getQuantityUnit());
        assertFalse(response.getItems().get(0).isEstimated());
        assertEquals(450, response.getItems().get(0).getCalories());
        assertEquals(10, response.getItems().get(0).getProtein());
        assertEquals(70, response.getItems().get(0).getCarbs());
        assertEquals(15, response.getItems().get(0).getFat());
        assertEquals(6, response.getItems().get(0).getFiber());

        verify(promptBuilder).build(mealDescription);
        verify(claudeClient).testRequest(prompt);
    }

    @Test
    void shouldThrowAiResponseParseExceptionWhenResponseHasNoItems() {
        when(promptBuilder.build("Poha")).thenReturn("test prompt");
        when(claudeClient.testRequest("test prompt"))
                .thenReturn("""
                        {
                          "items": []
                        }
                        """);

        AiResponseParseException exception = assertThrows(
                AiResponseParseException.class,
                () -> aiService.parseMeal("Poha")
        );

        assertEquals(
                "AI response contains no meal items",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowAiResponseParseExceptionWhenResponseContainsInvalidMealItem() {
        when(promptBuilder.build("Poha")).thenReturn("test prompt");
        when(claudeClient.testRequest("test prompt"))
                .thenReturn("""
                        {
                          "items": [
                            {
                              "food": "Poha",
                              "quantity": 2,
                              "quantityUnit": "plate",
                              "estimated": false,
                              "calories": -450,
                              "protein": 10,
                              "carbs": 70,
                              "fat": 15,
                              "fiber": 6,
                              "sugar":1
                            }
                          ]
                        }
                        """);

        AiResponseParseException exception = assertThrows(
                AiResponseParseException.class,
                () -> aiService.parseMeal("Poha")
        );

        assertEquals(
                "AI returned invalid nutrition values",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowAiResponseParseExceptionWhenClaudeResponseIsMalformedJson() {
        when(promptBuilder.build("Poha")).thenReturn("test prompt");
        when(claudeClient.testRequest("test prompt"))
                .thenReturn("{\"items\":[{\"food\":\"Poha\"");

        AiResponseParseException exception = assertThrows(
                AiResponseParseException.class,
                () -> aiService.parseMeal("Poha")
        );

        assertEquals(
                "Failed to parse Claude response",
                exception.getMessage()
        );
    }

    @Test
    void shouldPropagateClaudeApiExceptionFromClaudeClient() {
        when(promptBuilder.build("Poha")).thenReturn("test prompt");

        ClaudeApiException apiException =
                new ClaudeApiException("Claude API failed");

        when(claudeClient.testRequest("test prompt"))
                .thenThrow(apiException);

        ClaudeApiException exception = assertThrows(
                ClaudeApiException.class,
                () -> aiService.parseMeal("Poha")
        );

        assertEquals("Claude API failed", exception.getMessage());
    }
}