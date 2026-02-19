/*
  JPA Entity (Infra): orders
  - Pedido com relacionamento 1:N para itens.
*/
package pedidoprodutos.adapters.persistence.jpa.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_name", nullable=false, length=120)
    private String customerName;

    @Column(name="status", nullable=false, length=30)
    private String status;

    @Column(name="total", nullable=false, precision=18, scale=2)
    private BigDecimal total;

    @Column(name="created_at", nullable=false)
    private Instant createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItemJpaEntity> items = new ArrayList<>();

    public void addItem(OrderItemJpaEntity item) {
        item.setOrder(this);
        items.add(item);
    }

    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getStatus() { return status; }
    public BigDecimal getTotal() { return total; }
    public Instant getCreatedAt() { return createdAt; }
    public List<OrderItemJpaEntity> getItems() { return items; }

    public void setId(Long id) { this.id = id; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setStatus(String status) { this.status = status; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public void setItems(List<OrderItemJpaEntity> items) { this.items = items; }
}
