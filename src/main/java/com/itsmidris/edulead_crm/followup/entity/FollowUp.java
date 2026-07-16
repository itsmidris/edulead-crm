package com.itsmidris.edulead_crm.followup.entity;

import com.itsmidris.edulead_crm.common.entity.BaseEntity;
import com.itsmidris.edulead_crm.common.enums.FollowUpPriority;
import com.itsmidris.edulead_crm.common.enums.FollowUpStatus;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "follow_ups")
public class FollowUp extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caller_id", nullable = false)
    private AppUser caller;

    @Column(nullable = false, length = 200)
    private String subject;

    @Column(nullable = false)
    private LocalDateTime followUpDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FollowUpStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FollowUpPriority priority;

    private LocalDateTime completedAt;

    @Column(nullable = false)
    private boolean active = true;

}
