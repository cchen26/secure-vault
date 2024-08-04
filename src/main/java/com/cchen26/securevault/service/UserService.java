package com.cchen26.securevault.service;

import com.cchen26.securevault.entity.RoleEntity;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-06-24
 */

public interface UserService {
    void createUser(String firstName, String lastName, String email, String password);
    RoleEntity getRoleName(String name);
}
