package es.nextdigital.invoice_management.product.domain.repositories;

import es.nextdigital.invoice_management.product.domain.models.ProductModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

  List<ProductModel> findByIdIn(List<UUID> ids);

  Optional<ProductModel> findById(UUID id);

}
