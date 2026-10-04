package com.calorietracker.service;

import com.calorietracker.dto.CreateMealFoodEntryRequest;
import com.calorietracker.dto.CreateMealRequest;
import com.calorietracker.entity.FoodItemEntity;
import com.calorietracker.entity.MealFoodEntryEntity;
import com.calorietracker.entity.MealLogEntity;
import com.calorietracker.entity.UserProfileEntity;
import com.calorietracker.repository.FoodItemRepository;
import com.calorietracker.repository.MealFoodEntryRepository;
import com.calorietracker.repository.MealLogRepository;
import com.calorietracker.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.calorietracker.entity.MealFoodOccurrenceEntity;
import com.calorietracker.repository.MealFoodOccurrenceRepository;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MealService {

    private final MealLogRepository mealLogRepository;
    private final UserProfileRepository userProfileRepository;
    private final FoodItemRepository foodItemRepository;
    private final MealFoodEntryRepository mealFoodEntryRepository;
    private final MealFoodOccurrenceRepository mealFoodOccurrenceRepository;

    public MealService(
            MealLogRepository mealLogRepository,
            UserProfileRepository userProfileRepository,
            FoodItemRepository foodItemRepository,
            MealFoodEntryRepository mealFoodEntryRepository,
            MealFoodOccurrenceRepository mealFoodOccurrenceRepository
    ) {
        this.mealLogRepository = mealLogRepository;
        this.userProfileRepository = userProfileRepository;
        this.foodItemRepository = foodItemRepository;
        this.mealFoodEntryRepository = mealFoodEntryRepository;
        this.mealFoodOccurrenceRepository = mealFoodOccurrenceRepository;
    }

    @Transactional
    public MealLogEntity createMeal(CreateMealRequest request) {

        UserProfileEntity user = userProfileRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found: " + request.getUserId()
                ));

        MealLogEntity meal = new MealLogEntity(
                UUID.randomUUID().toString(),
                user,
                request.getMealType(),
                request.getMealSource(),
                LocalDateTime.now()
        );

        meal.setOriginalPrompt(request.getOriginalPrompt());

        MealLogEntity savedMeal = mealLogRepository.save(meal);

        for (CreateMealFoodEntryRequest foodRequest : request.getFoodEntries()) {

            FoodItemEntity food = foodItemRepository.findById(foodRequest.getFoodId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Food not found: " + foodRequest.getFoodId()
                    ));

            double multiplier =
                    foodRequest.getQuantity() / food.getServingSize();

            MealFoodEntryEntity entry = new MealFoodEntryEntity(
                    savedMeal,
                    food,
                    foodRequest.getFoodName(),
                    foodRequest.getQuantity(),
                    foodRequest.getQuantityUnit(),
                    food.getCalories() * multiplier,
                    food.getProtein() * multiplier,
                    food.getCarbs() * multiplier,
                    food.getFat() * multiplier,
                    food.getFiber() * multiplier,
                    food.getSugar() * multiplier
            );

            MealFoodEntryEntity savedEntry =
                    mealFoodEntryRepository.save(entry);

            MealFoodOccurrenceEntity occurrence = new MealFoodOccurrenceEntity(
                    savedEntry,
                    foodRequest.getQuantity(),
                    foodRequest.getQuantityUnit(),
                    LocalDateTime.now()
            );

            mealFoodOccurrenceRepository.save(occurrence);
        }

        return savedMeal;
    }
}