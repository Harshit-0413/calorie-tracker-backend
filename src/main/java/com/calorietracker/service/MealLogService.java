package com.calorietracker.service;

import com.calorietracker.entity.MealLogEntity;
import com.calorietracker.repository.MealLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealLogService {

    private final MealLogRepository mealLogRepository;

    public MealLogService(MealLogRepository mealLogRepository) {
        this.mealLogRepository = mealLogRepository;
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
}