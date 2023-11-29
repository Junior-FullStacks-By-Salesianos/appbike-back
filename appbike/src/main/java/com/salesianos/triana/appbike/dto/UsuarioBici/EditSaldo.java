package com.salesianos.triana.appbike.dto.UsuarioBici;

import com.salesianos.triana.appbike.validation.annotation.Recharge;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditSaldo(
        @Recharge
        @NotNull( message = "{editSaldo.recharge.notnull}")
        Double recharge,

        @NotBlank(message = "{editSaldo.pin.notblank}")
        String pin
) {

}
