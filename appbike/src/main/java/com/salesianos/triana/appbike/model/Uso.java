package com.salesianos.triana.appbike.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//@RequiredArgsConstructor
@Builder
public class Uso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaInicio, fechaFin;
    private double coste;

    @ManyToOne
    @JoinColumn(name = "bicicleta_id")
    private Bicicleta bicicleta; //es usada

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacion; //finaliza

    @CreatedBy
    private String usuario;

}
