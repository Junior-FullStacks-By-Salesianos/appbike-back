package com.salesianos.triana.appbike.dto.Uso;

import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.model.Uso;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.model.UsuarioBici;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsoResponse(
        UUID id,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        double coste,
        String bicicleta,
        String estacionFin,
        String usuario
) {

    public static UsoResponse of(Uso u){
        return new UsoResponse(
                u.getUuid(),
                u.getFechaInicio(),
                u.getFechaFin(),
                u.getCoste(),
                u.getBicicleta() != null ? u.getBicicleta().getNombre(): "Bike has been deleted from database",
                u.getEstacion() != null ? u.getEstacion().getNombre() : "Unfinished Use",
                u.getAuthor()
        );
    }

    public static UsoResponse of (Uso u, UsuarioBici usuarioBici){
        return new UsoResponse(
                u.getUuid(),
                u.getFechaInicio(),
                u.getFechaFin(),
                u.getCoste(),
                u.getBicicleta() != null ? u.getBicicleta().getNombre(): "Bike has been deleted from database",
                u.getEstacion() != null ? u.getEstacion().getNombre() : "Unfinished Use",
                usuarioBici.getUsername()
        );
    }

}
