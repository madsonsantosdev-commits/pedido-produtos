/*
  Adapter (Infra -> Application)
  - Implementa ProductRepositoryPort usando JPA.
  - Faz o "map" entre JPA Entity e Domain Entity.
*/
package pedidoprodutos.adapters.persistence.adapters;

import pedidoprodutos.adapters.persistence.jpa.entities.ProductJpaEntity;
import pedidoprodutos.adapters.persistence.jpa.repositories.ProductJpaRepository;
import pedidoprodutos.application.ports.out.ProductRepositoryPort;
import pedidoprodutos.domain.entities.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository repo;

    public ProductRepositoryAdapter(ProductJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity e = toJpa(product);
        ProductJpaEntity saved = repo.save(e);
        Product d = toDomain(saved);
        return d;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return repo.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    private ProductJpaEntity toJpa(Product d) {
        ProductJpaEntity e = new ProductJpaEntity();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setPrice(d.getPrice());
        e.setActive(d.isActive());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    private Product toDomain(ProductJpaEntity e) {
        return new Product(
                e.getId(),
                e.getName(),
                e.getPrice(),
                e.isActive(),
                e.getCreatedAt()
        );
    }
}
