package com.itsmidris.edulead_crm.calllog.service;

import com.itsmidris.edulead_crm.calllog.dto.request.CreateCallLogRequest;
import com.itsmidris.edulead_crm.calllog.dto.response.CallLogResponse;
import com.itsmidris.edulead_crm.common.enums.CallOutcome;

import java.time.LocalDate;
import java.util.List;

public interface CallLogService {

    CallLogResponse createCallLog(CreateCallLogRequest request);

    List<CallLogResponse> getLeadCallHistory(Long leadId);

    List<CallLogResponse> getCallerCallHistory(Long callerId);

    List<CallLogResponse> getTodayCallLogs();

    List<CallLogResponse> getCallLogsBetween(LocalDate startDate, LocalDate endDate);

    List<CallLogResponse> getCallLogsByOutcome(CallOutcome callOutcome);

    CallLogResponse getCallLogById(Long id);

    List<CallLogResponse> getAllCallLogs();

}
