/*
  JPA Entity (Infra): order_items
  - Item do pedido.
*/
package pedidoprodutos.adapters.persistence.jpa.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItemJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="order_id", nullable=false)
    private OrderJpaEntity order;

    @Column(name="product_id", nullable=false)
    private Long productId;

    @Column(name="quantity", nullable=false)
    private int quantity;

    @Column(name="unit_price", nullable=false, precision=18, scale=2)
    private BigDecimal unitPrice;

    @Column(name="line_total", nullable=false, precision=18, scale=2)
    private BigDecimal lineTotal;

    public Long getId() { return id; }
    public OrderJpaEntity getOrder() { return order; }
    public Long getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getLineTotal() { return lineTotal; }

    public void setId(Long id) { this.id = id; }
    public void setOrder(OrderJpaEntity order) { this.order = order; }
    public void setProductId(Long productId) { this.productId = productId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public void setLineTotal(BigDecimal lineTotal) { this.lineTotal = lineTotal; }
}
