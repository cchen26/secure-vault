package com.cchen26.securevault.dtorequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-09-04
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {
    @NotEmpty(message = "Email cannot be empty or null")
    @Email(message = "Invalid email address")
    private String email;
    @NotEmpty(message = "Password cannot be empty or null")
    private String password;
}