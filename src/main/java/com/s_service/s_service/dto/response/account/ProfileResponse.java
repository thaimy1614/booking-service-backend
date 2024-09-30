package com.s_service.s_service.dto.response.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {
    private String name;

    private String email;

    private String phone;

    private String address;
}
