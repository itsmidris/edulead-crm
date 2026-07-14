package com.itsmidris.edulead_crm.user.dto.response;

import com.itsmidris.edulead_crm.common.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private Boolean enabled;
}
