package com.s_service.s_service.controller;


import com.nimbusds.jose.JOSEException;
import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.request.*;
import com.s_service.s_service.dto.response.LoginResponse;
import com.s_service.s_service.dto.response.SignupResponse;
import com.s_service.s_service.service.authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("${application.api.prefix}/identity")
@Slf4j
public class AuthenticationController {
    private final AuthenticationService accountService;

    @PostMapping("/outbound/authentication")
    ApiResponse<LoginResponse> outboundAuthenticate(
            @RequestParam("code") String code
    ) throws JOSEException {
        var result = accountService.outboundAuthenticate(code);
        return ApiResponse.<LoginResponse>builder().message("Login with google successfully!").result(result).build();
    }

    //Insert new User with POST method
    @PostMapping("/signup")
    ApiResponse<SignupResponse> signup(@RequestBody SignupRequest request) {
        SignupResponse response = accountService.signup(request);
        return ApiResponse.<SignupResponse>builder()
                .message("Signup successfully, please go to email to verify your email address.")
                .result(response)
                .build();
    }


    @PostMapping("/auth")
    ApiResponse<LoginResponse> authenticate(@RequestBody LoginRequest request) throws Exception {
        var auth = accountService.authenticate(request);
        return ApiResponse.<LoginResponse>builder()
                .message("Login successfully!")
                .result(auth)
                .build();
    }

    @GetMapping("/log-out")
    ApiResponse<Object> logout(@RequestBody LogoutRequest request) throws Exception {
        accountService.logout(request.getToken());
        return ApiResponse.builder().message("Logout successfully!").result(true).build();
    }

//    @GetMapping("/refresh")
//    ResponseEntity<ResponseObject> refreshToken(@RequestBody RefreshTokenRequest request) throws Exception {
//        String token = authService.refreshToken(request);
//        return ResponseEntity.ok(new ResponseObject("OK", "Refresh token successful!", token));
//    }

    @PostMapping("/change-password")
    ApiResponse<Object> changePassword(@RequestBody ChangePasswordRequest request) {
        accountService.changePassword(request);
        return ApiResponse.builder().message("Password changed successfully!").result(true).build();
    }

    @PostMapping("/forget-password/send-otp")
    ApiResponse<Object> sendOtp(@RequestBody SendOTPRequest request) {
        accountService.sendOTPForForgetPassword(request);
        return ApiResponse.builder()
                .message("OTP sent successfully!")
                .result(true)
                .build();
    }

    @PostMapping("/forget-password/check-otp")
    ApiResponse<Object> checkOtp(@RequestBody CheckOTPRequest request) {
        accountService.checkOTP(request.getOtp(), request.getEmail());
        return ApiResponse.builder()
                .message("OTP check successfully!")
                .result(true)
                .build();
    }

    @GetMapping("/verify")
    public void verifyAccount(
            HttpServletResponse response,
            @RequestParam("email") String email,
            @RequestParam("code") String token
    ) throws IOException {
        accountService.verifyAccount(email, token);
        response.sendRedirect("http://localhost:3000/login");
    }
}
