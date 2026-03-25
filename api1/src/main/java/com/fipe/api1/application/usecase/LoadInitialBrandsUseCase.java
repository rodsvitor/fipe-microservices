package com.fipe.api1.application.usecase;

import com.fipe.api1.domain.Category;
import com.fipe.api1.infrastructure.client.FipeClient;
import com.fipe.api1.infrastructure.client.dto.FipeBrandResponse;
import com.fipe.api1.infrastructure.messaging.BrandMessage;
import com.fipe.api1.infrastructure.messaging.BrandProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadInitialBrandsUseCase {

  private final FipeClient fipeClient; // TODO creates port for that
  private final BrandProducer producer; // TODO creates port for that

  public void execute() {

    for (Category category : Category.values()) {

      var brands = fipeClient.getBrands(category);

      brands.forEach(brand ->
          producer.send(
              new BrandMessage(
                  brand.id(),
                  brand.name(),
                  category
              )
          )
      );

    }
  }

}
