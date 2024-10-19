package com.cchen26.securevault.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-09-05
 */

@Builder
@Getter
@Setter
public class Token {
    private String access;
    private String refresh;
}