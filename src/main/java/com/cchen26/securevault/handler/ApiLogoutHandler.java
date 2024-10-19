package com.cchen26.securevault.handler;

import com.cchen26.securevault.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import static com.cchen26.securevault.enumeration.TokenType.ACCESS;
import static com.cchen26.securevault.enumeration.TokenType.REFRESH;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-10-05
 */

@Service
@RequiredArgsConstructor
public class ApiLogoutHandler implements LogoutHandler {
    private final JwtService jwtService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        var logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        jwtService.removeCookie(request, response, ACCESS.getValue());
        jwtService.removeCookie(request, response, REFRESH.getValue());
    }
}