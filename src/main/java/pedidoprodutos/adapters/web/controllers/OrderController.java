/*
  Web Adapter (Controller) - Orders
  - Recebe CreateOrderRequest e monta itens para o OrderService.
*/
package pedidoprodutos.adapters.web.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pedidoprodutos.adapters.web.requests.CreateOrderRequest;
import pedidoprodutos.adapters.web.requests.UpdateOrderStatusRequest;
import pedidoprodutos.adapters.web.responses.OrderItemResponse;
import pedidoprodutos.adapters.web.responses.OrderResponse;
import pedidoprodutos.application.services.OrderService;
import pedidoprodutos.domain.entities.Order;
import pedidoprodutos.domain.enums.OrderStatus;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@RequestBody @Valid CreateOrderRequest req) {
        List<OrderService.CreateItem> items = req.items().stream()
                .map(i -> new OrderService.CreateItem(i.productId(), i.quantity()))
                .toList();

        Order o = service.create(req.customerName(), items);
        return toResponse(o);
    }

    @GetMapping
    public List<OrderResponse> list() {
        return service.list().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Long id) {
        return toResponse(service.get(id));
    }

    @PatchMapping("/{id}/status")
    public OrderResponse updateStatus(@PathVariable Long id, @RequestBody @Valid UpdateOrderStatusRequest req) {
        OrderStatus status = OrderStatus.from(req.status());
        return toResponse(service.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    private OrderResponse toResponse(Order o) {
        List<OrderItemResponse> items = o.getItems().stream()
                .map(i -> new OrderItemResponse(
                        i.getId(),
                        i.getProductId(),
                        i.getQuantity(),
                        i.getUnitPrice(),
                        i.lineTotal()
                )).toList();

        return new OrderResponse(
                o.getId(),
                o.getCustomerName(),
                o.getStatus().name(),
                o.getTotal(),
                o.getCreatedAt(),
                items
        );
    }
}
