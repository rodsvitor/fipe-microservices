package com.fipe.api1.application.usecase;

import com.fipe.api1.domain.Category;
import com.fipe.api1.infrastructure.client.FipeClient;
import com.fipe.api1.infrastructure.client.dto.FipeBrandResponse;
import com.fipe.api1.infrastructure.messaging.BrandProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoadInitialBrandsUseCaseTest {

  @Mock
  private FipeClient fipeClient;

  @Mock
  private BrandProducer brandProducer;

  @InjectMocks
  private LoadInitialBrandsUseCase useCase;

  @Test
  void shouldFetchBrandsAndSendToQueue() {
    var brands = List.of(new FipeBrandResponse(1L, "Fiat"));

    when(fipeClient.getBrands(any())).thenReturn(brands);

    useCase.execute();

    verify(fipeClient).getBrands(Category.CAR);
    verify(brandProducer, times(3)).send(any());
  }
}