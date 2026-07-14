package com.itsmidris.edulead_crm.user.service;

import com.itsmidris.edulead_crm.user.dto.request.CreateUserRequest;
import com.itsmidris.edulead_crm.user.dto.response.UserResponse;
import com.itsmidris.edulead_crm.user.dto.response.UserSummaryResponse;

import java.util.List;

public interface AppUserService {

    UserResponse createUser(CreateUserRequest request);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    List<UserSummaryResponse> getUserSummaries();
    void disableUser(Long id);
}
