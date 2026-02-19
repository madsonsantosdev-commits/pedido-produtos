package pedidoprodutos.domain.entities;

import pedidoprodutos.domain.exceptions.DomainException;

import java.math.BigDecimal;

public class OrderItem {
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal unitPrice;

    public OrderItem(Long id, Long productId, int quantity, BigDecimal unitPrice) {
        validate(productId, quantity, unitPrice);
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public static OrderItem of(Long productId, int quantity, BigDecimal unitPrice) {
        return new OrderItem(null, productId, quantity, unitPrice);
    }

    private void validate(Long productId, int quantity, BigDecimal unitPrice) {
        if (productId == null) throw new DomainException("Item precisa de productId.");
        if (quantity <= 0) throw new DomainException("Quantidade deve ser maior que zero.");
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) throw new DomainException("Preço unitário inválido.");
    }

    public BigDecimal lineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }

    public void setId(Long id) { this.id = id; }
}
