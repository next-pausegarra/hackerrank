package es.nextdigital.invoice_management.product.domain.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class ProductModel {

  private final UUID id;

  private final String name;

  private final BigDecimal price;

}
