package com.itsmidris.edulead_crm.lead.controller;

import com.itsmidris.edulead_crm.lead.service.LeadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/leads")
public class LeadViewController {

    private final LeadService leadService;

    public LeadViewController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping
    public String listLeads(Model model) {

        model.addAttribute("leads", leadService.getAllLeads());

        return "lead/list";
    }
}
