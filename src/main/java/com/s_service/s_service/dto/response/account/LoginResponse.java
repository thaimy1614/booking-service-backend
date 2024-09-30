package com.s_service.s_service.dto.response.account;

import com.s_service.s_service.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String username;
    private Role.UserRole role;
}
