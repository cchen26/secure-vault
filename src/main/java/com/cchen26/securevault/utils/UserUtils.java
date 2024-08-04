package com.cchen26.securevault.utils;

import com.cchen26.securevault.entity.RoleEntity;
import com.cchen26.securevault.entity.UserEntity;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.antlr.v4.runtime.atn.SemanticContext;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.StringUtils.EMPTY;


/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-06-30
 */
public class UserUtils {
    public static UserEntity createUserEntity(String firstName, String lastName, String email, RoleEntity role){
        return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .lastLogin(now())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(false)
                .loginAttempts(0)
                .qrCodeSecret(EMPTY)
                .phone(EMPTY)
                .bio(EMPTY)
                .imageUrl("https://cdn-icons-png.flaticon.com/512/149/149071.png")
                .role(role)
                .build();
    }
}
