package com.salesianos.triana.appbike.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coste {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double precioMinuto;

    private LocalDateTime fechaInicio, fechaFin;

}
