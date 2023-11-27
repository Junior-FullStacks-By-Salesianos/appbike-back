package com.salesianos.triana.appbike.dto.Bike;

import com.salesianos.triana.appbike.model.Estados;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.UUID;

public record PostBicicletaDTO(

        @NotEmpty
        @NotNull
        String nombre,
        @NotNull
        String marca,
        @NotNull
        String modelo,
        @NotNull
        Estados estado,

        Long estacion

) {
}
