package com.fipe.api1.infrastructure.messaging;

import com.fipe.api1.domain.Category;

public record BrandMessage(
    Long brandId,
    String brandName,
    Category brandCategory) {
}
