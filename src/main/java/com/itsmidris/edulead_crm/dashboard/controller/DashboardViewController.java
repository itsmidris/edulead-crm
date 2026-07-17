package com.itsmidris.edulead_crm.dashboard.controller;

import com.itsmidris.edulead_crm.dashboard.dto.DashboardResponse;
import com.itsmidris.edulead_crm.dashboard.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardViewController {

    private final DashboardService dashboardService;

    public DashboardViewController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        DashboardResponse dashboard = dashboardService.getDashboardSummary();

        model.addAttribute("dashboard", dashboard);

        return "dashboard/dashboard";
    }
}