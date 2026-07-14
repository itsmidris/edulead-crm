package com.itsmidris.edulead_crm.user.mapper;

import com.itsmidris.edulead_crm.user.dto.request.UpdateUserRequest;
import com.itsmidris.edulead_crm.user.dto.response.UserSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.itsmidris.edulead_crm.user.dto.request.CreateUserRequest;
import com.itsmidris.edulead_crm.user.dto.response.UserResponse;
import com.itsmidris.edulead_crm.user.entity.AppUser;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    AppUser toEntity(CreateUserRequest request);

    UserResponse toResponse(AppUser appUser);

    @Mapping(target = "fullName",
            expression = "java(appUser.getFirstName() + \" \" + appUser.getLastName())")
    UserSummaryResponse toSummaryResponse(AppUser appUser);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AppUser updateEntity(UpdateUserRequest request);
}
