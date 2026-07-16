package com.itsmidris.edulead_crm.counseling.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.itsmidris.edulead_crm.counseling.dto.request.CreateCounselingRequest;
import com.itsmidris.edulead_crm.counseling.dto.response.CounselingResponse;
import com.itsmidris.edulead_crm.counseling.entity.Counseling;

@Mapper(componentModel = "spring")
public interface CounselingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "referenceCode", ignore = true)
    @Mapping(target = "lead", ignore = true)
    @Mapping(target = "requestedBy", ignore = true)
    @Mapping(target = "assignedCounselor", ignore = true)
    @Mapping(target = "counselingStatus", ignore = true)
    @Mapping(target = "counselingOutcome", ignore = true)
    @Mapping(target = "completedDateTime", ignore = true)
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Counseling toEntity(CreateCounselingRequest request);

    @Mapping(target = "leadReference", source = "lead.referenceCode")

    @Mapping(target = "studentName", expression = "java(counseling.getLead().getFirstName() + \" \" + counseling.getLead().getLastName())")

    @Mapping(target = "requestedById", source = "requestedBy.id")

    @Mapping(target = "requestedByName", expression = "java(counseling.getRequestedBy().getFirstName() + \" \" + counseling.getRequestedBy().getLastName())")

    @Mapping(target = "requestedByPhone", source = "requestedBy.phoneNumber")

    @Mapping(target = "assignedCounselorId", expression = "java(counseling.getAssignedCounselor() != null ? counseling.getAssignedCounselor().getId() : null)")

    @Mapping(target = "assignedCounselorName", expression = "java(counseling.getAssignedCounselor() != null ? counseling.getAssignedCounselor().getFirstName() + \" \" + counseling.getAssignedCounselor().getLastName() : null)")

    CounselingResponse toResponse(Counseling counseling);
}