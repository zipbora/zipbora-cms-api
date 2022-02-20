package com.zipbom.zipbom.Product.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zipbom.zipbom.Auth.jwt.JwtAuthorityChecker;
import com.zipbom.zipbom.Auth.jwt.UserAuthority;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.dto.LetRoomRequestDto;
import com.zipbom.zipbom.Product.dto.ProductFilterRequest;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.Product.service.ProductService;
import com.zipbom.zipbom.Util.response.SuccessResponseDto;

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
	public SuccessResponseDto<?> createProduct(HttpServletRequest httpServletRequest,
		@ModelAttribute LetRoomRequestDto letRoomRequestDto) throws IOException {
		return new SuccessResponseDto<>(true, productService.letRoom(httpServletRequest, letRoomRequestDto));
	}

	@GetMapping
	public SuccessResponseDto<?> getProducts(@ModelAttribute ProductFilterRequest productFilterRequest) {
		return new SuccessResponseDto<>(true, productService.getProducts(productFilterRequest));
	}

	@DeleteMapping("/{id}")
	public SuccessResponseDto<?> deleteProduct(@PathVariable("id") Long id) {
		productRepository.deleteById(id);
		return new SuccessResponseDto<>(true, null);
	}
}
