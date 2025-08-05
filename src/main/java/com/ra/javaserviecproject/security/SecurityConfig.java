package com.ra.javaserviecproject.security;

import com.ra.javaserviecproject.security.jwt.JwtEntryPoint;
import com.ra.javaserviecproject.security.jwt.JwtFilterChainInternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                    auth.requestMatchers("api/v1/auth/login").permitAll();
                    auth.requestMatchers("api/v1/auth/register").hasRole("ADMIN");
                    auth.requestMatchers("api/v1/users/**").hasRole("ADMIN");
                    auth.requestMatchers("api/v1/profile/me").hasAnyRole("ADMIN","STUDENT", "MENTOR");

                    auth.requestMatchers(HttpMethod.GET,"api/v1/students/**").hasAnyRole("ADMIN", "STUDENT", "MENTOR");
                    auth.requestMatchers(HttpMethod.POST,"api/v1/students/**").hasAnyRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PATCH,"api/v1/students/**").hasAnyRole("ADMIN", "STUDENT", "MENTOR");
                    auth.requestMatchers(HttpMethod.DELETE,"api/v1/students/**").hasAnyRole("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"api/v1/mentors/**").hasAnyRole("ADMIN", "STUDENT", "MENTOR");
                    auth.requestMatchers(HttpMethod.POST,"api/v1/mentors/**").hasAnyRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PATCH,"api/v1/mentors/**").hasAnyRole("ADMIN", "STUDENT", "MENTOR");
                    auth.requestMatchers(HttpMethod.DELETE,"api/v1/mentors/**").hasAnyRole("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"api/v1/internships/**").hasAnyRole("ADMIN", "STUDENT", "MENTOR");
                    auth.requestMatchers(HttpMethod.POST,"api/v1/internships/**").hasAnyRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PATCH,"api/v1/internships/**").hasAnyRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"api/v1/internships/**").hasAnyRole("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"api/v1/evaluations/**").hasAnyRole("ADMIN", "STUDENT", "MENTOR");
                    auth.requestMatchers(HttpMethod.POST,"api/v1/evaluations/**").hasAnyRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PATCH,"api/v1/evaluations/**").hasAnyRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"api/v1/evaluations/**").hasAnyRole("ADMIN");

                    auth.requestMatchers("api/v1/assessmentsRounds/**").hasRole("ADMIN");
                    auth.requestMatchers("api/v1/roundCriterias/**").hasRole("ADMIN");
                    auth.requestMatchers("api/v1/assignments/**").hasRole("ADMIN");
                    auth.requestMatchers("api/v1/assessmentResult/**").hasRole("ADMIN");
                    auth.anyRequest().permitAll();
                })
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilterChain, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint));
        return http.build();
    }
}