package com.calorietracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "weight_entries")
public class WeightEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfileEntity user;

    @Column(name = "weight_kg", nullable = false)
    private Double weightKg;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    protected WeightEntryEntity() {
    }

    public WeightEntryEntity(
            UserProfileEntity user,
            Double weightKg,
            LocalDateTime recordedAt
    ) {
        this.user = user;
        this.weightKg = weightKg;
        this.recordedAt = recordedAt;
    }

    public Long getId() {
        return id;
    }

    public UserProfileEntity getUser() {
        return user;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }
}