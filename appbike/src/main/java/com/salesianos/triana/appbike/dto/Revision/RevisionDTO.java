package com.salesianos.triana.appbike.dto.Revision;

import com.salesianos.triana.appbike.dto.Station.GetStationDto;
import com.salesianos.triana.appbike.dto.Trabajador.TrabajadorDTO;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.model.EstadoRevision;
import com.salesianos.triana.appbike.model.Revision;
import com.salesianos.triana.appbike.model.Trabajador;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

public record RevisionDTO(Long id, @NotNull LocalDate fechaProgramada,
                          LocalDate fechaRealizacion,String anotaciones,
                          @NotNull GetStationDto estacion, @NotNull TrabajadorDTO trabajador,
                          EstadoRevision estado) {

    public static RevisionDTO of(Revision r) {
        return new RevisionDTO(r.getId(),
                r.getFechaProgramada(),
                r.getFechaRealizacion(),
                r.getAnotaciones(),
                GetStationDto.of(r.getEstacion()),
                TrabajadorDTO.of(r.getTrabajador()),
                r.getEstado());
    }

    public static Revision toEntity(RevisionDTO r){
        return Revision.builder()
                .id(r.id)
                .estado(r.estado)
                .trabajador(TrabajadorDTO.toEntity(r.trabajador))
                .anotaciones(r.anotaciones)
                .fechaRealizacion(r.fechaRealizacion)
                .fechaProgramada(r.fechaProgramada)
                .build();
    }
}
