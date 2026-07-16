package com.itsmidris.edulead_crm.counseling.dto.response;

import java.time.LocalDateTime;

import com.itsmidris.edulead_crm.common.enums.CounselingMode;
import com.itsmidris.edulead_crm.common.enums.CounselingOutcome;
import com.itsmidris.edulead_crm.common.enums.CounselingStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounselingResponse {

    private Long id;

    private String referenceCode;

    private String leadReference;

    private String studentName;

    private String leadPhoneNumber;

    private Long requestedById;

    private String requestedByName;

    private String requestedByPhone;

    private Long assignedCounselorId;

    private String assignedCounselorName;

    private CounselingMode counselingMode;

    private CounselingStatus counselingStatus;

    private CounselingOutcome counselingOutcome;

    private LocalDateTime scheduledDateTime;

    private LocalDateTime completedDateTime;

    private String requestReason;

    private String notes;

}