package com.cchen26.securevault.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-08-11
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class FilterChainConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/user/login").permitAll()
                                .anyRequest().authenticated())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        //var myOwnAuthenticationProvider = new ApiAuthenticationProvider(userDetailsService);
        //myOwnAuthenticationProvider
        //return new ProviderManager(null);
        return null;
    }


    /*@Bean
    public UserDetailsService userDetailsService() {
        var junior = User.withDefaultPasswordEncoder()
                .username("junior")
                .password("{noop}letmein")
                .roles("USER")
                .build();

        var hanna = User.withDefaultPasswordEncoder()
                .username("hanne")
                .password("{noop}letmein")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(List.of(junior, hanna));
    }*/

    @Bean
    public UserDetailsService inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("junior").password("letmein").roles("USER").build(),
                User.withUsername("hanna").password("letmein").roles("USER").build()
        );
    }

}



