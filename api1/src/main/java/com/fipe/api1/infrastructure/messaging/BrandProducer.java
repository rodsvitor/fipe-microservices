package com.fipe.api1.infrastructure.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandProducer {

  private final RabbitTemplate rabbitTemplate;

  public void send(BrandMessage message) {
    rabbitTemplate.convertAndSend("brand.queue", message);
  }

}
