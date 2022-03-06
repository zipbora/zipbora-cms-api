package com.zipbom.zipbom.Product.unit;

import java.util.Map;

import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ProductStep {
	public static ExtractableResponse<Response> 방_내놓기(String jwtToken, Map<String, String> userInfo) {
		return RestAssured
			.given().log().all()
			.header("jwt-auth-token", jwtToken)
			.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
			.multiPart("address", userInfo.get("address"))
			.multiPart("detailAddress", userInfo.get("detailAddress"))
			.multiPart("haveLoan", userInfo.get("haveLoan"))
			.multiPart("productType", userInfo.get("productType"))
			.when().post("/products")
			.then().log().all().extract();
	}

	public static ExtractableResponse<Response> 내_방_보기(String jwtToken) {
		return RestAssured
			.given().log().all()
			.header("jwt-auth-token", jwtToken)
			.when().get("/products")
			.then().log().all().extract();
	}
}
