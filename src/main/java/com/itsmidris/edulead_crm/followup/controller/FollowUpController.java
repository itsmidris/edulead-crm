package com.itsmidris.edulead_crm.followup.controller;


import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import com.itsmidris.edulead_crm.followup.dto.request.CreateFollowUpRequest;
import com.itsmidris.edulead_crm.followup.dto.response.FollowUpResponse;
import com.itsmidris.edulead_crm.followup.service.FollowUpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follow-ups")
public class FollowUpController {

    private final FollowUpService  followUpService;

    public FollowUpController(FollowUpService followUpService) {
        this.followUpService = followUpService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FollowUpResponse>> createFollowUp(@Valid @RequestBody CreateFollowUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success("Follow-up created successfully.", followUpService.createFollowUp(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FollowUpResponse>> getFollowUpById(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBuilder.success("Follow-up fetched successfully.", followUpService.getFollowUpById(id)));
    }

    @GetMapping("/lead/{leadId}")
    public ResponseEntity<ApiResponse<List<FollowUpResponse>>> getLeadFollowUps(@PathVariable Long leadId) {
        return ResponseEntity.ok(ResponseBuilder.success("Lead follow-ups fetched successfully.", followUpService.getLeadFollowUps(leadId)));
    }

    @GetMapping("/caller/{callerId}")
    public ResponseEntity<ApiResponse<List<FollowUpResponse>>> getCallerFollowUps(@PathVariable Long callerId) {
        return ResponseEntity.ok(ResponseBuilder.success("Caller follow-ups fetched successfully.", followUpService.getCallerFollowUps(callerId)));
    }

    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<FollowUpResponse>>> getTodayFollowUps() {
        return ResponseEntity.ok(ResponseBuilder.success("Today's follow-ups fetched successfully.", followUpService.getTodayFollowUps()));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<ApiResponse<FollowUpResponse>> markAsCompleted(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBuilder.success("Follow-up completed successfully.", followUpService.markAsCompleted(id)));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<FollowUpResponse>> cancelFollowUp(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBuilder.success("Follow-up cancelled successfully.", followUpService.cancelFollowUp(id)));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateFollowUp(@PathVariable Long id) {
        followUpService.deactivateFollowUp(id);
        return ResponseEntity.ok(ResponseBuilder.success("Follow-up deactivated successfully.", null));
    }
}
