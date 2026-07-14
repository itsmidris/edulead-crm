package com.itsmidris.edulead_crm.user.service.impl;


import com.itsmidris.edulead_crm.user.mapper.AppUserMapper;
import com.itsmidris.edulead_crm.user.repository.AppUserRepository;
import com.itsmidris.edulead_crm.user.service.AppUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

}
