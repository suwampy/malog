package com.malog.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

/**
 * 403 인가
 * */
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public DefaultAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        var outputStream = response.getOutputStream();
        objectMapper.writeValue(outputStream, "권한이 없습니다.");
    }
}
