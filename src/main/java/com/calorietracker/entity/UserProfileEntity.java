package com.calorietracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfileEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String uid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    private String gender;

    @Column(name = "weight_kg")
    private Double weightKg;

    @Column(name = "height_cm")
    private Double heightCm;

    private Integer age;

    private String goal;

    @Column(name = "activity_level")
    private String activityLevel;

    @Column(name = "daily_calorie_goal")
    private Double dailyCalorieGoal;

    @Column(name = "daily_protein_goal")
    private Double dailyProteinGoal;

    @Column(name = "daily_carbs_goal")
    private Double dailyCarbsGoal;

    @Column(name = "daily_fat_goal")
    private Double dailyFatGoal;

    protected UserProfileEntity() {
    }

    public UserProfileEntity(
            String uid,
            String name,
            String email
    ) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getGender() {
        return gender;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public Double getHeightCm() {
        return heightCm;
    }

    public Integer getAge() {
        return age;
    }

    public String getGoal() {
        return goal;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public Double getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }

    public Double getDailyProteinGoal() {
        return dailyProteinGoal;
    }

    public Double getDailyCarbsGoal() {
        return dailyCarbsGoal;
    }

    public Double getDailyFatGoal() {
        return dailyFatGoal;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public void setHeightCm(Double heightCm) {
        this.heightCm = heightCm;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setDailyCalorieGoal(Double dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public void setDailyProteinGoal(Double dailyProteinGoal) {
        this.dailyProteinGoal = dailyProteinGoal;
    }

    public void setDailyCarbsGoal(Double dailyCarbsGoal) {
        this.dailyCarbsGoal = dailyCarbsGoal;
    }

    public void setDailyFatGoal(Double dailyFatGoal) {
        this.dailyFatGoal = dailyFatGoal;
    }
}