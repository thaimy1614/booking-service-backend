package com.s_service.s_service.dto.response.stage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StageResponse {
    private int id;
    private String name;
}
