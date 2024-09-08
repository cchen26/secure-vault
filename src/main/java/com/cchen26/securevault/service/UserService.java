package com.cchen26.securevault.service;

import com.cchen26.securevault.dto.User;
import com.cchen26.securevault.entity.CredentialEntity;
import com.cchen26.securevault.entity.RoleEntity;
import com.cchen26.securevault.enumeration.LoginType;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-06-24
 */

public interface UserService {
    void createUser(String firstName, String lastName, String email, String password);
    RoleEntity getRoleName(String name);
    void verifyAccountKey(String key);
    void updateLoginAttempt(String email, LoginType loginType);
    User getUserByUserId(String userId);
    User getUserByEmail(String email);
    CredentialEntity getUserCredentialById(Long id);
}
