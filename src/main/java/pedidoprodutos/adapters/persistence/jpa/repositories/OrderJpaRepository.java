/*
  Spring Data Repository (Infra)
*/
package pedidoprodutos.adapters.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pedidoprodutos.adapters.persistence.jpa.entities.OrderJpaEntity;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {}
