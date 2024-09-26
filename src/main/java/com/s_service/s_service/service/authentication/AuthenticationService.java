package com.s_service.s_service.service.authentication;

import com.nimbusds.jose.JOSEException;
import com.s_service.s_service.dto.request.ChangePasswordRequest;
import com.s_service.s_service.dto.request.LoginRequest;
import com.s_service.s_service.dto.request.SignupRequest;
import com.s_service.s_service.dto.response.LoginResponse;
import com.s_service.s_service.dto.response.SignupResponse;

public interface AuthenticationService {
    LoginResponse authenticate(LoginRequest request) throws JOSEException;

    boolean introspect(String token);

    void logout(String token) throws Exception;

    LoginResponse outboundAuthenticate(String code) throws JOSEException;

    SignupResponse signup(SignupRequest request);

    void changePassword(ChangePasswordRequest request);
}
