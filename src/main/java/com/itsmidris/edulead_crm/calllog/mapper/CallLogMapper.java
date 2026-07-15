package com.itsmidris.edulead_crm.calllog.mapper;

import com.itsmidris.edulead_crm.calllog.dto.request.CreateCallLogRequest;
import com.itsmidris.edulead_crm.calllog.dto.response.CallLogResponse;
import com.itsmidris.edulead_crm.calllog.entity.CallLog;
import org.aspectj.weaver.ast.Call;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.lang.foreign.StructLayout;

@Mapper(componentModel = "spring")
public interface CallLogMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "lead", ignore = true)
    @Mapping(target = "caller", ignore = true)
    @Mapping(target = "callDateTime", ignore = true)
    CallLog toEntity(CreateCallLogRequest request);

    @Mapping(target = "leadReference",
            source = "lead.referenceCode")

    @Mapping(target = "studentName",
            expression = "java(callLog.getLead().getFirstName()+\" \"+callLog.getLead().getLastName())")

    @Mapping(target = "callerName",
            expression = "java(callLog.getCaller().getFirstName()+\" \"+callLog.getCaller().getLastName())")
    CallLogResponse toResponse(CallLog callLog);

}
