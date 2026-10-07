        package com.calorietracker.repository;

import com.calorietracker.entity.FoodItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FoodItemRepository
        extends JpaRepository<FoodItemEntity, String> {

    Optional<FoodItemEntity> findByNameIgnoreCase(String name);

    @Query("""
            SELECT f FROM FoodItemEntity f
            WHERE LOWER(f.name) = LOWER(:name)
               OR LOWER(f.aliases) LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<FoodItemEntity> findByNameOrAlias(@Param("name") String name);
}