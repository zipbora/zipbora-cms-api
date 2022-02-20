package com.zipbom.zipbom.CustomerSupport.service;

import com.zipbom.zipbom.CustomerSupport.dto.NoticeRequest;
import com.zipbom.zipbom.CustomerSupport.dto.NoticeResponse;
import com.zipbom.zipbom.CustomerSupport.model.Notice;
import com.zipbom.zipbom.CustomerSupport.repository.NoticeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

   private NoticeService(NoticeRepository noticeRepository) {
       this.noticeRepository = noticeRepository;
   }

    @Transactional
    public NoticeResponse createNotice(NoticeRequest noticeRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Notice notice = modelMapper.map(noticeRequest, Notice.class);
        noticeRepository.save(notice);
        return new NoticeResponse(notice);
    }

}
