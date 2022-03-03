package com.zipbom.zipbom.Auth.acceptance;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipbom.zipbom.Auth.controller.AuthController;
import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Global.interceptor.RestInterceptor;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginMockTest {

	@MockBean
	private RestInterceptor restInterceptor;
	@MockBean
	private KakaoAPI kakaoAPI;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void loginTest() throws Exception {

		HashMap<String, Object> userInfo = new HashMap<>();

		userInfo.put("providerId", "111");
		userInfo.put("nickname", "mj");
		userInfo.put("email", "minjoon1995@naver.com");

		when(restInterceptor.preHandle(anyObject(), anyObject(), anyObject())).thenReturn(true);
		when(kakaoAPI.getUserInfo(anyString())).thenReturn(userInfo);

		Map<String, String> input = new HashMap<>();
		input.put("accessToken", "test2");
		input.put("userAuthority", "ROLE_USER");

		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message").value("jwt 반환"));
	}
}

