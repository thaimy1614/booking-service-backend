package com.s_service.s_service.dto.response.profile;

import com.s_service.s_service.model.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {
    private String id;

    private String name;

    private String email;

    private String phone;

    private String address;

    private Account.AccountStatus status;
}
