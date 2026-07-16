package com.itsmidris.edulead_crm.counseling.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import com.itsmidris.edulead_crm.counseling.dto.request.AssignCounselorRequest;
import com.itsmidris.edulead_crm.counseling.dto.request.CompleteCounselingRequest;
import com.itsmidris.edulead_crm.counseling.dto.request.CreateCounselingRequest;
import com.itsmidris.edulead_crm.counseling.dto.response.CounselingResponse;
import com.itsmidris.edulead_crm.counseling.service.CounselingService;

import jakarta.validation.Valid;

@Tag(name = "Counseling", description = "Manage counseling requests and sessions")
@RestController
@RequestMapping("/api/v1/counselings")
@Validated
public class CounselingController {

    private final CounselingService counselingService;

    public CounselingController(CounselingService counselingService) {
        this.counselingService = counselingService;
    }

    @Operation(summary = "Create Counseling", description = "Create a counseling of a student.")
    @PostMapping
    public ResponseEntity<ApiResponse<CounselingResponse>> createCounseling(@Valid @RequestBody CreateCounselingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseBuilder.success("Counseling request created successfully.", counselingService.createCounseling(request)));
    }

    @Operation(summary = "Assign Counselor", description = "Assign a counselor for a student.")
    @PatchMapping("/{id}/assign")
    public ResponseEntity<ApiResponse<CounselingResponse>> assignCounselor(@PathVariable Long id, @Valid @RequestBody AssignCounselorRequest request) {
        return ResponseEntity.ok(ResponseBuilder.success("Counselor assigned successfully.", counselingService.assignCounselor(id, request)));
    }

    @Operation(summary = "Complete Counseling", description = "Complete a counseling.")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<ApiResponse<CounselingResponse>> completeCounseling(@PathVariable Long id, @Valid @RequestBody CompleteCounselingRequest request) {
        return ResponseEntity.ok(ResponseBuilder.success("Counseling completed successfully.", counselingService.completeCounseling(id, request)));
    }

    @Operation(summary = "Cancel A Counseling", description = "Cancel a counseling.")
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<CounselingResponse>> cancelCounseling(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBuilder.success("Counseling cancelled successfully.", counselingService.cancelCounseling(id)));
    }

    @Operation(summary = "Get Counseling By Id", description = "Get counseling using Counseling id.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CounselingResponse>> getCounselingById(@PathVariable Long id) {

        return ResponseEntity.ok(ResponseBuilder.success("Counseling fetched successfully.", counselingService.getCounselingById(id)));
    }

    @Operation(summary = "Get Lead Counseling", description = "Get counseling of a lead.")
    @GetMapping("/lead/{leadId}")
    public ResponseEntity<ApiResponse<List<CounselingResponse>>> getLeadCounselings(@PathVariable Long leadId) {
        return ResponseEntity.ok(ResponseBuilder.success("Lead counseling history fetched successfully.", counselingService.getLeadCounselings(leadId)));
    }

    @Operation(summary = "Get Counselor Schedule", description = "Check counselor's counseling.")
    @GetMapping("/counselor/{counselorId}")
    public ResponseEntity<ApiResponse<List<CounselingResponse>>> getCounselorSchedule(@PathVariable Long counselorId) {
        return ResponseEntity.ok(ResponseBuilder.success("Counselor schedule fetched successfully.", counselingService.getCounselorSchedule(counselorId)));
    }

    @Operation(summary = "Get Today's Counseling", description = "Check today's counseling")
    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<CounselingResponse>>> getTodayCounselings() {
        return ResponseEntity.ok(ResponseBuilder.success("Today's counseling schedule fetched successfully.", counselingService.getTodayCounselings()));
    }

    @Operation(summary = "Deactivate Counseling", description = "Deactivate a counseling -> Soft Delete")
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateCounseling(@PathVariable Long id) {
        counselingService.deactivateCounseling(id);
        return ResponseEntity.ok(ResponseBuilder.success("Counseling deactivated successfully.", null));
    }

}