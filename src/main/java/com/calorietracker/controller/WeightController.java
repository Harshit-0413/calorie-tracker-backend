package com.calorietracker.controller;

import com.calorietracker.dto.WeightEntryResponse;
import com.calorietracker.entity.WeightEntryEntity;
import com.calorietracker.service.UserProfileService;
import com.calorietracker.service.WeightEntryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users/{uid}/weights")
public class WeightController {

    private final WeightEntryService weightEntryService;
    private final UserProfileService userProfileService;

    public WeightController(
            WeightEntryService weightEntryService,
            UserProfileService userProfileService
    ) {
        this.weightEntryService = weightEntryService;
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public WeightEntryResponse addWeight(
            @PathVariable String uid,
            @RequestParam Double weightKg
    ) {
        WeightEntryEntity weightEntry = new WeightEntryEntity(
                userProfileService.getUser(uid),
                weightKg,
                LocalDateTime.now()
        );

        WeightEntryEntity savedEntry =
                weightEntryService.saveWeight(weightEntry);

        return toResponse(savedEntry);
    }

    @GetMapping
    public List<WeightEntryResponse> getWeights(
            @PathVariable String uid
    ) {
        return weightEntryService.getWeightsForUser(uid)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private WeightEntryResponse toResponse(WeightEntryEntity entry) {
        return new WeightEntryResponse(
                entry.getId(),
                entry.getWeightKg(),
                entry.getRecordedAt()
        );
    }
}