package com.itsmidris.edulead_crm.dashboard.service.impl;

import com.itsmidris.edulead_crm.calllog.repository.CallLogRepository;
import com.itsmidris.edulead_crm.common.enums.LeadStatus;
import com.itsmidris.edulead_crm.course.repository.CourseRepository;
import com.itsmidris.edulead_crm.dashboard.dto.DashboardResponse;
import com.itsmidris.edulead_crm.dashboard.service.DashboardService;
import com.itsmidris.edulead_crm.lead.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class DashboardServiceImpl  implements DashboardService {

    private final LeadRepository leadRepository;
    private final CallLogRepository callLogRepository;
    private final CourseRepository courseRepository;

    public DashboardServiceImpl(LeadRepository leadRepository, CallLogRepository callLogRepository, CourseRepository courseRepository) {
        this.leadRepository = leadRepository;
        this.callLogRepository = callLogRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public DashboardResponse getDashboardSummary() {
        DashboardResponse response = new DashboardResponse();

        response.setTotalLeads(leadRepository.count());

        response.setActiveLeads(leadRepository.countByActiveTrue());

        response.setNewLeads(leadRepository.countByLeadStatus(LeadStatus.NEW));

        response.setContactedLeads(leadRepository.countByLeadStatus(LeadStatus.CONTACTED));

        response.setFollowUpLeads(leadRepository.countByLeadStatus(LeadStatus.FOLLOW_UP));

        response.setCounselingRequiredLeads(leadRepository.countByLeadStatus(LeadStatus.COUNSELING_REQUIRED));

        response.setAdmissionCompletedLeads(leadRepository.countByLeadStatus(LeadStatus.ADMISSION_COMPLETED));

        response.setLostLeads(leadRepository.countByLeadStatus(LeadStatus.LOST));

        LocalDate today = LocalDate.now();

        response.setTodayCalls(callLogRepository.countByCallDateTimeBetween(today.atStartOfDay(), today.atTime(LocalTime.MAX)));

        response.setTotalCourses(courseRepository.count());

        return response;
    }
}
