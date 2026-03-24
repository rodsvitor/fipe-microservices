package com.fipe.api1.application.usecase;

import com.fipe.api1.infrastructure.client.FipeBrandResponse;
import com.fipe.api1.infrastructure.client.FipeClient;
import com.fipe.api1.infrastructure.messaging.BrandMessage;
import com.fipe.api1.infrastructure.messaging.BrandProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoadInitialBrandsUseCase {

  private final FipeClient fipeClient;
  private final BrandProducer producer;

  public void execute() {

    List<FipeBrandResponse> brands = fipeClient.getBrands();

    FipeBrandResponse brand = brands.getFirst();

    producer.send(
        new BrandMessage(
            brand.id(),
            brand.name()));

//    brands.forEach(brand ->
//        producer.send(
//            new BrandMessage(
//                brand.id(),
//                brand.name())));

  }

}
