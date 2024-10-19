package com.cchen26.securevault.security;

import com.cchen26.securevault.domain.Response;
import com.cchen26.securevault.dto.User;
import com.cchen26.securevault.dtorequest.LoginRequest;
import com.cchen26.securevault.service.JwtService;
import com.cchen26.securevault.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

import static com.cchen26.securevault.constant.Constants.LOGIN_PATH;
import static com.cchen26.securevault.domain.ApiAuthentication.unauthenticated;
import static com.cchen26.securevault.enumeration.LoginType.LOGIN_ATTEMPT;
import static com.cchen26.securevault.enumeration.LoginType.LOGIN_SUCCESS;
import static com.cchen26.securevault.enumeration.TokenType.ACCESS;
import static com.cchen26.securevault.enumeration.TokenType.REFRESH;
import static com.cchen26.securevault.utils.RequestUtils.getResponse;
import static com.cchen26.securevault.utils.RequestUtils.handleErrorResponse;
import static com.fasterxml.jackson.core.JsonParser.Feature.AUTO_CLOSE_SOURCE;
import static java.util.Map.of;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-09-02
 */

@Slf4j
public class ApiAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final UserService userService;
    private final JwtService jwtService;

    public ApiAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService) {
        super(new AntPathRequestMatcher(LOGIN_PATH, POST.name()), authenticationManager);
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            var user = new ObjectMapper().configure(AUTO_CLOSE_SOURCE, true).readValue(request.getInputStream(), LoginRequest.class);
            userService.updateLoginAttempt(user.getEmail(), LOGIN_ATTEMPT);
            var authentication = unauthenticated(user.getEmail(), user.getPassword());
            return getAuthenticationManager().authenticate(authentication);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            handleErrorResponse(request, response, exception);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        var user = (User) authentication.getPrincipal();
        userService.updateLoginAttempt(user.getEmail(), LOGIN_SUCCESS);
        var httpResponse = user.isMfa() ? sendQrCode(request, user) : sendResponse(request, response, user);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(OK.value());
        var out = response.getOutputStream();
        var mapper = new ObjectMapper();
        mapper.writeValue(out, httpResponse);
        out.flush();
    }

    private Response sendResponse(HttpServletRequest request, HttpServletResponse response, User user) {
        jwtService.addCookie(response, user, ACCESS);
        jwtService.addCookie(response, user, REFRESH);
        return getResponse(request, of("user", user), "Login Success", OK);
    }

    private Response sendQrCode(HttpServletRequest request, User user) {
        return getResponse(request, of("user", user), "Please enter QR code", OK);
    }
}
