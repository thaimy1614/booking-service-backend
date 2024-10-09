package com.s_service.s_service.service.authentication;

import com.nimbusds.jose.JOSEException;
import com.s_service.s_service.dto.request.account.ChangePasswordRequest;
import com.s_service.s_service.dto.request.account.LoginRequest;
import com.s_service.s_service.dto.request.account.SendOTPRequest;
import com.s_service.s_service.dto.request.account.SignupRequest;
import com.s_service.s_service.dto.response.account.LoginResponse;
import com.s_service.s_service.dto.response.account.SignupResponse;

public interface AuthenticationService {
    LoginResponse authenticate(LoginRequest request) throws JOSEException;

    boolean introspect(String token);

    void logout(String token) throws Exception;

    LoginResponse outboundAuthenticate(String code) throws JOSEException;

    SignupResponse signup(SignupRequest request);

    void changePassword(ChangePasswordRequest request);

    void sendOTPForForgetPassword(SendOTPRequest request);

    void checkOTP(String otp, String email);

    void verifyAccount(String email, String token);

    void deleteAccount(String userId);
}
