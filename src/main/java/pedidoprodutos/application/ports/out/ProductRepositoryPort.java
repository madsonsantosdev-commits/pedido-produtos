/*
  Port (Output): ProductRepositoryPort
  - Interface que define o que a aplicação precisa do banco.
  - Implementação concreta fica em adapters/persistence.
*/
package pedidoprodutos.application.ports.out;

import pedidoprodutos.domain.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
