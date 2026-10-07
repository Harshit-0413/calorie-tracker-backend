package com.calorietracker.service;

import com.calorietracker.dto.TodayMealsResponse;
import com.calorietracker.entity.MealFoodEntryEntity;
import com.calorietracker.entity.MealLogEntity;
import com.calorietracker.repository.MealFoodEntryRepository;
import com.calorietracker.repository.MealLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealLogService {

    private final MealLogRepository mealLogRepository;
    private final MealFoodEntryRepository mealFoodEntryRepository;

    public MealLogService(
            MealLogRepository mealLogRepository,
            MealFoodEntryRepository mealFoodEntryRepository
    ) {
        this.mealLogRepository = mealLogRepository;
        this.mealFoodEntryRepository = mealFoodEntryRepository;
    }

    public MealLogEntity saveMeal(MealLogEntity meal) {
        return mealLogRepository.save(meal);
    }

    public MealLogEntity getMeal(String mealId) {
        return mealLogRepository.findById(mealId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Meal not found: " + mealId
                ));
    }

    public List<MealLogEntity> getMealsForUser(String uid) {
        return mealLogRepository.findByUserUid(uid);
    }

    public TodayMealsResponse getTodayMeals(String uid) {

        LocalDate today = LocalDate.now();

        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime startOfTomorrow = today.plusDays(1).atStartOfDay();

        List<MealLogEntity> meals =
                mealLogRepository
                        .findByUserUidAndLoggedAtGreaterThanEqualAndLoggedAtLessThanOrderByLoggedAtAsc(
                                uid,
                                startOfDay,
                                startOfTomorrow
                        );

        List<TodayMealsResponse.TodayMeal> mealResponses =
                new ArrayList<>();

        double totalCalories = 0.0;
        double totalProtein = 0.0;
        double totalCarbs = 0.0;
        double totalFat = 0.0;
        double totalFiber = 0.0;
        double totalSugar = 0.0;

        for (MealLogEntity meal : meals) {

            List<MealFoodEntryEntity> entries =
                    mealFoodEntryRepository.findByMealId(meal.getId());

            List<TodayMealsResponse.TodayMealEntry> entryResponses =
                    new ArrayList<>();

            double mealCalories = 0.0;
            double mealProtein = 0.0;
            double mealCarbs = 0.0;
            double mealFat = 0.0;
            double mealFiber = 0.0;
            double mealSugar = 0.0;

            for (MealFoodEntryEntity entry : entries) {

                String foodId = entry.getFood() == null
                        ? null
                        : entry.getFood().getId();

                TodayMealsResponse.TodayMealEntry entryResponse =
                        new TodayMealsResponse.TodayMealEntry(
                                entry.getId(),
                                foodId,
                                entry.getFoodName(),
                                entry.getQuantity(),
                                entry.getQuantityUnit(),
                                entry.getCalories(),
                                entry.getProtein(),
                                entry.getCarbs(),
                                entry.getFat(),
                                entry.getFiber(),
                                entry.getSugar(),
                                entry.isEstimated()
                        );

                entryResponses.add(entryResponse);

                mealCalories += entry.getCalories();
                mealProtein += entry.getProtein();
                mealCarbs += entry.getCarbs();
                mealFat += entry.getFat();
                mealFiber += entry.getFiber();
                mealSugar += entry.getSugar();
            }

            TodayMealsResponse.TodayMeal mealResponse =
                    new TodayMealsResponse.TodayMeal(
                            meal.getId(),
                            meal.getMealType(),
                            meal.getMealSource(),
                            meal.getOriginalPrompt(),
                            meal.getLoggedAt(),
                            entryResponses,
                            mealCalories,
                            mealProtein,
                            mealCarbs,
                            mealFat,
                            mealFiber,
                            mealSugar
                    );

            mealResponses.add(mealResponse);

            totalCalories += mealCalories;
            totalProtein += mealProtein;
            totalCarbs += mealCarbs;
            totalFat += mealFat;
            totalFiber += mealFiber;
            totalSugar += mealSugar;
        }

        return new TodayMealsResponse(
                today,
                mealResponses,
                totalCalories,
                totalProtein,
                totalCarbs,
                totalFat,
                totalFiber,
                totalSugar
        );
    }
}