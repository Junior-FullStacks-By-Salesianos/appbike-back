package com.salesianos.triana.appbike.model;

import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Estacion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
// @RequiredArgsConstructor
@Builder
public class Uso {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID uuid;

    private LocalDateTime fechaInicio, fechaFin;
    private double coste;

    @ManyToOne
    @JoinColumn(name = "bicicleta_id")
    private Bicicleta bicicleta; //es usada

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacion; //finaliza

    @CreatedBy
    private String author;

}
