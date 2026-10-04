package com.calorietracker.service;

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
}