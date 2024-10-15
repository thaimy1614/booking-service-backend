package com.s_service.s_service.controller;

import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.response.stage.StageResponse;
import com.s_service.s_service.repository.StageRepository;
import com.s_service.s_service.service.stage.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.api.prefix}/admin/stage")
public class StageController {
    private final StageService stageService;

    @GetMapping
    ApiResponse<List<StageResponse>> getAll(){
        List<StageResponse> response = stageService.getAllStages();
        return ApiResponse.<List<StageResponse>>builder()
                .result(response)
                .build();
    }


}
