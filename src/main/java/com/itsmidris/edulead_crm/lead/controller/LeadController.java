package com.itsmidris.edulead_crm.lead.controller;

import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import com.itsmidris.edulead_crm.lead.dto.request.CreateLeadRequest;
import com.itsmidris.edulead_crm.lead.dto.response.LeadResponse;
import com.itsmidris.edulead_crm.lead.dto.response.LeadSummaryResponse;
import com.itsmidris.edulead_crm.lead.service.LeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Lead Management", description = "Manage student leads")
@RestController
@RequestMapping("/api/v1/leads")
@Validated
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @Operation(summary = "Create Lead", description = "Creates a new lead.")
    @PostMapping
    public ResponseEntity<ApiResponse<LeadResponse>> createLead(@Valid @RequestBody CreateLeadRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success("Lead created successfully.", leadService.createLead(request)));
    }

    @Operation(summary = "Get Lead", description = "Check lead with lead id.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LeadResponse>> getLeadById(@PathVariable Long id) {

        return ResponseEntity.ok(ResponseBuilder.success("Lead fetched successfully.", leadService.getLeadById(id)));
    }

    @Operation(summary = "Get All Leads", description = "Check all leads in the system.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<LeadResponse>>> getAllLeads() {

        return ResponseEntity.ok(ResponseBuilder.success("Leads fetched successfully.", leadService.getAllLeads()));
    }

    @Operation(summary = "Get Active Leads", description = "Check all active leads.")
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<LeadSummaryResponse>>> getActiveLeads() {

        return ResponseEntity.ok(ResponseBuilder.success("Active leads fetched successfully.", leadService.getActiveLeads()));
    }

    @Operation(summary = "Deactivate Lead", description = "Deactivate a lead but it will be stored in the system.")
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateLead(@PathVariable Long id) {
        leadService.deactivateLead(id);
        return ResponseEntity.ok(ResponseBuilder.success("Lead deactivated successfully.", null));
    }
}
