package com.cchen26.securevault.service;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-06-20
 */
public interface EmailService {
    void sendNewAccountEmail(String name, String email, String token);
    void sendPasswordResetEmail(String name, String email, String token);
}
