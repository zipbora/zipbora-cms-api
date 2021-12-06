package com.zipbom.zipbom.Auth.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CMRespDto<T> {
    private int code;
    private String message;
    private T data;
}

