package com.zipbom.zipbom.InterestedRoom.service;

import com.zipbom.zipbom.Auth.model.PrincipalDetails;
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

@Service
public class InterestedRoomService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InterestedRoomRepository InterestedRoomRepository;

    public CMRespDto<?> addInterestedRoom(PrincipalDetails principalDetails, AddInterestedRoomRequestDto addInterestedRoomRequestDto) {
        User user = userRepository.findByUserId(principalDetails.getUserId()).get();

        Product product = productRepository.findByProductId(addInterestedRoomRequestDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException());
        InterestedRoom interestedRoom = new InterestedRoom(product, user);
        user.addInterestedRoom(interestedRoom);
        return new CMRespDto<>(200, "add success", null);
    }
}
