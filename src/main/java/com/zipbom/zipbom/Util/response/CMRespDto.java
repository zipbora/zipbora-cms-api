package com.zipbom.zipbom.Util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CMRespDto<T> {
    private int code;
    private String message;
    private T data;
}

