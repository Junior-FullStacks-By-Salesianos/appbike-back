package com.salesianos.triana.appbike.bicicleta;

import com.salesianos.triana.appbike.estacion.Estacion;
import com.salesianos.triana.appbike.uso.Uso;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String nombre;

    private String marca, modelo, estado;

    @OneToMany(mappedBy = "bicicleta")
    private List<Uso> usos;

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacion;
}
