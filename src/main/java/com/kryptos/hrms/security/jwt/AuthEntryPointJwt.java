package com.kryptos.hrms.security.jwt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kryptos.hrms.model.enums.EError;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Unauthorized errors: {}", authException.getMessage());
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		EError eError = EError.UNAUTHORIZED_ACCESS;
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		final Map<String, Object> body =new LinkedHashMap<>();
		body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		body.put("error", eError);
		body.put("message", authException.getMessage());
		body.put("timestamp", formattedTimestamp);
		body.put("path", request.getServletPath());
		
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
		
	}

}
