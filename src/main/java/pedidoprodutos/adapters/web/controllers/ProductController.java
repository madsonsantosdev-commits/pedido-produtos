/*
  Web Adapter (Controller)
  - Traduz HTTP -> Use Cases
  - Não tem regra de negócio: apenas valida, chama service e devolve DTO.
*/
package pedidoprodutos.adapters.web.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pedidoprodutos.adapters.web.requests.CreateProductRequest;
import pedidoprodutos.adapters.web.requests.UpdateProductRequest;
import pedidoprodutos.adapters.web.responses.ProductResponse;
import pedidoprodutos.application.services.ProductService;
import pedidoprodutos.domain.entities.Product;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Valid CreateProductRequest req) {
        Product p = service.create(req.name(), req.price());
        return toResponse(p);
    }

    @GetMapping
    public List<ProductResponse> list() {
        return service.list().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return toResponse(service.get(id));
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody @Valid UpdateProductRequest req) {
        Product p = service.update(id, req.name(), req.price(), req.active());
        return toResponse(p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getPrice(), p.isActive(), p.getCreatedAt());
    }
}
