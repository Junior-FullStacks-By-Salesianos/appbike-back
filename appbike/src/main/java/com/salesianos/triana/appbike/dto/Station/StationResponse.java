package com.salesianos.triana.appbike.dto.Station;
import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Estacion;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Data
//DTO creado por Cristian Pulido Cabello
    public class StationResponse {

        private Long numero;
        private String name;
        private String coordinates;
        private int capacity;
        private List<GetBicicletaDTO> bikes = new ArrayList<>();

    public static StationResponse of(Estacion es) {
        List<GetBicicletaDTO> bikeDTO = new ArrayList<>();
        if (es.getBicicletas() != null && !es.getBicicletas().isEmpty()) {
            for (Bicicleta bicicleta : es.getBicicletas()) {
                bikeDTO.add(GetBicicletaDTO.of(bicicleta));}
        } else
            {
                GetBicicletaDTO emptyBikeDTO = new GetBicicletaDTO(null, "", "", "", "", 0, "");
                bikeDTO.add(emptyBikeDTO);
            }

        return StationResponse.builder().numero(es.getNumero())
                .name(es.getNombre())
                .capacity(es.getCapacidad())
                .coordinates(es.getCoordenadas())
                .bikes(bikeDTO).build();
        }
    }