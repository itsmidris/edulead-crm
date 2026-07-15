package com.itsmidris.edulead_crm.calllog.controller;

import com.itsmidris.edulead_crm.calllog.dto.request.CreateCallLogRequest;
import com.itsmidris.edulead_crm.calllog.dto.response.CallLogResponse;
import com.itsmidris.edulead_crm.calllog.service.CallLogService;
import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
