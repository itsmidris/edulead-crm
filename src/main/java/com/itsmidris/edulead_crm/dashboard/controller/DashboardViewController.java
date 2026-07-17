package com.itsmidris.edulead_crm.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardViewController {

    @GetMapping("/")
    public String dashboard() {
        return "dashboard/dashboard";
    }
}
