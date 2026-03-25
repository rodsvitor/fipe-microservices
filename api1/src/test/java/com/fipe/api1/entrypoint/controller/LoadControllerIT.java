package com.fipe.api1.entrypoint.controller;

import com.fipe.api1.domain.Category;
import com.fipe.api1.infrastructure.client.FipeClient;
import com.fipe.api1.infrastructure.client.dto.FipeBrandResponse;
import com.fipe.api1.infrastructure.messaging.BrandProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class LoadControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FipeClient fipeClient;

  @MockBean
  private BrandProducer brandProducer;

  @Test
  void shouldTriggerInitialLoad() throws Exception {
    when(fipeClient.getBrands(Category.CAR))
        .thenReturn(List.of(new FipeBrandResponse(1L, "Fiat")));

    mockMvc.perform(post("/load"))
        .andExpect(status().isAccepted());

    verify(brandProducer).send(any());
  }
}