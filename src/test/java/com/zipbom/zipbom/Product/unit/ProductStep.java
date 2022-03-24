package com.zipbom.zipbom.Product.unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;

import com.zipbom.zipbom.Product.dto.ProductFilterRequest;
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
			.multiPart("tradeType", createProduct.get("tradeType"))
			.multiPart("price", createProduct.get("price"))

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

	public static List<Product> 방_필터(HashMap<String,String> params) {
		return RestAssured
			.given().log().all()
			.queryParam("upperLatitude", params.get("upperLatitude"))
			.queryParam("upperLongitude", params.get("upperLongitude"))
			.queryParam("lowerLatitude", params.get("lowerLatitude"))
			.queryParam("lowerLongitude", params.get("lowerLongitude"))
			.queryParam("productType", params.get("productType"))
			.queryParam("upperPrice", params.get("upperPrice"))
			.queryParam("lowerPrice", params.get("lowerPrice"))
			.queryParam("tradeType", params.get("tradeType"))
			.when().get("/products/filter")
			.then().log().all().extract().body().jsonPath().getList("data", Product.class);
	}

	public static HashMap<String, String> createProduct() {
		HashMap<String, String> userInfo = new HashMap<>();
		userInfo.put("address", "seoul");
		userInfo.put("detailAddress", "mokdong");
		userInfo.put("haveLoan", "true");
		userInfo.put("productType", "APARTMENT");
		userInfo.put("latitude", "50");
		userInfo.put("longitude", "50");
		userInfo.put("productType", "APARTMENT");
		userInfo.put("price", "1000000000");
		userInfo.put("tradeType", "lease");
		return userInfo;
	}
}
