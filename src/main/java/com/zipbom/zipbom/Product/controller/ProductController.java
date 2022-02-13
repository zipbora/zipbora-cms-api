package com.zipbom.zipbom.Product.controller;

import com.zipbom.zipbom.Auth.jwt.JwtAuthorityChecker;
import com.zipbom.zipbom.Auth.jwt.UserAuthority;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.dto.LetRoomRequestDto;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.Product.service.ProductService;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping
    @JwtAuthorityChecker(authority = UserAuthority.ROLE_USER)
    public CMRespDto<?> createProduct(HttpServletRequest httpServletRequest, @ModelAttribute LetRoomRequestDto letRoomRequestDto) throws IOException {
        return productService.letRoom(httpServletRequest, letRoomRequestDto);
    }

    @GetMapping
    public CMRespDto<?> getProducts(@PathVariable float latitude, @PathVariable float longitude) {
        return productService.getProducts();
    }
}
