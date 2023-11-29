package com.salesianos.triana.appbike.dto.UsuarioBici;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EditSaldo(
        @Min(value = 2, message = "Recharge amount must be greater than or equal to 2")
        @Max(value = 100, message = "Recharge amount must be lower than or equal to 100")
        double recharge,

        @NotBlank(message = "PIN is required")
        String pin
) {

}
