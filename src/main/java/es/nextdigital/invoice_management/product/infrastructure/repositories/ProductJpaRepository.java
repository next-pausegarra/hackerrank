package es.nextdigital.invoice_management.product.infrastructure.repositories;

import es.nextdigital.invoice_management.product.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {}
