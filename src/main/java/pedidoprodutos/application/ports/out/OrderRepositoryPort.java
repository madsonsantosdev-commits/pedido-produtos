/*
  Port (Output): OrderRepositoryPort
  - Interface de persistência para pedidos.
*/
package pedidoprodutos.application.ports.out;

import pedidoprodutos.domain.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
