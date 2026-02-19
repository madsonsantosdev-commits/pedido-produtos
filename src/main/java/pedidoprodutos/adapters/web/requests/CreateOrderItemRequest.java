
/*
  Web DTO (Input)
  - Item do pedido recebido via HTTP.
*/
package pedidoprodutos.adapters.web.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderItemRequest(
        @NotNull Long productId,
        @Positive int quantity
) {}
