package com.salesianos.triana.appbike.dto.station;
import com.salesianos.triana.appbike.model.Estacion;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.UUID;

public record AddStationDto(
        UUID id,

        @Min(1)
        Long numero,

        String nombre,
        @Pattern(regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$")
        String coordenadas,
        int capacidad



){

    public static AddStationDto of(Estacion e){
        return new AddStationDto(
                e.getId(),
                e.getNumero(),
                e.getNombre(),
                e.getCoordenadas(),
                e.getCapacidad()
        );
    }

}
