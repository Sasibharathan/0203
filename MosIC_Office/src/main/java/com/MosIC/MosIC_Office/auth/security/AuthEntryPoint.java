package com.MosIC.MosIC_Office.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Triggered whenever a request reaches a protected endpoint without a valid JWT.
 * Returns structured JSON 401 instead of Spring's default HTML error page.
 */
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper; // injected — not new ObjectMapper()

    public AuthEntryPoint(ObjectMapper objectMapper) { // Spring provides its own configured ObjectMapper bean
       this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest      request,
                         HttpServletResponse     response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status",  401);
        body.put("error",   "Unauthorized");
        body.put("message", "Access denied — please login to continue");
        body.put("path",    request.getServletPath());

        objectMapper.writeValue(response.getOutputStream(), body);
    }
}


