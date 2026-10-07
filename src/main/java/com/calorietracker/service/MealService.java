
package com.calorietracker.service;

import com.calorietracker.dto.AiLogMealRequest;
import com.calorietracker.dto.AiMealLogResponse;
import com.calorietracker.dto.CreateMealFoodEntryRequest;
import com.calorietracker.dto.CreateMealRequest;
import com.calorietracker.dto.MealAnalysisResponse;
import com.calorietracker.dto.MealItem;
import com.calorietracker.entity.FoodItemEntity;
import com.calorietracker.entity.MealFoodEntryEntity;
import com.calorietracker.entity.MealFoodOccurrenceEntity;
import com.calorietracker.entity.MealLogEntity;
import com.calorietracker.entity.UserProfileEntity;
import com.calorietracker.repository.FoodItemRepository;
import com.calorietracker.repository.MealFoodEntryRepository;
import com.calorietracker.repository.MealFoodOccurrenceRepository;
import com.calorietracker.repository.MealLogRepository;
import com.calorietracker.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

            MealFoodOccurrenceEntity occurrence =
                    new MealFoodOccurrenceEntity(
                            savedEntry,
                            foodRequest.getQuantity(),
                            foodRequest.getQuantityUnit(),
                            LocalDateTime.now()
                    );

            mealFoodOccurrenceRepository.save(occurrence);
        }

        return savedMeal;
    }

    @Transactional
    public AiMealLogResponse logAiMeal(
            AiLogMealRequest request,
            MealAnalysisResponse analysis
    ) {
        UserProfileEntity user = userProfileRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found: " + request.getUserId()
                ));

        LocalDateTime loggedAt = LocalDateTime.now();

        MealLogEntity meal = new MealLogEntity(
                UUID.randomUUID().toString(),
                user,
                request.getMealType(),
                "AI",
                loggedAt
        );

        meal.setOriginalPrompt(request.getPrompt());

        MealLogEntity savedMeal = mealLogRepository.save(meal);

        List<FoodItemEntity> catalog = foodItemRepository.findAll();
        List<AiMealLogResponse.LoggedItem> loggedItems = new ArrayList<>();

        for (MealItem item : analysis.getItems()) {

            FoodItemEntity matchedFood = findCatalogMatch(item.getFood(), catalog);

            boolean useCatalogNutrition =
                    matchedFood != null && hasCompatibleUnits(
                            item.getQuantityUnit(),
                            matchedFood.getServingUnit()
                    ) && matchedFood.getServingSize() != null
                            && Double.isFinite(matchedFood.getServingSize())
                            && matchedFood.getServingSize() > 0;

            double calories;
            double protein;
            double carbs;
            double fat;
            double fiber;
            double sugar;
            boolean estimated;

            if (useCatalogNutrition) {
                double quantityInCatalogUnits = convertQuantity(
                        item.getQuantity(),
                        item.getQuantityUnit(),
                        matchedFood.getServingUnit()
                );

                double multiplier =
                        quantityInCatalogUnits / matchedFood.getServingSize();

                calories = matchedFood.getCalories() * multiplier;
                protein = matchedFood.getProtein() * multiplier;
                carbs = matchedFood.getCarbs() * multiplier;
                fat = matchedFood.getFat() * multiplier;
                fiber = matchedFood.getFiber() * multiplier;
                sugar = matchedFood.getSugar() * multiplier;

                estimated = item.isEstimated();
            } else {
                calories = item.getCalories();
                protein = item.getProtein();
                carbs = item.getCarbs();
                fat = item.getFat();
                fiber = item.getFiber();
                sugar = item.getSugar();

                // Nutrition is AI-provided rather than catalog-derived.
                estimated = true;
            }

            MealFoodEntryEntity entry = new MealFoodEntryEntity(
                    savedMeal,
                    matchedFood,
                    item.getFood(),
                    item.getQuantity(),
                    item.getQuantityUnit(),
                    calories,
                    protein,
                    carbs,
                    fat,
                    fiber,
                    sugar,
                    estimated
            );

            MealFoodEntryEntity savedEntry =
                    mealFoodEntryRepository.save(entry);

            mealFoodOccurrenceRepository.save(
                    new MealFoodOccurrenceEntity(
                            savedEntry,
                            item.getQuantity(),
                            item.getQuantityUnit(),
                            loggedAt
                    )
            );

            loggedItems.add(new AiMealLogResponse.LoggedItem(
                    savedEntry.getId(),
                    matchedFood == null ? null : matchedFood.getId(),
                    savedEntry.getFoodName(),
                    savedEntry.getQuantity(),
                    savedEntry.getQuantityUnit(),
                    savedEntry.isEstimated(),
                    savedEntry.getCalories(),
                    savedEntry.getProtein(),
                    savedEntry.getCarbs(),
                    savedEntry.getFat(),
                    savedEntry.getFiber(),
                    savedEntry.getSugar()
            ));
        }

        return new AiMealLogResponse(
                savedMeal.getId(),
                savedMeal.getMealType(),
                savedMeal.getMealSource(),
                savedMeal.getLoggedAt(),
                loggedItems
        );
    }

    private FoodItemEntity findCatalogMatch(
            String foodName,
            List<FoodItemEntity> catalog
    ) {
        String target = normalizeName(foodName);

        for (FoodItemEntity food : catalog) {
            if (normalizeName(food.getName()).equals(target)) {
                return food;
            }

            String aliases = food.getAliases();

            if (aliases != null) {
                for (String alias : aliases.split(",")) {
                    if (normalizeName(alias).equals(target)) {
                        return food;
                    }
                }
            }
        }

        return null;
    }

    private String normalizeName(String value) {
        if (value == null) {
            return "";
        }

        return value.trim()
                .toLowerCase(Locale.ROOT)
                .replaceAll("\\s+", " ");
    }

    private boolean hasCompatibleUnits(String quantityUnit, String servingUnit) {
        String quantityCanonical = canonicalUnit(quantityUnit);
        String servingCanonical = canonicalUnit(servingUnit);

        return quantityCanonical != null
                && quantityCanonical.equals(servingCanonical);
    }

    private String canonicalUnit(String unit) {
        if (unit == null) {
            return null;
        }

        return switch (unit.trim().toLowerCase(Locale.ROOT)) {
            case "g", "gram", "grams", "kg", "kilogram", "kilograms" -> "mass";
            case "ml", "milliliter", "milliliters", "millilitre",
                 "millilitres", "l", "liter", "liters", "litre", "litres" -> "volume";
            case "piece", "pieces", "pc", "pcs" -> "piece";
            case "serving", "servings" -> "serving";
            case "cup", "cups" -> "cup";
            case "bowl", "bowls" -> "bowl";
            default -> null;
        };
    }

    private double convertQuantity(
            double quantity,
            String quantityUnit,
            String servingUnit
    ) {
        String source = quantityUnit.trim().toLowerCase(Locale.ROOT);
        String target = servingUnit.trim().toLowerCase(Locale.ROOT);

        if (isKilograms(source) && isGrams(target)) {
            return quantity * 1000.0;
        }

        if (isGrams(source) && isKilograms(target)) {
            return quantity / 1000.0;
        }

        if (isLiters(source) && isMilliliters(target)) {
            return quantity * 1000.0;
        }

        if (isMilliliters(source) && isLiters(target)) {
            return quantity / 1000.0;
        }

        return quantity;
    }

    private boolean isKilograms(String unit) {
        return unit.equals("kg")
                || unit.equals("kilogram")
                || unit.equals("kilograms");
    }

    private boolean isGrams(String unit) {
        return unit.equals("g")
                || unit.equals("gram")
                || unit.equals("grams");
    }

    private boolean isLiters(String unit) {
        return unit.equals("l")
                || unit.equals("liter")
                || unit.equals("liters")
                || unit.equals("litre")
                || unit.equals("litres");
    }

    private boolean isMilliliters(String unit) {
        return unit.equals("ml")
                || unit.equals("milliliter")
                || unit.equals("milliliters")
                || unit.equals("millilitre")
                || unit.equals("millilitres");
    }
}
