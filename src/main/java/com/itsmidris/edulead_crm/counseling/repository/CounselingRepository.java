package com.itsmidris.edulead_crm.counseling.repository;

import com.itsmidris.edulead_crm.common.enums.CounselingStatus;
import com.itsmidris.edulead_crm.counseling.entity.Counseling;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {

    Optional<Counseling> findTopByReferenceCodeStartingWithOrderByReferenceCodeDesc(String prefix);

    List<Counseling> findByLeadOrderByScheduledDateTimeDesc(Lead lead);

    List<Counseling> findByAssignedCounselorOrderByScheduledDateTimeAsc(AppUser assignedCounselor);

    List<Counseling> findByCounselingStatusOrderByScheduledDateTimeAsc(CounselingStatus counselingStatus);

    List<Counseling> findByScheduledDateTimeBetweenOrderByScheduledDateTimeAsc(LocalDateTime start, LocalDateTime end);

    boolean existsByLeadAndCounselingStatusIn(Lead lead, List<CounselingStatus> statuses);

    List<Counseling> findByActiveTrueOrderByScheduledDateTimeAsc();

}
