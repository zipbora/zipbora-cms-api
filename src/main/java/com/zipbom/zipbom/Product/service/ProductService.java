package com.zipbom.zipbom.Product.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.dto.LetRoomRequestDto;
import com.zipbom.zipbom.Product.dto.ProductFilterRequest;
import com.zipbom.zipbom.Product.dto.ProductResponse;
import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Product.model.ProductImage;
import com.zipbom.zipbom.Product.model.ProductImages;
import com.zipbom.zipbom.Product.model.QProduct;
import com.zipbom.zipbom.Product.repository.ProductImageRepository;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.Util.response.CMRespDto;
import com.zipbom.zipbom.Util.response.SuccessResponseDto;

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
	@Autowired
	EntityManager em;

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
				.longitude(letRoomRequestDto.getLongitude())
				.latitude(letRoomRequestDto.getLatitude())
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

	public SuccessResponseDto<?> getProducts(ProductFilterRequest productFilterRequest) {

		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		QProduct p = QProduct.product;

		List<Product> products = jpaQueryFactory
			.selectFrom(p)
			.where(p.latitude.between(productFilterRequest.getLowerLatitude()
				, productFilterRequest.getUpperLatitude()))
			.where(p.latitude.between(productFilterRequest.getLowerLongitude()
				, productFilterRequest.getUpperLongitude()))
			.fetch();

		return new SuccessResponseDto<>(true,  products.stream()
			.map(product -> new ProductResponse(product))
			.collect(Collectors.toList()));
	}
}
