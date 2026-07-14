package com.itsmidris.edulead_crm.user.dto.response;

import com.itsmidris.edulead_crm.common.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private String type;
    private Long userId;
    private String fullName;
    private UserRole role;
}
