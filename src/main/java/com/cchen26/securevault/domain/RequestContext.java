package com.cchen26.securevault.domain;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-06-19
 */

public class RequestContext {
    private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();

    private RequestContext() {}

    public static void start() {
        USER_ID.remove();
    }

    public static void setUserId(Long userId) {
        USER_ID.set(userId);
    }

    public static Long getUserId() {
        return USER_ID.get();
    }
}
