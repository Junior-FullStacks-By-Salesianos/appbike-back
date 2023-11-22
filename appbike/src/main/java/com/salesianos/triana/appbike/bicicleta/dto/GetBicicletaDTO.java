package com.salesianos.triana.appbike.bicicleta.dto;
import com.salesianos.triana.appbike.bicicleta.Bicicleta;


public record GetBicicletaDTO(

        String nombre,
        String marca,
        String modelo,
        String estado,
        int usos,
        String estacion
) {

    public static GetBicicletaDTO of(Bicicleta b){

        return new GetBicicletaDTO (
                b.getNombre(),
                b.getMarca(),
                b.getModelo(),
                b.getEstado(),
                b.getUsos().size(),
                b.getEstacion() == null ? "Sin estación" : b.getEstacion().getNombre()
        );
    }
}
