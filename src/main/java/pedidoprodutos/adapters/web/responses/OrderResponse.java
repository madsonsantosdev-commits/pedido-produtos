/*
  Web DTO (Output)
*/
package pedidoprodutos.adapters.web.responses;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        Long id,
        String customerName,
        String status,
        BigDecimal total,
        Instant createdAt,
        List<OrderItemResponse> items
) {}
