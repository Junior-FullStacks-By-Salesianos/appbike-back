package com.salesianos.triana.appbike.dto.Revision;

import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.model.EstadoRevision;
import com.salesianos.triana.appbike.model.Revision;
import com.salesianos.triana.appbike.model.Trabajador;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewRevisionDTO(@NotNull LocalDate fechaProgramada, String anotaciones,
                             @NotEmpty String nombreEstacion, @NotEmpty String nombreTrabajador,
                             String estado) {

    public static NewRevisionDTO of(Revision r) {
        return new NewRevisionDTO(r.getFechaProgramada(),
                r.getAnotaciones(),
                r.getEstacion().getNombre(),
                r.getTrabajador().getNombre(),
                r.getEstado().toString());
    }

    public static Revision toEntity(NewRevisionDTO r, Estacion e, Trabajador t){
        return Revision.builder()
                .fechaProgramada(r.fechaProgramada)
                .anotaciones(r.anotaciones)
                .trabajador(t)
                .estacion(e)
                .estado(EstadoRevision.valueOf(r.estado))
                .build();
    }
}
