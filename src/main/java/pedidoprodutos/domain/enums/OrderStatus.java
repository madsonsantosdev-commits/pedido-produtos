package pedidoprodutos.domain.enums;

import pedidoprodutos.domain.exceptions.DomainException;

import java.util.Locale;

public enum OrderStatus {
    CRIADO,
    PAGO,
    ENVIADO,
    CANCELADO;

    public static OrderStatus from(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new DomainException("Status é obrigatório.");
        }

        try {
            return OrderStatus.valueOf(value.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new DomainException("Status inválido: " + value);
        }
    }
}
