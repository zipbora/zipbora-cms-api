package com.zipbom.zipbom.Product.acceptance;

import static com.zipbom.zipbom.Product.unit.ProductStep.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Global.interceptor.RestInterceptor;
import com.zipbom.zipbom.Product.dto.LetRoomRequestDto;
import com.zipbom.zipbom.Product.model.ProductType;
import com.zipbom.zipbom.Product.model.TradeType;
import com.zipbom.zipbom.Util.AcceptanceTest;

import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.internal.multipart.MultiPartSpecificationImpl;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;

public class ProductTest extends AcceptanceTest {
	@MockBean
	private RestInterceptor restInterceptor;
	@MockBean
	private KakaoAPI kakaoAPI;

	@Test
	void createProductTest() throws Exception {
		String jwtToken = loginTest().jsonPath().getString("data.jwtToken");

		HashMap<String, String> userInfo = new HashMap<>();
		userInfo.put("address", "서울 특별시");
		userInfo.put("detailAddress", "목동남로2길 60-7");
		userInfo.put("haveLoan", "true");
		userInfo.put("productType", "APARTMENT");

		ExtractableResponse<Response> response = 방_내놓기(jwtToken,userInfo);
		assertThat(response.jsonPath().getBoolean("success")).isEqualTo(true);
	}

	private ExtractableResponse<Response> loginTest() throws Exception {
		HashMap<String, Object> userInfo = new HashMap<>();
		userInfo.put("providerId", "111");
		userInfo.put("nickname", "mj");
		userInfo.put("email", "minjoon1995@naver.com");

		when(restInterceptor.preHandle(anyObject(), anyObject(), anyObject())).thenReturn(true);
		when(kakaoAPI.getUserInfo(anyString())).thenReturn(userInfo);

		Map<String, String> input = new HashMap<>();
		input.put("accessToken", "test2");
		input.put("userAuthority", "ROLE_USER");

		ObjectMapper objectMapper = new ObjectMapper();

		return RestAssured
			.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(input)
			.when().post("/login")
			.then().log().all().extract();
	}
}
