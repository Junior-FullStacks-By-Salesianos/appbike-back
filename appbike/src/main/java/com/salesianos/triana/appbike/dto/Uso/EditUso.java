package com.salesianos.triana.appbike.dto.Uso;

import com.salesianos.triana.appbike.validation.annotation.CostRestriction;
import com.salesianos.triana.appbike.validation.annotation.Recharge;
import jakarta.validation.constraints.*;

public record EditUso(
        @CostRestriction
        @Digits(integer = 3, fraction = 2, message = "{editUso.coste.digits}")
        @NotNull(message = "The cost can´t be null")
        Double coste
) {

}
