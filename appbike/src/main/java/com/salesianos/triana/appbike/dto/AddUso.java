package com.salesianos.triana.appbike.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddUso(
        @NotNull(message = "{addUso.id_bicicleta.notnull}")
        UUID id_bicicleta
) {
}
