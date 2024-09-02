package com.maniraj.userPostManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1. All requests should be authendicated
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // 2. If a request is not authenticated, web page is shown
        http.httpBasic(withDefaults());
        // 3. CSRF for POST and PUT requests
        http.csrf().disable();

        return http.build();
    }
}
