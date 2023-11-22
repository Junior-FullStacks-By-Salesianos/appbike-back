package com.salesianos.triana.appbike.uso;

import com.salesianos.triana.appbike.bicicleta.Bicicleta;
import com.salesianos.triana.appbike.estacion.Estacion;
import com.salesianos.triana.appbike.usuariobici.UsuarioBici;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedBy
    private String usuario;

}
