package com.fipe.api2.infrastructure.messaging;

import com.fipe.api2.domain.model.Category;

public record BrandMessage(
    Long brandId,
    String brandName,
    Category brandCategory) {
}
