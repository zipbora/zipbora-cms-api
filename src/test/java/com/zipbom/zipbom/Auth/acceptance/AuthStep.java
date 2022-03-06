package com.zipbom.zipbom.Auth.acceptance;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class AuthStep {

	public static ExtractableResponse<Response> 회원가입(String jwtToken,HashMap<String,String> params) {
		return RestAssured
			.given().log().all()
			.header("jwt-auth-token", jwtToken)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(params)
			.when().post("/signUp")
			.then().log().all().extract();
	}
}
