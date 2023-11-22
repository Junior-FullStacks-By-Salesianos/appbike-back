package com.salesianos.triana.appbike.revision;

import com.salesianos.triana.appbike.enums.EstadoRevision;
import com.salesianos.triana.appbike.estacion.Estacion;
import com.salesianos.triana.appbike.trabajador.Trabajador;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaProgramada;

    private LocalDate fechaRealizacion;

    private String anotaciones;

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacion;

    @ManyToOne
    @JoinColumn(name = "trabajador_id")
    private Trabajador trabajador;

    private EstadoRevision estado;

}
