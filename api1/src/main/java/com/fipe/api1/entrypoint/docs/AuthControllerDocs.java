package com.fipe.api1.entrypoint.docs;

import com.fipe.api1.entrypoint.dto.AuthRequest;
import com.fipe.api1.entrypoint.dto.AuthResponse;
import com.fipe.api1.infrastructure.exception.handler.BadRequestExceptionResponse;
import com.fipe.api1.infrastructure.exception.handler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

@Tag(name = "Auth", description = "Authentication endpoints")
public interface AuthControllerDocs {

  @Operation(
      summary = "Authenticate user",
      description = "Performs login and returns a JWT token"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Authenticated successfully"),
      @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BadRequestExceptionResponse.class))),
      @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content)
  })
  ResponseEntity<AuthResponse> login(
      @Valid @RequestBody(description = "User credentials", required = true) AuthRequest request
  );
}
