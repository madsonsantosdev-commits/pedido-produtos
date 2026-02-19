/*
  Use Case Service: ProductService
  - Implementa regras da aplicação para Produtos.
  - Depende somente do Port (interface), não do JPA.
*/
package pedidoprodutos.application.services;

import pedidoprodutos.application.ports.out.ProductRepositoryPort;
import pedidoprodutos.domain.entities.Product;
import pedidoprodutos.domain.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {

    private final ProductRepositoryPort productRepo;

    public ProductService(ProductRepositoryPort productRepo) {
        this.productRepo = productRepo;
    }

    public Product create(String name, BigDecimal price) {
        Product p = Product.newProduct(name, price);
        return productRepo.save(p);
    }

    public List<Product> list() {
        return productRepo.findAll();
    }

    public Product get(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado: " + id));
    }

    public Product update(Long id, String name, BigDecimal price, Boolean active) {
        Product p = get(id);
        p.update(name, price, active);
        return productRepo.save(p);
    }

    public void delete(Long id) {
        if (!productRepo.existsById(id)) throw new NotFoundException("Produto não encontrado: " + id);
        productRepo.deleteById(id);
    }
}
