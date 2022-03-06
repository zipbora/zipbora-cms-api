package com.zipbom.zipbom.Auth.acceptance;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Global.interceptor.RestInterceptor;
import com.zipbom.zipbom.Util.AcceptanceTest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@AutoConfigureMockMvc
public class AuthMockTest extends AcceptanceTest {

	@MockBean
	private RestInterceptor restInterceptor;
	@MockBean
	private KakaoAPI kakaoAPI;

	@Test
	void loginTest() throws Exception {
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

		ExtractableResponse<Response> response =  RestAssured
			.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(input)
			.when().post("/login")
			.then().log().all().extract();
	}
}

