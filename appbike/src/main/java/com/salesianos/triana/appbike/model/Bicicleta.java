package com.salesianos.triana.appbike.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @RequiredArgsConstructor
@Builder
public class Bicicleta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
    })
    @Column(columnDefinition = "uuid")
    private UUID id;

    @NaturalId
    private String nombre;

    private String marca, modelo, estado;

    @OneToMany(mappedBy = "bicicleta")
    private List<Uso> usos;

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacion;
}
