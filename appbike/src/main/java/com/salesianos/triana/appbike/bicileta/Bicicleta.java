package com.salesianos.triana.appbike.bicileta;

import com.salesianos.triana.appbike.estacion.Estacion;
import com.salesianos.triana.appbike.uso.Uso;
import jakarta.persistence.*;
import lombok.*;

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

    private String marca, modelo, estado;

    @OneToMany(mappedBy = "bicicleta")
    private List<Uso> usos;

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacion;
}
