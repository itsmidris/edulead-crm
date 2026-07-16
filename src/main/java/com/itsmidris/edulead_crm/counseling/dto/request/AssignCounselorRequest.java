package com.itsmidris.edulead_crm.counseling.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignCounselorRequest {

    @NotNull(message = "Counselor Id is required.")
    private Long counselorId;

    @NotNull(message = "Scheduled date & time is required.")
    @Future(message = "Scheduled date & time must be in the future.")
    private LocalDateTime scheduledDateTime;

}