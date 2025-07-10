package es.nextdigital.invoice_management.product.application.services.find_product_by_id;

import es.nextdigital.invoice_management.product.application.dto.ProductDto;
import es.nextdigital.invoice_management.product.domain.entities.ProductModel;
import es.nextdigital.invoice_management.product.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindProductByIdService {

  private final ProductRepository productRepository;

  public ProductDto execute(FindProductByIdDto dto) {
    Optional<ProductModel> productModel = productRepository.findById(dto.id());

    return ProductDto.fromModel(productModel.get());
  }

}
