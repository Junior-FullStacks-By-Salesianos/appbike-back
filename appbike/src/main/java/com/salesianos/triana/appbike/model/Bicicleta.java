package com.salesianos.triana.appbike.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Set;
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
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID uuid;

    @NaturalId
    private String nombre;

    private String marca, modelo;
    private Estados estado;

    @OneToMany(mappedBy = "bicicleta", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Uso> usos;

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacion;

    public void removeUsosFromBicicleta() {
        if (usos != null) {
            for (Uso uso : usos) {
                uso.setBicicleta(null);
            }
            usos.clear();
        }
    }
}
