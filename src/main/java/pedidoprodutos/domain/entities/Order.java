
package pedidoprodutos.domain.entities;

import pedidoprodutos.domain.enums.OrderStatus;
import pedidoprodutos.domain.exceptions.DomainException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private String customerName;
    private OrderStatus status;
    private BigDecimal total;
    private Instant createdAt;
    private final List<OrderItem> items = new ArrayList<>();

    public Order(Long id, String customerName, OrderStatus status, BigDecimal total, Instant createdAt, List<OrderItem> items) {
        validateCustomer(customerName);
        this.id = id;
        this.customerName = customerName.trim();
        this.status = status == null ? OrderStatus.CRIADO : status;
        this.createdAt = createdAt == null ? Instant.now() : createdAt;
        if (items != null) this.items.addAll(items);
        recalcTotal();
        if (total != null) this.total = total; // normalmente não precisa, mas permite reconstrução
    }

    public static Order newOrder(String customerName, List<OrderItem> items) {
        Order o = new Order(null, customerName, OrderStatus.CRIADO, null, Instant.now(), items);
        o.ensureHasItems();
        return o;
    }

    public void updateStatus(OrderStatus newStatus) {
        if (newStatus == null) throw new DomainException("Status é obrigatório.");
        this.status = newStatus;
    }

    public void recalcTotal() {
        this.total = items.stream()
                .map(OrderItem::lineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void ensureHasItems() {
        if (items.isEmpty()) throw new DomainException("Pedido deve conter pelo menos 1 item.");
    }

    private void validateCustomer(String customerName) {
        if (customerName == null || customerName.trim().isEmpty())
            throw new DomainException("Nome do cliente é obrigatório.");
    }

    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public OrderStatus getStatus() { return status; }
    public BigDecimal getTotal() { return total; }
    public Instant getCreatedAt() { return createdAt; }
    public List<OrderItem> getItems() { return List.copyOf(items); }

    public void setId(Long id) { this.id = id; }
}
