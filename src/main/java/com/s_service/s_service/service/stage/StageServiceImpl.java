package com.s_service.s_service.service.stage;

import com.s_service.s_service.dto.response.stage.StageResponse;
import com.s_service.s_service.mapper.StageMapper;
import com.s_service.s_service.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StageServiceImpl implements StageService{
    private final StageRepository stageRepository;
    private final StageMapper stageMapper;

    @Override
    public List<StageResponse> getAllStages() {
        return stageRepository.findAll().stream().map(stageMapper::toStageResponse).toList();
    }
}
