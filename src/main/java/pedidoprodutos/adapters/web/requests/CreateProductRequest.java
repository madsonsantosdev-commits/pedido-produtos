/*
  Web DTO (Input)
  - DTO de entrada HTTP para criar produto.
  - Validação fica aqui (camada web), regra de negócio fica no domínio/app.
*/
package pedidoprodutos.adapters.web.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank String name,
        @NotNull @Positive BigDecimal price
) {}
