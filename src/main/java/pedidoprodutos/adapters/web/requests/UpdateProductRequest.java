/*
  Web DTO (Input)
  - Campos opcionais para update.
*/
package pedidoprodutos.adapters.web.requests;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String name,
        @Positive BigDecimal price,
        Boolean active
) {}
