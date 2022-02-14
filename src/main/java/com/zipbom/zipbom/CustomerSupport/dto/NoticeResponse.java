package com.zipbom.zipbom.CustomerSupport.dto;

import com.zipbom.zipbom.CustomerSupport.model.Notice;

public class NoticeResponse {
    private Long id;

    private String title;

    private String content;

    public NoticeResponse(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }
}
