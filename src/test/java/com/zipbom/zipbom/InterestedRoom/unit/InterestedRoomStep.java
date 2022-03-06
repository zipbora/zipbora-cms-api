package com.zipbom.zipbom.InterestedRoom.unit;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;

import com.zipbom.zipbom.InterestedRoom.dto.InterestedRoomResponseDto;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class InterestedRoomStep {

	public static ExtractableResponse<Response> 관심있는_방_추가(String jwtToken, HashMap<String,String> params) {
		return RestAssured
			.given().log().all()
			.header("jwt-auth-token", jwtToken)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(params)
			.when().post("/interestedRooms")
			.then().log().all().extract();
	}

	public static List<InterestedRoomResponseDto> 내_관심있는_방들_보기(String jwtToken) {
		return RestAssured
			.given().log().all()
			.header("jwt-auth-token", jwtToken)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.when().get("/interestedRooms")
			.then().log().all().extract().body().jsonPath().getList("data", InterestedRoomResponseDto.class);
	}
}
