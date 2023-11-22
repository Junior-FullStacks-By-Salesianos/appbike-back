package com.salesianos.triana.appbike.revision.dto;

import com.salesianos.triana.appbike.enums.EstadoRevision;
import com.salesianos.triana.appbike.estacion.Estacion;
import com.salesianos.triana.appbike.revision.Revision;
import com.salesianos.triana.appbike.trabajador.dto.TrabajadorDTO;

import java.time.LocalDate;
import java.util.Optional;

public record EditRevisionDTO(Long uuid, LocalDate fechaProgramada,
                              String anotaciones,
                              Estacion estacion, TrabajadorDTO trabajador,
                              EstadoRevision estado) {
    //Hay que cambiar de Estacion a EstacionDTO

    public static EditRevisionDTO of(Revision r) {
        return new EditRevisionDTO(r.getId(),
                r.getFechaProgramada(),
                r.getAnotaciones(),
                r.getEstacion(),
                TrabajadorDTO.of(r.getTrabajador()),
                r.getEstado());
    }
}
