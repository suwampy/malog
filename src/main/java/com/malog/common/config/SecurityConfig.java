package com.malog.common.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests(authorize -> {
                try {
                    authorize
                        .requestMatchers("/api/v1/**").permitAll()
                        .requestMatchers(new IpAddressMatcher("127.0.0.1")).permitAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
