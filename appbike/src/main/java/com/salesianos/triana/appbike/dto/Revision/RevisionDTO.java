package com.salesianos.triana.appbike.dto.Revision;

import com.salesianos.triana.appbike.dto.Trabajador.TrabajadorDTO;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.model.EstadoRevision;
import com.salesianos.triana.appbike.model.Revision;
import com.salesianos.triana.appbike.model.Trabajador;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

public record RevisionDTO(Long id, LocalDate fechaProgramada,
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
