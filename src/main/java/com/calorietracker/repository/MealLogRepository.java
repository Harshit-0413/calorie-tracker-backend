package com.calorietracker.repository;

import com.calorietracker.entity.MealLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealLogRepository
        extends JpaRepository<MealLogEntity, String> {

    List<MealLogEntity> findByUserUid(String uid);
}