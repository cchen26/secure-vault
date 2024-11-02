package com.cchen26.securevault.enumeration;

import lombok.Getter;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-09-05
 */

@Getter
public enum TokenType {
    ACCESS("access-token"), REFRESH("refresh-token");

    private final String value;

    TokenType(String value) { this.value = value; }

}