package com.itsmidris.edulead_crm.lead.controller;

import com.itsmidris.edulead_crm.common.enums.LeadCreationMethod;
import com.itsmidris.edulead_crm.common.enums.LeadSource;
import com.itsmidris.edulead_crm.common.enums.LeadStatus;
import com.itsmidris.edulead_crm.common.enums.LeadType;
import com.itsmidris.edulead_crm.course.service.CourseService;
import com.itsmidris.edulead_crm.lead.dto.request.CreateLeadRequest;
import com.itsmidris.edulead_crm.lead.dto.request.UpdateLeadRequest;
import com.itsmidris.edulead_crm.lead.service.LeadService;
import com.itsmidris.edulead_crm.user.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/leads")
public class LeadViewController {

    private final LeadService leadService;
    private final CourseService courseService;
    private final AppUserService appUserService;

    public LeadViewController(LeadService leadService, CourseService courseService, AppUserService appUserService) {
        this.leadService = leadService;
        this.courseService = courseService;
        this.appUserService = appUserService;
    }

    @GetMapping
    public String listLeads(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) LeadStatus status,
            Model model) {

        model.addAttribute("leads", leadService.searchLeads(keyword, status));
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("statuses", LeadStatus.values());
        model.addAttribute("activeMenu", "lead");

        return "lead/list";
    }

    @GetMapping("/add")
    public String addLeadPage(Model model) {
        model.addAttribute("leadForm", new CreateLeadRequest());

        loadFormData(model);
        model.addAttribute("activeMenu", "lead");
        model.addAttribute("isEdit", false);

        return "lead/form";
    }

    @PostMapping("/add")
    public String saveLead(@Valid @ModelAttribute("leadForm") CreateLeadRequest createLeadRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            loadFormData(model);
            model.addAttribute("activeMenu", "lead");

            model.addAttribute("isEdit", false);

            return "lead/form";
        }
        leadService.createLead(createLeadRequest);
        return "redirect:/leads?success";
    }

    @GetMapping("/{id}")
    public String viewLead(@PathVariable Long id, Model model) {

        model.addAttribute("lead", leadService.getLeadById(id));
        model.addAttribute("activeMenu", "lead");

        return "lead/view";
    }

    @GetMapping("/edit/{id}")
    public String showEditLeadForm(@PathVariable Long id, Model model) {

        model.addAttribute("leadId", id);

        model.addAttribute("leadForm", leadService.getLeadForUpdate(id));

        loadFormData(model);
        model.addAttribute("activeMenu", "lead");
        model.addAttribute("isEdit", true);

        return "lead/form";
    }

    @PostMapping("/edit/{id}")
    public String updateLead(@PathVariable Long id, @Valid @ModelAttribute("leadForm") UpdateLeadRequest updateLeadRequest, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("leadId", id);
            model.addAttribute("activeMenu", "lead");

            loadFormData(model);

            model.addAttribute("isEdit", true);

            return "lead/form";
        }

        leadService.updateLead(id, updateLeadRequest);

        return "redirect:/leads?updated";
    }

    private void loadFormData(Model model) {

        model.addAttribute("courses", courseService.getAllCourses());

        model.addAttribute("users", appUserService.getAllUsers());

        model.addAttribute("leadTypes", LeadType.values());

        model.addAttribute("leadSources", LeadSource.values());

        model.addAttribute("leadCreationMethods", LeadCreationMethod.values());

    }

}
