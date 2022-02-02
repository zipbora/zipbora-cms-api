package com.zipbom.zipbom.Product.controller;

import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.dto.LetRoomRequestDto;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.Product.service.ProductService;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public CMRespDto<?> letRoom(@AuthenticationPrincipal PrincipalDetails principalDetails, @ModelAttribute LetRoomRequestDto letRoomRequestDto) throws IOException {
        return productService.letRoom(principalDetails, letRoomRequestDto);
    }

    @GetMapping("/")
    public CMRespDto<?> rooms() {
        return productService.getProducts();
    }
}
