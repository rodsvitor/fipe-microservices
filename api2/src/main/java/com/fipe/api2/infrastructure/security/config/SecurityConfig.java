package com.fipe.api2.infrastructure.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final InternalApiKeyFilter internalApiKeyFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    return http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/internal/**").authenticated()
            .anyRequest().permitAll()
        )
        .addFilterBefore(internalApiKeyFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }
}