package com.zipbom.zipbom.Product.unit;

import java.util.HashMap;

import org.springframework.http.MediaType;

import com.zipbom.zipbom.Product.dto.LetRoomRequestDto;

import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;

public class ProductStep {
	public static ExtractableResponse<Response> 방_내놓기(String jwtToken, HashMap<String, String> userInfo) {
		return RestAssured
			.given().log().all()
			.header("jwt-auth-token", jwtToken)
			.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
			.multiPart("haveLoan",userInfo.get("haveLoan"))
			.multiPart("address",userInfo.get("address"))
			.multiPart("detailAddress",userInfo.get("detailAddress"))

			.when().post("/products")
			.then().log().all().extract();
	}

}
