package com.zipbom.zipbom.CustomerSupport.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zipbom.zipbom.CustomerSupport.dto.NoticeRequest;
import com.zipbom.zipbom.CustomerSupport.dto.NoticeResponse;
import com.zipbom.zipbom.CustomerSupport.repository.NoticeRepository;
import com.zipbom.zipbom.CustomerSupport.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;
	private final NoticeRepository noticeRepository;

	public NoticeController(NoticeService noticeService, NoticeRepository noticeRepository) {
		this.noticeService = noticeService;
		this.noticeRepository = noticeRepository;
	}

	@PostMapping
	public ResponseEntity<NoticeResponse> createNotice(@RequestBody NoticeRequest noticeRequest) {
		NoticeResponse noticeResponse = noticeService.createNotice(noticeRequest);
		return ResponseEntity.created(URI.create("/notice/" + noticeResponse.getId())).body(noticeResponse);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<NoticeResponse> deleteNotice(@PathVariable("id") Long id) {
		noticeRepository.deleteById(id);
		return ResponseEntity.notFound().build();
	}
}
