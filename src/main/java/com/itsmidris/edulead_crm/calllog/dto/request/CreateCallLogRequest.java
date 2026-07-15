package com.itsmidris.edulead_crm.calllog.dto.request;

import com.itsmidris.edulead_crm.common.enums.CallOutcome;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateCallLogRequest {

    @NotNull(message = "Lead Id is required.")
    private Long leadId;

    @NotNull(message = "Caller Id is required.")
    private Long callerId;

    @NotNull(message = "Call outcome is required.")
    private CallOutcome callOutcome;

    private Integer durationInSeconds;

    @Size(max = 1000)
    private String remarks;

    private LocalDate nextFollowUpDate;

}