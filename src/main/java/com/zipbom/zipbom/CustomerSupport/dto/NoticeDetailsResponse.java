package com.zipbom.zipbom.CustomerSupport.dto;

import com.zipbom.zipbom.CustomerSupport.model.Notice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class NoticeDetailsResponse {
	private Long id;

	private String title;

	private String content;

	private String imageEncoding;

	@Override
	public String toString() {
		return "NoticeDetailsResponse{" +
			"id=" + id +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			", imageEncoding='" + imageEncoding + '\'' +
			'}';
	}
}
