package com.zipbom.zipbom.Global.exception;

import com.zipbom.zipbom.Global.exception.error.ErrorCode;

public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
