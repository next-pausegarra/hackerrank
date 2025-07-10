package es.nextdigital.invoice_management.product.application.services.find_product_by_id;

import java.util.UUID;

public record FindProductByIdDto(
  UUID id
) {

  public static FindProductByIdDto from(String id) {
    return new FindProductByIdDto(UUID.fromString(id));
  }

}
