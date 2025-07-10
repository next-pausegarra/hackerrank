package es.nextdigital.invoice_management.product.infrastructure.repositories;

import es.nextdigital.invoice_management.product.domain.entities.ProductModel;
import es.nextdigital.invoice_management.product.domain.repositories.ProductRepository;
import es.nextdigital.invoice_management.product.infrastructure.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductJpaRepositoryAdapter implements ProductRepository {

  private final ProductJpaRepository productJpaRepository;

  @Override
  public List<ProductModel> findByIdIn(List<UUID> ids) {
    return productJpaRepository.findAllById(ids)
      .stream()
      .map(ProductEntity::toModel)
      .toList();
  }

  @Override
  public Optional<ProductModel> findById(UUID id) {
    Optional<ProductEntity> model = productJpaRepository.findById(id);

    return model.map(ProductEntity::toModel);
  }

}
