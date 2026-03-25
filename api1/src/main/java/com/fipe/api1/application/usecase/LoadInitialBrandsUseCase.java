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

  private final FipeClient fipeClient;
  private final BrandProducer producer;

  public void execute() {

    for (Category category : Category.values()) {

      var brands = fipeClient.getBrands(category);

      for (int i = 0; i < 5; i++) {

        FipeBrandResponse brand = brands.get(i); // TODO REMOVE THAT

        producer.send(
            new BrandMessage(
                brand.id(),
                brand.name(),
                category
            )
        );

      }

//      brands.forEach(brand ->
//          producer.send(
//              new BrandMessage(
//                  brand.id(),
//                  brand.name(),
//                  category
//              )
//          )
//      );

    }
  }

}
