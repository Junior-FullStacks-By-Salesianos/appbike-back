package com.salesianos.triana.appbike.dto.Uso;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record AddUso(
        @NotBlank(message = "{addUso.id_bicicleta.notnull}")
        UUID id_bicicleta
) {
}
