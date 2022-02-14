package com.zipbom.zipbom.CustomerSupport.model;

import javax.persistence.*;

@Entity
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Lob
    private String content;

    private String imageEncoding;

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getImageEncoding() {
        return imageEncoding;
    }
}
