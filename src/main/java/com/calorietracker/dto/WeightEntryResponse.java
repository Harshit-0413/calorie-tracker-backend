package com.calorietracker.dto;

import java.time.LocalDateTime;

public class WeightEntryResponse {

    private Long id;
    private Double weightKg;
    private LocalDateTime recordedAt;

    public WeightEntryResponse() {
    }

    public WeightEntryResponse(
            Long id,
            Double weightKg,
            LocalDateTime recordedAt
    ) {
        this.id = id;
        this.weightKg = weightKg;
        this.recordedAt = recordedAt;
    }

    public Long getId() {
        return id;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }
}