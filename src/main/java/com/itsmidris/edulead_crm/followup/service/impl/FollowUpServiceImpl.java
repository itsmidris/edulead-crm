package com.itsmidris.edulead_crm.followup.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itsmidris.edulead_crm.common.enums.FollowUpStatus;
import com.itsmidris.edulead_crm.common.exception.ResourceNotFoundException;
import com.itsmidris.edulead_crm.followup.dto.request.CreateFollowUpRequest;
import com.itsmidris.edulead_crm.followup.dto.response.FollowUpResponse;
import com.itsmidris.edulead_crm.followup.entity.FollowUp;
import com.itsmidris.edulead_crm.followup.mapper.FollowUpMapper;
import com.itsmidris.edulead_crm.followup.repository.FollowUpRepository;
import com.itsmidris.edulead_crm.followup.service.FollowUpService;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.lead.repository.LeadRepository;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import com.itsmidris.edulead_crm.user.repository.AppUserRepository;

@Service
public class FollowUpServiceImpl implements FollowUpService {

    private final FollowUpRepository followUpRepository;
    private final FollowUpMapper followUpMapper;
    private final LeadRepository leadRepository;
    private final AppUserRepository appUserRepository;

    public FollowUpServiceImpl(
            FollowUpRepository followUpRepository,
            FollowUpMapper followUpMapper,
            LeadRepository leadRepository,
            AppUserRepository appUserRepository) {

        this.followUpRepository = followUpRepository;
        this.followUpMapper = followUpMapper;
        this.leadRepository = leadRepository;
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    @Override
    public FollowUpResponse createFollowUp(CreateFollowUpRequest request) {

        Lead lead = leadRepository.findById(request.getLeadId()).orElseThrow(() -> new ResourceNotFoundException("Lead not found."));

        AppUser caller = appUserRepository.findById(request.getCallerId()).orElseThrow(() -> new ResourceNotFoundException("Caller not found."));

        FollowUp followUp = followUpMapper.toEntity(request);

        followUp.setLead(lead);
        followUp.setCaller(caller);
        followUp.setStatus(FollowUpStatus.PENDING);

        FollowUp saved = followUpRepository.save(followUp);

        return followUpMapper.toResponse(saved);
    }

    @Override
    public FollowUpResponse getFollowUpById(Long id) {

        FollowUp followUp = followUpRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Follow-up not found."));

        return followUpMapper.toResponse(followUp);
    }

    @Override
    public List<FollowUpResponse> getLeadFollowUps(Long leadId) {

        Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new ResourceNotFoundException("Lead not found."));

        return followUpRepository.findByLeadOrderByFollowUpDateTimeDesc(lead).stream().map(followUpMapper::toResponse).toList();
    }

    @Override
    public List<FollowUpResponse> getCallerFollowUps(Long callerId) {

        AppUser caller = appUserRepository.findById(callerId).orElseThrow(() -> new ResourceNotFoundException("Caller not found."));

        return followUpRepository.findByCallerOrderByFollowUpDateTimeAsc(caller).stream().map(followUpMapper::toResponse).toList();
    }

    @Override
    public List<FollowUpResponse> getTodayFollowUps() {

        LocalDate today = LocalDate.now();

        return followUpRepository.findByFollowUpDateTimeBetweenOrderByFollowUpDateTimeAsc(today.atStartOfDay(), today.atTime(LocalTime.MAX))
                .stream()
                .map(followUpMapper::toResponse)
                .toList();
    }

    @Transactional
    @Override
    public FollowUpResponse markAsCompleted(Long followUpId) {

        FollowUp followUp = followUpRepository.findById(followUpId).orElseThrow(() -> new ResourceNotFoundException("Follow-up not found."));

        followUp.setStatus(FollowUpStatus.COMPLETED);
        followUp.setCompletedAt(LocalDateTime.now());

        return followUpMapper.toResponse(followUpRepository.save(followUp));
    }

    @Transactional
    @Override
    public FollowUpResponse cancelFollowUp(Long followUpId) {

        FollowUp followUp = followUpRepository.findById(followUpId).orElseThrow(() -> new ResourceNotFoundException("Follow-up not found."));

        followUp.setStatus(FollowUpStatus.CANCELLED);

        return followUpMapper.toResponse(followUpRepository.save(followUp));
    }

    @Transactional
    @Override
    public void deactivateFollowUp(Long id) {

        FollowUp followUp = followUpRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Follow-up not found."));

        followUp.setActive(false);

        followUpRepository.save(followUp);
    }

}
