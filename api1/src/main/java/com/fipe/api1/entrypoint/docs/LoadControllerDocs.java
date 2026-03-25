package com.fipe.api1.entrypoint.docs;

import com.fipe.api1.infrastructure.exception.handler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Load", description = "Data initialization")
public interface LoadControllerDocs {

  @Operation(
      summary = "Load initial vehicle brands",
      description = "Triggers a background process to load vehicle brands into the system"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "202", description = "Load process started successfully"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  ResponseEntity<Void> load();
}
