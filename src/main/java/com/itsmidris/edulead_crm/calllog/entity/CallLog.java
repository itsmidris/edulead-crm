package com.itsmidris.edulead_crm.calllog.entity;

import com.itsmidris.edulead_crm.common.entity.BaseEntity;
import com.itsmidris.edulead_crm.common.enums.CallOutcome;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "call_logs")
public class CallLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caller_id", nullable = false)
    private AppUser caller;

    @Column(nullable = false)
    private LocalDateTime callDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CallOutcome callOutcome;

    private Integer durationInSeconds;

    @Column(length = 1000)
    private String remarks;

    private LocalDate nextFollowUpDate;
}
