package com.salesianos.triana.appbike.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @RequiredArgsConstructor
@Builder
public class Estacion {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    @NaturalId
    private Long numero;

    private String nombre;
    private String coordenadas;
    private int capacidad;

    @OneToMany(mappedBy = "estacion", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Bicicleta> bicicletas;

    @OneToMany(mappedBy = "estacion", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Uso> usos;

    public void addBicicleta(Bicicleta bike) {
        bicicletas.add(bike);
        bike.setEstacion(this);
    }

    public void removeBicicleta(Bicicleta bike) {
        bicicletas.remove(bike);
        bike.setEstacion(null);
    }
}
