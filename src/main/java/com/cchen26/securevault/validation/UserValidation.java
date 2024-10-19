package com.cchen26.securevault.validation;

import com.cchen26.securevault.entity.UserEntity;
import com.cchen26.securevault.exception.ApiException;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-09-21
 */

public class UserValidation {

    public static void verifyAccountStatus(UserEntity user) {
        if(!user.isEnabled()) { throw new ApiException("Account is disabled"); }
        if(!user.isAccountNonExpired()) { throw new ApiException("Account is expired"); }
        if(!user.isAccountNonLocked()) { throw new ApiException("Account is locked"); }
    }
}