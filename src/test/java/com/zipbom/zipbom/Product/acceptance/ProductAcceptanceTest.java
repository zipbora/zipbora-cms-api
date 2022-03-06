package com.zipbom.zipbom.Product.acceptance;

import static com.zipbom.zipbom.Auth.acceptance.AuthAcceptanceMockTest.*;
import static com.zipbom.zipbom.Auth.acceptance.AuthStep.*;
import static com.zipbom.zipbom.Product.unit.ProductStep.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Product.service.ProductService;
import com.zipbom.zipbom.Util.AcceptanceTest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ProductAcceptanceTest extends AcceptanceTest {
	@MockBean
	private KakaoAPI kakaoAPI;
	@Autowired
	ProductService productService;
	@Autowired
	JwtServiceImpl jwtService;
	@Test
	void createProductTest() {
		String jwtToken = JWT_반환();
		String updatedJwtToken = 회원가입_후_JWT_반환(jwtToken);

		ExtractableResponse<Response> response = 방_내놓기(updatedJwtToken, createProduct());
		assertThat(response.jsonPath().getBoolean("success")).isEqualTo(true);
	}

	//TODO 한글이 깨진다
	@Test
	void getProductsTest() {
		String jwtToken = JWT_반환();
		String updatedJwtToken = 회원가입_후_JWT_반환(jwtToken);

		방_내놓기(updatedJwtToken, createProduct());

		List<Product> products = 내_방_보기(updatedJwtToken);

		assertNotNull(products.get(0));
	}

	private String JWT_반환() {
		HashMap<String, Object> userInfo = new HashMap<>();
		userInfo.put("providerId", "111");
		userInfo.put("nickname", "mj");
		userInfo.put("email", "minjoon1995@naver.com");

		when(kakaoAPI.getUserInfo(anyString())).thenReturn(userInfo);

		Map<String, String> input = new HashMap<>();
		input.put("accessToken", "test2");
		input.put("userAuthority", "ROLE_USER");

		return RestAssured
			.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(input)
			.when().post("/login")
			.then().log().all().extract().jsonPath().getString("data.jwtToken");
	}

	private String 회원가입_후_JWT_반환(String jwtToken) {
		HashMap<String, String> params = new HashMap<>();
		params.put("email", "test");
		params.put("nickname", "test");
		params.put("id", jwtService.getUserId(jwtToken));
		return 회원가입(jwtToken, params).jsonPath().getString("data.jwtToken");
	}
}
