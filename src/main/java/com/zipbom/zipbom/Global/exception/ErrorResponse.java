package com.zipbom.zipbom.Global.exception;

import com.zipbom.zipbom.Global.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private int status;
    private String code;

    public ErrorResponse(final ErrorCode code, final String message) {
        this.message = message;
        this.status = code.getStatus();
        this.code = code.getCode();
    }
}
