package com.itsmidris.edulead_crm.user.service.impl;


import com.itsmidris.edulead_crm.common.exception.DuplicateResourceException;
import com.itsmidris.edulead_crm.common.exception.ResourceNotFoundException;
import com.itsmidris.edulead_crm.user.dto.request.CreateUserRequest;
import com.itsmidris.edulead_crm.user.dto.response.UserResponse;
import com.itsmidris.edulead_crm.user.dto.response.UserSummaryResponse;
import com.itsmidris.edulead_crm.user.entity.AppUser;
import com.itsmidris.edulead_crm.user.mapper.AppUserMapper;
import com.itsmidris.edulead_crm.user.repository.AppUserRepository;
import com.itsmidris.edulead_crm.user.service.AppUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    public AppUserServiceImpl(
            AppUserRepository appUserRepository,
            AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        if (appUserRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (appUserRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        AppUser appUser = appUserMapper.toEntity(request);

        AppUser savedUser = appUserRepository.save(appUser);

        return appUserMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {

        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));

        return appUserMapper.toResponse(appUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        throw new UnsupportedOperationException("Not Implemented Yet. Contact Developer");
    }

    @Override
    public List<UserSummaryResponse> getUserSummaries() {
        throw new UnsupportedOperationException("Not Implemented Yet. Contact Developer");
    }

    @Override
    public void disableUser(Long id) {
        throw new UnsupportedOperationException("Not Implemented Yet. Contact Developer");
    }
}
