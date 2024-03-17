package com.addario.webcrawlerapi.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Item(String name, String description, BigDecimal price, String link, String imageUrl, Category category,
                   Size size) {
}
