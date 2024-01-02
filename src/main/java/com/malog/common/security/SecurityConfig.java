package com.malog.common.security;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Import(value = {DefaultAuthenticationEntryPoint.class, DefaultAccessDeniedHandler.class,
    JwtAuthFilter.class, FilterChainExceptionHelper.class, BCryptPasswordEncoder.class})
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final FilterChainExceptionHelper filterChainExceptionHelper;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable).sessionManagement(
                sessionManagement -> sessionManagement
                    .sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS))
            .exceptionHandling(
                exceptionHandle -> exceptionHandle.authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers(new IpAddressMatcher("127.0.0.1")).permitAll())
            .addFilterBefore(filterChainExceptionHelper, LogoutFilter.class)
            .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
