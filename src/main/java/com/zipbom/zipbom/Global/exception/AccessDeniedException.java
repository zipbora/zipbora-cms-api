package com.zipbom.zipbom.Global.exception;

public class AccessDeniedException extends RuntimeException{
    private final ErrorCode errorCode;

    public AccessDeniedException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
