/*
  Dependency Injection (Composition Root)
  - Onde "amarramos" as implementações concretas (adapters) com os use cases.
  - Isso mantém domain/application livres de Spring annotations.
*/
package pedidoprodutos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pedidoprodutos.adapters.persistence.adapters.OrderRepositoryAdapter;
import pedidoprodutos.adapters.persistence.adapters.ProductRepositoryAdapter;
import pedidoprodutos.adapters.persistence.jpa.repositories.OrderJpaRepository;
import pedidoprodutos.adapters.persistence.jpa.repositories.ProductJpaRepository;
import pedidoprodutos.application.ports.out.OrderRepositoryPort;
import pedidoprodutos.application.ports.out.ProductRepositoryPort;
import pedidoprodutos.application.services.OrderService;
import pedidoprodutos.application.services.ProductService;

@Configuration
public class BeanConfig {

    @Bean
    public ProductRepositoryPort productRepositoryPort(ProductJpaRepository repo) {
        return new ProductRepositoryAdapter(repo);
    }

    @Bean
    public OrderRepositoryPort orderRepositoryPort(OrderJpaRepository repo) {
        return new OrderRepositoryAdapter(repo);
    }

    @Bean
    public ProductService productService(ProductRepositoryPort productRepo) {
        return new ProductService(productRepo);
    }

    @Bean
    public OrderService orderService(OrderRepositoryPort orderRepo, ProductRepositoryPort productRepo) {
        return new OrderService(orderRepo, productRepo);
    }
}
