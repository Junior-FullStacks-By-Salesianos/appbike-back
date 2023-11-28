package com.salesianos.triana.appbike.dto.Bike;

import com.salesianos.triana.appbike.model.Estados;
import jakarta.validation.constraints.NotNull;

public record EditBicicletaDTO(

        @NotNull
        String modelo,
         @NotNull
        String marca,
        @NotNull
        Estados estado,
        Long estacion

) {
}
