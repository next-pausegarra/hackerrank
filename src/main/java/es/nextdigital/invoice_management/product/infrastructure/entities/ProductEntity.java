package es.nextdigital.invoice_management.product.infrastructure.entities;

import es.nextdigital.invoice_management.product.domain.models.ProductModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public final class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private final UUID id;

  private final String name;

  private final BigDecimal price;

  public ProductModel toModel() {
    return new ProductModel(id, name, price);
  }

  public static ProductEntity create(UUID id, String name, BigDecimal price) {
    return new ProductEntity(id, name, price);
  }

}
