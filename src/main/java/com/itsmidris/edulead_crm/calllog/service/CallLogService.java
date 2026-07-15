package com.itsmidris.edulead_crm.calllog.service;

import com.itsmidris.edulead_crm.calllog.dto.request.CreateCallLogRequest;
import com.itsmidris.edulead_crm.calllog.dto.response.CallLogResponse;

import java.util.List;

public interface CallLogService {

    CallLogResponse createCallLog(CreateCallLogRequest request);

    List<CallLogResponse> getLeadCallHistory(Long leadId);

}
