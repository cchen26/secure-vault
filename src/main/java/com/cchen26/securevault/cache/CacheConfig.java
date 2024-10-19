package com.cchen26.securevault.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-09-03
 */

@Configuration
public class CacheConfig {

    @Bean
    public CacheStore<String, Integer> userCache() {
        return new CacheStore<>(900, TimeUnit.SECONDS);
    }
}