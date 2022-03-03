package com.zipbom.zipbom.Auth.unit;


import static com.zipbom.zipbom.Auth.acceptance.AuthAcceptanceTest.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Global.interceptor.RestInterceptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@ExtendWith(MockitoExtension.class)
public class RestInterceptorTest {
    private static String JWT_TOKEN = "yJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4MzYzYWExNy0xY2MyLTQxNWMtOTBhNS0zOWI5YTdjNzczMTAiLCJyb2xlIjpbIlJPTEVfVVNFUiJdLCJleHAiOjE2NDQzMjkyNTksInVzZXJJZCI6IjgzNjNhYTE3LTFjYzItNDE1Yy05MGE1LTM5YjlhN2M3NzMxMCIsImlhdCI6MTY0NDMyMzI1OSwiZW1haWwiOm51bGx9.vzZHvrPkgThSKZ69ifwnoyGrGeWem8IuU8J4-E5-hOeuPzi3AfNiskanGXCOD857OyVmnf-uuxon6xG2ti_mEg";

    @Mock
    private RestInterceptor restInterceptor;
    @Mock
    private KakaoAPI kakaoAPI;
    @Mock
    private UserRepository userRepository;

    @Test
    void loginTest() throws IOException {
        회원_생성_요청("minjoon1995@naver.com", "password", 28);

        RestInterceptor restInterceptor = mock(RestInterceptor.class);
        KakaoAPI kakaoAPI = mock(KakaoAPI.class);

        HashMap<String, Object> userInfo = new HashMap<>();

        userInfo.put("nickname", nickname);
        userInfo.put("email", email);

        MockHttpServletRequest request = createMockRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        when(restInterceptor.preHandle(anyObject(),anyObject(),anyObject())).thenReturn(true);
        when(kakaoAPI.getUserInfo(anyString())).thenReturn();

        ExtractableResponse<Response> response1 = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/login")
                .then().log().all().extract();
        }
    }

    private MockHttpServletRequest createMockRequest() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("jwt-auth-token", JWT_TOKEN);
        return request;
    }
}
