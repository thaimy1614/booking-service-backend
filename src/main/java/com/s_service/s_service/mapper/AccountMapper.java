package com.s_service.s_service.mapper;

import com.s_service.s_service.dto.request.SignupRequest;
import com.s_service.s_service.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(SignupRequest request);
}
