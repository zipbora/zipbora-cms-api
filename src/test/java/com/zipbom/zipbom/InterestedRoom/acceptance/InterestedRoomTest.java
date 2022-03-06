package com.zipbom.zipbom.InterestedRoom.acceptance;

import static com.zipbom.zipbom.Auth.acceptance.AuthAcceptanceMockTest.*;
import static com.zipbom.zipbom.Product.unit.ProductStep.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Product.dto.ProductResponse;
import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Util.AcceptanceTest;

import io.restassured.RestAssured;


public class InterestedRoomTest extends AcceptanceTest {
	@MockBean
	private KakaoAPI kakaoAPI;

	@Test
	/***
	 *
	 */
	void addInterestedRoomTest() {
		String jwtToken = JWT_반환("ROLE_USER");

		방_내놓기(jwtToken,createProduct());
		List<Product> products = 내_방_보기(jwtToken);

		HashMap<String, String> params = new HashMap<>();
		params.put("productId", products.get(0).getId().toString());

		int statusCode = RestAssured
			.given().log().all()
			.header("jwt-auth-token", jwtToken)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(params)
			.when().post("/interestedRooms")
			.then().log().all().extract().statusCode();

		assertThat(statusCode).isEqualTo(200);
	}

	public String JWT_반환(String role) {
		HashMap<String, Object> userInfo = new HashMap<>();
		userInfo.put("providerId", "111");
		userInfo.put("nickname", "mj");
		userInfo.put("email", "minjoon1995@naver.com");

		when(kakaoAPI.getUserInfo(anyString())).thenReturn(userInfo);

		Map<String, String> input = new HashMap<>();
		input.put("accessToken", "test2");
		input.put("userAuthority", role);

		return RestAssured
			.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(input)
			.when().post("/login")
			.then().log().all().extract().jsonPath().getString("data.jwtToken");
	}
}
