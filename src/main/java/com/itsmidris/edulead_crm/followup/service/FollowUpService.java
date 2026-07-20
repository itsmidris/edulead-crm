package com.itsmidris.edulead_crm.followup.service;

import java.util.List;

import com.itsmidris.edulead_crm.followup.dto.request.CreateFollowUpRequest;
import com.itsmidris.edulead_crm.followup.dto.response.FollowUpResponse;

public interface FollowUpService {

    FollowUpResponse createFollowUp(CreateFollowUpRequest request);

    FollowUpResponse getFollowUpById(Long id);

    List<FollowUpResponse> getLeadFollowUps(Long leadId);

    List<FollowUpResponse> getCallerFollowUps(Long callerId);

    List<FollowUpResponse> getTodayFollowUps();

    FollowUpResponse markAsCompleted(Long followUpId);

    FollowUpResponse cancelFollowUp(Long followUpId);

    void deactivateFollowUp(Long id);

    List<FollowUpResponse> getAllFollowUps();

}