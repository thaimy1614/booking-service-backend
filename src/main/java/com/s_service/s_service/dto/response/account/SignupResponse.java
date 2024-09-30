package com.s_service.s_service.dto.response.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponse {
    private boolean success;
}
