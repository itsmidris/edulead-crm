package com.itsmidris.edulead_crm.calllog.repository;

import com.itsmidris.edulead_crm.calllog.entity.CallLog;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallLogRepository extends JpaRepository<CallLog, Long> {
    List<CallLog> findByLeadOrderByCallDateTimeDesc(Lead lead);
}
