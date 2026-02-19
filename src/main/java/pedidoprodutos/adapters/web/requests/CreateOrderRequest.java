/*
  Web DTO (Input)
  - Pedido com lista de itens.
*/
package pedidoprodutos.adapters.web.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateOrderRequest(
        @NotBlank String customerName,
        @NotEmpty @Valid List<CreateOrderItemRequest> items
) {}
