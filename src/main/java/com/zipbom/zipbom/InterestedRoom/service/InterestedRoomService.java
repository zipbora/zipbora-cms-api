package com.zipbom.zipbom.InterestedRoom.service;

import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.InterestedRoom.dto.AddInterestedRoomRequestDto;
import com.zipbom.zipbom.InterestedRoom.model.InterestedRoom;
import com.zipbom.zipbom.InterestedRoom.repository.InterestedRoomRepository;
import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Service
public class InterestedRoomService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InterestedRoomRepository InterestedRoomRepository;
    @Autowired
    private JwtServiceImpl jwtService;
    public CMRespDto<?> addInterestedRoom(HttpServletRequest httpServletRequest, AddInterestedRoomRequestDto addInterestedRoomRequestDto) {
        User user = userRepository.findByUserId(
                (String) jwtService.getInfo(httpServletRequest.getHeader("jwt-auth-token")).get("userId"))
                .orElseThrow(IllegalArgumentException::new);

        Product product = productRepository.findByProductId(addInterestedRoomRequestDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException());
        InterestedRoom interestedRoom = new InterestedRoom(product, user);
        user.addInterestedRoom(interestedRoom);
        return new CMRespDto<>(200, "add success", null);
    }
}
