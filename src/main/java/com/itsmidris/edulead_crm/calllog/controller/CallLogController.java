package com.itsmidris.edulead_crm.calllog.controller;

import com.itsmidris.edulead_crm.calllog.dto.request.CreateCallLogRequest;
import com.itsmidris.edulead_crm.calllog.dto.response.CallLogResponse;
import com.itsmidris.edulead_crm.calllog.service.CallLogService;
import com.itsmidris.edulead_crm.common.enums.CallOutcome;
import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/call-logs")
@Validated
public class CallLogController {

    private final CallLogService callLogService;

    public CallLogController(CallLogService callLogService) {
        this.callLogService = callLogService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CallLogResponse>> createCallLog(@Valid @RequestBody CreateCallLogRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success("Call log created successfully.", callLogService.createCallLog(request)));
    }

    @GetMapping("/lead/{leadId}")
    public ResponseEntity<ApiResponse<List<CallLogResponse>>> getCallHistory(@PathVariable Long leadId) {

        return ResponseEntity.ok(ResponseBuilder
                .success("Call history fetched successfully.", callLogService.getLeadCallHistory(leadId)));
    }

    @GetMapping("/caller/{callerId}")
    public ResponseEntity<ApiResponse<List<CallLogResponse>>> getCallerHistory(@PathVariable Long callerId) {
        return ResponseEntity.ok(ResponseBuilder.success("Caller call history fetched successfully", callLogService.getCallerCallHistory(callerId)));
    }

    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<CallLogResponse>>> getTodayCallLogs() {
        return ResponseEntity.ok(ResponseBuilder.success("Today's call logs fetched successfully", callLogService.getTodayCallLogs()));
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<List<CallLogResponse>>> getCallLogsBetween(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(ResponseBuilder.success("Call logs fetched successfully", callLogService.getCallLogsBetween(startDate, endDate)));
    }

    @GetMapping("/outcome/{callOutcome}")
    public ResponseEntity<ApiResponse<List<CallLogResponse>>> getCallLogsByOutcome(@PathVariable CallOutcome callOutcome) {
        return ResponseEntity.ok(ResponseBuilder.success("Call logs fetched successfully",callLogService.getCallLogsByOutcome(callOutcome)));
    }
}
