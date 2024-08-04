package com.cchen26.securevault.exception;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-06-19
 */
public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
    public ApiException() {
        super("An error occurred");
    }
}
