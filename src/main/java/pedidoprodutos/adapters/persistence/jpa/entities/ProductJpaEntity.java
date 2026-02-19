/*
  JPA Entity (Infra)
  - Representa a tabela products.
  - Aqui pode ter anotações JPA/Hibernate.
  - Mapeamento para o domínio é feito no Adapter.
*/
package pedidoprodutos.adapters.persistence.jpa.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, length=120)
    private String name;

    @Column(name="price", nullable=false, precision=18, scale=2)
    private BigDecimal price;

    @Column(name="active", nullable=false)
    private boolean active;

    @Column(name="created_at", nullable=false)
    private Instant createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
