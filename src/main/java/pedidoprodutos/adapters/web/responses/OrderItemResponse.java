/*
  Web DTO (Output)
*/
package pedidoprodutos.adapters.web.responses;

import java.math.BigDecimal;

public record OrderItemResponse(
        Long id,
        Long productId,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal lineTotal
) {}
