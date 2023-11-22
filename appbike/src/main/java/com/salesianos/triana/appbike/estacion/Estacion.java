package com.salesianos.triana.appbike.estacion;

import com.salesianos.triana.appbike.bicileta.Bicicleta;
import com.salesianos.triana.appbike.uso.Uso;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
public class Estacion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numero;
    private String nombre;
    private String coordenadas;
    private int capacidad;

    @OneToMany(mappedBy = "estacion")
    private Set<Bicicleta> bicicletas;

    @OneToMany(mappedBy = "estacion")
    private List<Uso> usos;
}
