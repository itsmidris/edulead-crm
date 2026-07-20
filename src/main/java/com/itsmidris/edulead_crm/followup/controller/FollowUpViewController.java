package com.itsmidris.edulead_crm.followup.controller;

import com.itsmidris.edulead_crm.followup.dto.request.CreateFollowUpRequest;
import com.itsmidris.edulead_crm.followup.service.FollowUpService;
import com.itsmidris.edulead_crm.lead.service.LeadService;
import com.itsmidris.edulead_crm.user.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/follow-ups")
public class FollowUpViewController {

    private final FollowUpService followUpService;
    private final LeadService leadService;
    private final AppUserService appUserService;

    public FollowUpViewController(
            FollowUpService followUpService,
            LeadService leadService,
            AppUserService appUserService) {

        this.followUpService = followUpService;
        this.leadService = leadService;
        this.appUserService = appUserService;
    }

    @GetMapping
    public String listFollowUps(Model model) {
        model.addAttribute("followUps", followUpService.getAllFollowUps());
        return "followup/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("followUpForm", new CreateFollowUpRequest());
        loadFormData(model);
        return "followup/form";
    }

    @PostMapping("/add")
    public String saveFollowUp(@Valid @ModelAttribute("followUpForm") CreateFollowUpRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            loadFormData(model);
            return "followup/form";
        }
        followUpService.createFollowUp(request);
        return "redirect:/follow-ups?success";
    }

    @GetMapping("/{id}")
    public String viewFollowUp(@PathVariable Long id, Model model) {
        model.addAttribute("followUp", followUpService.getFollowUpById(id));
        return "followup/view";
    }

    @PostMapping("/{id}/complete")
    public String completeFollowUp(@PathVariable Long id) {

        followUpService.markAsCompleted(id);

        return "redirect:/follow-ups?completed";
    }

    @PostMapping("/{id}/cancel")
    public String cancelFollowUp(@PathVariable Long id) {
        followUpService.cancelFollowUp(id);
        return "redirect:/follow-ups?cancelled";
    }

    private void loadFormData(Model model) {
        model.addAttribute("leads", leadService.getActiveLeads());
        model.addAttribute("users", appUserService.getAllUsers());
    }

}
