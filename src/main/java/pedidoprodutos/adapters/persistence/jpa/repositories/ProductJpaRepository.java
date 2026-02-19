/*
  Spring Data Repository (Infra)
  - Só existe na borda de infra.
*/
package pedidoprodutos.adapters.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pedidoprodutos.adapters.persistence.jpa.entities.ProductJpaEntity;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {}
