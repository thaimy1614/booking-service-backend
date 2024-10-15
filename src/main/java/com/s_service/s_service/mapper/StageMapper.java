package com.s_service.s_service.mapper;

import com.s_service.s_service.dto.response.stage.StageResponse;
import com.s_service.s_service.model.Stage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StageMapper {
    StageResponse toStageResponse(Stage stage);
}
