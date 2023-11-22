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
    private UUID id;
    private Long numero;
    private String nombre;
    private String coordenadas;
    private int capacidad;
    private Set<Bicicleta> bicicletas = new HashSet<>();
    private List<Uso> usos=new ArrayList<>();

    public static StationResponse of (Estacion e){
        return StationResponse.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .capacidad(e.getCapacidad())
                .numero(e.getNumero())
                .coordenadas(e.getCoordenadas())
                .bicicletas(e.getBicicletas())
                .usos(e.getUsos())
                .build();

    }
}
