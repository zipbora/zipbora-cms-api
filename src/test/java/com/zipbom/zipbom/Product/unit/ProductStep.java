package com.zipbom.zipbom.Product.unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;

import com.zipbom.zipbom.Product.model.Product;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ProductStep {
	public static ExtractableResponse<Response> 방_내놓기(String jwtToken, Map<String, String> createProduct) {
		return RestAssured
			.given().log().all()
			.header("jwt-auth-token", jwtToken)
			.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
			.multiPart("address", createProduct.get("address"))
			.multiPart("detailAddress", createProduct.get("detailAddress"))
			.multiPart("haveLoan", createProduct.get("haveLoan"))
			.multiPart("productType", createProduct.get("productType"))
			.when().post("/products")
			.then().log().all().extract();
	}

	public static List<Product> 내_방_보기(String jwtToken) {
		return RestAssured
			.given().log().all()
			.header("jwt-auth-token", jwtToken)
			.when().get("/products")
			.then().log().all().extract().body().jsonPath().getList("data", Product.class);
	}

	public static HashMap<String, String> createProduct() {
		HashMap<String, String> userInfo = new HashMap<>();
		userInfo.put("address", "seoul");
		userInfo.put("detailAddress", "mokdong");
		userInfo.put("haveLoan", "true");
		userInfo.put("productType", "APARTMENT");
		return userInfo;
	}
}
