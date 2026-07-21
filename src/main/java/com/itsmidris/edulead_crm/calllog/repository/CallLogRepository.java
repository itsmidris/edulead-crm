package com.itsmidris.edulead_crm.calllog.repository;

import com.itsmidris.edulead_crm.calllog.entity.CallLog;
import com.itsmidris.edulead_crm.common.enums.CallOutcome;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CallLogRepository extends JpaRepository<CallLog, Long> {
    List<CallLog> findByLeadOrderByCallDateTimeDesc(Lead lead);

    List<CallLog> findByCallerOrderByCallDateTimeDesc(AppUser caller);

    List<CallLog> findByCallDateTimeBetweenOrderByCallDateTimeDesc(LocalDateTime start, LocalDateTime end);

    List<CallLog> findByCallOutcomeOrderByCallDateTimeDesc(CallOutcome callOutcome);

    long countByCallDateTimeBetween(LocalDateTime start, LocalDateTime end);

    List<CallLog> findAllByOrderByCallDateTimeDesc();
}
