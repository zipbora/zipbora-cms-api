package com.zipbom.zipbom.CustomerSupport.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeRequest {
    private String title;

    private String content;

    protected NoticeRequest() {
    }
}
