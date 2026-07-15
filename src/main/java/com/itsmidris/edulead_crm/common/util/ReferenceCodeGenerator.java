package com.itsmidris.edulead_crm.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.itsmidris.edulead_crm.lead.entity.Lead;
import com.itsmidris.edulead_crm.lead.repository.LeadRepository;

@Component
public class ReferenceCodeGenerator {

    private static final String LEAD_PREFIX = "LD";

    private final LeadRepository leadRepository;

    public ReferenceCodeGenerator(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
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

}