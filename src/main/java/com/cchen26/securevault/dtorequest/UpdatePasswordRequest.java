package com.cchen26.securevault.dtorequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-10-05
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePasswordRequest {
    @NotEmpty(message = "Password cannot be empty or null")
    private String password;
    @NotEmpty(message = "New password cannot be empty or null")
    private String newPassword;
    @NotEmpty(message = "Confirm new password cannot be empty or null")
    private String confirmNewPassword;
}