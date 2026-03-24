package com.fipe.api1.entrypoint.controller;

import com.fipe.api1.application.usecase.LoadInitialBrandsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
public class LoadController {

  private final LoadInitialBrandsUseCase useCase;

  @PostMapping
  public ResponseEntity<Void> load() {
    useCase.execute();
    return ResponseEntity.accepted().build();
  }

}
