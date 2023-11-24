package com.salesianos.triana.appbike.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
public class Estacion {


    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @NaturalId
    private Long numero;

    private String nombre;
    private String coordenadas;
    private int capacidad;

    @OneToMany(mappedBy = "estacion")
    private Set<Bicicleta> bicicletas;

    @OneToMany(mappedBy = "estacion")
    private List<Uso> usos;

    @PrePersist
    private void asegurarNumeroUnico() {
        if (this.numero == null) {
            this.numero = generarNumeroUnico();
        }
    }

    private static Long numeroUnicoActual = 0L;
    //Por el momento esto está bien pero realizar una consulta
    private synchronized Long generarNumeroUnico() {
        numeroUnicoActual++;
        return numeroUnicoActual;
    }
}
