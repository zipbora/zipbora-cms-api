package com.zipbom.zipbom.CustomerSupport.dto;

import com.zipbom.zipbom.CustomerSupport.model.Notice;

public class NoticeResponse {
    private Long id;

    private String title;

    private String content;

    protected NoticeResponse() {
    }

    public NoticeResponse(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }
}
