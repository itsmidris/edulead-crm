package com.itsmidris.edulead_crm.lead.entity;

import com.itsmidris.edulead_crm.common.entity.BaseEntity;
import com.itsmidris.edulead_crm.common.enums.LeadCreationMethod;
import com.itsmidris.edulead_crm.common.enums.LeadSource;
import com.itsmidris.edulead_crm.common.enums.LeadStatus;
import com.itsmidris.edulead_crm.common.enums.LeadType;
import com.itsmidris.edulead_crm.course.entity.Course;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "leads")
public class Lead extends BaseEntity {

    @Column(nullable = false, unique = true, length = 20, updatable = false)
    private String referenceCode;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "First Name is required")
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 15)
    @NotBlank(message = "Student Phone is required")
    private String studentPhone;

    @Column(length = 15)
    private String parentPhone;

    @Column(length = 100)
    @Email(message = "Please enter a valid email address")
    private String email;

    @Column(length = 50)
    private String state;

    @Column(length = 50)
    private String city;

    @Column(length = 300)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeadStatus leadStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeadType leadType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Please select a lead source")
    private LeadSource leadSource;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeadCreationMethod leadCreationMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_caller_id")
    private AppUser assignedCaller;

    private LocalDate nextFollowUpDate;

    @Column(nullable = false)
    private boolean counselingRequired = false;

    @Column(nullable = false)
    private boolean active = true;
}
