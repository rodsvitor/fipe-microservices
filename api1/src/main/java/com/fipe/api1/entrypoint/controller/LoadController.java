package com.fipe.api1.entrypoint.controller;

import com.fipe.api1.application.usecase.LoadInitialBrandsUseCase;
import com.fipe.api1.entrypoint.docs.LoadControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
public class LoadController implements LoadControllerDocs {

  private final LoadInitialBrandsUseCase useCase;

  @PostMapping
  public ResponseEntity<Void> load() {
    useCase.execute();
    return ResponseEntity.accepted().build();
  }

}
