package com.itsmidris.edulead_crm.lead.service.impl;

import java.util.List;

import com.itsmidris.edulead_crm.common.util.ReferenceCodeGenerator;
import org.springframework.stereotype.Service;

import com.itsmidris.edulead_crm.common.enums.LeadStatus;
import com.itsmidris.edulead_crm.common.exception.DuplicateResourceException;
import com.itsmidris.edulead_crm.common.exception.ResourceNotFoundException;
import com.itsmidris.edulead_crm.course.entity.Course;
import com.itsmidris.edulead_crm.course.repository.CourseRepository;
import com.itsmidris.edulead_crm.lead.dto.request.CreateLeadRequest;
import com.itsmidris.edulead_crm.lead.dto.response.LeadResponse;
import com.itsmidris.edulead_crm.lead.dto.response.LeadSummaryResponse;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.lead.mapper.LeadMapper;
import com.itsmidris.edulead_crm.lead.repository.LeadRepository;
import com.itsmidris.edulead_crm.lead.service.LeadService;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import com.itsmidris.edulead_crm.user.repository.AppUserRepository;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;
    private final CourseRepository courseRepository;
    private final AppUserRepository appUserRepository;

    private final ReferenceCodeGenerator referenceCodeGenerator;

    public LeadServiceImpl(

            LeadRepository leadRepository,
            LeadMapper leadMapper,
            CourseRepository courseRepository,
            AppUserRepository appUserRepository,
            ReferenceCodeGenerator referenceCodeGenerator) {

        this.leadRepository = leadRepository;
        this.leadMapper = leadMapper;
        this.courseRepository = courseRepository;
        this.appUserRepository = appUserRepository;
        this.referenceCodeGenerator = referenceCodeGenerator;

    }

    @Override
    public LeadResponse createLead(CreateLeadRequest request) {
        if (leadRepository.existsByStudentPhone(request.getStudentPhone())) {
            throw new DuplicateResourceException("Student phone already exists");
        }

        Course course = courseRepository
                .findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found"));

        Lead lead = leadMapper.toEntity(request);

        lead.setCourse(course);

        lead.setLeadStatus(LeadStatus.NEW);

        lead.setReferenceCode(referenceCodeGenerator.generateLeadReference());

        if (request.getAssignedCallerId() != null) {
            AppUser caller = appUserRepository.findById(request.getAssignedCallerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Caller Not Found"));

            lead.setAssignedCaller(caller);
        }

        Lead savedLead = leadRepository.save(lead);

        return leadMapper.toResponse(savedLead);
    }

    @Override
    public LeadResponse getLeadById(Long id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));
        return leadMapper.toResponse(lead);
    }

    @Override
    public List<LeadResponse> getAllLeads() {
        return leadRepository.findAll().stream().map(leadMapper::toResponse).toList();
    }

    @Override
    public List<LeadSummaryResponse> getActiveLeads() {
        return leadRepository.findByActiveTrue().stream().map(leadMapper::toSummaryResponse).toList();
    }

    @Override
    public void deactivateLead(Long id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));

        lead.setActive(false);

        leadRepository.save(lead);
    }

}