package com.zipbom.zipbom.Global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum ErrorCode {
    HANDLE_ACCESS_DENIED(403, "C0001", "Access denied"),
    LOGIN_AUTHORITY_NULL(400, "C0002", "Authority is null");

    private final int status;
    private final String code;
    private final String message;
}
