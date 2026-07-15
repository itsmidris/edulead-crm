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

    @NotNull
    private Long leadId;

    @NotNull
    private Long callerId;

    @NotNull
    private CallOutcome callOutcome;

    private Integer durationInSeconds;

    @Size(max = 1000)
    private String remarks;

    private LocalDate nextFollowUpDate;

}