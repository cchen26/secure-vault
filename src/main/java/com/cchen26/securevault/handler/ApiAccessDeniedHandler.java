package com.cchen26.securevault.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class ApiAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        handleErrorResponse(request, response, exception);
    }
}