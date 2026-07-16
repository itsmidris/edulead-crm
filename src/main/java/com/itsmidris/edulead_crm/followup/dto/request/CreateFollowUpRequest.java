package com.itsmidris.edulead_crm.followup.dto.request;

import com.itsmidris.edulead_crm.common.enums.FollowUpPriority;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateFollowUpRequest {

    @NotNull
    private Long leadId;

    @NotNull
    private Long callerId;

    @NotBlank
    private String subject;

    @NotNull
    @Future
    private LocalDateTime followUpDateTime;

    @NotNull
    private FollowUpPriority priority;

}
