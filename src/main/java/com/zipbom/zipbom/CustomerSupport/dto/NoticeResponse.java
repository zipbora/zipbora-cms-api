package com.zipbom.zipbom.CustomerSupport.dto;

import com.zipbom.zipbom.CustomerSupport.model.Notice;

public class NoticeResponse {
    private Long id;

    private String title;

    protected NoticeResponse() {
    }

    public NoticeResponse(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
