package com.ra.javaserviecproject.security;

import com.ra.javaserviecproject.security.jwt.JwtEntryPoint;
import com.ra.javaserviecproject.security.jwt.JwtFilterChainInternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    @Autowired
    private JwtFilterChainInternal jwtFilterChain;

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                    corsConfiguration.setAllowedMethods(List.of("*"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    corsConfiguration.setExposedHeaders(List.of("*"));
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("api/v1/admin/**").hasAuthority("ADMIN");
                    auth.requestMatchers("api/v1/mentors/**").hasAuthority("ADMIN");
                    auth.requestMatchers("api/v1/students/**").hasAuthority("ADMIN");
                    auth.anyRequest().permitAll();

                })
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilterChain, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint));
        return http.build();
    }
}
