package com.zipbom.zipbom.Auth.acceptance;

import static com.zipbom.zipbom.Product.unit.ProductStep.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Util.AcceptanceTest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@AutoConfigureMockMvc
public class AuthAcceptanceMockTest extends AcceptanceTest {

	@MockBean
	private KakaoAPI kakaoAPI;

	@Test
	/***
	 * given 유저의 정보가 주어지고
	 * when 로그인 했을때
	 * then 200을 반환
	 */
	void loginTest() {
		ExtractableResponse<Response> response = 로그인("ROLE_USER");
		assertThat(response.statusCode()).isEqualTo(200);
	}

	@Test
	/***
	 * given 권한이 로그인만한_유저 인 유저가 주어졌을때
	 * when 방 내놓기를 하면
	 * 401을 반환한다
	 */
	void checkUserAuthority() {
		ExtractableResponse<Response> loginResponse = 로그인("ROLE_ONLY_LOGIN");

		String jwtToken = loginResponse.jsonPath().getString("data.jwtToken");

		ExtractableResponse<Response> response = 방_내놓기(jwtToken, createProduct());
		assertThat(response.statusCode()).isEqualTo(403);
	}

	private ExtractableResponse<Response> 로그인(String role) {
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
			.then().log().all().extract();
	}
}

