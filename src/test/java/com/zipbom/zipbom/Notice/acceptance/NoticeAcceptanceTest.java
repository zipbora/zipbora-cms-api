package com.zipbom.zipbom.Notice.acceptance;

import com.zipbom.zipbom.CustomerSupport.dto.NoticeResponse;
import com.zipbom.zipbom.CustomerSupport.model.Notice;
import com.zipbom.zipbom.Util.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class NoticeAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("공지사항을 만든다")
    void createNotice() {
        ExtractableResponse<Response> response = 공지사항_생성_요청("제목입니당", "내용입니당");
        assertThat(response.jsonPath().getObject("data",NoticeResponse.class).getTitle()).isEqualTo("제목입니당");
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
}