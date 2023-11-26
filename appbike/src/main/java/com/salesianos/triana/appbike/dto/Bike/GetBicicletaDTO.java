package com.salesianos.triana.appbike.dto.Bike;
import com.salesianos.triana.appbike.model.Bicicleta;


public record GetBicicletaDTO(
        String uuid,

        String nombre,
        String marca,
        String modelo,
        String estado,
        int usos,
        String estacion
) {

    public static GetBicicletaDTO of(Bicicleta b){

        return new GetBicicletaDTO (
                b.getUuid().toString(),
                b.getNombre(),
                b.getMarca(),
                b.getModelo(),
                b.getEstado().toString(),
                b.getUsos().size(),
                b.getEstacion() == null ? "Sin estación" : b.getEstacion().getNombre()
        );
    }
}
