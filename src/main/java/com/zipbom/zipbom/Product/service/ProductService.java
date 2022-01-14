package com.zipbom.zipbom.Product.service;

import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.dto.LetRoomRequestDto;
import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Product.model.ProductImage;
import com.zipbom.zipbom.Product.model.ProductImages;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public CMRespDto<?> letRoom(PrincipalDetails principalDetails, LetRoomRequestDto letRoomRequestDto) {
        User user = userRepository.findByProviderId(principalDetails.getUserId()).get();
        letRoomRequestDto.getProductImageList().stream().forEach(file -> {
            try {
                ProductImage.FileWrite(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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
                        .productImages(new ProductImages(letRoomRequestDto.getProductImageList().stream()
                                .map(file -> {
                                    try {
                                        return ProductImage.of(file);
                                    } catch (IOException e) {
                                    }
                                    return null;
                                }).collect(Collectors.toList())))
                        .build();
        productRepository.save(product);
        return new CMRespDto<>(200, "let room success", null);
    }
}
