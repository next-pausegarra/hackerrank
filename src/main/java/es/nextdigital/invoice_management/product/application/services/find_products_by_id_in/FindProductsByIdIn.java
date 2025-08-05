package es.nextdigital.invoice_management.product.application.services.find_products_by_id_in;

import es.nextdigital.invoice_management.product.application.dto.ProductDto;
import es.nextdigital.invoice_management.product.domain.models.ProductModel;
import es.nextdigital.invoice_management.product.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindProductsByIdIn {

  private final ProductRepository productRepository;

  @Transactional(readOnly = true)
  public List<ProductDto> execute(FindProductsByIdInDto dto) {
    List<ProductModel> models = productRepository.findByIdIn(dto.ids());

    return models.stream()
      .map(ProductDto::fromModel)
      .toList();
  }

}
