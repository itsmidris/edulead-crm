package com.itsmidris.edulead_crm.counseling.service;

import java.util.List;

import com.itsmidris.edulead_crm.counseling.dto.request.AssignCounselorRequest;
import com.itsmidris.edulead_crm.counseling.dto.request.CompleteCounselingRequest;
import com.itsmidris.edulead_crm.counseling.dto.request.CreateCounselingRequest;
import com.itsmidris.edulead_crm.counseling.dto.response.CounselingResponse;
import org.springframework.stereotype.Service;

public interface CounselingService {

    CounselingResponse createCounseling(CreateCounselingRequest request);

    CounselingResponse assignCounselor(Long counselingId, AssignCounselorRequest request);

    CounselingResponse getCounselingById(Long id);

    List<CounselingResponse> getLeadCounselings(Long leadId);

    List<CounselingResponse> getCounselorSchedule(Long counselorId);

    List<CounselingResponse> getTodayCounselings();

    CounselingResponse completeCounseling(Long counselingId, CompleteCounselingRequest request);

    CounselingResponse cancelCounseling(Long counselingId);

    void deactivateCounseling(Long id);

}