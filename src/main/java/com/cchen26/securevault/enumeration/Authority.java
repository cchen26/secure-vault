package com.cchen26.securevault.enumeration;

import static com.cchen26.securevault.constant.Constants.*;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-06-19
 */

public enum Authority {
    USER(USER_AUTHORITIES),
    ADMIN(ADMIN_AUTHORITIES),
    SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES),
    MANAGER(MANAGER_AUTHORITIES);

    private final String value;

    Authority(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
