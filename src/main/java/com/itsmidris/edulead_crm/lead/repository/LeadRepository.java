package com.itsmidris.edulead_crm.lead.repository;

import com.itsmidris.edulead_crm.common.enums.LeadStatus;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    Optional<Lead> findByReferenceCode(String referenceCode);

    Optional<Lead> findByStudentPhone(String studentPhone);

    boolean existsByStudentPhone(String studentPhone);

    List<Lead> findByAssignedCaller(AppUser assignedCaller);

    List<Lead> findByActiveTrue();

    Optional<Lead> findTopByReferenceCodeStartingWithOrderByReferenceCodeDesc(String prefix);

    long countByActiveTrue();

    long countByLeadStatus(LeadStatus leadStatus);

    long countByLeadStatusAndActiveTrue(LeadStatus leadStatus);

    @Query("""
        SELECT l
        FROM Lead l
        WHERE l.active = true
        AND (
            :keyword IS NULL
            OR :keyword = ''
            OR LOWER(l.referenceCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(l.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(l.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(CONCAT(l.firstName, ' ', l.lastName)) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(l.studentPhone) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(COALESCE(l.parentPhone, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(COALESCE(l.email, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))
        )
        AND (
            :status IS NULL
            OR l.leadStatus = :status
        )
        ORDER BY l.createdAt DESC
        """)
    List<Lead> searchActiveLeads(
            @Param("keyword") String keyword,
            @Param("status") LeadStatus status);
}
