package com.zipbom.zipbom.InterestedRoom.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.InterestedRoom.dto.InterestedRoomRequestDto;
import com.zipbom.zipbom.InterestedRoom.dto.InterestedRoomResponseDto;
import com.zipbom.zipbom.InterestedRoom.model.InterestedRoom;
import com.zipbom.zipbom.InterestedRoom.repository.InterestedRoomRepository;
import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.Util.response.CMRespDto;
import com.zipbom.zipbom.Util.response.SuccessResponseDto;

@Service
public class InterestedRoomService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private InterestedRoomRepository interestedRoomRepository;
	@Autowired
	private JwtServiceImpl jwtService;

	@Transactional
	public CMRespDto<?> addInterestedRoom(HttpServletRequest httpServletRequest,
		InterestedRoomRequestDto interestedRoomRequestDto) {

		User user = userRepository.findByUserId(jwtService.getUserId(httpServletRequest.getHeader("jwt-auth-token")))
			.orElseThrow(EntityNotFoundException::new);

		Product product = productRepository.findByProductId(interestedRoomRequestDto.getProductId())
			.orElseThrow(EntityNotFoundException::new);

		InterestedRoom interestedRoom = new InterestedRoom(product, user);
		user.addInterestedRoom(interestedRoom);
		return new CMRespDto<>(200, "add success", null);
	}

	public SuccessResponseDto<?> deleteInterestedRoom(HttpServletRequest httpServletRequest,
		InterestedRoomRequestDto interestedRoomRequestDto) {
		User user = userRepository.findByUserId(jwtService.getUserId(httpServletRequest.getHeader("jwt-auth-token")))
			.orElseThrow(IllegalArgumentException::new);

		user.getUserInterestedRooms().deleteInterestedRooms(interestedRoomRequestDto.getProductId(), user);
		interestedRoomRepository.deleteById(interestedRoomRequestDto.getProductId());
		return new SuccessResponseDto(true, null);
	}

	public SuccessResponseDto<?> getInterestedRooms(HttpServletRequest httpServletRequest) {
		User user = userRepository.findByUserId(jwtService.getUserId(httpServletRequest.getHeader("jwt-auth-token")))
			.orElseThrow(IllegalArgumentException::new);
		List<InterestedRoom> interestedRooms = interestedRoomRepository.findAllByUser(user);
		List<InterestedRoomResponseDto> interestedRoomResponseDtos = interestedRooms.stream().map(interestedRoom ->
			new InterestedRoomResponseDto(interestedRoom.getId())
		).collect(Collectors.toList());
		return new SuccessResponseDto(true, interestedRoomResponseDtos);
	}
}
