package com.fipe.api2.infrastructure.messaging;

import com.fipe.api2.application.usecase.ProcessBrandUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BrandConsumer {

  private final ProcessBrandUseCase useCase;

  @RabbitListener(queues = "brand.queue")
  public void consume(BrandMessage message) {

    // TODO Implement retry and DLQ later.

    log.info("Processing brand: {} - {}", message.id(), message.name());

    useCase.execute(
        message.id(),
        message.name());
  }

}