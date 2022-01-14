package com.zipbom.zipbom.Product.controller;

import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.dto.LetRoomRequestDto;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.Product.service.ProductService;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @PostMapping("/let/room")
    public CMRespDto<?> letRoom(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody LetRoomRequestDto letRoomRequestDto) {
        return productService.letRoom(principalDetails, letRoomRequestDto);
    }
}
