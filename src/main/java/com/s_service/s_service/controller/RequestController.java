package com.s_service.s_service.controller;

import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.request.mail.CustomerInfo;
import com.s_service.s_service.service.notification.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${application.api.prefix}/request")
@RequiredArgsConstructor
public class RequestController {
    private final EmailService emailService;

    @PostMapping()
    ApiResponse<Boolean> sendContactRequest(@RequestBody CustomerInfo customerInfo){
        emailService.sendContact("", customerInfo);
        return ApiResponse.<Boolean>builder()
                .message("Send request successfully")
                .result(true)
                .build();
    }



}
