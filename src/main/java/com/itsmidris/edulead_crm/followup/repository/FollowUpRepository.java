package com.itsmidris.edulead_crm.followup.repository;

import com.itsmidris.edulead_crm.common.enums.FollowUpStatus;
import com.itsmidris.edulead_crm.followup.entity.FollowUp;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {

    List<FollowUp> findByLeadOrderByFollowUpDateTimeDesc(Lead lead);

    List<FollowUp> findByCallerOrderByFollowUpDateTimeAsc(AppUser caller);

    List<FollowUp> findByStatusOrderByFollowUpDateTimeAsc(FollowUpStatus status);

    List<FollowUp> findByFollowUpDateTimeBetweenOrderByFollowUpDateTimeAsc(LocalDateTime start, LocalDateTime end);

    List<FollowUp> findByCallerAndStatusOrderByFollowUpDateTimeAsc(AppUser caller, FollowUpStatus status);

    List<FollowUp> findByActiveTrueOrderByFollowUpDateTimeAsc();
}
