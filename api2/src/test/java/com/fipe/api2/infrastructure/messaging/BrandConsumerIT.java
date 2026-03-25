package com.fipe.api2.infrastructure.messaging;

import com.fipe.api2.application.usecase.ProcessBrandUseCase;
import com.fipe.api2.domain.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
class BrandConsumerIT {

  @Autowired
  private BrandConsumer consumer;

  @MockBean
  private ProcessBrandUseCase useCase;

  @Test
  void shouldConsumeMessage() {
    var message = new BrandMessage(1L, "Fiat", Category.CAR);

    consumer.consume(message);

    verify(useCase).execute(
        message.brandId(),
        message.brandName(),
        message.brandCategory());
  }
}