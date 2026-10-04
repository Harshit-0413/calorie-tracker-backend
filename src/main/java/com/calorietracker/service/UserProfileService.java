package com.calorietracker.service;

import com.calorietracker.dto.UpdateUserProfileRequest;
import com.calorietracker.entity.UserProfileEntity;
import com.calorietracker.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileEntity getUser(String uid) {
        return userProfileRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found: " + uid
                ));
    }

    public UserProfileEntity saveUser(UserProfileEntity user) {
        return userProfileRepository.save(user);
    }
    public UserProfileEntity updateProfile(
            String uid,
            UpdateUserProfileRequest request
    ) {
        UserProfileEntity user = getUser(uid);

        user.setPhotoUrl(request.getPhotoUrl());
        user.setGender(request.getGender());
        user.setWeightKg(request.getWeightKg());
        user.setHeightCm(request.getHeightCm());
        user.setAge(request.getAge());
        user.setGoal(request.getGoal());
        user.setActivityLevel(request.getActivityLevel());
        user.setDailyCalorieGoal(request.getDailyCalorieGoal());
        user.setDailyProteinGoal(request.getDailyProteinGoal());
        user.setDailyCarbsGoal(request.getDailyCarbsGoal());
        user.setDailyFatGoal(request.getDailyFatGoal());

        return userProfileRepository.save(user);
    }
}