package com.s_service.s_service.mapper;

import com.s_service.s_service.dto.response.service.ServiceResponse;
import com.s_service.s_service.model.Service;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceResponse toServiceResponse(Service service);
}
