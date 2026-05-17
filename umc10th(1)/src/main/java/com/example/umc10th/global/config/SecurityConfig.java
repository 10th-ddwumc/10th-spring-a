package com.example.umc10th.global.config;

import com.example.umc10th.global.handler.CustomAccessDenied;
import com.example.umc10th.global.handler.CustomEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomEntryPoint customEntryPoint;
    private final CustomAccessDenied customAccessDenied;

    private final String[] allowUris = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/api/signup"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )

                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customEntryPoint)
                        .accessDeniedHandler(customAccessDenied)
                )

                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}