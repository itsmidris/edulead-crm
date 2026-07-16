package com.itsmidris.edulead_crm.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.itsmidris.edulead_crm.counseling.entity.Counseling;
import com.itsmidris.edulead_crm.counseling.repository.CounselingRepository;
import org.springframework.stereotype.Component;

import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.lead.repository.LeadRepository;

@Component
public class ReferenceCodeGenerator {

    private static final String LEAD_PREFIX = "LD";
    private static final String CNS_PREFIX = "CNS";

    private final LeadRepository leadRepository;
    private final CounselingRepository counselingRepository;

    public ReferenceCodeGenerator(LeadRepository leadRepository, CounselingRepository counselingRepository) {
        this.leadRepository = leadRepository;
        this.counselingRepository = counselingRepository;
    }

    public String generateLeadReference() {

        String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));

        String prefix = LEAD_PREFIX + "-" + yearMonth + "-";

        Optional<Lead> lastLead = leadRepository.findTopByReferenceCodeStartingWithOrderByReferenceCodeDesc(prefix);

        int nextSerial = 1;

        if (lastLead.isPresent()) {

            String reference = lastLead.get().getReferenceCode();

            String serial = reference.substring(reference.lastIndexOf('-') + 1);

            nextSerial = Integer.parseInt(serial) + 1;
        }

        return prefix + String.format("%06d", nextSerial);

    }

    public String generateCounselingReference() {

        String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));

        String prefix = CNS_PREFIX + "-" + yearMonth + "-";

        Optional<Counseling> lastCounseling = counselingRepository.findTopByReferenceCodeStartingWithOrderByReferenceCodeDesc(prefix);

        int nextSerial = 1;

        if (lastCounseling.isPresent()) {

            String reference = lastCounseling.get().getReferenceCode();

            String serial = reference.substring(reference.lastIndexOf('-') + 1);

            nextSerial = Integer.parseInt(serial) + 1;
        }

        return prefix + String.format("%06d", nextSerial);


    }

}