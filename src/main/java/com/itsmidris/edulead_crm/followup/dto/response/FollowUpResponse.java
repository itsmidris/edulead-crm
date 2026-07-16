package com.itsmidris.edulead_crm.followup.dto.response;

import com.itsmidris.edulead_crm.common.enums.FollowUpPriority;
import com.itsmidris.edulead_crm.common.enums.FollowUpStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FollowUpResponse {

    private Long id;

    private String leadReference;

    private String studentName;

    private String callerName;

    private String subject;

    private LocalDateTime followUpDateTime;

    private FollowUpStatus status;

    private FollowUpPriority priority;

    private LocalDateTime completedAt;

}