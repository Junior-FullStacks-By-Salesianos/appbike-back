package com.salesianos.triana.appbike.dto.station;

import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.model.Uso;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class StationResponse {
    private Long numero;
    private String name;
    private String coordinates;
    private int capacity;
    private Set<Bicicleta> bikes = new HashSet<>();

    public static StationResponse of (Estacion e){
        return StationResponse.builder()
                .numero(e.getNumero())
                .name(e.getNombre())
                .capacity(e.getCapacidad())
                .coordinates(e.getCoordenadas())
                .bikes(e.getBicicletas())
                .build();

    }
}
