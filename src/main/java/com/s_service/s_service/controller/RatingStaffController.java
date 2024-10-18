package com.s_service.s_service.controller;

import com.s_service.s_service.service.rating.staff.RatingStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.api.prefix}/rating")
public class RatingStaffController {
    private final RatingStaffService ratingStaffService;
}
