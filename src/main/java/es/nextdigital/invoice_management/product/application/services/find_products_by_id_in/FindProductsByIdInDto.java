package es.nextdigital.invoice_management.product.application.services.find_products_by_id_in;

import java.util.List;
import java.util.UUID;

public record FindProductsByIdInDto(
  List<UUID> ids
) {

  public static FindProductsByIdInDto from(List<String> ids) {
    return new FindProductsByIdInDto(ids.stream().map(UUID::fromString).toList());
  }

}
