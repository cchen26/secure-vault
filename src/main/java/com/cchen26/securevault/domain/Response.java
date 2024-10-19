package com.cchen26.securevault.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.*;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-07-07
 */

@JsonInclude(NON_DEFAULT)
public record Response(String time, int code, String path, HttpStatus status, String message, String exception, Map<?, ?> data) {}