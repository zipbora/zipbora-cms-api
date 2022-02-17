package com.zipbom.zipbom.Product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipbom.zipbom.Auth.dto.JwtGetUserInfoResponseDto;
import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.dto.LetRoomRequestDto;
import com.zipbom.zipbom.Product.dto.ProductResponse;
import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Product.model.ProductImage;
import com.zipbom.zipbom.Product.model.ProductImages;
import com.zipbom.zipbom.Product.repository.ProductImageRepository;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private JwtServiceImpl jwtService;

    @Transactional
    public CMRespDto<?> letRoom(HttpServletRequest httpServletRequest, LetRoomRequestDto letRoomRequestDto)
            throws IOException {

        User user = userRepository.findByUserId(jwtService.getUserId(httpServletRequest.getHeader("jwt-auth-token")))
                .orElseThrow(IllegalArgumentException::new);

        Product product =
                Product.builder().address(letRoomRequestDto.getAddress())
                        .availableTime(letRoomRequestDto.getAvailableTime())
                        .canPet(letRoomRequestDto.isCanPet())
                        .constructionYear(letRoomRequestDto.getConstructionYear())
                        .detailAddress(letRoomRequestDto.getDetailAddress())
                        .detailExplanation(letRoomRequestDto.getDetailExplanation())
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
                        .productImages(new ProductImages())
                        .build();
        if (letRoomRequestDto.getProductImageList() != null) {
            for (MultipartFile multipartFile : letRoomRequestDto.getProductImageList()) {
                ProductImage productImage = ProductImage.of(multipartFile);
                product.addProduct(productImage);
                productImage.fileWrite(multipartFile);
            }
        }
        productRepository.save(product);

        return new CMRespDto<>(200, "let room success", null);
    }

    public CMRespDto<?> getProducts() {
        List<Product> products = productRepository.findAll();
        return new CMRespDto<>(200, "get all products", products.stream()
                .map(product -> new ProductResponse(product))
                .collect(Collectors.toList()));
    }
}
