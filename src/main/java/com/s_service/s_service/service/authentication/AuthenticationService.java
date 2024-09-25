package com.s_service.s_service.service.authentication;

import com.nimbusds.jose.JOSEException;
import com.s_service.s_service.dto.request.LoginRequest;
import com.s_service.s_service.dto.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse authenticate(LoginRequest request) throws JOSEException;

    boolean introspect(String token);

    void logout(String token) throws Exception;
}
