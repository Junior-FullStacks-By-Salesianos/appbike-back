package com.salesianos.triana.appbike.bicicleta.dto;

import com.salesianos.triana.appbike.enums.Estados;
import com.salesianos.triana.appbike.estacion.Estacion;

public record PostBicicletaDTO(

        String nombre,
        String marca,
        String modelo,
        Estados estado,
        Estacion estacion

) {
}
