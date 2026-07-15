package com.itsmidris.edulead_crm.calllog.dto.response;

import com.itsmidris.edulead_crm.common.enums.CallOutcome;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CallLogResponse {

    private Long id;

    private String leadReference;

    private String studentName;

    private String callerName;

    private LocalDateTime callDateTime;

    private CallOutcome callOutcome;

    private Integer durationInSeconds;

    private String remarks;

    private LocalDate nextFollowUpDate;

}
