package com.fipe.api1.entrypoint.controller;

import com.fipe.api1.entrypoint.docs.AuthControllerDocs;
import com.fipe.api1.entrypoint.dto.AuthRequest;
import com.fipe.api1.entrypoint.dto.AuthResponse;
import com.fipe.api1.infrastructure.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final JwtService jwtService;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

    // simples (hardcoded pro teste)
    if (!"admin".equals(request.username()) ||
        !"admin".equals(request.password())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    String token = jwtService.generateToken(request.username());

    return ResponseEntity.ok(new AuthResponse(token));
  }
}
