package com.salesianos.triana.appbike;

import com.salesianos.triana.appbike.bicicleta.Bicicleta;
import com.salesianos.triana.appbike.bicicleta.BicicletaRepository;
import com.salesianos.triana.appbike.enums.Estados;
import com.salesianos.triana.appbike.estacion.Estacion;
import com.salesianos.triana.appbike.estacion.EstacionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitData {

    private final EstacionRepository estacionRepository;
    private final BicicletaRepository bicicletaRepository;

    @PostConstruct
    public void init() {

        Bicicleta b1 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Michael")
                .build();

        Bicicleta b2 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.GOOD))
                .nombre("Eustaquio")
                .build();

        Bicicleta b3 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("CamelBox")
                .estado(String.valueOf(Estados.WORN_OUT))
                .nombre("Rogelio")
                .build();

        Bicicleta b4 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("FieldTrooper")
                .estado(String.valueOf(Estados.ACCEPTABLE))
                .nombre("Antonia")
                .build();

        Bicicleta b5 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.NEEDS_TO_BE_REPLACED))
                .nombre("Pacote")
                .build();

        Bicicleta b6 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("SmokeyCruise")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Ñoño")
                .build();

        Bicicleta b7 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Fernando")
                .build();

        Bicicleta b8 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Braulio")
                .build();

        Bicicleta b9 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("SmokeyCruise")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Hofrague")
                .build();

        Bicicleta b10 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.WORN_OUT))
                .nombre("Patricio")
                .build();

        Bicicleta b11 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("FieldTrooper")
                .estado(String.valueOf(Estados.NEEDS_TO_BE_REPLACED))
                .nombre("Gaspar")
                .build();

        Bicicleta b12 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("CamelBox")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Bochán")
                .build();

        Bicicleta b13 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("SmokeyCruise")
                .estado(String.valueOf(Estados.GOOD))
                .nombre("Manubrio")
                .build();

        Bicicleta b14 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("FieldTrooper")
                .estado(String.valueOf(Estados.ACCEPTABLE))
                .nombre("Pinecilio")
                .build();

        Bicicleta b15 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.GOOD))
                .nombre("Bartolo")
                .build();

        Bicicleta b16 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("FieldTrooper")
                .estado(String.valueOf(Estados.GOOD))
                .nombre("Jesús")
                .build();

        Bicicleta b17 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("CamelBol")
                .estado(String.valueOf(Estados.GOOD))
                .nombre("Ballotelli")
                .build();

        Bicicleta b18 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("CamelBox")
                .estado(String.valueOf(Estados.WORN_OUT))
                .nombre("Zidane")
                .build();

        Bicicleta b19 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("FieldTrooper")
                .estado(String.valueOf(Estados.ACCEPTABLE))
                .nombre("Melendi")
                .build();

        Bicicleta b20 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.NEEDS_TO_BE_REPLACED))
                .nombre("Bloste")
                .build();

        Bicicleta b21 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("SmokeyCruise")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Poste")
                .build();

        Bicicleta b22 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Goste")
                .build();

        Bicicleta b23 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Toste")
                .build();

        Bicicleta b24 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("SmokeyCruise")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Donatello")
                .build();

        Bicicleta b25 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.WORN_OUT))
                .nombre("Leonardo")
                .build();

        Bicicleta b26 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("FieldTrooper")
                .estado(String.valueOf(Estados.NEEDS_TO_BE_REPLACED))
                .nombre("Raphael")
                .build();

        Bicicleta b27 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("CamelBox")
                .estado(String.valueOf(Estados.NEW))
                .nombre("Mariano")
                .build();

        Bicicleta b28 = Bicicleta.builder()
                .marca("ChimneyChains")
                .modelo("SmokeyCruise")
                .estado(String.valueOf(Estados.GOOD))
                .nombre("Bellingham")
                .build();

        Bicicleta b29 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("FieldTrooper")
                .estado(String.valueOf(Estados.ACCEPTABLE))
                .nombre("Alejandra")
                .build();

        Bicicleta b30 = Bicicleta.builder()
                .marca("FieldCletas")
                .modelo("Gen15")
                .estado(String.valueOf(Estados.GOOD))
                .nombre("Lopera")
                .build();

        Estacion e1 = Estacion.builder()
                .numero(1L)
                .nombre("Plaza de Armas")
                .coordenadas("")
                .capacidad(10)
                .bicicletas(Set.of(b1, b2, b3, b4, b5, b6))
                .build();

        Estacion e2 = Estacion.builder()
                .numero(1L)
                .nombre("Plaza de España")
                .coordenadas("")
                .capacidad(10)
                .bicicletas(Set.of(b7, b8, b9, b10, b11, b12))
                .build();

        Estacion e3 = Estacion.builder()
                .numero(1L)
                .nombre("Setas de Sevilla")
                .coordenadas("")
                .capacidad(10)
                .bicicletas(Set.of(b13, b14, b15, b16, b17, b18))
                .build();

        Estacion e4 = Estacion.builder()
                .numero(1L)
                .nombre("Isla de la Cartuja")
                .coordenadas("")
                .capacidad(10)
                .bicicletas(Set.of(b19, b20, b21, b22, b23, b24))
                .build();

        Estacion e5 = Estacion.builder()
                .numero(1L)
                .nombre("La Giralda")
                .coordenadas("")
                .capacidad(10)
                .bicicletas(Set.of(b25, b26, b27, b28, b29, b30))
                .build();

        b1.setEstacion(e1);
        b2.setEstacion(e1);
        b3.setEstacion(e1);
        b4.setEstacion(e1);
        b5.setEstacion(e1);
        b6.setEstacion(e1);
        b7.setEstacion(e2);
        b8.setEstacion(e2);
        b9.setEstacion(e2);
        b10.setEstacion(e2);
        b11.setEstacion(e2);
        b12.setEstacion(e2);
        b13.setEstacion(e3);
        b14.setEstacion(e3);
        b15.setEstacion(e3);
        b16.setEstacion(e3);
        b17.setEstacion(e3);
        b18.setEstacion(e3);
        b19.setEstacion(e4);
        b20.setEstacion(e4);
        b21.setEstacion(e4);
        b22.setEstacion(e4);
        b23.setEstacion(e4);
        b24.setEstacion(e4);
        b25.setEstacion(e5);
        b26.setEstacion(e5);
        b27.setEstacion(e5);
        b28.setEstacion(e5);
        b29.setEstacion(e5);
        b30.setEstacion(e5);


    }
}
