package pedidoprodutos.domain.entities;

import pedidoprodutos.domain.exceptions.DomainException;

import java.math.BigDecimal;
import java.time.Instant;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private boolean active;
    private Instant createdAt;

    public Product(Long id, String name, BigDecimal price, boolean active, Instant createdAt) {
        validate(name, price);
        this.id = id;
        this.name = name.trim();
        this.price = price;
        this.active = active;
        this.createdAt = createdAt;
    }

    public static Product newProduct(String name, BigDecimal price) {
        return new Product(null, name, price, true, Instant.now());
    }

    public void update(String name, BigDecimal price, Boolean active) {
        if (name != null) {
            validate(name, this.price);
            this.name = name.trim();
        }
        if (price != null) {
            validate(this.name, price);
            this.price = price;
        }
        if (active != null) this.active = active;
    }

    private void validate(String name, BigDecimal price) {
        if (name == null || name.trim().isEmpty()) throw new DomainException("Nome do produto é obrigatório.");
        if (price == null) throw new DomainException("Preço é obrigatório.");
        if (price.compareTo(BigDecimal.ZERO) <= 0) throw new DomainException("Preço deve ser maior que zero.");
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public boolean isActive() { return active; }
    public Instant getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; } // usado pelo adapter de persistência
}
