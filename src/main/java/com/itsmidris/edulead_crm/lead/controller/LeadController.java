package com.itsmidris.edulead_crm.lead.controller;

import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import com.itsmidris.edulead_crm.lead.dto.request.CreateLeadRequest;
import com.itsmidris.edulead_crm.lead.dto.response.LeadResponse;
import com.itsmidris.edulead_crm.lead.dto.response.LeadSummaryResponse;
import com.itsmidris.edulead_crm.lead.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leads")
@Validated
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LeadResponse>> createLead(@Valid @RequestBody CreateLeadRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success("Lead created successfully.", leadService.createLead(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LeadResponse>> getLeadById(@PathVariable Long id) {

        return ResponseEntity.ok(ResponseBuilder.success("Lead fetched successfully.", leadService.getLeadById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<LeadResponse>>> getAllLeads() {

        return ResponseEntity.ok(ResponseBuilder.success("Leads fetched successfully.", leadService.getAllLeads()));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<LeadSummaryResponse>>> getActiveLeads() {

        return ResponseEntity.ok(ResponseBuilder.success("Active leads fetched successfully.", leadService.getActiveLeads()));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateLead(@PathVariable Long id) {
        leadService.deactivateLead(id);
        return ResponseEntity.ok(ResponseBuilder.success("Lead deactivated successfully.", null));
    }
}
