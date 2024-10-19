package com.cchen26.securevault.domain;

import com.cchen26.securevault.dto.User;
import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-09-05
 */

@Builder
@Getter
@Setter
public class TokenData {
    private User user;
    private Claims claims;
    private boolean valid;
    private List<GrantedAuthority> authorities;
}