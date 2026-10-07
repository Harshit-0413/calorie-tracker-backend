package com.calorietracker.controller;

import com.calorietracker.dto.DashboardSummaryResponse;
import com.calorietracker.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService
    ) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/today/{uid}")
    public DashboardSummaryResponse getTodayDashboard(
            @PathVariable String uid
    ) {
        return dashboardService.getTodayDashboard(uid);
    }
}