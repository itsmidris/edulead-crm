package com.itsmidris.edulead_crm.lead.controller;

import com.itsmidris.edulead_crm.common.enums.LeadCreationMethod;
import com.itsmidris.edulead_crm.common.enums.LeadSource;
import com.itsmidris.edulead_crm.common.enums.LeadType;
import com.itsmidris.edulead_crm.course.service.CourseService;
import com.itsmidris.edulead_crm.lead.dto.request.CreateLeadRequest;
import com.itsmidris.edulead_crm.lead.service.LeadService;
import com.itsmidris.edulead_crm.user.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String listLeads(Model model) {

        model.addAttribute("leads", leadService.getAllLeads());

        return "lead/list";
    }

    @GetMapping("/add")
    public String addLeadPage(Model model) {
        model.addAttribute("createLeadRequest", new CreateLeadRequest());
        model.addAttribute("courses",courseService.getAllCourses());
        model.addAttribute("users", appUserService.getAllUsers());
        model.addAttribute("leadTypes", LeadType.values());
        model.addAttribute("leadSources", LeadSource.values());
        model.addAttribute("leadCreationMethods", LeadCreationMethod.values());
        return "lead/add";
    }

    @PostMapping("/add")
    public String saveLead(@Valid @ModelAttribute("createLeadRequest") CreateLeadRequest createLeadRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.getAllCourses());
            model.addAttribute("users", appUserService.getAllUsers());
            model.addAttribute("leadTypes", LeadType.values());
            model.addAttribute("leadSources", LeadSource.values());
            model.addAttribute("leadCreationMethods", LeadCreationMethod.values());

            return "lead/add";
        }
        leadService.createLead(createLeadRequest);
        return "redirect:/leads";
    }

}
