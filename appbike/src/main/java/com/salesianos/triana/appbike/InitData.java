package com.salesianos.triana.appbike;

import com.salesianos.triana.appbike.model.Bicicleta;
import com.salesianos.triana.appbike.model.Estacion;
import com.salesianos.triana.appbike.model.Estados;
import com.salesianos.triana.appbike.model.Trabajador;
import com.salesianos.triana.appbike.repository.BicicletaRepository;
import com.salesianos.triana.appbike.repository.EstacionRepository;
import com.salesianos.triana.appbike.repository.TrabajadorRepository;
import com.salesianos.triana.appbike.repository.UsuarioBiciRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitData {

        private final EstacionRepository estacionRepository;
        private final BicicletaRepository bicicletaRepository;
        private final TrabajadorRepository trabajadorRepository;
        private final PasswordEncoder passwordEncoder;

        @PostConstruct
        public void init() {

                Bicicleta b1 = Bicicleta.builder()
                                .marca("FieldCletas")
                                .modelo("Gen15")
                                .estado(Estados.valueOf(String.valueOf(Estados.NEW)))
                                .nombre("Michael")
                                .build();

                Bicicleta b2 = Bicicleta.builder()
                                .marca("FieldCletas")
                                .modelo("Gen15")
                                .estado(Estados.valueOf(String.valueOf(Estados.GOOD)))
                                .nombre("Eustaquio")
                                .build();

                Bicicleta b3 = Bicicleta.builder()
                                .marca("ChimneyChains")
                                .modelo("CamelBox")
                                .estado(Estados.valueOf(String.valueOf(Estados.WORN_OUT)))
                                .nombre("Rogelio")
                                .build();

                Bicicleta b4 = Bicicleta.builder()
                                .marca("FieldCletas")
                                .modelo("FieldTrooper")
                                .estado(Estados.valueOf(String.valueOf(Estados.ACCEPTABLE)))
                                .nombre("Antonia")
                                .build();

                Bicicleta b5 = Bicicleta.builder()
                                .marca("FieldCletas")
                                .modelo("Gen15")
                                .estado(Estados.valueOf(String.valueOf(Estados.NEEDS_TO_BE_REPLACED)))
                                .nombre("Pacote")
                                .build();

                Bicicleta b6 = Bicicleta.builder()
                                .marca("ChimneyChains")
                                .modelo("SmokeyCruise")
                                .estado(Estados.valueOf(String.valueOf(Estados.NEW)))
                                .nombre("Ñoño")
                                .build();

                Bicicleta b7 = Bicicleta.builder()
                                .marca("FieldCletas")
                                .modelo("Gen15")
                                .estado(Estados.valueOf(String.valueOf(Estados.NEW)))
                                .nombre("Fernando")
                                .build();

                Bicicleta b8 = Bicicleta.builder()
                                .marca("FieldCletas")
                                .modelo("Gen15")
                                .estado(Estados.valueOf(String.valueOf(Estados.NEW)))
                                .nombre("Braulio")
                                .build();

                Bicicleta b9 = Bicicleta.builder()
                                .marca("ChimneyChains")
                                .modelo("SmokeyCruise")
                                .estado(Estados.valueOf(String.valueOf(Estados.NEW)))
                                .nombre("Hofrague")
                                .build();

                Bicicleta b10 = Bicicleta.builder()
                                .marca("FieldCletas")
                                .modelo("Gen15")
                                .estado(Estados.valueOf(String.valueOf(Estados.WORN_OUT)))
                                .nombre("Patricio")
                                .build();

                Bicicleta b11 = Bicicleta.builder()
                                .marca("FieldCletas")
                                .modelo("FieldTrooper")
                                .estado(Estados.valueOf(String.valueOf(Estados.NEEDS_TO_BE_REPLACED)))
                                .nombre("Gaspar")
                                .build();

                Estacion e1 = Estacion.builder()
                                .numero(1L)
                                .nombre("Plaza de Armas")
                                .coordenadas("")
                                .capacidad(10)
                                .bicicletas(Set.of(b1, b2, b3, b4, b5, b6))
                                .build();

                Estacion e2 = Estacion.builder()
                                .numero(2L)
                                .nombre("Plaza de España")
                                .coordenadas("")
                                .capacidad(10)
                                .bicicletas(Set.of(b7, b8, b9, b10, b11))
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

                estacionRepository.saveAll(List.of(e1, e2));
                bicicletaRepository.saveAll(List.of(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11));

                Trabajador t1 = new Trabajador();
                t1.setUsername("admin");
                t1.setPassword(passwordEncoder.encode("admin"));
                t1.setEmail("admin@bikeapp.com");
                t1.setNombre("admin");
                t1.setCreatedAt(LocalDateTime.now());
                t1.setTurno("Tarde");
                t1.setEsJefe(true);

                trabajadorRepository.save(t1);
        }
}
