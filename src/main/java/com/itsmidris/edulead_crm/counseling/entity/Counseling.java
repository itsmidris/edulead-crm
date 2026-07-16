package com.itsmidris.edulead_crm.counseling.entity;

import java.time.LocalDateTime;

import com.itsmidris.edulead_crm.common.entity.BaseEntity;
import com.itsmidris.edulead_crm.common.enums.CounselingMode;
import com.itsmidris.edulead_crm.common.enums.CounselingOutcome;
import com.itsmidris.edulead_crm.common.enums.CounselingStatus;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.user.entity.AppUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "counselings")
public class Counseling extends BaseEntity {

    @Column(nullable = false, unique = true, updatable = false)
    private String referenceCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id", nullable = false)
    private Lead lead;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "counselor_id", nullable = false)
//    private AppUser counselor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_by", nullable = false)
    private AppUser requestedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_counselor_id")
    private AppUser assignedCounselor;

    @Column(nullable = false, length = 500)
    private String requestReason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CounselingMode counselingMode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CounselingStatus counselingStatus;

    @Enumerated(EnumType.STRING)
    private CounselingOutcome counselingOutcome;

    @Column(nullable = false)
    private LocalDateTime scheduledDateTime;

    private LocalDateTime completedDateTime;

    @Column(length = 1500)
    private String notes;

    @Column(nullable = false)
    private boolean active = true;

}
