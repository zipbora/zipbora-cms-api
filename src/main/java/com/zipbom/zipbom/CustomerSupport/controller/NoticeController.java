package com.zipbom.zipbom.CustomerSupport.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipbom.zipbom.CustomerSupport.dto.NoticeDetailsResponse;
import com.zipbom.zipbom.CustomerSupport.dto.NoticeRequest;
import com.zipbom.zipbom.CustomerSupport.dto.NoticeResponse;
import com.zipbom.zipbom.CustomerSupport.mapstruct.NoticeMapper;
import com.zipbom.zipbom.CustomerSupport.model.Notice;
import com.zipbom.zipbom.CustomerSupport.repository.NoticeRepository;
import com.zipbom.zipbom.CustomerSupport.service.NoticeService;
import com.zipbom.zipbom.Global.exception.ErrorCode;
import com.zipbom.zipbom.Global.exception.NoticeEntityNotFoundException;
import com.zipbom.zipbom.Util.response.SuccessResponseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;
    private final NoticeRepository noticeRepository;
    private final ObjectMapper objectMapper;

    public NoticeController(NoticeService noticeService, NoticeRepository noticeRepository
            , ObjectMapper objectMapper) {
        this.noticeService = noticeService;
        this.noticeRepository = noticeRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    @ApiOperation(value = "공지사항 아이디, 제목 리스트 반환")
    public SuccessResponseDto<?> getNotices() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeResponse> noticeResponses = notices.stream()
                .map(notice -> new NoticeResponse(notice))
                .collect(Collectors.toList());
        return new SuccessResponseDto<>(true, noticeResponses);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "공지사항 자세한 내용 반환")
    public SuccessResponseDto<?> getNoticeDetails(@PathVariable("id") Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeEntityNotFoundException(ErrorCode.NOTICE_NOT_FOUND));
        NoticeDetailsResponse noticeDetailsResponse = NoticeMapper.noticeMapper.noticeToDetailsDto(notice);
        return new SuccessResponseDto<>(true, noticeDetailsResponse);
    }

    @PostMapping
    @ApiOperation(value = "공지사항 생성")
    public ResponseEntity<NoticeResponse> createNotice(@RequestBody NoticeRequest noticeRequest) {
        NoticeResponse noticeResponse = noticeService.createNotice(noticeRequest);
        return ResponseEntity.created(URI.create("/notice/" + noticeResponse.getId())).body(noticeResponse);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "공지사항 제거")
    public SuccessResponseDto<?> deleteNotice(@PathVariable("id") Long id) {
        noticeRepository.deleteById(id);
        return new SuccessResponseDto<>(true, null);
    }
}
