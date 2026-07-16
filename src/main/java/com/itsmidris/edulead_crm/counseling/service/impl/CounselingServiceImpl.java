package com.itsmidris.edulead_crm.counseling.service.impl;

import com.itsmidris.edulead_crm.common.enums.CounselingStatus;
import com.itsmidris.edulead_crm.common.enums.LeadStatus;
import com.itsmidris.edulead_crm.common.exception.BusinessException;
import com.itsmidris.edulead_crm.common.exception.ResourceNotFoundException;
import com.itsmidris.edulead_crm.common.util.ReferenceCodeGenerator;
import com.itsmidris.edulead_crm.counseling.dto.request.AssignCounselorRequest;
import com.itsmidris.edulead_crm.counseling.dto.request.CompleteCounselingRequest;
import com.itsmidris.edulead_crm.counseling.dto.request.CreateCounselingRequest;
import com.itsmidris.edulead_crm.counseling.dto.response.CounselingResponse;
import com.itsmidris.edulead_crm.counseling.entity.Counseling;
import com.itsmidris.edulead_crm.counseling.mapper.CounselingMapper;
import com.itsmidris.edulead_crm.counseling.repository.CounselingRepository;
import com.itsmidris.edulead_crm.counseling.service.CounselingService;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.lead.repository.LeadRepository;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import com.itsmidris.edulead_crm.user.repository.AppUserRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CounselingServiceImpl implements CounselingService {

    private final CounselingRepository counselingRepository;
    private final CounselingMapper counselingMapper;
    private final LeadRepository leadRepository;
    private final AppUserRepository appUserRepository;
    private final ReferenceCodeGenerator referenceCodeGenerator;
    public CounselingServiceImpl(CounselingRepository counselingRepository, CounselingMapper counselingMapper, LeadRepository leadRepository, AppUserRepository appUserRepository, ReferenceCodeGenerator referenceCodeGenerator) {
        this.counselingRepository = counselingRepository;
        this.counselingMapper = counselingMapper;
        this.leadRepository = leadRepository;
        this.appUserRepository = appUserRepository;
        this.referenceCodeGenerator = referenceCodeGenerator;
    }

    @Transactional
    @Override
    public CounselingResponse createCounseling(CreateCounselingRequest request) {

        Lead lead = getLead(request.getLeadId());

        AppUser requestedBy = getUser(request.getRequestedById());

        validateActiveCounseling(lead);

        Counseling counseling = counselingMapper.toEntity(request);

        counseling.setReferenceCode(referenceCodeGenerator.generateCounselingReference());

        counseling.setLead(lead);

        counseling.setRequestedBy(requestedBy);

        counseling.setCounselingStatus(CounselingStatus.PENDING_ASSIGNMENT);

        counseling.setAssignedCounselor(null);

        counseling.setCounselingOutcome(null);

        counseling.setCompletedDateTime(null);

        lead.setLeadStatus(LeadStatus.COUNSELING_REQUIRED);

        lead.setCounselingRequired(true);

        Counseling saved = counselingRepository.save(counseling);

        leadRepository.save(lead);

        return counselingMapper.toResponse(saved);
    }

    @Transactional
    @Override
    public CounselingResponse assignCounselor(Long counselingId, AssignCounselorRequest request) {

        Counseling counseling = getCounseling(counselingId);

        if (counseling.getCounselingStatus() != CounselingStatus.PENDING_ASSIGNMENT) {
            throw new BusinessException("Only pending counseling requests can be assigned.");
        }

        AppUser counselor = getUser(request.getCounselorId());

        counseling.setAssignedCounselor(counselor);

        counseling.setScheduledDateTime(request.getScheduledDateTime());

        counseling.setCounselingStatus(CounselingStatus.SCHEDULED);

        Counseling saved = counselingRepository.save(counseling);

        return counselingMapper.toResponse(saved);
    }

    @Override
    public CounselingResponse getCounselingById(Long id) {
        Counseling counseling = getCounseling(id);
        return counselingMapper.toResponse(counseling);
    }

    @Override
    public List<CounselingResponse> getLeadCounselings(Long leadId) {
        Lead lead = getLead(leadId);

        return counselingRepository.findByLeadOrderByScheduledDateTimeDesc(lead)
                .stream()
                .map(counselingMapper::toResponse)
                .toList();
    }

    @Override
    public List<CounselingResponse> getCounselorSchedule(Long counselorId) {
        AppUser counselor = getUser(counselorId);

        return counselingRepository.findByAssignedCounselorOrderByScheduledDateTimeAsc(counselor)
                .stream()
                .map(counselingMapper::toResponse)
                .toList();
    }

    @Override
    public List<CounselingResponse> getTodayCounselings() {
        LocalDate today = LocalDate.now();

        return counselingRepository
                .findByScheduledDateTimeBetweenOrderByScheduledDateTimeAsc(today.atStartOfDay(), today.atTime(LocalTime.MAX))
                .stream()
                .map(counselingMapper::toResponse)
                .toList();
    }

    @Override
    public CounselingResponse completeCounseling(Long counselingId, CompleteCounselingRequest request) {

        Counseling counseling = getCounseling(counselingId);

        if (counseling.getCounselingStatus() != CounselingStatus.SCHEDULED && counseling.getCounselingStatus() != CounselingStatus.RESCHEDULED) {
            throw new BusinessException("Only scheduled counseling can be completed.");
        }

        counseling.setCounselingStatus(CounselingStatus.COMPLETED);

        counseling.setCounselingOutcome(request.getCounselingOutcome());

        counseling.setNotes(request.getNotes());

        counseling.setCompletedDateTime(LocalDateTime.now());

        updateLeadAfterCounseling(counseling.getLead(), request);

        Counseling saved = counselingRepository.save(counseling);

        leadRepository.save(counseling.getLead());

        return counselingMapper.toResponse(saved);
    }

    @Override
    public CounselingResponse cancelCounseling(Long counselingId) {
        Counseling counseling = getCounseling(counselingId);

        if (counseling.getCounselingStatus() == CounselingStatus.COMPLETED) {
            throw new BusinessException("Completed counseling cannot be cancelled.");
        }

        counseling.setCounselingStatus(CounselingStatus.CANCELLED);

        Counseling saved = counselingRepository.save(counseling);

        return counselingMapper.toResponse(saved);
    }

    @Override
    public void deactivateCounseling(Long id) {
        Counseling counseling = getCounseling(id);
        counseling.setActive(false);
        counselingRepository.save(counseling);
    }

    private Lead getLead(Long leadId) {
        return leadRepository.findById(leadId).orElseThrow(() -> new ResourceNotFoundException("Lead not found."));
    }

    private AppUser getUser(Long userId) {
        return appUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    private Counseling getCounseling(Long counselingId) {
        return counselingRepository.findById(counselingId).orElseThrow(() -> new ResourceNotFoundException("Counseling not found."));
    }

    private void validateActiveCounseling(Lead lead) {

        boolean exists = counselingRepository.existsByLeadAndCounselingStatusIn(lead, List.of(
                CounselingStatus.PENDING_ASSIGNMENT,
                CounselingStatus.SCHEDULED,
                CounselingStatus.RESCHEDULED
        ));

        if (exists) {
            throw new BusinessException("Lead already has an active counseling request.");
        }
    }

    private void updateLeadAfterCounseling(Lead lead, CompleteCounselingRequest request) {

        switch (request.getCounselingOutcome()) {

            case ADMISSION_CONFIRMED -> {

                lead.setLeadStatus(
                        LeadStatus.ADMISSION_COMPLETED);

                lead.setCounselingRequired(false);
            }

            case INTERESTED -> {

                lead.setLeadStatus(
                        LeadStatus.COUNSELING_COMPLETED);

                lead.setCounselingRequired(false);
            }

            case NOT_INTERESTED -> {

                lead.setLeadStatus(
                        LeadStatus.NOT_INTERESTED);

                lead.setCounselingRequired(false);
            }

            case FOLLOW_UP_REQUIRED -> {

                lead.setLeadStatus(
                        LeadStatus.FOLLOW_UP);

                lead.setCounselingRequired(false);

                // Version 2:
                // Automatically create FollowUp
            }
        }
    }
}
