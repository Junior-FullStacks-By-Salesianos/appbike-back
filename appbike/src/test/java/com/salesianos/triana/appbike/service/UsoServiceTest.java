package com.salesianos.triana.appbike.service;

import com.salesianos.triana.appbike.model.*;
import com.salesianos.triana.appbike.repository.*;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsoServiceTest {

    @Mock
    UsoRepository usoRepository;

    @Mock
    CosteRepository costeRepository;

    @Mock
    BicicletaRepository bicicletaRepository;

    @Mock
    EstacionRepository estacionRepository;

    @Mock
    UsuarioBiciRepository usuarioBiciRepository;

    @InjectMocks
    UsoService usoService;

    @Test
    void seFinalizaElUso(){

        UUID bicicletaId = UUID.fromString("128329b5-4080-4e0a-8b09-8cf2d3a88c20");
        UsuarioBici user = new UsuarioBici();
        user.setId(UUID.fromString("04d0595e-45d5-4f63-8b53-1d79e9d84a5d"));
        user.setSaldo(10);

        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setUuid(bicicletaId);

        Mockito.when(bicicletaRepository.findById(bicicletaId)).thenReturn(Optional.of(bicicleta));
        Mockito.when(usoRepository.findCurrentUsoByUser(user.getId().toString())).thenReturn(Optional.empty());
        Mockito.when(usoRepository.findCurrentUsoByBicicleta(bicicletaId)).thenReturn(Optional.empty());

        Uso result = usoService.addUso(bicicletaId, user);

        assertNotNull(result);
        assertEquals(bicicleta, result.getBicicleta());
        assertEquals(user.getId().toString(), result.getAuthor());
    }
}