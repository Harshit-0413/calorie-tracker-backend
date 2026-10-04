package com.calorietracker.repository;

import com.calorietracker.entity.WeightEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightEntryRepository
        extends JpaRepository<WeightEntryEntity, Long> {

    List<WeightEntryEntity> findByUserUid(String uid);
}