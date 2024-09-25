package com.s_service.s_service.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppException extends RuntimeException{

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;

}