package com.calorietracker.service;

import com.calorietracker.dto.DashboardSummaryResponse;
import com.calorietracker.dto.TodayMealsResponse;
import com.calorietracker.entity.UserProfileEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    private final UserProfileService userProfileService;
    private final MealLogService mealLogService;

    public DashboardService(
            UserProfileService userProfileService,
            MealLogService mealLogService
    ) {
        this.userProfileService = userProfileService;
        this.mealLogService = mealLogService;
    }

    public DashboardSummaryResponse getTodayDashboard(String uid) {

        UserProfileEntity user = userProfileService.getUser(uid);

        TodayMealsResponse todayMeals =
                mealLogService.getTodayMeals(uid);

        return new DashboardSummaryResponse(
                LocalDate.now(),

                new DashboardSummaryResponse.NutritionSummary(
                        user.getDailyCalorieGoal(),
                        todayMeals.getTotalCalories()
                ),

                new DashboardSummaryResponse.NutritionSummary(
                        user.getDailyProteinGoal(),
                        todayMeals.getTotalProtein()
                ),

                new DashboardSummaryResponse.NutritionSummary(
                        user.getDailyCarbsGoal(),
                        todayMeals.getTotalCarbs()
                ),

                new DashboardSummaryResponse.NutritionSummary(
                        user.getDailyFatGoal(),
                        todayMeals.getTotalFat()
                ),

                todayMeals.getTotalFiber(),
                todayMeals.getTotalSugar(),
                todayMeals
        );
    }
}