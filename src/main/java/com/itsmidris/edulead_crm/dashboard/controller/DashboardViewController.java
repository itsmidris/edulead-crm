package com.itsmidris.edulead_crm.dashboard.controller;

import com.itsmidris.edulead_crm.course.repository.CourseRepository;
import com.itsmidris.edulead_crm.lead.repository.LeadRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardViewController {

    private final LeadRepository leadRepository;
    private final CourseRepository courseRepository;

    public DashboardViewController(
            LeadRepository leadRepository,
            CourseRepository courseRepository) {

        this.leadRepository = leadRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute("leadCount", leadRepository.count());
        model.addAttribute("courseCount", courseRepository.count());

        return "dashboard/dashboard";
    }
}
