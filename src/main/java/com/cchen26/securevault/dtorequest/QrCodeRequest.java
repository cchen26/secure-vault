package com.cchen26.securevault.dtorequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-09-21
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QrCodeRequest {
    @NotEmpty(message = "User ID cannot be empty or null")
    private String userId;
    @NotEmpty(message = "QR code cannot be empty or null")
    private String qrCode;
}