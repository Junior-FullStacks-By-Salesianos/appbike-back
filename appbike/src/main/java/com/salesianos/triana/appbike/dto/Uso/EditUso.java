package com.salesianos.triana.appbike.dto.Uso;

import jakarta.validation.constraints.*;

public record EditUso(
        @DecimalMin(value = "0.00", inclusive = false, message = "The cost can't be lower than 0.01")
        @DecimalMax(value = "100.01", inclusive = false, message = "The cost can't be higher than 100.00")
        @Digits(integer = 3, fraction = 2, message = "Invalid cost format.")
        @NotNull(message = "The cost can´t be null")
        Double coste
) {

}
