package com.itsmidris.edulead_crm.lead.service;

import java.util.List;

import com.itsmidris.edulead_crm.common.enums.LeadStatus;
import com.itsmidris.edulead_crm.lead.dto.request.CreateLeadRequest;
import com.itsmidris.edulead_crm.lead.dto.request.UpdateLeadRequest;
import com.itsmidris.edulead_crm.lead.dto.response.LeadResponse;
import com.itsmidris.edulead_crm.lead.dto.response.LeadSummaryResponse;

public interface LeadService {

    LeadResponse createLead(CreateLeadRequest request);

    LeadResponse getLeadById(Long id);

    List<LeadResponse> getAllLeads();

    List<LeadSummaryResponse> getActiveLeads();

    void deactivateLead(Long id);

    LeadResponse updateLead(Long id, UpdateLeadRequest request);

    UpdateLeadRequest getLeadForUpdate(Long id);

    List<LeadResponse> searchLeads(String keyword, LeadStatus status);

}