package com.cchen26.securevault.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.cchen26.securevault.utils.RequestUtils.handleErrorResponse;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-09-08
 */

@Component
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        handleErrorResponse(request, response, exception);
    }
}