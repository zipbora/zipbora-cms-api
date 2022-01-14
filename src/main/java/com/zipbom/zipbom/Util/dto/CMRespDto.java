package com.zipbom.zipbom.Util.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CMRespDto<T> {
    private int code;
    private String message;
    private T data;
}

