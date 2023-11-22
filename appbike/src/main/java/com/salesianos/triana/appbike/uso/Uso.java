package com.salesianos.triana.appbike.uso;

import com.salesianos.triana.appbike.bicicleta.Bicicleta;
import com.salesianos.triana.appbike.estacion.Estacion;
import com.salesianos.triana.appbike.usuariobici.UsuarioBici;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Bicicleta bicicleta;

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private Estacion estacion;

    @ManyToOne
    @JoinColumn(name = "usuarioBici_id")
    private UsuarioBici usuarioBici;

}
