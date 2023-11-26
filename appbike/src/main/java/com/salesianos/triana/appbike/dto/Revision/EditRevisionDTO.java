package com.salesianos.triana.appbike.dto.Revision;

import com.salesianos.triana.appbike.model.EstadoRevision;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.model.Revision;
import com.salesianos.triana.appbike.dto.Trabajador.TrabajadorDTO;

import java.time.LocalDate;

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

    public static Revision toEntity(EditRevisionDTO editDTO){
        return Revision.builder()
                .fechaProgramada(editDTO.fechaProgramada)
                .anotaciones(editDTO.anotaciones())
                .estacion(editDTO.estacion())
                .trabajador(TrabajadorDTO.toEntity(editDTO.trabajador))
                .estado(editDTO.estado)
                .build();
    }
}
