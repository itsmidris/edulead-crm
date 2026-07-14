package com.itsmidris.edulead_crm.user.repository;

import com.itsmidris.edulead_crm.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

//      AppUser user = repository.findByEmail(email);
//      which could return null, we use:
//      Optional<AppUser> user = repository.findByEmail(email);
//      This forces us to handle the "user not found" case explicitly.
}
