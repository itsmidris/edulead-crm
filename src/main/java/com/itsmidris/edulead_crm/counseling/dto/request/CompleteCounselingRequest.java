package com.itsmidris.edulead_crm.counseling.dto.request;

import com.itsmidris.edulead_crm.common.enums.CounselingOutcome;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteCounselingRequest {

    @NotNull(message = "Counseling outcome is required.")
    private CounselingOutcome counselingOutcome;

    @NotBlank(message = "Counseling notes are required.")
    private String notes;

}