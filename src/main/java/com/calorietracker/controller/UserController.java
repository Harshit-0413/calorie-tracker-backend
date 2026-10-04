package com.calorietracker.controller;

import com.calorietracker.dto.CreateUserRequest;
import com.calorietracker.dto.UpdateUserProfileRequest;
import com.calorietracker.entity.UserProfileEntity;
import com.calorietracker.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserProfileService userProfileService;

    public UserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public UserProfileEntity createUser(
            @Valid @RequestBody CreateUserRequest request
    ) {
        UserProfileEntity user = new UserProfileEntity(
                request.getUid(),
                request.getName(),
                request.getEmail()
        );

        return userProfileService.saveUser(user);
    }

    @GetMapping("/{uid}")
    public UserProfileEntity getUser(@PathVariable String uid) {
        return userProfileService.getUser(uid);
    }

    @PutMapping("/{uid}")
    public UserProfileEntity updateProfile(
            @PathVariable String uid,
            @RequestBody UpdateUserProfileRequest request
    ) {
        return userProfileService.updateProfile(uid, request);
    }
}