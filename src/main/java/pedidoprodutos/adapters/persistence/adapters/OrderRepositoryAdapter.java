/*
  Adapter (Infra -> Application)
  - Implementa OrderRepositoryPort usando JPA.
  - Reconstitui agregado Order + itens.
*/
package pedidoprodutos.adapters.persistence.adapters;

import pedidoprodutos.adapters.persistence.jpa.entities.OrderItemJpaEntity;
import pedidoprodutos.adapters.persistence.jpa.entities.OrderJpaEntity;
import pedidoprodutos.adapters.persistence.jpa.repositories.OrderJpaRepository;
import pedidoprodutos.application.ports.out.OrderRepositoryPort;
import pedidoprodutos.domain.entities.Order;
import pedidoprodutos.domain.entities.OrderItem;
import pedidoprodutos.domain.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository repo;

    public OrderRepositoryAdapter(OrderJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity jpa = toJpa(order);
        OrderJpaEntity saved = repo.save(jpa);
        return toDomain(saved);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Order> findAll() {
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

    private OrderJpaEntity toJpa(Order d) {
        OrderJpaEntity e = new OrderJpaEntity();
        e.setId(d.getId());
        e.setCustomerName(d.getCustomerName());
        e.setStatus(d.getStatus().name());
        e.setTotal(d.getTotal());
        e.setCreatedAt(d.getCreatedAt());

        // recria itens (orphanRemoval true)
        e.getItems().clear();
        for (OrderItem item : d.getItems()) {
            OrderItemJpaEntity ie = new OrderItemJpaEntity();
            ie.setId(item.getId());
            ie.setProductId(item.getProductId());
            ie.setQuantity(item.getQuantity());
            ie.setUnitPrice(item.getUnitPrice());
            ie.setLineTotal(item.lineTotal());
            e.addItem(ie);
        }
        return e;
    }

    private Order toDomain(OrderJpaEntity e) {
        List<OrderItem> items = e.getItems().stream()
                .map(i -> new OrderItem(i.getId(), i.getProductId(), i.getQuantity(), i.getUnitPrice()))
                .toList();

        return new Order(
                e.getId(),
                e.getCustomerName(),
            OrderStatus.from(e.getStatus()),
                e.getTotal(),
                e.getCreatedAt(),
                items
        );
    }
}
