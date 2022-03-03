package com.zipbom.zipbom.Auth.acceptance;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class AuthAcceptanceTest {

	@Test
	void loginTest() {

	}

	public static ExtractableResponse<Response> 회원_생성_요청(String email, String password, Integer age) {
		Map<String, String> params = new HashMap<>();
		params.put("email", email);
		params.put("password", password);
		params.put("age", age + "");

		return RestAssured
			.given().log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(params)
			.when().post("/members")
			.then().log().all().extract();
	}
}
