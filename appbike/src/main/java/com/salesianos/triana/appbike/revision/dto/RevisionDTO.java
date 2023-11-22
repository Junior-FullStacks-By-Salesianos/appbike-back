package com.salesianos.triana.appbike.revision.dto;

import com.salesianos.triana.appbike.enums.EstadoRevision;
import com.salesianos.triana.appbike.estacion.Estacion;
import com.salesianos.triana.appbike.revision.Revision;

import java.time.LocalDate;
import java.util.UUID;

public record RevisionDTO(Long uuid, LocalDate fechaProgramada,
                          LocalDate fechaRealizacion, String anotaciones,
                          String nombreEstacion, String nombreTrabajador,
                          EstadoRevision estado) {

    public static RevisionDTO of(Revision r) {
        return new RevisionDTO(r.getId(),
                r.getFechaProgramada(),
                r.getFechaRealizacion(),
                r.getAnotaciones(),
                r.getEstacion().getNombre(),
                r.getTrabajador().getNombre(),
                r.getEstado());
    }
}
