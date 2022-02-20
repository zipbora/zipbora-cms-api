package com.zipbom.zipbom.CustomerSupport.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.zipbom.zipbom.CustomerSupport.dto.NoticeDetailsResponse;
import com.zipbom.zipbom.CustomerSupport.model.Notice;

@Mapper
public interface NoticeMapper {
	NoticeMapper noticeMapper = Mappers.getMapper(NoticeMapper.class);

	NoticeDetailsResponse noticeToDetailsDto(Notice notice);
}
