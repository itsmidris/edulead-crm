package com.itsmidris.edulead_crm.calllog.controller;

import com.itsmidris.edulead_crm.calllog.dto.request.CreateCallLogRequest;
import com.itsmidris.edulead_crm.calllog.dto.response.CallLogResponse;
import com.itsmidris.edulead_crm.calllog.service.CallLogService;
import com.itsmidris.edulead_crm.common.enums.CallOutcome;
import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Call Log", description = "Manage student call history")
@RestController
@RequestMapping("/api/v1/call-logs")
@Validated
public class CallLogController {

    private final CallLogService callLogService;

    public CallLogController(CallLogService callLogService) {
        this.callLogService = callLogService;
    }

    @Operation(summary = "Create Call Log", description = "Create call log with student.")
    @PostMapping
    public ResponseEntity<ApiResponse<CallLogResponse>> createCallLog(@Valid @RequestBody CreateCallLogRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success("Call log created successfully.", callLogService.createCallLog(request)));
    }

    @Operation(summary = "Get Call History", description = "Check when a particular student contacted by caller.")
    @GetMapping("/lead/{leadId}")
    public ResponseEntity<ApiResponse<List<CallLogResponse>>> getCallHistory(@PathVariable Long leadId) {

        return ResponseEntity.ok(ResponseBuilder
                .success("Call history fetched successfully.", callLogService.getLeadCallHistory(leadId)));
    }

    @Operation(summary = "Get Caller History", description = "Check history of a caller who contacted multiple students.")
    @GetMapping("/caller/{callerId}")
    public ResponseEntity<ApiResponse<List<CallLogResponse>>> getCallerHistory(@PathVariable Long callerId) {
        return ResponseEntity.ok(ResponseBuilder.success("Caller call history fetched successfully", callLogService.getCallerCallHistory(callerId)));
    }

    @Operation(summary = "Get Today's Call Log", description = "Check call Today's call log of every caller.")
    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<CallLogResponse>>> getTodayCallLogs() {
        return ResponseEntity.ok(ResponseBuilder.success("Today's call logs fetched successfully", callLogService.getTodayCallLogs()));
    }

    @Operation(summary = "Get Call Logs Between Dates", description = "Get call logs between a start date and end date")
    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<List<CallLogResponse>>> getCallLogsBetween(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(ResponseBuilder.success("Call logs fetched successfully", callLogService.getCallLogsBetween(startDate, endDate)));
    }

    @Operation(summary = "Get Call Log By Outcome", description = "Get call log by outcome like interested, etc.")
    @GetMapping("/outcome/{callOutcome}")
    public ResponseEntity<ApiResponse<List<CallLogResponse>>> getCallLogsByOutcome(@PathVariable CallOutcome callOutcome) {
        return ResponseEntity.ok(ResponseBuilder.success("Call logs fetched successfully",callLogService.getCallLogsByOutcome(callOutcome)));
    }
}
