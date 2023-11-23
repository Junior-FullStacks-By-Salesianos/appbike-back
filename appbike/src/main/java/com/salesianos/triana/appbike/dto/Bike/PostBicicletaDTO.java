package com.salesianos.triana.appbike.dto.Bike;

import com.salesianos.triana.appbike.model.Estados;
import com.salesianos.triana.appbike.model.Estacion;

public record PostBicicletaDTO(

        String nombre,
        String marca,
        String modelo,
        Estados estado,
        Estacion estacion

) {
}
