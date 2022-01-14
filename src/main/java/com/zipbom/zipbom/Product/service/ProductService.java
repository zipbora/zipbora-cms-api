package com.zipbom.zipbom.Product.service;

import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.dto.LetRoomRequestDto;
import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public CMRespDto<?> letRoom(PrincipalDetails principalDetails,LetRoomRequestDto letRoomRequestDto) {
        User user = userRepository.findByUserId(principalDetails.getUserId()).get();
        Product product =
                Product.builder().address(letRoomRequestDto.getAddress())
                .availableTime(letRoomRequestDto.getAvailableTime())
                .canPet(letRoomRequestDto.isCanPet())
                .constructionYear(letRoomRequestDto.getConstructionYear())
                .detailAddress(letRoomRequestDto.getDetailAddress())
                .detailExplanation(letRoomRequestDto.getDetailExplanation())
                .address(letRoomRequestDto.getAddress())
                .haveElevator(letRoomRequestDto.isHaveElevator())
                .haveLoan(letRoomRequestDto.isHaveLoan())
                .haveParkingLot(letRoomRequestDto.isHaveParkingLot())
                .isAgent(letRoomRequestDto.isAgent())
                .isLiving(letRoomRequestDto.isLiving())
                .livingFloor(letRoomRequestDto.getLivingFloor())
                .productType(letRoomRequestDto.getProductType())
                .maintenanceFees(letRoomRequestDto.getMaintenanceFees())
                .moveInDate(letRoomRequestDto.getMoveInDate())
                .numberOfBathrooms(letRoomRequestDto.getNumberOfBathrooms())
                .numberOfRooms(letRoomRequestDto.getNumberOfRooms())
                .size(letRoomRequestDto.getSize())
                .user(user)
                .tradeType(letRoomRequestDto.getTradeType())
                .totalNumberOfFloor(letRoomRequestDto.getTotalNumberOfFloor())
                .build();
        productRepository.save(product);
        return new CMRespDto<>(200, "let room success", null);
    }
}
