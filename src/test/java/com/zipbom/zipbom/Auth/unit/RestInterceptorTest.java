package com.zipbom.zipbom.Auth.unit;

import com.zipbom.zipbom.Auth.jwt.JwtAuthorityChecker;
import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Global.interceptor.RestInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import java.io.IOException;

public class RestInterceptorTest {
    private static String JWT_TOKEN = "yJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4MzYzYWExNy0xY2MyLTQxNWMtOTBhNS0zOWI5YTdjNzczMTAiLCJyb2xlIjpbIlJPTEVfVVNFUiJdLCJleHAiOjE2NDQzMjkyNTksInVzZXJJZCI6IjgzNjNhYTE3LTFjYzItNDE1Yy05MGE1LTM5YjlhN2M3NzMxMCIsImlhdCI6MTY0NDMyMzI1OSwiZW1haWwiOm51bGx9.vzZHvrPkgThSKZ69ifwnoyGrGeWem8IuU8J4-E5-hOeuPzi3AfNiskanGXCOD857OyVmnf-uuxon6xG2ti_mEg";

    @Test
    void preHandle() throws IOException {
        RestInterceptor restInterceptor = new RestInterceptor();

        MockHttpServletRequest request = createMockRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = new Object();
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        restInterceptor.preHandle(request, response, new Object());


    }

    private MockHttpServletRequest createMockRequest() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("jwt-auth-token", JWT_TOKEN);
        return request;
    }
}
