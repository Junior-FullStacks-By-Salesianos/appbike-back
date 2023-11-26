package com.salesianos.triana.appbike.dto.Revision;

import com.salesianos.triana.appbike.dto.Station.GetStationDto;
import com.salesianos.triana.appbike.dto.Trabajador.TrabajadorDTO;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.model.EstadoRevision;
import com.salesianos.triana.appbike.model.Revision;
import com.salesianos.triana.appbike.model.Trabajador;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public record NewRevisionDTO(@NotNull LocalDate fechaProgramada,
                             String anotaciones,
                             @NotNull UUID estacion,
                             @NotNull UUID trabajador,
                             EstadoRevision estado){

    public static Revision toEntity(NewRevisionDTO r, Estacion e, Trabajador t){
        return Revision.builder()
                .fechaProgramada(r.fechaProgramada)
                .anotaciones(r.anotaciones)
                .trabajador(t)
                .estado(r.estado)
                .estacion(e)
                .build();
    }
}
