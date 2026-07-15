package com.itsmidris.edulead_crm.lead.repository;

import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    Optional<Lead> findByReferenceCode(String referenceCode);

    Optional<Lead> findByStudentPhone(String studentPhone);

    boolean existsByStudentPhone(String studentPhone);

    List<Lead> findByAssignedCaller(AppUser assignedCaller);

    List<Lead> findByActiveTrue();

}
