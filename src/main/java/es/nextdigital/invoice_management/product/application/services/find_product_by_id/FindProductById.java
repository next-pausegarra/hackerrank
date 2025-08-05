package es.nextdigital.invoice_management.product.application.services.find_product_by_id;

import es.nextdigital.invoice_management.product.application.dto.ProductDto;
import es.nextdigital.invoice_management.product.domain.models.ProductModel;
import es.nextdigital.invoice_management.product.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindProductById {

  private final ProductRepository productRepository;

  @Transactional(readOnly = true)
  public ProductDto execute(FindProductByIdDto dto) {
    Optional<ProductModel> productModel = productRepository.findById(dto.id());

    return ProductDto.fromModel(productModel.get());
  }

}
