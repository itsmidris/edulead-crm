package com.itsmidris.edulead_crm.lead.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadSummaryResponse {

    private Long id;

    private String referenceCode;

    private String fullName;

    private String studentPhone;

    private String courseName;

    private String leadStatus;

}