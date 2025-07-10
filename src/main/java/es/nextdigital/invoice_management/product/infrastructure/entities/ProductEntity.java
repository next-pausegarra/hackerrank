package es.nextdigital.invoice_management.product.infrastructure.entities;

import es.nextdigital.invoice_management.product.domain.entities.ProductModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  private BigDecimal price;

  public ProductModel toModel() {
    return new ProductModel(id, name, price);
  }

}
