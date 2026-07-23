package com.itsmidris.edulead_crm.lead.mapper;

import com.itsmidris.edulead_crm.lead.dto.request.UpdateLeadRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.itsmidris.edulead_crm.lead.dto.request.CreateLeadRequest;
import com.itsmidris.edulead_crm.lead.dto.response.LeadResponse;
import com.itsmidris.edulead_crm.lead.dto.response.LeadSummaryResponse;
import com.itsmidris.edulead_crm.lead.entity.Lead;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LeadMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "referenceCode", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "leadStatus", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "assignedCaller", ignore = true)
    Lead toEntity(CreateLeadRequest request);

    @Mapping(target = "courseName", source = "course.courseName")
    @Mapping(target = "assignedCaller",
            expression = "java(lead.getAssignedCaller() != null ? lead.getAssignedCaller().getFirstName() + \" \" + lead.getAssignedCaller().getLastName() : null)")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    LeadResponse toResponse(Lead lead);

    @Mapping(target = "fullName",
            expression = "java(lead.getFirstName() + \" \" + lead.getLastName())")
    @Mapping(target = "courseName", source = "course.courseName")
    @Mapping(target = "leadStatus", expression = "java(lead.getLeadStatus().name())")
    LeadSummaryResponse toSummaryResponse(Lead lead);


    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "assignedCallerId", source = "assignedCaller.id")
    UpdateLeadRequest toUpdateRequest(Lead lead);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "referenceCode", ignore = true)
    @Mapping(target = "leadStatus", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "assignedCaller", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateEntityFromRequest(UpdateLeadRequest request, @MappingTarget Lead lead);
}