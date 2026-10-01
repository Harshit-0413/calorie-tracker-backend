package com.calorietracker.repository;

import com.calorietracker.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository
        extends JpaRepository<UserProfileEntity, String> {
}