package com.itsmidris.edulead_crm.followup.controller;


import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import com.itsmidris.edulead_crm.followup.dto.request.CreateFollowUpRequest;
import com.itsmidris.edulead_crm.followup.dto.response.FollowUpResponse;
import com.itsmidris.edulead_crm.followup.service.FollowUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Follow Up", description = "Manage follow-up tasks")
@RestController
@RequestMapping("/api/v1/follow-ups")
public class FollowUpController {

    private final FollowUpService  followUpService;

    public FollowUpController(FollowUpService followUpService) {
        this.followUpService = followUpService;
    }

    @Operation(summary = "Create Follow Up", description = "Create follow ups with student.")
    @PostMapping
    public ResponseEntity<ApiResponse<FollowUpResponse>> createFollowUp(@Valid @RequestBody CreateFollowUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success("Follow-up created successfully.", followUpService.createFollowUp(request)));
    }

    @Operation(summary = "Get Follow Up By Id", description = "Get follow up with id.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FollowUpResponse>> getFollowUpById(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBuilder.success("Follow-up fetched successfully.", followUpService.getFollowUpById(id)));
    }

    @Operation(summary = "Get Lead Follow ups", description = "Check follow ups with lead id.")
    @GetMapping("/lead/{leadId}")
    public ResponseEntity<ApiResponse<List<FollowUpResponse>>> getLeadFollowUps(@PathVariable Long leadId) {
        return ResponseEntity.ok(ResponseBuilder.success("Lead follow-ups fetched successfully.", followUpService.getLeadFollowUps(leadId)));
    }

    @Operation(summary = "Get Caller Follow Ups.", description = "Check follow ups of a Caller.")
    @GetMapping("/caller/{callerId}")
    public ResponseEntity<ApiResponse<List<FollowUpResponse>>> getCallerFollowUps(@PathVariable Long callerId) {
        return ResponseEntity.ok(ResponseBuilder.success("Caller follow-ups fetched successfully.", followUpService.getCallerFollowUps(callerId)));
    }

    @Operation(summary = "Get Today's Follow Ups", description = "Check today's follow ups.")
    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<FollowUpResponse>>> getTodayFollowUps() {
        return ResponseEntity.ok(ResponseBuilder.success("Today's follow-ups fetched successfully.", followUpService.getTodayFollowUps()));
    }

    @Operation(summary = "Mark Complete A Followup.", description = "Complete a follow up.")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<ApiResponse<FollowUpResponse>> markAsCompleted(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBuilder.success("Follow-up completed successfully.", followUpService.markAsCompleted(id)));
    }

    @Operation(summary = "Cancel A Follow Up", description = "Cancel a follow ups.")
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<FollowUpResponse>> cancelFollowUp(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBuilder.success("Follow-up cancelled successfully.", followUpService.cancelFollowUp(id)));
    }

    @Operation(summary = "Deactivate a Follow up.", description = "Deactivate a follow up.")
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateFollowUp(@PathVariable Long id) {
        followUpService.deactivateFollowUp(id);
        return ResponseEntity.ok(ResponseBuilder.success("Follow-up deactivated successfully.", null));
    }
}
