package com.salesianos.triana.appbike.dto.station;

import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
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
    private List<GetBicicletaDTO> bikes = new ArrayList<>();

    public static StationResponse of(Estacion e) {
        List<GetBicicletaDTO> bikeDTOs = new ArrayList<>();

        if (e.getBicicletas() != null && !e.getBicicletas().isEmpty()) {
            for (Bicicleta bicicleta : e.getBicicletas()) {
                bikeDTOs.add(GetBicicletaDTO.of(bicicleta));
            }
        } else {
            GetBicicletaDTO emptyBikeDTO = new GetBicicletaDTO(null, "", "", "", "", 0, "");
            bikeDTOs.add(emptyBikeDTO);
        }

        return StationResponse.builder()
                .numero(e.getNumero())
                .name(e.getNombre())
                .capacity(e.getCapacidad())
                .coordinates(e.getCoordenadas())
                .bikes(bikeDTOs)
                .build();
    }
}
