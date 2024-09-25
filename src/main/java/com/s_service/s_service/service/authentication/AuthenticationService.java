package com.s_service.s_service.service.authentication;

import com.nimbusds.jose.JOSEException;
import com.s_service.s_service.dto.response.LoginResponse;
import com.s_service.s_service.model.Account;

public interface AuthenticationService {
    LoginResponse authenticate(Account account) throws JOSEException;

    boolean introspect(String token);

    void logout(String token) throws Exception;
}
