package com.zipbom.zipbom.Global.exception;

public class NoticeEntityNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public NoticeEntityNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

