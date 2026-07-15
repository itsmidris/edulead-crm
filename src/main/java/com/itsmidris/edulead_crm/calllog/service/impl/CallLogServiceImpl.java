package com.itsmidris.edulead_crm.calllog.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.itsmidris.edulead_crm.common.enums.CallOutcome;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itsmidris.edulead_crm.calllog.dto.request.CreateCallLogRequest;
import com.itsmidris.edulead_crm.calllog.dto.response.CallLogResponse;
import com.itsmidris.edulead_crm.calllog.entity.CallLog;
import com.itsmidris.edulead_crm.calllog.mapper.CallLogMapper;
import com.itsmidris.edulead_crm.calllog.repository.CallLogRepository;
import com.itsmidris.edulead_crm.calllog.service.CallLogService;
import com.itsmidris.edulead_crm.common.enums.LeadStatus;
import com.itsmidris.edulead_crm.common.exception.ResourceNotFoundException;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.lead.repository.LeadRepository;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import com.itsmidris.edulead_crm.user.repository.AppUserRepository;

@Service
public class CallLogServiceImpl implements CallLogService {

    private final CallLogRepository callLogRepository;
    private final CallLogMapper callLogMapper;
    private final LeadRepository leadRepository;
    private final AppUserRepository appUserRepository;

    public CallLogServiceImpl(
            CallLogRepository callLogRepository,
            CallLogMapper callLogMapper,
            LeadRepository leadRepository,
            AppUserRepository appUserRepository) {

        this.callLogRepository = callLogRepository;
        this.callLogMapper = callLogMapper;
        this.leadRepository = leadRepository;
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    @Override
    public CallLogResponse createCallLog(CreateCallLogRequest request) {

        Lead lead = leadRepository.findById(request.getLeadId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lead not found."));

        AppUser caller = appUserRepository.findById(request.getCallerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Caller not found."));

        CallLog callLog = callLogMapper.toEntity(request);

        callLog.setLead(lead);
        callLog.setCaller(caller);
        callLog.setCallDateTime(LocalDateTime.now());

        updateLeadAfterCall(lead, request);

        CallLog savedCallLog = callLogRepository.save(callLog);

        leadRepository.save(lead);

        return callLogMapper.toResponse(savedCallLog);
    }

    @Override
    public List<CallLogResponse> getLeadCallHistory(Long leadId) {

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lead not found."));

        return callLogRepository.findByLeadOrderByCallDateTimeDesc(lead)
                .stream()
                .map(callLogMapper::toResponse)
                .toList();
    }

    private void updateLeadAfterCall(Lead lead, CreateCallLogRequest request) {

        switch (request.getCallOutcome()) {

            case INTERESTED -> {
                lead.setLeadStatus(LeadStatus.CONTACTED);
            }

            case CALLBACK_REQUESTED -> {
                lead.setLeadStatus(LeadStatus.FOLLOW_UP);
                lead.setNextFollowUpDate(request.getNextFollowUpDate());
            }

            case COUNSELING_REQUIRED -> {
                lead.setLeadStatus(LeadStatus.COUNSELING_REQUIRED);
                lead.setCounselingRequired(true);
                lead.setNextFollowUpDate(request.getNextFollowUpDate());
            }

            case NOT_INTERESTED -> {
                lead.setLeadStatus(LeadStatus.NOT_INTERESTED);
            }

            case WRONG_NUMBER -> {
                lead.setLeadStatus(LeadStatus.LOST);
            }

            case ADMISSION_TAKEN_ELSEWHERE -> {
                lead.setLeadStatus(LeadStatus.LOST);
            }

            case BUSY, NO_ANSWER -> {
                // Keep current lead status unchanged.
            }
        }
    }

    @Override
    public List<CallLogResponse> getCallerCallHistory(Long callerId) {

        AppUser caller = appUserRepository.findById(callerId)
                .orElseThrow( ()->new ResourceNotFoundException("Caller not found"));

        return callLogRepository.findByCallerOrderByCallDateTimeDesc(caller)
                .stream().map(callLogMapper::toResponse).toList();
    }

    @Override
    public List<CallLogResponse> getTodayCallLogs() {

        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);

        return callLogRepository
                .findByCallDateTimeBetweenOrderByCallDateTimeDesc(start,end)
                .stream().map(callLogMapper::toResponse).toList();
    }

    @Override
    public List<CallLogResponse> getCallLogsBetween(LocalDate startDate, LocalDate endDate) {
        return callLogRepository
                .findByCallDateTimeBetweenOrderByCallDateTimeDesc(startDate.atStartOfDay(),endDate.atTime(LocalTime.MAX))
                .stream().map(callLogMapper::toResponse).toList();
    }

    @Override
    public List<CallLogResponse> getCallLogsByOutcome(CallOutcome callOutcome) {
        return callLogRepository
                .findByCallOutcomeOrderByCallDateTimeDesc(callOutcome)
                .stream().map(callLogMapper::toResponse).toList();
    }
}