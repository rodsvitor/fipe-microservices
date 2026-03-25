package com.fipe.api2.infrastructure.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class InternalApiKeyFilter extends OncePerRequestFilter {

  @Value("${internal.api.key}")
  private String expectedKey;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {

    String key = request.getHeader("X-Internal-Key");

    if (!expectedKey.equals(key)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(
            "internal-service",
            null,
            List.of()
        );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }
}
