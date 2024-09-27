package com.s_service.s_service.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class ProfileResponse {
    private String name;

    private String email;

    private String phone;

    private String address;
}
