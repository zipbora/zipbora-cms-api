package com.zipbom.zipbom.CustomerSupport.controller;

import com.zipbom.zipbom.CustomerSupport.dto.NoticeResponse;
import com.zipbom.zipbom.CustomerSupport.dto.NoticeRequest;
import com.zipbom.zipbom.CustomerSupport.model.Notice;
import com.zipbom.zipbom.CustomerSupport.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

//    public NoticeController(NoticeService noticeService) {
//        this.noticeService = noticeService;
//    }

    @PostMapping
    public ResponseEntity<NoticeResponse> createNotice(@RequestBody  NoticeRequest noticeRequest) {
        NoticeResponse noticeResponse = noticeService.createNotice(noticeRequest);
        return ResponseEntity.created(URI.create("/notice/"+noticeResponse.getId())).body(noticeResponse);
    }
}
