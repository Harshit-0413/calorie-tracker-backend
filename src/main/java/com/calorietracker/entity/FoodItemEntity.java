package com.calorietracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_items")
public class FoodItemEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(name = "name_hindi")
    private String nameHindi;

    @Column(nullable = false)
    private Double calories;

    @Column(nullable = false)
    private Double protein;

    @Column(nullable = false)
    private Double carbs;

    @Column(nullable = false)
    private Double fat;

    @Column(nullable = false)
    private Double fiber;

    @Column(nullable = false)
    private Double sugar;

    @Column(name = "serving_size", nullable = false)
    private Double servingSize;

    @Column(name = "serving_unit", nullable = false)
    private String servingUnit;

    private String category;

    private String aliases;

    private String brand;

    @Column(name = "is_verified", nullable = false)
    private Boolean verified;

    private String source;

    protected FoodItemEntity() {
    }

    public FoodItemEntity(
            String id,
            String name,
            Double calories,
            Double protein,
            Double carbs,
            Double fat,
            Double fiber,
            Double sugar,
            Double servingSize,
            String servingUnit
    ) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.fiber = fiber;
        this.sugar = sugar;
        this.servingSize = servingSize;
        this.servingUnit = servingUnit;
        this.verified = false;
        this.source = "IFCT 2017";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameHindi() {
        return nameHindi;
    }

    public Double getCalories() {
        return calories;
    }

    public Double getProtein() {
        return protein;
    }

    public Double getCarbs() {
        return carbs;
    }

    public Double getFat() {
        return fat;
    }

    public Double getFiber() {
        return fiber;
    }

    public Double getSugar() {
        return sugar;
    }

    public Double getServingSize() {
        return servingSize;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public String getCategory() {
        return category;
    }

    public String getAliases() {
        return aliases;
    }

    public String getBrand() {
        return brand;
    }

    public Boolean getVerified() {
        return verified;
    }

    public String getSource() {
        return source;
    }

    public void setNameHindi(String nameHindi) {
        this.nameHindi = nameHindi;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public void setSource(String source) {
        this.source = source;
    }
}