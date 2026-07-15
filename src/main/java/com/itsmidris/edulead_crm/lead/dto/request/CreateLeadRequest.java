package com.itsmidris.edulead_crm.lead.dto.request;

import java.time.LocalDate;

import com.itsmidris.edulead_crm.common.enums.LeadCreationMethod;
import com.itsmidris.edulead_crm.common.enums.LeadSource;
import com.itsmidris.edulead_crm.common.enums.LeadType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLeadRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String studentPhone;

    private String parentPhone;

    @Email
    private String email;

    private String state;

    private String city;

    private String address;

    @NotNull
    private Long courseId;

    @NotNull
    private LeadType leadType;

    @NotNull
    private LeadSource leadSource;

    @NotNull
    private LeadCreationMethod leadCreationMethod;

    private Long assignedCallerId;

    private LocalDate nextFollowUpDate;

    private boolean counselingRequired;

    private String remark;

}