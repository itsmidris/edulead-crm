package com.itsmidris.edulead_crm.dashboard.controller;

import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import com.itsmidris.edulead_crm.dashboard.dto.DashboardResponse;
import com.itsmidris.edulead_crm.dashboard.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Dashboard", description = "Dashboard summary and analytics")
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Operation(summary = "Check Summary", description = "Summary  of Leads, ")
    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<DashboardResponse>> getDashboardSummary() {

        return ResponseEntity.ok(ResponseBuilder.success("Dashboard fetched successfully.", dashboardService.getDashboardSummary()));
    }
}
