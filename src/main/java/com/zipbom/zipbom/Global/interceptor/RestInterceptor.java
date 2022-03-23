package com.zipbom.zipbom.Global.interceptor;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipbom.zipbom.Auth.dto.JwtGetUserInfoResponseDto;
import com.zipbom.zipbom.Auth.jwt.JwtAuthorityChecker;
import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Global.exception.AccessDeniedException;
import com.zipbom.zipbom.Global.exception.ErrorCode;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtServiceImpl jwtService;

	public RestInterceptor() {
	}

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull Object handler) {

		if (activeProfile.equals("dev") || activeProfile.equals("local")) {
			return true;
		}

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod)handler;

		JwtAuthorityChecker functionAuthority = handlerMethod.getMethodAnnotation(JwtAuthorityChecker.class);

		if (functionAuthority == null) {
			return true;
		}

		String token = request.getHeader("jwt-auth-token");
		if (token != null && token.length() > 0) {
			jwtService.checkValid(token);
			ObjectMapper mapper = new ObjectMapper();
			if (jwtService.getInfo(token).get("user") != null) {
				JwtGetUserInfoResponseDto jwtGetUserInfoResponseDto = mapper.convertValue(
					jwtService.getInfo(token).get("user"), JwtGetUserInfoResponseDto.class);
				//TODO 권한이 한 개라고 가정
				if (functionAuthority.authority().compareTo(jwtGetUserInfoResponseDto.getRole().get(0)) >= 0) {
					return true;
				}
			}
		}

		throw new AccessDeniedException(ErrorCode.HANDLE_ACCESS_DENIED);
	}
	@Override
	public void afterCompletion(@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull Object handler, Exception ex)
		throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
		final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

		Map<String, String> queryParamMap = new HashMap<>();
		String queryString = "";

		if (request.getQueryString() != null) {
			queryString += URLDecoder.decode(request.getQueryString(), "UTF-8");
			String[] queryParams = queryString.split("&");
			for (String param : queryParams) {
				String name = param.split("=")[0];
				String value = "";
				if (param.split("=").length > 1) {
					value = param.split("=")[1];
				}
				queryParamMap.put(name, value);
			}
		}

		JsonNode requestBody = null;
		JsonNode responseBody = null;
		String logString = String.format("\nFROM:%s, [%s] %s%s",
			request.getRemoteAddr(), request.getMethod(), request.getRequestURL(),
			queryString.equals("") ? "" : "?" + queryString);


		if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
			cachingRequest.getContentAsByteArray();
			if (cachingRequest.getContentAsByteArray().length != 0) {
				requestBody = objectMapper.readTree(cachingRequest.getContentAsByteArray());
				logString += String.format("\nREQUEST: %s", requestBody);
			}
		}

		if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
			cachingResponse.getContentAsByteArray();
			if (cachingResponse.getContentAsByteArray().length != 0) {
				responseBody = objectMapper.readTree(cachingResponse.getContentAsByteArray());
				if (response.getStatus() == 200) {
					logString += "\nOK";
					logString += String.format("\nRESPONSE: %s", responseBody);
				} else {
					logString += String.format("\nERROR RESPONSE: %s", responseBody);
				}
			}
		}

		logString += "\n";

		log.info(logString);
	}
}
