package com.itsmidris.edulead_crm.calllog.controller;

import com.itsmidris.edulead_crm.calllog.dto.request.CreateCallLogRequest;
import com.itsmidris.edulead_crm.calllog.service.CallLogService;
import com.itsmidris.edulead_crm.common.enums.CallOutcome;
import com.itsmidris.edulead_crm.lead.service.LeadService;
import com.itsmidris.edulead_crm.user.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        model.addAttribute("pageTitle", "Today's Call Logs");
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

    @GetMapping("/all")
    public String allCallLogs(Model model) {
        model.addAttribute("callLogs", callLogService.getAllCallLogs());
        model.addAttribute("pageTitle", "All Call Logs");
        return "calllog/list";
    }

    @GetMapping("/caller-history")
    public String callerHistory(Model model) {
        model.addAttribute("users", appUserService.getAllUsers());
        return "calllog/caller-history";
    }

    @GetMapping("/caller-history/result")
    public String callerHistoryResult(@RequestParam Long callerId, Model model) {
        model.addAttribute("callLogs", callLogService.getCallerCallHistory(callerId));
        model.addAttribute("pageTitle", "Caller History");
        return "calllog/list";
    }

    @GetMapping("/lead-history")
    public String leadHistory(Model model) {
        model.addAttribute("leads", leadService.getActiveLeads());
        return "calllog/lead-history";
    }

    @GetMapping("/lead-history/result")
    public String leadHistoryResult(@RequestParam Long leadId, Model model) {
        model.addAttribute("callLogs", callLogService.getLeadCallHistory(leadId));
        model.addAttribute("pageTitle", "Lead Call History");
        return "calllog/list";
    }

    @GetMapping("/outcome")
    public String outcomePage() {
        return "calllog/outcome";
    }

    @GetMapping("/outcome/result")
    public String outcomeResult(@RequestParam CallOutcome outcome, Model model) {
        model.addAttribute("callLogs", callLogService.getCallLogsByOutcome(outcome));
        model.addAttribute("pageTitle", "Outcome : " + outcome);
        return "calllog/list";
    }

    @GetMapping("/date-range")
    public String dateRangePage() {
        return "calllog/date-range";
    }

    @GetMapping("/date-range/result")
    public String dateRangeResult(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, Model model) {
        model.addAttribute("callLogs", callLogService.getCallLogsBetween(startDate, endDate));
        model.addAttribute("pageTitle", "Call Logs from " + startDate + " to " + endDate);
        return "calllog/list";
    }

    private void loadFormData(Model model) {
        model.addAttribute("leads", leadService.getActiveLeads());
        model.addAttribute("users", appUserService.getAllUsers());
    }
}
