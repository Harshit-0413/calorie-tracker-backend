package com.calorietracker.service;

import com.calorietracker.dto.AiLogMealRequest;
import com.calorietracker.dto.AiMealLogResponse;
import com.calorietracker.dto.MealAnalysisResponse;
import com.calorietracker.dto.MealItem;
import com.calorietracker.entity.FoodItemEntity;
import com.calorietracker.entity.MealFoodEntryEntity;
import com.calorietracker.entity.MealLogEntity;
import com.calorietracker.entity.UserProfileEntity;
import com.calorietracker.repository.FoodItemRepository;
import com.calorietracker.repository.MealFoodEntryRepository;
import com.calorietracker.repository.MealFoodOccurrenceRepository;
import com.calorietracker.repository.MealLogRepository;
import com.calorietracker.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MealServiceTest {

    @Mock
    private MealLogRepository mealLogRepository;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private FoodItemRepository foodItemRepository;

    @Mock
    private MealFoodEntryRepository mealFoodEntryRepository;

    @Mock
    private MealFoodOccurrenceRepository mealFoodOccurrenceRepository;

    @Mock
    private UserProfileEntity user;

    @Mock
    private FoodItemEntity roti;

    @Mock
    private FoodItemEntity rice;

    private MealService mealService;

    @BeforeEach
    void setUp() {
        mealService = new MealService(
                mealLogRepository,
                userProfileRepository,
                foodItemRepository,
                mealFoodEntryRepository,
                mealFoodOccurrenceRepository
        );
    }

    @Test
    void shouldLogAiMealUsingCatalogNutrition() {

        // User
        when(userProfileRepository.findById("test-user-001"))
                .thenReturn(Optional.of(user));

        // Roti catalog
        when(roti.getId())
                .thenReturn("food-roti-001");

        when(roti.getName())
                .thenReturn("Roti");

        when(roti.getServingUnit())
                .thenReturn("g");

        // Rice catalog
        when(rice.getId())
                .thenReturn("food-rice-001");

        when(rice.getName())
                .thenReturn("Cooked Rice");

        when(rice.getServingSize())
                .thenReturn(100.0);

        when(rice.getServingUnit())
                .thenReturn("g");

        when(rice.getCalories())
                .thenReturn(130.0);

        when(rice.getProtein())
                .thenReturn(2.7);

        when(rice.getCarbs())
                .thenReturn(28.2);

        when(rice.getFat())
                .thenReturn(0.3);

        when(rice.getFiber())
                .thenReturn(0.4);

        when(rice.getSugar())
                .thenReturn(0.1);

        when(foodItemRepository.findAll())
                .thenReturn(List.of(roti, rice));

        // Fake AI response

        MealItem rotiItem = new MealItem();
        rotiItem.setFood("Roti");
        rotiItem.setQuantity(2.0);
        rotiItem.setQuantityUnit("piece");
        rotiItem.setEstimated(false);

        rotiItem.setCalories(208.0);
        rotiItem.setProtein(6.2);
        rotiItem.setCarbs(37.0);
        rotiItem.setFat(5.0);
        rotiItem.setFiber(4.0);
        rotiItem.setSugar(1.0);

        MealItem riceItem = new MealItem();
        riceItem.setFood("Cooked Rice");
        riceItem.setQuantity(100.0);
        riceItem.setQuantityUnit("g");
        riceItem.setEstimated(false);

        riceItem.setCalories(130.0);
        riceItem.setProtein(2.7);
        riceItem.setCarbs(28.2);
        riceItem.setFat(0.3);
        riceItem.setFiber(0.4);
        riceItem.setSugar(0.1);

        MealAnalysisResponse analysis = new MealAnalysisResponse();
        analysis.setItems(List.of(rotiItem, riceItem));

        // Request

        AiLogMealRequest request = new AiLogMealRequest();
        request.setUserId("test-user-001");
        request.setMealType("LUNCH");
        request.setPrompt("2 rotis and 100 grams of cooked rice");

        // Repository saves

        when(mealLogRepository.save(any(MealLogEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(mealFoodEntryRepository.save(any(MealFoodEntryEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Execute

        AiMealLogResponse response =
                mealService.logAiMeal(request, analysis);

        // Basic response checks

        assertNotNull(response);
        assertNotNull(response.getMealId());
        assertEquals("LUNCH", response.getMealType());
        assertEquals("AI", response.getMealSource());
        assertNotNull(response.getLoggedAt());

        assertEquals(2, response.getItems().size());

        // Roti

        AiMealLogResponse.LoggedItem loggedRoti =
                response.getItems().get(0);

        assertEquals("Roti", loggedRoti.getFoodName());
        assertEquals("food-roti-001", loggedRoti.getFoodId());
        assertEquals(2.0, loggedRoti.getQuantity());
        assertEquals("piece", loggedRoti.getQuantityUnit());

        // Roti is matched to catalog,
        // but piece != g, so AI nutrition is used.

        assertTrue(loggedRoti.isEstimated());
        assertEquals(208.0, loggedRoti.getCalories());
        assertEquals(6.2, loggedRoti.getProtein());
        assertEquals(37.0, loggedRoti.getCarbs());
        assertEquals(5.0, loggedRoti.getFat());
        assertEquals(4.0, loggedRoti.getFiber());
        assertEquals(1.0, loggedRoti.getSugar());

        // Rice

        AiMealLogResponse.LoggedItem loggedRice =
                response.getItems().get(1);

        assertEquals("Cooked Rice", loggedRice.getFoodName());
        assertEquals("food-rice-001", loggedRice.getFoodId());
        assertEquals(100.0, loggedRice.getQuantity());
        assertEquals("g", loggedRice.getQuantityUnit());

        // Rice uses catalog nutrition because g == g.

        assertFalse(loggedRice.isEstimated());
        assertEquals(130.0, loggedRice.getCalories());
        assertEquals(2.7, loggedRice.getProtein());
        assertEquals(28.2, loggedRice.getCarbs());
        assertEquals(0.3, loggedRice.getFat());
        assertEquals(0.4, loggedRice.getFiber());
        assertEquals(0.1, loggedRice.getSugar());

        // Database operations

        verify(mealLogRepository, times(1))
                .save(any(MealLogEntity.class));

        verify(mealFoodEntryRepository, times(2))
                .save(any(MealFoodEntryEntity.class));

        verify(mealFoodOccurrenceRepository, times(2))
                .save(any());

        verify(foodItemRepository, times(1))
                .findAll();
    }
}