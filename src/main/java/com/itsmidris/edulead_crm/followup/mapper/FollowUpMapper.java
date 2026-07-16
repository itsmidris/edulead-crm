package com.itsmidris.edulead_crm.followup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.itsmidris.edulead_crm.followup.dto.request.CreateFollowUpRequest;
import com.itsmidris.edulead_crm.followup.dto.response.FollowUpResponse;
import com.itsmidris.edulead_crm.followup.entity.FollowUp;

@Mapper(componentModel = "spring")
public interface FollowUpMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lead", ignore = true)
    @Mapping(target = "caller", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    FollowUp toEntity(CreateFollowUpRequest request);

    @Mapping(target = "leadReference", source = "lead.referenceCode")
    @Mapping(target = "studentName", expression = "java(followUp.getLead().getFirstName() + \" \" + followUp.getLead().getLastName())")
    @Mapping(target = "callerName", expression = "java(followUp.getCaller().getFirstName() + \" \" + followUp.getCaller().getLastName())")
    FollowUpResponse toResponse(FollowUp followUp);

}