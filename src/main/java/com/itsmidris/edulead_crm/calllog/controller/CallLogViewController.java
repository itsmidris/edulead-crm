package com.itsmidris.edulead_crm.calllog.controller;

import com.itsmidris.edulead_crm.calllog.dto.request.CreateCallLogRequest;
import com.itsmidris.edulead_crm.calllog.service.CallLogService;
import com.itsmidris.edulead_crm.lead.service.LeadService;
import com.itsmidris.edulead_crm.user.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/call-logs")
public class CallLogViewController {

    private final CallLogService callLogService;
    private final LeadService leadService;
    private final AppUserService appUserService;

    public CallLogViewController(CallLogService callLogService, LeadService leadService, AppUserService appUserService) {
        this.callLogService = callLogService;
        this.leadService = leadService;
        this.appUserService = appUserService;
    }

    @GetMapping
    public String listCallLogs(Model model) {
        model.addAttribute("callLogs", callLogService.getTodayCallLogs());
        return "calllog/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("callLogForm", new CreateCallLogRequest());
        loadFormData(model);
        return "calllog/form";
    }

    @PostMapping("/add")
    public String saveCallLog(@Valid @ModelAttribute("callLogForm") CreateCallLogRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            loadFormData(model);
            return "calllog/form";
        }
        callLogService.createCallLog(request);
        return "redirect:/call-logs?success";
    }

    @GetMapping("/{id}")
    public String viewCallLog(@PathVariable Long id, Model model) {
        model.addAttribute("callLog", callLogService.getCallLogById(id));
        return "calllog/view";
    }

    private void loadFormData(Model model) {
        model.addAttribute("leads", leadService.getActiveLeads());
        model.addAttribute("users", appUserService.getAllUsers());
    }
}
