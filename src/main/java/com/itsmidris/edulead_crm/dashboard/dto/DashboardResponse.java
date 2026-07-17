package com.itsmidris.edulead_crm.dashboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardResponse {

    private long totalLeads;

    private long activeLeads;

    private long newLeads;

    private long contactedLeads;

    private long followUpLeads;

    private long counselingRequiredLeads;

    private long admissionCompletedLeads;

    private long lostLeads;

    private long todayCalls;

    private Long totalCourses;

}