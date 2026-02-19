/*
  Use Case Service: OrderService
  - Casos de uso de Pedido:
    - criar pedido com itens
    - listar
    - buscar por id
    - atualizar status
    - remover
  - Regras:
    - produto deve existir e estar ativo
    - unitPrice vem do produto no momento do pedido
    - total é calculado pelo domínio
*/
package pedidoprodutos.application.services;

import pedidoprodutos.application.ports.out.OrderRepositoryPort;
import pedidoprodutos.application.ports.out.ProductRepositoryPort;
import pedidoprodutos.domain.entities.Order;
import pedidoprodutos.domain.entities.OrderItem;
import pedidoprodutos.domain.entities.Product;
import pedidoprodutos.domain.enums.OrderStatus;
import pedidoprodutos.domain.exceptions.DomainException;
import pedidoprodutos.domain.exceptions.NotFoundException;

import java.util.List;

public class OrderService {

    private final OrderRepositoryPort orderRepo;
    private final ProductRepositoryPort productRepo;

    public OrderService(OrderRepositoryPort orderRepo, ProductRepositoryPort productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    public Order create(String customerName, List<CreateItem> items) {
        if (items == null || items.isEmpty()) throw new DomainException("Pedido deve conter pelo menos 1 item.");

        List<OrderItem> domainItems = items.stream().map(i -> {
            Product p = productRepo.findById(i.productId())
                    .orElseThrow(() -> new NotFoundException("Produto não encontrado: " + i.productId()));

            if (!p.isActive()) throw new DomainException("Produto inativo: " + p.getId());

            return OrderItem.of(p.getId(), i.quantity(), p.getPrice());
        }).toList();

        Order order = Order.newOrder(customerName, domainItems);
        order.recalcTotal();
        return orderRepo.save(order);
    }

    public List<Order> list() {
        return orderRepo.findAll();
    }

    public Order get(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado: " + id));
    }

    public Order updateStatus(Long id, OrderStatus status) {
        Order o = get(id);
        o.updateStatus(status);
        return orderRepo.save(o);
    }

    public void delete(Long id) {
        if (!orderRepo.existsById(id)) throw new NotFoundException("Pedido não encontrado: " + id);
        orderRepo.deleteById(id);
    }

    public record CreateItem(Long productId, int quantity) {}
}
