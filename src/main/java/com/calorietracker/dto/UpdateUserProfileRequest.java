package com.calorietracker.dto;

public class UpdateUserProfileRequest {

    private String photoUrl;
    private String gender;
    private Double weightKg;
    private Double heightCm;
    private Integer age;
    private String goal;
    private String activityLevel;
    private Double dailyCalorieGoal;
    private Double dailyProteinGoal;
    private Double dailyCarbsGoal;
    private Double dailyFatGoal;

    public UpdateUserProfileRequest() {
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public Double getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(Double heightCm) {
        this.heightCm = heightCm;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Double getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }

    public void setDailyCalorieGoal(Double dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public Double getDailyProteinGoal() {
        return dailyProteinGoal;
    }

    public void setDailyProteinGoal(Double dailyProteinGoal) {
        this.dailyProteinGoal = dailyProteinGoal;
    }

    public Double getDailyCarbsGoal() {
        return dailyCarbsGoal;
    }

    public void setDailyCarbsGoal(Double dailyCarbsGoal) {
        this.dailyCarbsGoal = dailyCarbsGoal;
    }

    public Double getDailyFatGoal() {
        return dailyFatGoal;
    }

    public void setDailyFatGoal(Double dailyFatGoal) {
        this.dailyFatGoal = dailyFatGoal;
    }
}