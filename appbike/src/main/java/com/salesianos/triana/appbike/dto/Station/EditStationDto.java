package com.salesianos.triana.appbike.dto.Station;

import com.salesianos.triana.appbike.model.Estacion;
import jakarta.validation.constraints.*;


public record EditStationDto(

        Long numero,

        @NotNull
        String nombre,
        @Pattern(regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$")
        @NotNull
        String coordenadas,
        @Min(0)
        @Max(31)
        @NotNull
        int capacidad
) {

        public static EditStationDto of(Estacion e) {
                return new EditStationDto(
                        e.getNumero(),
                        e.getNombre(),
                        e.getCoordenadas(),
                        e.getCapacidad());
        }


}
