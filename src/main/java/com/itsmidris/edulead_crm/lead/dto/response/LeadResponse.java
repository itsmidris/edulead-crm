package com.itsmidris.edulead_crm.lead.dto.response;

import java.time.LocalDate;

import com.itsmidris.edulead_crm.common.enums.LeadCreationMethod;
import com.itsmidris.edulead_crm.common.enums.LeadSource;
import com.itsmidris.edulead_crm.common.enums.LeadStatus;
import com.itsmidris.edulead_crm.common.enums.LeadType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadResponse {

    private Long id;

    private String referenceCode;

    private String firstName;

    private String lastName;

    private String studentPhone;

    private String parentPhone;

    private String email;

    private String state;

    private String city;

    private String address;

    private String courseName;

    private LeadStatus leadStatus;

    private LeadType leadType;

    private LeadSource leadSource;

    private LeadCreationMethod leadCreationMethod;

    private String assignedCaller;

    private LocalDate nextFollowUpDate;

    private boolean counselingRequired;

    private String remark;

}