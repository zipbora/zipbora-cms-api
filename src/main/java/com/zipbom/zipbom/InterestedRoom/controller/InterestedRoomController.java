package com.zipbom.zipbom.InterestedRoom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zipbom.zipbom.Auth.jwt.JwtAuthorityChecker;
import com.zipbom.zipbom.InterestedRoom.dto.InterestedRoomRequestDto;
import com.zipbom.zipbom.InterestedRoom.service.InterestedRoomService;
import com.zipbom.zipbom.Util.response.CMRespDto;
import com.zipbom.zipbom.Util.response.SuccessResponseDto;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/interestedRoom")
public class InterestedRoomController {

	@Autowired
	private InterestedRoomService interestedRoomService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping
	@ApiOperation(value = "관심있는 방 추가")
	@JwtAuthorityChecker
	public CMRespDto<?> addInterestedRood(HttpServletRequest httpServletRequest,
		@RequestBody InterestedRoomRequestDto interestedRoomRequestDto) {
		return interestedRoomService.addInterestedRoom(httpServletRequest, interestedRoomRequestDto);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@DeleteMapping
	@ApiOperation(value = "관심있는 방 제거")
	@JwtAuthorityChecker
	public SuccessResponseDto<?> deleteInterestedRood(HttpServletRequest httpServletRequest,
		@RequestBody InterestedRoomRequestDto interestedRoomRequestDto) {
		return interestedRoomService.deleteInterestedRoom(httpServletRequest, interestedRoomRequestDto);
	}
}
