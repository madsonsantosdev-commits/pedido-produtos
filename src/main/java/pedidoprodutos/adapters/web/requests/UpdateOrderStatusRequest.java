/*
  Web DTO (Input)
  - Atualização de status (PATCH).
*/
package pedidoprodutos.adapters.web.requests;

import jakarta.validation.constraints.NotBlank;

public record UpdateOrderStatusRequest(
        @NotBlank String status
) {}
