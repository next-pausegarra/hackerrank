package es.nextdigital.invoice_management.product.application.dto;

import es.nextdigital.invoice_management.product.domain.entities.ProductModel;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(
  UUID id,
  String name,
  BigDecimal price
) {

  public static ProductDto fromModel(ProductModel entity) {
    return new ProductDto(entity.getId(), entity.getName(), entity.getPrice());
  }

}
