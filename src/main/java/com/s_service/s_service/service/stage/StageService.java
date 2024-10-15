package com.s_service.s_service.service.stage;

import com.s_service.s_service.dto.response.stage.StageResponse;

import java.util.List;

public interface StageService {
    List<StageResponse> getAllStages();
}
