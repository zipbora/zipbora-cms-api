package com.zipbom.zipbom.Notice.acceptance;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.CustomerSupport.dto.NoticeResponse;
import com.zipbom.zipbom.Global.interceptor.RestInterceptor;
import com.zipbom.zipbom.Util.AcceptanceTest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class NoticeAcceptanceTest extends AcceptanceTest {
	@MockBean
	private RestInterceptor restInterceptor;
	@MockBean
	private KakaoAPI kakaoAPI;

	@Test
	@DisplayName("공지사항을 만든다")
	void createNotice() throws Exception {
		String jwtToken = loginTest().jsonPath().getString("data.jwtToken");
		System.out.println(jwtToken);

		ExtractableResponse<Response> response = 공지사항_생성_요청("제목입니당", "내용입니당");
		assertThat(response.jsonPath().getObject("data", NoticeResponse.class).getTitle()).isEqualTo("제목입니당");
	}

	private ExtractableResponse<Response> 공지사항_생성_요청(String title, String content) {

		Map<String, String> params = new HashMap<>();
		params.put("title", title);
		params.put("content", content);
		return RestAssured
			.given().log().all()
			.body(params)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.when().post("/notices")
			.then().log().all().extract();
	}

	private ExtractableResponse<Response> 공지사항_삭제_요청(String title, String content) {
		Map<String, String> params = new HashMap<>();
		params.put("title", title);
		params.put("content", content);
		return RestAssured
			.given().log().all()
			.body(params)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.when().delete("/notices")
			.then().log().all().extract();
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