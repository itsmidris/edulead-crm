package com.itsmidris.edulead_crm.counseling.dto.request;

import java.time.LocalDateTime;

import com.itsmidris.edulead_crm.common.enums.CounselingMode;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCounselingRequest {

    @NotNull(message = "Lead Id is required.")
    private Long leadId;

    @NotNull(message = "Requested By Id is required.")
    private Long requestedById;

    @NotNull(message = "Counseling mode is required.")
    private CounselingMode counselingMode;

    @NotNull(message = "Scheduled date & time is required.")
    @Future(message = "Scheduled date must be in the future.")
    private LocalDateTime scheduledDateTime;

    @NotBlank(message = "Request reason is required.")
    private String requestReason;

}
