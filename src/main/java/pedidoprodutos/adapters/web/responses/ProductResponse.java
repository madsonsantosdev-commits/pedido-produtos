/*
  Web DTO (Output)
  - Representação que o front consome.
*/
package pedidoprodutos.adapters.web.responses;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(
        Long id,
        String name,
        BigDecimal price,
        boolean active,
        Instant createdAt
) {}
