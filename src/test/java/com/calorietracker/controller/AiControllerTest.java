package com.calorietracker.controller;

import com.calorietracker.exception.GlobalExceptionHandler;
import com.calorietracker.service.AiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.web.servlet.MockMvc;
import com.calorietracker.dto.MealAnalysisResponse;
import com.calorietracker.dto.MealItem;
import com.calorietracker.exception.ClaudeApiException;

import static org.mockito.Mockito.when;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class AiControllerTest {

    @Mock
    private AiService aiService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        AiController aiController = new AiController(aiService);

        mockMvc = standaloneSetup(aiController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnBadRequestWhenMealDescriptionIsEmpty() throws Exception {

        mockMvc.perform(
                        post("/api/ai/parse-meal")
                                .contentType("application/json")
                                .content("""
                                        {
                                          "prompt": ""
                                        }
                                        """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Validation failed")))
                .andExpect(jsonPath("$.message", is("Meal description cannot be empty")));
    }

    @Test
    void shouldReturnMealAnalysisForValidRequest() throws Exception {

        MealItem item = new MealItem();
        item.setFood("Poha");
        item.setQuantity("2 plates");
        item.setCalories(450);
        item.setProtein(10);
        item.setCarbs(70);
        item.setFat(15);
        item.setFiber(6);

        MealAnalysisResponse response = new MealAnalysisResponse();
        response.setItems(List.of(item));

        when(aiService.parseMeal("2 plates poha"))
                .thenReturn(response);

        mockMvc.perform(
                        post("/api/ai/parse-meal")
                                .contentType("application/json")
                                .content("""
                                        {
                                          "prompt": "2 plates poha"
                                        }
                                        """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].food", is("Poha")))
                .andExpect(jsonPath("$.items[0].quantity", is("2 plates")))
                .andExpect(jsonPath("$.items[0].calories", is(450.0)))
                .andExpect(jsonPath("$.items[0].protein", is(10.0)))
                .andExpect(jsonPath("$.items[0].carbs", is(70.0)))
                .andExpect(jsonPath("$.items[0].fat", is(15.0)))
                .andExpect(jsonPath("$.items[0].fiber", is(6.0)))
                .andExpect(jsonPath("$.totalCalories", is(450.0)))
                .andExpect(jsonPath("$.totalProtein", is(10.0)))
                .andExpect(jsonPath("$.totalCarbs", is(70.0)))
                .andExpect(jsonPath("$.totalFat", is(15.0)))
                .andExpect(jsonPath("$.totalFiber", is(6.0)));
    }
    @Test
    void shouldReturnBadGatewayWhenClaudeApiFails() throws Exception {

        when(aiService.parseMeal("Poha"))
                .thenThrow(new ClaudeApiException("Claude API failed"));

        mockMvc.perform(
                        post("/api/ai/parse-meal")
                                .contentType("application/json")
                                .content("""
                            {
                              "prompt": "Poha"
                            }
                            """)
                )
                .andExpect(status().isBadGateway())
                .andExpect(jsonPath("$.error", is("Claude API request failed")))
                .andExpect(jsonPath("$.message", is("Claude API failed")));
    }
}
