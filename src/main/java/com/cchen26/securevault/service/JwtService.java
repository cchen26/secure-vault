package com.cchen26.securevault.service;

import com.cchen26.securevault.domain.Token;

import com.cchen26.securevault.domain.TokenData;
import com.cchen26.securevault.dto.User;
import com.cchen26.securevault.enumeration.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-09-04
 */

public interface JwtService {
    String createToken(User user, Function<Token, String> tokenFunction);
    Optional<String> extractToken(HttpServletRequest request, String tokenType);
    void addCookie(HttpServletResponse response, User user, TokenType type);
    <T> T getTokenData(String token, Function<TokenData, T> tokenFunction);
    void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName);
}
